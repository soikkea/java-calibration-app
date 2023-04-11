

Attached are 3 raw data images (16 bit unsigned grayscale, width 896, height 1088, big-endian):
 - 1_xray_frame_896x1088.raw  : Original unmodified image frame received from the x-ray sensor. 
 - 2_offset_data_896x1088.raw : Offset (i.e. "dark current") frame, containing only electronic and environmental noise which should be removed from the original x-ray frame.
 - 3_defect_map_896x1088.raw  : Map file of defective pixels, where a non-zero value indicates a defective pixel.
 * Tip: You can use ImageJ to view the raw images: https://imagej.nih.gov/ij/download.html


The task
--------
 - Calibrate the x-ray frame by removing offset noise and filtering out defective pixels.
    * Offset removal: Subtract offset pixel values from the original x-ray frame
    * Defective pixel correction: Use average of surrounding pixels
 - Save the end-result to disk

Basic requirements
------------------
 - Implement with Java
 - Console and/or GUI application
 - Use Gradle to build
 - Don't use 3rd party libraries (JavaFX, logging and unit test frameworks are OK)
 - Use git to document your work progress
 - Documentation
    - Build instructions
    - Description of the application
    - Usage instructions

Extras + things to consider in your implementation
--------------------------------------------------
 - Code reusability and support for possible additional calibration/filtering steps or methods
 - Tests
 - Documentation: List improvement ideas, known issues, etc. 
 - Indicate progress, steps, etc.
 - GUI: File selections, preview input data, visualize steps and results


Submit your work either in a zip package (with sources and all the .git* files & folders) or as an online git repository.

--
