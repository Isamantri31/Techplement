import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class FileCompressor {

    public static void compress(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath + ".zip");
             ZipOutputStream zos = new ZipOutputStream(fos)) {
             
            ZipEntry zipEntry = new ZipEntry(new File(filePath).getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
        }
        System.out.println("File compressed successfully.");
    }

    public static void decompress(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        String outputFilePath = filePath.replace(".zip", "");
        try (FileInputStream fis = new FileInputStream(filePath);
             ZipInputStream zis = new ZipInputStream(fis)) {
             
            ZipEntry zipEntry = zis.getNextEntry();
            if (zipEntry != null) {
                try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) >= 0) {
                        fos.write(buffer, 0, length);
                    }
                }
            } else {
                throw new IOException("Invalid zip file: " + filePath);
            }
        }
        System.out.println("File decompressed successfully.");
    }
}
