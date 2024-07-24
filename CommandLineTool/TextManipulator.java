import java.io.IOException;
import java.nio.file.*;

public class TextManipulator {

    public static void findAndReplace(String filePath, String searchText, String replaceText) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        String content = new String(Files.readAllBytes(path));
        if (!content.contains(searchText)) {
            throw new IOException("Text to find not found in the file: " + searchText);
        }

        content = content.replace(searchText, replaceText);
        Files.write(path, content.getBytes());
        System.out.println("Text replaced successfully.");
    }

    public static void convertCase(String filePath, boolean toUpperCase) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        String content = new String(Files.readAllBytes(path));
        content = toUpperCase ? content.toUpperCase() : content.toLowerCase();
        Files.write(path, content.getBytes());
        System.out.println("Case conversion successful.");
    }

    public static void countFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        String content = new String(Files.readAllBytes(path));
        int wordCount = content.split("\\s+").length;
        int lineCount = content.split("\n").length;
        int charCount = content.length();

        System.out.println("Words: " + wordCount);
        System.out.println("Lines: " + lineCount);
        System.out.println("Characters: " + charCount);
    }
}
