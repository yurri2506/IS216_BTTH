package Java_Lab;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bai7_BTVN {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        File file = new File(inputFile);
        if (!file.exists()) {
            System.out.println("File khong ton tai.");
            return;
        }
        Scanner scanner = new Scanner(file);
        PrintWriter writer = new PrintWriter(outputFile);
        if (!scanner.hasNextInt()) {
            System.out.println("input khong hop le.");
            scanner.close();
            writer.close();
            return;
        }
        int days = scanner.nextInt();
        scanner.nextLine();

        double max_ndo = Double.MIN_VALUE, min_ndo = Double.MAX_VALUE, tongndo = 0;
        int totalReadings = 0;
        writer.println("Ngay\tND_TN\tND_CN\tND_TB");
        for (int i = 1; i <= days; i++) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            String[] tempStrings = line.split("\\s+");
            if (tempStrings.length != 12) {
                System.out.println("Du lieu ngay " + i + " khong hop le.");
                continue;
            }
            double sum = 0, minTemp = Double.MAX_VALUE, maxTemp = Double.MIN_VALUE;
            for (String tempStr : tempStrings) {
                    double temp = Double.parseDouble(tempStr);
                    sum += temp;
                    minTemp = Math.min(minTemp, temp);
                    maxTemp = Math.max(maxTemp, temp);
            }

            double avgTemp = sum / 12;
            writer.printf("%d\t%.1f\t%.1f\t%.2f%n", i, minTemp, maxTemp, avgTemp);

            tongndo += sum;
            totalReadings += 12;
            min_ndo = Math.min(min_ndo, minTemp);
            max_ndo = Math.max(max_ndo, maxTemp);
        }
        if (totalReadings > 0) {
            double ndo_tbinh = tongndo / totalReadings;
            writer.println("Trong tat ca cac ngay:");
            writer.printf("Nhiet do thap nhat: %.1f%n", min_ndo);
            writer.printf("Nhiet do cao nhat: %.1f%n", max_ndo);
            writer.printf("Nhiet do trung binh: %.2f%n", ndo_tbinh);
        } else {
            writer.println("loi.");
        }
        scanner.close();
        writer.close();

       System.out.println("Kiem tra " + outputFile);
    }
}
