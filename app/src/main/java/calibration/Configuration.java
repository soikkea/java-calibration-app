package calibration;

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

}
