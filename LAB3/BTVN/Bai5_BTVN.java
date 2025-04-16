import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Bai5_BTVN {
    static public class TU {
        private String tu, nghia, loaiTu, ghiChu;
        public TU (String tu, String loaiTu, String nghia, String ghiChu)
        {
            this.tu = tu;
            this.loaiTu = loaiTu;
            this.nghia = nghia;
            this.ghiChu = ghiChu;
        }
        public String getTu (){
            return tu;
        }
        public void xuatTu(){
            System.out.println(tu + "  (" + loaiTu + ")\t:" + nghia + " (" + ghiChu + ")" );
        }


    }
    static public class  TUDIEN{
        TreeMap<String, TU> dstuDien = new TreeMap<>();

        public void addTuDien (String key, TU  value){
            dstuDien.put(key, value);
        }
        public void xuatTuTrongTuDien (String tu)
        {
            TU temp = dstuDien.get(tu);
            if (temp != null)
            {
                System.out.println("Thong tin cua tu tra la:");
                temp.xuatTu();
            }
            else
                System.out.println("Khong tim thay tu: " + tu +" trong tu dien!");
        }

    
        
    }

    public static void main(String[] args) {
        TUDIEN A = new TUDIEN();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong tu trong tu dien");
        int n = sc.nextInt();
        sc.nextLine();
        String tu, nghia, loaiTu, ghiChu;
        for (int i = 0; i< n; i++)
        {
            System.out.println("Nhap tu thu " + (i+1) +": ");
            tu = sc.nextLine();
            System.out.println("Nhap loai tu: ");
            loaiTu = sc.nextLine();
            System.out.println("Nhap nghia: ");
            nghia = sc.nextLine();
            System.out.println("Nhap ghi chu: ");
            ghiChu = sc.nextLine();
            A.addTuDien(tu, new TU(tu,loaiTu,nghia,ghiChu));
        }

        System.out.println("Nhap tu can tra: ");
        String temp = sc.nextLine();
        A.xuatTuTrongTuDien(temp);

        
        
    }
}
