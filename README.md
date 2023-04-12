# Calibration App

## Description

A program for calibrating x-ray images by removing offset noise and filtering defective pixels.

## Requirements

Requires Java 17 and gradle (developed with version 8.0.2).

## How to build

Run with gradle:

```
gradle run
```

Build with gradle:

```
gradle build
```

## Useage instructions

The program has 2 phases: configuration and calibration. The program starts in configuration phase.

### Configuration

In this phase the user must load three images:

1. Image: image to be calibrated.
2. Offsets: image containing offset noise which is to be subtracted from the uncalibrated image.
3. Defects: image telling the location of defective pixels.

Images are loaded by pressing the matching button and selecting a file. A preview of the image is then shown.

After all images are loaded, calibration phase can be started with the `Calibrate` button.

### Calibration

In this phase the calibration steps are displayed on the left and preview of the image is on the right. Initially the preview shows the unmodifier image. Pressing the `Next` button applies a calibration filter and updates the preview accordingly. The `Next` button becomes disabled after all calibration steps have been completed.

At any point during the calibration, the `Save` button can be used to save the current state of the preview image to a file.

## Improvement ideas

- The UI could use a lot more work, especially there could be better instructions in the program itself.
- It would be nice to move back and forward with the calibration steps, and to allow the user to customize the order of the steps and maybe add extra steps.
- Error handling in UI is a bit lacking.
- There could probably be more unit tests for the UI.
- The program could be modified so that it can be run only from console without UI, by passing the image files as arguments.
- Currently the program does not allow changing the width or the height of the images.
- There is some duplicate code in the Filters, there should probably be a PerPixelFilter base class or something.
- A proper Dependency Injection system would simplify the structure of program considerably
- The handling of different views could probably be simplified so adding new views would be easier.
