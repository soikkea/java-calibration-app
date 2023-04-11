package calibration.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import calibration.image.RawImage;

public class OffsetRemovalFilterTest {
    @Test
    void testFilterImage() {
        var offsetImage = createTestImage();
        var filter = new OffsetRemovalFilter(offsetImage);
        var output = filter.filterImage(createTestImage());
        assertEquals(0, output.getPixel(0, 0));
    }

    private RawImage createTestImage() {
        var pixels = new int[] {2};
        return new RawImage(1, 1, pixels);
    }
}
