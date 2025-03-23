package Java_Lab;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ban kinh:");
        double bankinh= scanner.nextDouble();
        if(bankinh >0) {
            double chuvi = tinhchuvi(bankinh);
            System.out.println("Chu vi: " + chuvi);
        }else {
            System.out.println("Nhap lai so lon hon 0");
        }
        scanner.close();
    }
    public static double tinhchuvi(double r){
        return 2*Math.PI*r;
    }

}