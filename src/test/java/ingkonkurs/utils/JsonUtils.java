package ingkonkurs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {
    public String readJsonFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readString(path);
    }
}
