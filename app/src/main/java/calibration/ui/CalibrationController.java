package calibration.ui;

import java.io.File;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import calibration.Calibrator;
import calibration.Configuration;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class CalibrationController {

    private static Logger logger = LoggerFactory.getLogger(CalibrationController.class);

    private class CalibrationStep {
        private final int number;
        private final String name;
        private BooleanProperty isCompletedProperty = new SimpleBooleanProperty(false);

        public CalibrationStep(int number, String name) {
            this.number = number;
            this.name = name;
        }
    }

    @FXML
    private Button advanceCalibrationButton;

    @FXML
    private Label calibrationStatusLabel;

    @FXML
    private ListView<?> calibrationStepsList;

    @FXML
    private BorderPane root;

    @FXML
    private Button saveImageButton;

    private final Configuration configuration;

    private CanvasPane canvasPane;

    private Calibrator calibrator;

    public CalibrationController(Configuration configuration) {
        this.configuration = configuration;
    }

    public void initialize() {
        canvasPane = new CanvasPane();
        root.setCenter(canvasPane);
        canvasPane.setImage(configuration.getImage());

        calibrator = configuration.buildCalibrator();

        advanceCalibrationButton.setOnAction(e -> nextStep());
        saveImageButton.setOnAction(e -> saveImage());
    }

    private void nextStep() {
        calibrator.processStep();
        Platform.runLater(() -> {
            canvasPane.setImage(calibrator.getResult());
        });
    }

    private void saveImage() {
        var image = calibrator.getResult();

        var file = getSaveFile();

        file.ifPresent(f -> {
            try {
                image.writeToFile(f.toString());
            } catch (Exception e) {
                logger.error("Failed to save file", e);
            }
        });
    }

    private Optional<File> getSaveFile() {
        var window = root.getScene().getWindow();

        var chooser = new FileChooser();
        chooser.setTitle("Save Image");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("RAW files (*.raw)", "*.raw"));

        return Optional.ofNullable(chooser.showSaveDialog(window));
    }

}
