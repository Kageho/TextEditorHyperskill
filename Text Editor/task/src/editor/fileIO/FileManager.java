package editor.fileIO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// util class that manages files
public final class FileManager {
    private FileManager() {
    }

    public static void saveFile(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(String path) {
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
