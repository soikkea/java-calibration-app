package calibration.ui;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import calibration.image.RawImage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class ConfigureController {

    private static Logger logger = LoggerFactory.getLogger(ConfigureController.class);

    @FXML
    private BorderPane root;

    @FXML
    private Button calibrateButton;

    @FXML
    private Label defectStatus;

    @FXML
    private Label imageStatus;

    @FXML
    private Button loadDefectsButton;

    @FXML
    private Button loadImageButton;

    @FXML
    private Button loadOffsetButton;

    @FXML
    private Label offsetStatus;

    @FXML
    private Label calibrateStatus;

    private CanvasPane canvasPane;

    private final ObjectProperty<RawImage> imageProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<RawImage> offsetsProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<RawImage> defectsProperty = new SimpleObjectProperty<>();

    public void initialize() {
        canvasPane = new CanvasPane();
        root.setCenter(canvasPane);
        loadImageButton.setOnAction(e -> loadImage(imageProperty));
        loadOffsetButton.setOnAction(e -> loadImage(offsetsProperty));
        loadDefectsButton.setOnAction(e -> loadImage(defectsProperty));

        Function<Boolean, String> objectStatusToText = o -> Boolean.TRUE.equals(o) ? "❌" : "✔";

        var imageStatusProperty = imageProperty.isNull().map(objectStatusToText);
        imageStatus.textProperty().bind(imageStatusProperty);
        var offsetsStatusProperty = offsetsProperty.isNull().map(objectStatusToText);
        offsetStatus.textProperty().bind(offsetsStatusProperty);
        var defectsStatusProperty = defectsProperty.isNull().map(objectStatusToText);
        defectStatus.textProperty().bind(defectsStatusProperty);

        var canCalibrate = imageProperty.isNotNull().and(offsetsProperty.isNotNull()).and(defectsProperty.isNotNull());

        calibrateButton.disableProperty()
                .bind(canCalibrate.not());
        var calibrateStatusProperty = canCalibrate.not().map(objectStatusToText);
        calibrateStatus.textProperty().bind(calibrateStatusProperty);
    }

    private void loadImage(ObjectProperty<RawImage> property) {
        var window = root.getScene().getWindow();

        var chooser = new FileChooser();
        chooser.setTitle("Load Image");
        var file = chooser.showOpenDialog(window);
        if (file == null) {
            return;
        }
        try {
            var image = RawImage.readFromFile(file.toString(), RawImage.DEFAULT_IMAGE_WIDTH,
                    RawImage.DEFAULT_IMAGE_HEIGHT);
            property.set(image);
            canvasPane.setImage(image);

        } catch (Exception e) {
            logger.error("Failed to load image", e);
        }

    }
}
