package calibration.ui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("configure"), 1000, 1200);

        stage.setTitle("Calibration App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var loader = new FXMLLoader(JavaFxApp.class.getClassLoader().getResource(fxml + ".fxml"));
        getController(fxml).ifPresent(c -> loader.setController(c));
        return loader.load();
    }

    private static Optional<Object> getController(String fxml) {
        return Optional.ofNullable(new ConfigureController());
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
}
