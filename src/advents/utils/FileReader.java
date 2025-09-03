package advents.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    /**
     * We will read a given file path and return the content as a String.
     * @param path The file path
     * @return The file content
     * @throws IOException
     */
    public static String readFileAsString(Path path) throws IOException {
        return Files.readString(path);
    }
}