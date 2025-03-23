package Java_Lab;


import java.util.Scanner;
public class Bai7 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so nguyen duong can kiem tra: ");
        int n = scanner.nextInt();
        boolean ok = true;
        for (int i = 2; i < n-1; i++){
            if (n % i == 0){
                ok = false;
                break;
            }
        }
        if(n<2){
            ok=false;
        }
        else if(ok) {
            System.out.println(n+ " la so nguyen to");
        } else {
            System.out.println(n + " khong la so nguyen to");
        }
         scanner.close();
    }
}
