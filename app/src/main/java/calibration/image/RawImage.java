package calibration.image;

import java.io.IOException;

public class RawImage {

    public static final int DEFAULT_IMAGE_WIDTH = 896;
    public static final int DEFAULT_IMAGE_HEIGHT = 1088;

    public static RawImage fromBytes(byte[] data, int width, int height) {
        var totalSize = width * height;
        if (data.length != 2 * totalSize) {
            throw new IllegalArgumentException("Size does not match");
        }
        var pixels = new int [totalSize];
        for (int i = 0; i < totalSize; i++) {
            var byteA = data[i * 2] & 0xFF;
            var byteB = data[i * 2 + 1] & 0xFF;
            var pixel = byteA << 8 | byteB;
            pixels[i] = pixel;
        }
        return new RawImage(width, height, pixels);
    }

    public static RawImage readFromFile(String path, int width, int height) throws IOException {
        var byteSize = 2 * width * height;
        var data = FileUtil.readBytes(path, byteSize);
        return fromBytes(data, width, height);
    }

    public static RawImage empty(int width, int height) {
        var pixels = new int[width * height];
        return new RawImage(width, height, pixels);
    }

    private final int width;
    private final int height;
    private final int[] pixels;

    public RawImage(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getPixelIndex(int x, int y) {
        var index = x + y * width;
        if (index > width * height) {
            throw new IllegalArgumentException(String.format("(%d, %d) outside image", x, y));
        }
        return index;
    }

    public int getPixel(int x, int y) {
        var index = getPixelIndex(x, y);
        return pixels[index];
    }

    public void setPixel(int x, int y, int value) {
        var index = getPixelIndex(x, y);
        pixels[index] = value;
    }

    public byte[] toBytes() {
        var bytes = new byte[2 * width * height];
        int i = 0;
        for (var pixel : pixels) {
            bytes[i] = (byte) ((pixel >> 8) & 0xFF);
            i++;
            bytes[i] = (byte) (pixel & 0xFF);
            i++;
        }
        return bytes;
    }

    public void writeToFile(String path) throws IOException {
        var bytes = toBytes();
        FileUtil.writeBytes(path, bytes);
    }

}
