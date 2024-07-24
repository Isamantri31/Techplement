import java.io.IOException;

public class CommandLineTool {

    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        String command = args[0];
        String filePath = args[args.length - 1];

        try {
            switch (command) {
                case "findreplace":
                    if (args.length < 5) {
                        printUsage();
                        return;
                    }
                    String searchText = args[2];
                    String replaceText = args[4];
                    TextManipulator.findAndReplace(filePath, searchText, replaceText);
                    break;
                case "toupper":
                    TextManipulator.convertCase(filePath, true);
                    break;
                case "tolower":
                    TextManipulator.convertCase(filePath, false);
                    break;
                case "count":
                    TextManipulator.countFile(filePath);
                    break;
                case "compress":
                    FileCompressor.compress(filePath);
                    break;
                case "decompress":
                    FileCompressor.decompress(filePath);
                    break;
                case "encrypt":
                    if (args.length < 3) {
                        printUsage();
                        return;
                    }
                    String password = args[2];
                    FileEncryptor.encrypt(filePath, password);
                    break;
                case "decrypt":
                    if (args.length < 3) {
                        printUsage();
                        return;
                    }
                    password = args[2];
                    FileEncryptor.decrypt(filePath, password);
                    break;
                default:
                    printUsage();
            }
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java CommandLineTool [command] [options] [file]");
        System.out.println("Commands:");
        System.out.println("  findreplace -find <searchText> -replace <replaceText> <file>");
        System.out.println("  toupper <file>");
        System.out.println("  tolower <file>");
        System.out.println("  count <file>");
        System.out.println("  compress <file>");
        System.out.println("  decompress <file>");
        System.out.println("  encrypt <password> <file>");
        System.out.println("  decrypt <password> <file>");
    }
}
