package calibration.ui;

import calibration.image.RawImage;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class CanvasPane extends Pane {

    private final Canvas canvas;

    private final ObjectProperty<RawImage> imageProperty = new SimpleObjectProperty<>();

    public CanvasPane() {
        super();
        canvas = new Canvas();
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        getChildren().add(canvas);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        imageProperty.addListener((ob, ov, nv) -> drawImage());
        ChangeListener<Number> resizeListener = (ob, ov, nv) -> drawImage();
        widthProperty().addListener(resizeListener);
        heightProperty().addListener(resizeListener);
    }

    public void setImage(RawImage image) {
        imageProperty.set(image);
    }

    protected int convert16BitGreyscaleToARGB(int grey) {
        var div = ((double) grey) / ((double) 0xFFFF);
        var channel = (int) Math.round(div * 255);
        var argb = channel * 0x00010101;
        return argb | 0xFF000000;
    }

    private void drawImage() {
        var image = imageProperty.get();
        if (image == null) {
            return;
        }
        Platform.runLater(() -> {
            var pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
            for (var y = 0; y < image.getHeight(); y++) {
                for (var x = 0; x < image.getWidth(); x++) {
                    var greyscale = image.getPixel(x, y);
                    var argb = convert16BitGreyscaleToARGB(greyscale);
                    pixelWriter.setArgb(x, y, argb);
                }
            }
        });
    }
}
