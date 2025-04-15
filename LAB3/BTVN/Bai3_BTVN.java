import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Bai3_BTVN {
    static final int MAX_SIZE = 5;
    static public class CongTy {
        private List<String> dsNhanVien = new ArrayList<>();

        public void addNhanVien(String name) {
            dsNhanVien.add(name);
        }

        public String randomNhanVien() {
            if (dsNhanVien.isEmpty())
                return "";
            Random random = new Random();
            return dsNhanVien.get(random.nextInt(dsNhanVien.size()));

        }

        public Set<String> dsNhanVienTenKhongTrung() {
            return new HashSet<>(dsNhanVien);
        }

        public String tenPhoBienNhat() {
            Map<String, Integer> dsTen = new HashMap<>();
            for (String x : dsNhanVien) {
                dsTen.put(x, dsTen.getOrDefault(x, 0) + 1);
            }
            int max = 0;
            String tenPhoBien = null;
            for (Map.Entry<String, Integer> x : dsTen.entrySet()) // Map.Entry để truy cập vào từng cặp khóa-giá trị mà
                                                                  // Map lưu trữ.
            {
                if (x.getValue() > max) {
                    max = x.getValue();
                    tenPhoBien = x.getKey();
                }
            }
            return tenPhoBien;

        }
        public Queue<String> dsNhanVienDangKy ()
        {
            Queue<String> DangKy = new LinkedList<>();
            while (true)
            {
                if (DangKy.size() > MAX_SIZE)
                    break;
                DangKy.add(randomNhanVien());

            }
            return DangKy;

        }
    }

  
    static public class KhachHang {
        private TreeMap<Double, String> dsKhachHang = new TreeMap<>(Collections.reverseOrder()); // Dùng TreeMap để tự động sắp xếp theo key

        public void addKhachHang(Double doanhSo, String name) {
            dsKhachHang.put(doanhSo, name);
        }

        public void XuatKhachHang ()
        {
            for (Map.Entry < Double, String> x : dsKhachHang.entrySet())
                System.out.println("Khach hang: " + x.getValue() + " co doanh so la: " + x.getKey());
        }
    }

    public static void main(String[] args) {
        CongTy A = new CongTy();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong nhan vien trong cong ty: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap ten nhan vien " + (i + 1) + ": ");
            String name = sc.nextLine();
            A.addNhanVien(name);
        }
        System.out.println("Nhan vien duoc nhan qua la:" + A.randomNhanVien());
        System.out.print("Danh sach ten nhan vien khong trung nhau la: ");
        Set<String> uniqueName = A.dsNhanVienTenKhongTrung();
        for (String x : uniqueName) {
            System.out.print(x + " ");
        }
        System.out.println("");
        System.out.println("Ten pho bien trong cong ty la: " + A.tenPhoBienNhat());
        System.out.println("Danh sach cac nhan vien da dang ky di du lich la: ");
        Queue<String> dsNhanvienDK = A.dsNhanVienDangKy();
        for (String x: dsNhanvienDK)
        {
            System.out.print(x + " ");
        }
        System.out.println("");

        KhachHang B = new KhachHang();
        for (int i = 0; i < n; i++)
        {
            System.out.println("Nhap ten khach hang: ");
            String name = sc.nextLine();
            System.out.println("Nhap doanh so cua khach hang: ");
            Double doanhSo = sc.nextDouble();
            sc.nextLine();
            B.addKhachHang(doanhSo, name);
        }
        System.out.println("Danh sach cac khach hang co doanh so giam dan la: ");
        B.XuatKhachHang();



    }

}
