package calibration.filters;

import calibration.image.RawImage;

public class OffsetRemovalFilter implements Filter {

    private final RawImage offsetData;

    public OffsetRemovalFilter(RawImage offsetData) {
        this.offsetData = offsetData;
    }

    @Override
    public String getName() {
        return "Offset Removal";
    }

    @Override
    public RawImage filterImage(RawImage image) {

        for (var y = 0; y < offsetData.getHeight(); y++) {
            for (var x = 0; x < offsetData.getWidth(); x++) {
                if (x > image.getWidth() || y > image.getHeight()) {
                    continue;
                }
                var pixelValue = image.getPixel(x, y) - offsetData.getPixel(x, y);
                image.setPixel(x, y, pixelValue);
            }
        }

        return image;
    }

}
