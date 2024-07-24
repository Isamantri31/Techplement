import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.Base64;
import java.util.Arrays;

public class FileEncryptor {

    private static final String ALGORITHM = "AES";

    public static void encrypt(String filePath, String password) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] inputBytes = Files.readAllBytes(path);
            byte[] outputBytes = cipher.doFinal(inputBytes);

            String encoded = Base64.getEncoder().encodeToString(outputBytes);
            Files.write(path, encoded.getBytes());
            System.out.println("File encrypted successfully.");
        } catch (GeneralSecurityException e) {
            throw new IOException("Encryption error: " + e.getMessage());
        }
    }

    public static void decrypt(String filePath, String password) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        try {
            Key key = generateKey(password);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] inputBytes = Files.readAllBytes(path);
            byte[] decodedBytes = Base64.getDecoder().decode(new String(inputBytes));
            byte[] outputBytes = cipher.doFinal(decodedBytes);

            Files.write(path, outputBytes);
            System.out.println("File decrypted successfully.");
        } catch (GeneralSecurityException e) {
            throw new IOException("Decryption error: " + e.getMessage());
        }
    }

    private static Key generateKey(String password) throws NoSuchAlgorithmException {
        byte[] key = password.getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only the first 128 bit
        return new SecretKeySpec(key, ALGORITHM);
    }
}
