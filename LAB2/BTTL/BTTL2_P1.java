
import java.util.Scanner;

public class BTTL2_P1 {
    public static class HinhChuNhat {
        private int x,y;
        public HinhChuNhat(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public void setDai(int x)
        {
            this.x = x;
        }
        public void setRong(int y)
        {
            this.y = y;
        }
        public int getDai()
        {
            return this.x;
        }
        public int getRong()
        {
            return this.y;
        }
        public double DienTich()
        {
            return x*y;
        }
        public double ChuVi()
        {
            return (x+y)*2;
        }
        @Override
        public String toString()
        {
            return "Hinh chu nhat: \n" +
            "Chieu dai la: " + this.x + ", chieu rong la: " + this.y +
            "\nDien tich: " + DienTich() + "\nChu vi: " + ChuVi();
        }
    
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x,y;
        System.out.println("Nhap chieu dai cua hinh chu nhat: ");
        x = sc.nextInt();
        System.out.println("Nhap chieu rong cua hinh chu nhat: ");
        y = sc.nextInt();
        HinhChuNhat A = new HinhChuNhat(x, y);
        System.out.println(A);
        sc.close();

    }
}
