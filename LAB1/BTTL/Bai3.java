package Java_Lab;


import java.util.Scanner;


public class Bai3 
{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu so cua phan so thu nhat: ");
        int tu = sc.nextInt();
        System.out.println("Nhap mau so cua phan so thu nhat: ");
        int mau = sc.nextInt();
        while (mau == 0) {
            System.out.println("Nhap lai mau so: ");
            mau = sc.nextInt();
        }
        PhanSo a = new PhanSo(tu,mau);
        System.out.println("Nhap tu so cua phan so thu hai: ");
        tu = sc.nextInt();
        System.out.println("Nhap mau so cua phan so thu hait: ");
        mau = sc.nextInt();
        while (mau == 0) {
            System.out.println("Nhap lai mau so: ");
            mau = sc.nextInt();
        }
        PhanSo b = new PhanSo(tu,mau);
        System.out.println("Tong cau hai phan so: "+ a.Cong(b));
        System.out.println("Hieu cau hai phan so: "+ a.Tru(b));
        System.out.println("Tich cau hai phan so: "+ a.Nhan(b));
        System.out.println("Thuong cau hai phan so: "+ a.Chia(b));
        sc.close();
    }
}
