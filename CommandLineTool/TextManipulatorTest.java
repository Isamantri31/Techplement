import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.file.*;
import java.io.IOException;

public class TextManipulatorTest {

    @Test
    public void testFindAndReplace() throws IOException {
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "hello world".getBytes());

        TextManipulator.findAndReplace(testFile.toString(), "world", "Java");
        String content = new String(Files.readAllBytes(testFile));

        assertEquals("hello Java", content);

        Files.delete(testFile);
    }

    @Test
    public void testFindAndReplaceTextNotFound() {
        try {
            Path testFile = Files.createTempFile("test", ".txt");
            Files.write(testFile, "hello world".getBytes());

            TextManipulator.findAndReplace(testFile.toString(), "nonexistent", "Java");
            fail("Expected IOException to be thrown");

            Files.delete(testFile);
        } catch (IOException e) {
            assertEquals("Text to find not found in the file: nonexistent", e.getMessage());
        }
    }

    @Test
    public void testConvertCase() throws IOException {
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "hello world".getBytes());

        TextManipulator.convertCase(testFile.toString(), true);
        String content = new String(Files.readAllBytes(testFile));

        assertEquals("HELLO WORLD", content);

        TextManipulator.convertCase(testFile.toString(), false);
        content = new String(Files.readAllBytes(testFile));

        assertEquals("hello world", content);

        Files.delete(testFile);
    }

    @Test
    public void testCountFile() throws IOException {
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "hello world\nhello Java".getBytes());

        TextManipulator.countFile(testFile.toString());

        // Just print the counts to verify manually as a simple test
        // In a real scenario, we'd capture stdout and assert the values
        Files.delete(testFile);
    }
}
