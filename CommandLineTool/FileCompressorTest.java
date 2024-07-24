import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.file.*;
import java.io.IOException;

public class FileCompressorTest {

    @Test
    public void testCompressAndDecompress() throws IOException {
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "hello world".getBytes());

        FileCompressor.compress(testFile.toString());
        String compressedFilePath = testFile.toString() + ".zip";
        assertTrue(Files.exists(Paths.get(compressedFilePath)));

        FileCompressor.decompress(compressedFilePath);
        assertTrue(Files.exists(testFile));

        Files.delete(testFile);
        Files.delete(Paths.get(compressedFilePath));
    }
}
