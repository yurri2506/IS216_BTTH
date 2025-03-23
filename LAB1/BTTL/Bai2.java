
package Java_Lab;


import java.util.Scanner;
public class Bai2 {
    //Viet ham nhap vao 2 so a va b, xuat ra man hinh a/b lay 3 so le
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();   
        int b = sc.nextInt();
        while (b <= 0) {
            System.out.println("Nhap lai b > 0: ");
            b = sc.nextInt();
        }
        System.out.println("Ket qua: ");
        System.out.printf("%.3f\n", (float) a / b);
    }
}
