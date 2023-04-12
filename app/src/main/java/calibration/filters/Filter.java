package calibration.filters;

import calibration.image.RawImage;

/*
 * Interface for filters which modify RawImages
 */
public interface Filter {

    public String getName();

    public RawImage filterImage(RawImage image);

}
