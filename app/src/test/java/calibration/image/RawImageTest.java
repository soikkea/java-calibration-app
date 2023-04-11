package calibration.image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HexFormat;

import org.junit.jupiter.api.Test;

class RawImageTest {

    @Test
    void fromBytes() {
        var bytes = HexFormat.of().parseHex("FFFF");
        var image = RawImage.fromBytes(bytes, 1, 1);
        assertEquals(0xFFFF, image.getPixel(0, 0));
    }

    @Test
    void toBytes() {
        var image = RawImage.empty(1, 1);
        image.setPixel(0, 0, 0xABCD);
        var bytes = image.toBytes();
        var hex = HexFormat.of().formatHex(bytes);
        assertEquals("abcd", hex);
    }
    
}
