package calibration.filters;

import java.util.ArrayList;
import java.util.List;

import calibration.image.RawImage;

public class DefectivePixelCorrectionFilter implements Filter{

    private final RawImage defectivePixelData;

    public DefectivePixelCorrectionFilter(RawImage defectivePixelData) {
        this.defectivePixelData = defectivePixelData;
    }

    @Override
    public String getName() {
        return "Defective Pixel Correction";
    }

    @Override
    public RawImage filterImage(RawImage image) {
        for (var y = 0; y < defectivePixelData.getHeight(); y++) {
            for (var x = 0; x < defectivePixelData.getWidth(); x++) {
                var shouldCorrect = defectivePixelData.getPixel(x, y) != 0;
                if ((x > image.getWidth() || y > image.getHeight()) || !shouldCorrect) {
                    continue;
                }

                correctPixel(image, x, y);
            }
        }

        return image;
    }

    protected void correctPixel(RawImage image, int x, int y) {
        var surroundingPixels = getSurroundingPixels(image, x, y);
        var average = averagePixels(surroundingPixels);
        image.setPixel(x, y, average);
    }

    protected List<Integer> getSurroundingPixels(RawImage image, int x, int y) {
        var pixels = new ArrayList<Integer>();

        for (var dy = -1; dy <= 1; dy++) {
            for (var dx = -1; dx <= 1; dx++) {
                var nx = x + dx;
                var ny = y + dy;
                if ((dx == 0 && dy == 0) || !(image.isPixelInsideImage(nx, ny))) {
                    continue;
                }
                pixels.add(image.getPixel(nx, ny));
            }
        }

        return pixels;
    }

    protected int averagePixels(List<Integer> pixels) {
        if (pixels.isEmpty()) {
            return 0;
        }
        var total = pixels.stream().reduce(0, (a,b) -> a + b);
        return total / pixels.size();
    }
    
}
