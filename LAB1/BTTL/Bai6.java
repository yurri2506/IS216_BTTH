
package Java_Lab;

import java.util.Scanner;
public class Bai6 {
    public static void main(String[] args) {
    

        System.out.println("#####################");
        System.out.println("   Bài 1 (press 1)");
        System.out.println("   Bài 2 (press 2)");
        System.out.println("   Bài 3 (press 3)");
        System.out.println("   Bài 4 (press 4)");
        System.out.println("   bài 5 (press 5)");
        System.out.println("   Thoát (press 0)");
        System.out.println("#####################");
        int chocie = new Scanner(System.in).nextInt();
       
        switch(chocie)
        {
            case 1:
                Bai1.main(args);
                break;
            case 2:
                Bai2.main(args);
                break;
            case 3:
                Bai3.main(args);
                break;
            case 4:
                Bai4.main(args);
                break;
            case 5:
                Bai5.main(args);
                break;
            }
      
    }
}
