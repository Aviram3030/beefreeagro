import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
    public String getData(String path) {
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }

        scanner.close();
        return sb.toString();
    }
}
