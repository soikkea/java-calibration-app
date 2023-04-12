package calibration.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import calibration.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(JavaFxApp.class);

    private static Scene scene;

    public static final String CONFIGURATION_VIEW = "configure";
    public static final String CALIBRATION_VIEW = "calibration";

    private static final Configuration CONFIGURATION = new Configuration();
    private static final Map<String, Object> CONTROLLERS = new HashMap<>();

    static final ExecutorService executor = Executors.newSingleThreadExecutor();

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

    @Override
    public void stop() throws Exception {
        super.stop();
        logger.info("Shutting down ExecutorService...");
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdown();
        }
    }
}
