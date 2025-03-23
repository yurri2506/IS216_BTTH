
package Java_Lab;
import java.util.Scanner;
public class Bai1_BTVN {
    public static int UCLN(int a, int b){
        if (a == 0) return Math.abs(b);
        if (b == 0) return Math.abs(a);
        while (b!=0){
            int tmp=b;
            b=a%b;
            a=tmp;
        }
        return Math.abs(a);
    }
    public static int BCNN(int a, int b){
        return Math.abs(a*b)/UCLN(a,b);
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so nguyen thu nhat: ");
        int n1 = scanner.nextInt();
        System.out.print("Nhap so nguyen thu hai: ");
        int n2 = scanner.nextInt();
        System.out.println("Uoc chung lon nhat la: " + UCLN(n1, n2));
        if(n1==0||n2==0){
            System.out.println("Khong xac dinh duoc boi chung nho nhat.");
        }else{
        System.out.println("Boi chung nho nhat la: " + BCNN(n1, n2));}
        scanner.close();
    }
}

