package calibration.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calibration.image.RawImage;

public class DefectivePixelCorrectionFilterTest {

    private DefectivePixelCorrectionFilter filter;
    private RawImage defectivePixelData;

    @BeforeEach
    void setup() {
        defectivePixelData = RawImage.empty(3, 3);
        filter = new DefectivePixelCorrectionFilter(defectivePixelData);
    }

    @Test
    void testAveragePixels() {
        var pixels = List.of(3, 2, 3);
        var average = filter.averagePixels(pixels);
        assertEquals(3, average);
    }

    @Test
    void testCorrectPixel() {
        var image = createTestImage();
        filter.correctPixel(image, 1, 1);
        var newValue = image.getPixel(1, 1);
        assertEquals(2, newValue);
    }

    @Test
    void testFilterImage() {
        defectivePixelData.setPixel(1, 1, 1);
        var image = createTestImage();
        filter.filterImage(image);
        var newValue = image.getPixel(1, 1);
        assertEquals(2, newValue);
    }

    @Test
    void testGetSurroundingPixels() {
        var image = createTestImage();
        var pixels = filter.getSurroundingPixels(image, 1, 1);
        Collections.sort(pixels);
        assertEquals(List.of(1, 1, 1, 1, 2, 2, 2, 2), pixels);
    }

    @Test
    void testGetSurroundingPixelsCorner() {
        var image = createTestImage();
        var pixels = filter.getSurroundingPixels(image, 0, 0);
        Collections.sort(pixels);
        assertEquals(List.of(1, 2, 2), pixels);
    }

    private RawImage createTestImage() {
        var pixels = new int[] {1, 2, 1, 
                                2, 1, 2, 
                                1, 2, 1};
        return new RawImage(3, 3, pixels);
    }
}
