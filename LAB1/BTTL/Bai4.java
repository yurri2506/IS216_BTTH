
package Java_Lab;


import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        String y = sc.nextLine();
        //Cho biết tổng chiểu dài của chuỗi x.
        System.out.println("Tong chieu dai của chuoi x: " + x.length());

        if (x.length() < 3) {
            System.err.println("Khong du chieu dai");
        } else {
            //Cho biết 3 kí tự đầu tiên của chuỗi x. 
            System.out.println("3 ki tu đau tien cua chuoi x: " + x.substring(0, 3));

            //Cho biết 3 kí tự cuối cùng của chuỗi x.
            System.out.println("3 ki tu cuoi cung cua chuoi x: " + x.substring(x.length() - 3));
        }

        if (x.length() < 6) {
            System.out.println("Khong du chieu dai");
        } else {
            //Cho biết kí tự thứ 6 của chuỗi x.
            System.out.println("Ki tu thu 6 cua chuoi x: " + x.charAt(5));
        }

        if (x.length() < 3 || y.length() < 3) {
            System.out.println("Khong du chieu dai");
        } else {
            //Tạo chuỗi mới gồm 3 kí tự đầu tiên của chuỗi x và 3 kí tự cuối của chuỗi y.      
            String newString = x.substring(0, 3) + y.substring(y.length() - 3);
            System.out.println("Chuoi moi: " + newString);
        }   

        //Kiểm tra 2 chuỗi x, y có bằng nhau hay không (phân biệt chữ hoa, thường)
        if (x.equals(y)) {
            System.out.println("2 chuoi x, y bang nhau");
        } else {
            System.out.println("2 chuoi x, y khong bang nhau");
        }

        //Kiểm tra 2 chuỗi x, y có bằng nhau hay không (không phân biệt chữ hoa, h.thường)? 
        if (x.equalsIgnoreCase(y)) {
            System.out.println("2 chuoi x, y bang nhau");
        } else {
            System.out.println("2 chuoi x, y không bang nhau");
        }

        //Cho biết y có xuất hiện trong x hay không? Nếu có, tại vị trí nào?
        if (x.indexOf(y) != -1) {
            System.out.println("y xuat hien trong x tại vi tri: " + x.indexOf(y));
        } else {
            System.out.println("y khong xuat hien trong x");
        }

        //Cho biết tất cả các vị trí xuất hiện của y trong x.
        int index = x.indexOf(y);
        while (index != -1) {
            System.out.println("y xuat hien trong x tai vi tri: " + index);
            index = x.indexOf(y, index + 1);
        }
    }
}
