import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.file.*;
import java.io.IOException;

public class FileEncryptorTest {

    @Test
    public void testEncryptAndDecrypt() throws IOException {
        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, "hello world".getBytes());

        FileEncryptor.encrypt(testFile.toString(), "password");
        String encryptedContent = new String(Files.readAllBytes(testFile));
        assertNotEquals("hello world", encryptedContent);

        FileEncryptor.decrypt(testFile.toString(), "password");
        String decryptedContent = new String(Files.readAllBytes(testFile));
        assertEquals("hello world", decryptedContent);

        Files.delete(testFile);
    }
}
