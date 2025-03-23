package Java_Lab;

public class BT2_VN {
    public static void main(String[] args) {
        //nhập số nguyên dương N, liet kê các ước của N
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = scanner.nextInt();
        while (n <= 0) {
            System.out.println("N phai la so nguyen duong. Moi nhap lai: ");
            n = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }
}
