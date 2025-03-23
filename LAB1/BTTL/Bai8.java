
package Java_Lab;


import java.util.Scanner;

public class Bai8 {
    public static void main(String[] args) {
        //Viet chuong trinh xuat ra man hinh "bang tinh pythagoras"
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] a = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = i * j;
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
