import java.util.ArrayList;
import java.util.Scanner;

abstract class KhachHang{
    private String makh;
    private String tenkh;
    private String ngHD;
    private int solD;
    private double dongia;
    public KhachHang(){};
    public KhachHang(String makh, String tenkh, String ngHD, int solD, double dongia){
        this.makh= makh;
        this.tenkh = tenkh;
        this.ngHD = ngHD;
        this.solD = solD;
        this.dongia = dongia;
    }
    public String getMakh(){
        return makh;
    }
    public String getTenkh(){
        return tenkh;
    }
    public String getngHD(){
        return ngHD;
    }
    public int getsolD(){
        return solD;
    }
    public double getDongia(){
        return dongia;
    }
    public abstract double tinhTien();
    public void out(){
        System.out.println("Ma khach hang: " + makh + ", Ten: "+ tenkh+ ", so luong dien: "+ solD + ", thanh tien: "+tinhTien());
    }
}
class SinhHoat extends KhachHang{
    private int dinhmuc;
    public SinhHoat(){};
    public SinhHoat(String makh, String tenkh, String ngHD, int solD, double dongia, int dinhmuc){
        super(makh,tenkh,ngHD, solD, dongia);
        this.dinhmuc = dinhmuc;
    }
    @Override
    public double tinhTien(){
        if(getsolD() <= dinhmuc) return getsolD() *getDongia();
        else return dinhmuc * getDongia() + (getsolD() - dinhmuc)*getDongia()*2;
    }
}
class KinhDoanh extends KhachHang{
    public KinhDoanh(){};
    public KinhDoanh(String makh, String tenkh, String ngHD, int solD, double dongia){
        super(makh,tenkh,ngHD, solD, dongia);
    }
    @Override
    public double tinhTien(){
        if(getsolD()>400) return getsolD()*getDongia()*1.05;
        else return getsolD()*getDongia();
    }
}
class SanXuat extends KhachHang {
    private int loaidien;
    public SanXuat(){};
    public SanXuat(String makh, String tenkh, String ngHD, int solD, double dongia, int loaidien){
        super(makh,tenkh,ngHD, solD, dongia);
        this.loaidien = loaidien;
    }
    @Override
    public double tinhTien(){
        double tien = getsolD() * getDongia();
        if (loaidien ==2 && getsolD() > 200) {
            return tien * 0.98;
        } else if (loaidien == 3 && getsolD() > 150) {
            return tien * 0.97;
        }
        return tien;
    }
}
public class Main {
    static ArrayList<KhachHang> ds = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void in(){
        while(true) {
            System.out.print("Nhap ma khach hang. Thoat khi ma khach hang rong! ");
            String makh = sc.nextLine();
            if (makh.isEmpty()) break;
            System.out.print("Nhap ten khach hang: ");
            String tenkh = sc.nextLine();
            boolean check;
            String ngayhd;
            do {
                check = true;
                System.out.print("Nhap ngay hoa don: ");
                ngayhd = sc.nextLine();
                for (KhachHang kh : ds) {
                    if (kh.getMakh().equals(makh) && kh.getTenkh().equals(tenkh)) {
                        if (!kh.getngHD().equals(ngayhd)) {
                            continue;
                        } else {
                            System.out.println("Ngay hoa don da ton tai voi khach hang nay. Nhap ngay khac di");
                            check = false;
                            break;
                        }
                    }
                }
            } while (!check);
            System.out.print("Nhap so luong dien: ");
            int soLuong = Integer.parseInt(sc.nextLine());
            System.out.print("Nhap don gia: ");
            double donGia = Double.parseDouble(sc.nextLine());
            System.out.print("Nhap loai khach hang (1-SinhHoat, 2-KinhDoanh, 3-SanXuat): ");
            int loai = Integer.parseInt(sc.nextLine());
            switch (loai) {
                case 1:
                    System.out.print("Nhap dinh muc: ");
                    int dinhmuc = Integer.parseInt(sc.nextLine());
                    ds.add(new SinhHoat(makh, tenkh, ngayhd, soLuong, donGia, dinhmuc));
                    break;
                case 2:
                    ds.add(new KinhDoanh(makh, tenkh, ngayhd, soLuong, donGia));
                    break;
                case 3:
                    int loaidien;
                    do {
                        System.out.print("Nhap loai dien (2 - 2 pha, 3 - 3 pha): ");
                        loaidien = Integer.parseInt(sc.nextLine());
                        if (loaidien != 2 && loaidien != 3) {
                            System.out.println("Loai dien khong hop le. Nhap lai!");
                        }
                    } while (loaidien != 2 && loaidien != 3);
                    ds.add(new SanXuat(makh, tenkh, ngayhd, soLuong, donGia, loaidien));
                    break;
                default:
                    System.out.println("Loai khach hang khong hop le. Nhao lai!");
            }
        }
    }
    public static void Hoadon() {
        if (ds.isEmpty()) {
            System.out.println("Danh sach khach hang rong! Khong co hoa don de xuat.");
            return;
        }
        System.out.print("Nhap thang hoa don: ");
        String thang = sc.nextLine();
        System.out.print("Nhap nam hoa don: ");
        String nam = sc.nextLine();
        System.out.println("\nHoa don thang " + thang + "/" + nam + ":");
        boolean tmp = false;
        for (KhachHang kh : ds) {
            if (kh.getngHD().contains(thang + "/" + nam)) {
                kh.out();
                tmp = true;
            }
        }
        if (!tmp) {
            System.out.println("Khong co hoa don nao trong thang va nam nay!");
        }
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Nhap khach hang");
            System.out.println("2. Xuat hoa don");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    in();
                    break;
                case 2:
                    Hoadon();
                    break;
                case 3:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }
}
