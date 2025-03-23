package Java_Lab;
import java.util.Scanner;

public class Bai4_BTVN {
    public static boolean SHH(int n){
        int tmp =0;
        if(n==0) return false;
        else{
            for (int i=1; i<= n/2; i++){
                if(n%i==0) tmp+=i;
            }
        }
        return n==tmp;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Nhap vao so tu nhien: ");
            n = scanner.nextInt();
        } while (n < 0);
        if (SHH(n)) {
            System.out.println(n + " la so hoan hao.");
        } else System.out.print(n + " khong la so hoan hao.");
    }
}
