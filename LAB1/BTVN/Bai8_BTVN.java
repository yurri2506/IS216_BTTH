package Java_Lab;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class Bai8_BTVN {
    public static void compareFiles(String file1, String file2) {
        try {
            List<String> lines1 = Files.readAllLines(Paths.get(file1));
            List<String> lines2 = Files.readAllLines(Paths.get(file2));
            
            int maxLen = Math.max(lines1.size(), lines2.size());
            
            for (int i = 0; i < maxLen; i++) {
                String line1 = i < lines1.size() ? lines1.get(i).trim() : "";
                String line2 = i < lines2.size() ? lines2.get(i).trim() : "";
                
                if (!line1.equals(line2)) {
                    if (!line1.isEmpty()) {
                        System.out.println((i + 1) + "//" + line1);
                    }
                    if (!line2.isEmpty()) {
                        System.out.println((i + 1) + " \\ " + line2);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        compareFiles("input8.1.txt", "input8.2.txt");
    }
}
