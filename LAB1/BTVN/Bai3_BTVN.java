package Java_Lab;

import java.util.Scanner;

public class Bai3_BTVN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu so: ");
        int tu = sc.nextInt();
        System.out.println("Nhap mau so: ");
        int mau = sc.nextInt();
        while (mau == 0)
        {
            System.out.println("Nhap lai mau so: ");
            mau = sc.nextInt();
        }
        PhanSo a = new PhanSo(tu,mau).rutGon();
        System.out.println("Phan so sau khi toi gian la: " + a);
        sc.close(); 
    }
    
}
