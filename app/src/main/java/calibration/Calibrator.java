package calibration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import calibration.filters.Filter;
import calibration.image.RawImage;

public class Calibrator {

    private final Logger logger = LoggerFactory.getLogger(Calibrator.class);

    private final List<Filter> filters = new ArrayList<>();

    private RawImage result = null;

    public RawImage getResult() {
        return result;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void processImage(RawImage image) {
        var totalSteps = filters.size();
        logger.info("Processing image with {} steps", totalSteps);

        var currentStep = 1;

        for (var filter : filters) {
            logger.info("Starting filter {} ({}/{})", filter.getName(), currentStep, totalSteps);
            image = filter.filterImage(image);
            currentStep++;
        }

        logger.info("Processing completed");

        result = image;
    }
}
