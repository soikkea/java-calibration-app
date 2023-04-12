package calibration.ui;

import calibration.Configuration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class CalibrationController {

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

    public CalibrationController(Configuration configuration) {
        this.configuration = configuration;
    }

    public void initialize() {
        // TODO
    }

}
