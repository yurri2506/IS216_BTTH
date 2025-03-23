package Java_Lab;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class Bai6_BTVN {
    public static boolean isPrime(int n) 



    {
        if (n < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so dong cua mang; ");
        int n = sc.nextInt();
        System.out.println("Nhap so cot cua mang; ");
        int m = sc.nextInt();
        int arr[][] = new int[n][m];
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < m; j++) 
            {
                System.out.println("Nhap phan tu [" + i + "][" + j + "]: ");
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();

        //In các phần tử của mảng
        System.out.println("Cac phan tu cau mang la: ");
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < m; j++) 
            {
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println("");
        }

        //Tìm phần tử lớn nhất trong mảng
        int arrMax[] = { arr[0][0], 0, 0 };

        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < m; j++) 
            {
                if (arrMax[0] < arr[i][j]) 
                {
                    arrMax[0] = arr[i][j];
                    arrMax[1] = i;
                    arrMax[2] = j;
                }
            }
        }
        System.out.println("Phan tu lon nhat trong mang la: "+ arrMax[0]+
          " va chi so cua phan tu do trong mang la: [" + arrMax[1]+ "][" + arrMax[2] + "]");

        //In các phần tử là số nguyên tố
        System.out.println("Im cac phan tu trong mang la so nguyen to: ");
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < m; j++) 
            {
                if (isPrime(arr[i][j]))
                    System.out.print(arr[i][j]+ " ");
                else
                    System.out.print( "0 ");
            }
            System.out.println("");
        }

        //Sắp xếp các cột của mảng A theo thứ tự tăng dần 
        for (int j = 0; j < m; j++) 
        {
            int temp[] = new int [n];
            for (int i = 0; i < n; i++)
            {
                temp[i] = arr[i][j]; // Lưu các cột của mảng 2 chiềuchiều vào trong 1 mảng 1 chiềuchiều
            }
            
            Arrays.sort(temp); // Sắp xếp theo thứ tự tăng dần
            
            for (int i = 0; i < n; i++)
            {
                arr[i][j] = temp[i]; // Gắn các phần tử đã sắp xếp vào lại mảng 2 chiềuchiều
            }
        }

        System.out.println("Cac phan tu cau mang sau khi sap xep theo cot la: ");
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < m; j++) 
            {
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println("");
        }

        

    }
}
