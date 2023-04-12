package calibration.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import calibration.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    private static Scene scene;

    public static final String CONFIGURATION_VIEW = "configure";
    public static final String CALIBRATION_VIEW = "calibration";

    private static final Configuration CONFIGURATION = new Configuration();
    private static final Map<String, Object> CONTROLLERS = new HashMap<>();

    static {
        CONTROLLERS.put(CONFIGURATION_VIEW, new ConfigureController(CONFIGURATION));
        CONTROLLERS.put(CALIBRATION_VIEW, new CalibrationController(CONFIGURATION));
    }

    public static void main(String[] args) {
        launch(args);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var loader = new FXMLLoader(JavaFxApp.class.getClassLoader().getResource(fxml + ".fxml"));
        getController(fxml).ifPresent(loader::setController);
        return loader.load();
    }

    private static Optional<Object> getController(String fxml) {
        return Optional.ofNullable(CONTROLLERS.get(fxml));
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML(CONFIGURATION_VIEW), 1000, 1200);

        stage.setTitle("Calibration App");
        stage.setScene(scene);
        stage.show();
    }
}
