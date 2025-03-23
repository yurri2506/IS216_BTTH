package Java_Lab;

import java.util.Arrays;
import java.util.Scanner;

public class Bai5_BTVN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] b = new int[m];
        for(int i = 0; i < m; i++) {
            Math.random();
            b[i] = (int)(Math.random() * 100);
        }

        for(int i = 0; i < m; i++) {
            System.out.print(b[i] + " ");
        }

        //Tao mang c tu mang a (copy a sang c) bang ham copyOf()
        int[] c = new int[n];
        c = Arrays.copyOf(a, n);
        System.out.println();

        //Thay the phan tu thu 1 den 3 cua mang c bang 3 phan tu cuoi cua mang b su dung ham arraycopy()
        System.arraycopy(b, m - 3, c, 0, 3);

        //Sap xep mang c theo thu tu tang dan
        Arrays.sort(c);
        for(int i = 0; i < n; i++) {
            System.out.print(c[i] + " ");
        }
    }
}
