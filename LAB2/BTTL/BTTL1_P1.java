

import java.util.Scanner;

public class BTTL1_P1 {
    public static class Pointer {
        int x,y;
        public Pointer (int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public double KhoangCach (Pointer other)
        {
            return Math.round(Math.sqrt((Math.pow((other.x - this.x),2)) + (Math.pow((other.y - this.y),2)))*100)/100;
        }
        public void Xuat()
        {
            System.out.println("Toa do cua diem nay la: (" + x + ", " + y + ")");
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int x,y;
        System.out.println("Nhap toa do cho diem thu nhat: ");
        x = sc.nextInt();
        y = sc.nextInt();
        Pointer A = new Pointer(x, y);
        System.out.println("Nhap toa do cho diem thu hai: ");
        x = sc.nextInt();
        y = sc.nextInt();
        Pointer B = new Pointer(x, y);
        A.Xuat();
        B.Xuat();
        System.out.println("Khoang cach cua hai diem la: "+ A.KhoangCach(B));
        sc.close();

        
    }
}