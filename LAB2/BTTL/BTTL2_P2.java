package ThucHanhJava.Lab02.ThucHanhTaiLop;

import java.util.ArrayList;
import java.util.Scanner;

public class BTTL2_P2 {
    private class Xe {
        protected String maSoChuyen;
        protected  String hoTenTaiXe;    
        protected  String soXe;
        protected  double khoiLuongHangHoa;
        protected  double doanhThu;
        public Xe(String maSoChuyen, String hoTenTaiXe, String soXe, double khoiLuongHangHoa, double doanhThu) {
            this.maSoChuyen = maSoChuyen;
            this.hoTenTaiXe = hoTenTaiXe;
            this.soXe = soXe;
            this.khoiLuongHangHoa = khoiLuongHangHoa;
            this.doanhThu = doanhThu;
        }

        //Viết hàm nhập xuất danh sách các chuyến xe không quá 20 chuyến cho cả chuyến nội thành và ngoại thành.
        //@SuppressWarnings("rawtypes")
        public ArrayList<Xe> NhapChuyenXe() {
            Scanner sc = new Scanner(System.in);
            ArrayList<Xe> arr = new ArrayList<>(2);
            for (int i = 0; i < 3; i++) {
                System.out.println("Chon loai xe: 1. Noi Thanh 2. Ngoai Thanh");
                int choose = sc.nextInt();
                sc.nextLine();
                if (choose == 1) {
                    System.out.print("Nhap ma so chuyen: ");    
                    maSoChuyen = sc.nextLine();
                    System.out.print("Nhap ho ten: ");
                    hoTenTaiXe = sc.nextLine();
                    System.out.print("Nhap so xe: ");
                    soXe = sc.nextLine();
                    System.out.print("Nhập khoi luong hang hoa: ");
                    khoiLuongHangHoa = sc.nextDouble();
                    System.out.print("Nhap doanh thu: ");
                    doanhThu = sc.nextDouble();
                    System.out.print("Nhap quang duong di: ");
                    double quangDuongDi = sc.nextDouble();
                    arr.add(new NoiThanh(maSoChuyen, hoTenTaiXe, soXe, khoiLuongHangHoa, doanhThu, quangDuongDi));
                } else {
                    System.out.print("Nhap ma so chuyen: ");    
                    maSoChuyen = sc.nextLine();
                    System.out.print("Nhap ho ten: ");
                    hoTenTaiXe = sc.nextLine();
                    System.out.print("Nhap so xe: ");
                    soXe = sc.nextLine();
                    System.out.print("Nhap khoi luong hang hoa: ");
                    khoiLuongHangHoa = sc.nextDouble();
                    System.out.print("Nhap doanh thu: ");
                    doanhThu = sc.nextDouble();
                    System.out.print("Nhap noi den: ");
                    String noiDen = sc.next();
                    System.out.print("Nhap so ngay van chuyen: ");
                    int soNgayVanChuyen = sc.nextInt();
                    arr.add(new NgoaiThanh(maSoChuyen, hoTenTaiXe, soXe, khoiLuongHangHoa, doanhThu, noiDen, soNgayVanChuyen)); 
                }
            }
            return arr;
        }
        public void XuatChuyenXe(ArrayList<Xe> arr) {
            System.out.println("Danh sach chuyen xe: ");
            for (Xe xe : arr) {
                System.out.println(xe.XuatChuyen());
            }
        }
        public String XuatChuyen() {
            return "Ma so chuyen: " + maSoChuyen + "\tHo ten: " + hoTenTaiXe + "\tSo xe: " + soXe + "\tKhoi luong hang hoa: " + khoiLuongHangHoa + "\tDoanh thu: " + doanhThu;   
        }
        public double getDoanhThu() {
            return doanhThu;
        }

        //Viết hàm tính tổng doanh thu cho cả 2 loại xe.
        public void tongDoanhThu(ArrayList<Xe> arr) {
            double sumNoi = 0;
            double sumNgoai = 0;
            for (Xe s : arr) {
                if (s instanceof NoiThanh) {
                    sumNoi += s.getDoanhThu();
                } else {
                    if (s != null) {
                        sumNgoai += s.getDoanhThu();
                    }
                }
            }
            System.out.println("Tong doanh thu chuyen xe noi thanh: " + sumNoi);
            System.out.println("Tong doanh thu chuyen xe ngoai thanh: " + sumNgoai);
        }

        //Viết hàm tìm chuyến xe có doanh thu cao nhất.
        public Xe FindmaxDoanhThu(ArrayList<Xe> arr) {
            Xe max = null;
            double maxDoanhThu = 0;
            for (Xe s : arr) {
                if (s.getDoanhThu() > maxDoanhThu) {
                    maxDoanhThu = s.getDoanhThu();
                    max = s;
                }
            }
            return max;
        }
    }

    private class NoiThanh extends Xe {
        private final double quangDuongDi;  
        public NoiThanh(String maSoChuyen, String hoTenTaiXe, String soXe, double khoiLuongHangHoa, double dt, double quangDuongDi) {
            super(maSoChuyen, hoTenTaiXe, soXe, khoiLuongHangHoa, dt);
            this.quangDuongDi = quangDuongDi;
        }
        @Override
        public String XuatChuyen() {
            return super.XuatChuyen() + "\tQuang duong di: " + quangDuongDi;
        }
    }
    private class NgoaiThanh extends Xe {
        private final String noiDen;
        private final int soNgayVanChuyen;
        public NgoaiThanh(String maSoChuyen, String hoTenTaiXe, String soXe, double khoiLuongHangHoa, double doanhThu, String noiDen, int soNgayVanChuyen) {
            super(maSoChuyen, hoTenTaiXe, soXe, khoiLuongHangHoa, doanhThu);
            this.noiDen = noiDen;
            this.soNgayVanChuyen = soNgayVanChuyen;
        }
        @Override
        public String XuatChuyen() {
            return super.XuatChuyen() + "\tNoi den: " + noiDen + "\tSo ngay van chuyen: " + soNgayVanChuyen;
        }
    }

    public static void main(String[] args) {
        BTTL2_P2 bai2 = new BTTL2_P2();
        Xe xe = bai2.new Xe(null, null, null, 0, 0);      
        ArrayList<Xe> danhSachXe = xe.NhapChuyenXe();

        xe.XuatChuyenXe(danhSachXe);
        xe.tongDoanhThu( danhSachXe);

        Xe maxXe = xe.FindmaxDoanhThu(danhSachXe);
        if (maxXe != null) {
        System.out.println("Chuyen xe co doanh thu cao nhat: ");
        System.out.println(maxXe.XuatChuyen());
    }
    }
}

//input:
// 1
// 1   Trang  59  25  17900  20
// 2
// 2   Tri  92  21  19400  vinh 16
// 1
// 3   Tuan  93  22  16400  20
//ouput:
// Danh sach chuyen xe:
// Ma so chuyen: 1	Ho ten: Trang	So xe: 59	Khoi luong hang hoa: 25.0	Doanh thu: 17900.0	Quang duong di: 20.0
// Ma so chuyen: 2	Ho ten: Tri	    So xe: 92	Khoi luong hang hoa: 21.0	Doanh thu: 19400.0	Noi den: vinh    So ngay van chuyen: 16
// Ma so chuyen: 3	Ho ten: Tuan	So xe: 93	Khoi luong hang hoa: 22.0	Doanh thu: 16400.0	Quang duong di: 20.0
// Tong doanh thu chuyen xe noi thanh: 34300.0
// Tong doanh thu chuyen xe ngoai thanh: 19400.0
// Chuyen xe co doanh thu cao nhat:
// Ma so chuyen: 2	Ho ten: Tri	    So xe: 92	Khoi luong hang hoa: 21.0	Doanh thu: 19400.0	Noi den: vinh    So ngay van chuyen: 16


