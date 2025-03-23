package Java_Lab;


import java.util.Scanner;
public class Bai5 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so dien su dung trong thang:");
        int sodien= scanner.nextInt();
        if(sodien >0) {
            double tiendien = tinhtiendien(sodien);
            System.out.println("So tien dien la: " + tiendien);
        }else {
            System.out.println("Nhap lai so lon hon 0");
        }
        scanner.close();
    }
    public static int tinhtiendien(int d) {
        if (d <= 50) {
            return d * 2000;
        } else if (d <= 100) {
            return 50 * 2000 + (d - 50) * 2500;
        } else {
            return 50 * 2000 + 50 * 2500 + (d - 100) * 3500;
        }
    }
}

