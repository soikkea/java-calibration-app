package calibration.filters;

import calibration.image.RawImage;

public interface Filter {

    public String getName();

    public RawImage filterImage(RawImage image);
    
}
