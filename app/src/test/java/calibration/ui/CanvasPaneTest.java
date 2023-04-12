package calibration.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanvasPaneTest {
    @Test
    void testConvert16BitGreyscaleToARGB() {
        var pane = new CanvasPane();
        var color = 32767;
        var expected = 0xFF7F7F7F;
        assertEquals(expected, pane.convert16BitGreyscaleToARGB(color));
    }
}
