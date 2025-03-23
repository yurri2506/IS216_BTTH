package Java_Lab;
import java.util.Scanner;

public class Bai9_BTVN {
    public static boolean isPalindrome(String str)
    {
        //Loại các ký tự đặt biệt và khoảng trắng, thay đổi chữ hoa thành chữ thườngthường 
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String daoNguoc = new StringBuilder(str).reverse().toString();
        return str.equals(daoNguoc);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap cac chuo de kiem tra (Nhap EXIT de thoat chuong trinh): ");
        while (true) {
            String str = sc.nextLine();
            // Kiểm tra nếu là chữ EXIT thì thoát chương trình
            // .equalsIgnoreCase để kiểm tra xem có phải chữ EXIT 
            //không mà không cần phân biệt chữ hoa, thườngthường
            if (str.equalsIgnoreCase("EXIT"))
                break;
            if (isPalindrome(str))
                System.out.println("Chuoi '" + str + "' la chuoi Palindrome");
            else
                System.out.println("Chuoi '" + str +  "' khong phai la chuoi Palindrome");
        }
        sc.close();
    }
}
