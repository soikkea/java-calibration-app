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

    private RawImage result;

    private int currentStep = -1;

    public Calibrator(RawImage initialImage) {
        result = initialImage;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public RawImage getResult() {
        return result;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /*
     * Applies next filter to result image.
     */
    public void processStep() {
        currentStep++;
        if (isCalibrationCompleted()) {
            return;
        }

        var totalSteps = filters.size();
        var filter = filters.get(currentStep);

        logger.info("Running filter {} ({}/{})", filter.getName(), currentStep + 1, totalSteps);

        result = filter.filterImage(result);
    }

    public boolean isCalibrationCompleted() {
        return currentStep >= filters.size();
    }
}
