package Engine.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();

        InputStream resources = FileUtils.class.getResourceAsStream(path);
        if (resources == null)
            throw new IllegalStateException("\nERROR: Unable to get resource path: " + path);

        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(resources))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
