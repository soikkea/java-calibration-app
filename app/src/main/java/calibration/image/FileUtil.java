package calibration.image;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static byte[] readBytes(String path, int size) throws IOException {

        logger.info("Reading from file {}...", path);

        var buffer = new byte[size];

        try (var reader = new DataInputStream(new FileInputStream(new File(path)))) {

            var bytesRead = reader.read(buffer);

            if (bytesRead != size) {
                logger.warn("Read {} bytes instead of expected {}", bytesRead, size);
            }

            return buffer;

        }

    }

    public static void writeBytes(String path, byte[] content) throws IOException {
        var file = new File(path);
        var filePath = file.toPath();
        Files.createDirectories(filePath.getParent());
        if (!file.exists()) {
            Files.createFile(filePath);
        }
        try (var writer = new DataOutputStream(new FileOutputStream(file))) {
            writer.write(content);
            logger.info("Successfully wrote to {}", path);
        }
    }

    private FileUtil() {
    }
}
