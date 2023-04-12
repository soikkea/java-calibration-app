package calibration;

import calibration.filters.DefectivePixelCorrectionFilter;
import calibration.filters.OffsetRemovalFilter;
import calibration.image.RawImage;

public class Configuration {

    private RawImage image;
    private RawImage offsetImage;
    private RawImage defectsImage;

    public RawImage getImage() {
        return image;
    }

    public void setImage(RawImage image) {
        this.image = image;
    }

    public RawImage getOffsetImage() {
        return offsetImage;
    }

    public void setOffsetImage(RawImage offsetImage) {
        this.offsetImage = offsetImage;
    }

    public RawImage getDefectsImage() {
        return defectsImage;
    }

    public void setDefectsImage(RawImage defectsImage) {
        this.defectsImage = defectsImage;
    }

    public boolean areImagesLoaded() {
        return image != null && offsetImage != null && defectsImage != null;
    }

    public Calibrator buildCalibrator() {
        if (!areImagesLoaded()) {
            throw new IllegalStateException("All images are not loaded");
        }

        var calibrator = new Calibrator(image);

        var offsetFilter = new OffsetRemovalFilter(offsetImage);
        calibrator.addFilter(offsetFilter);

        var defectsFilter = new DefectivePixelCorrectionFilter(defectsImage);
        calibrator.addFilter(defectsFilter);

        return calibrator;
    }

}
