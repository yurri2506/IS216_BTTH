package IS216_BTTH.LAB2.BTTL;

import java.util.Scanner;
public class BTTL4_P2 {
    /*Một chiếc xe máy chạy 100km tốn 2lit xăng, cứ chở thêm 10kg hàng xe tốn thêm
0.1lit xăng. Một chiếc xe tải chạy 100km tốn 20lit xăng, cứ chở thêm 100kg hàng xe tốn
thêm 1lit xăng. Dùng kế thừa để xây dựng lớp XeMay và XeTai và cài đặt các phương thức
sau:
o Thêm một lượng hàng lên xe.
o Bớt một lượng hàng xuống xe.
o Đổ một lượng xăng vào xe.
o Cho xe chạy một đoạn đường.
o Kiểm tra xem xe đã hết xăng chưa.
o Cho biết lượng xăng còn trong xe */
    public class Xe {
        private double quangduong;
        private double khoiluong;
        private double xang;
        public Xe(double quangduong, double khoiluong, double xang) {
            this.quangduong = quangduong;
            this.khoiluong = khoiluong;
            this.xang = xang;
        }
        public void themHang(double khoiluong) {
            this.khoiluong += khoiluong;
        }
        public void botHang(double khoiluong) {
            this.khoiluong -= khoiluong;
        }
        public void doXang(double xang) {
            this.xang += xang;
        }
        public void choXeChay(double quangduong) {
            this.quangduong += quangduong;
            if (this instanceof XeMay) {
                this.xang -= quangduong / 50 + this.khoiluong / 10 * 0.1;
            } else {
                this.xang -= quangduong / 5 + this.khoiluong / 100 * 1;
            }
        }
        public boolean hetXang() {
            return this.xang <= 0;
        }
        public double xangConLai() {
            return this.xang;
        }
    }
    public class XeMay extends Xe {
        public XeMay(double quangduong, double khoiluong, double xang) {
            super(quangduong, khoiluong, xang);
        }
    }
    public class XeTai extends Xe {
        public XeTai(double quangduong, double khoiluong, double xang) {
            super(quangduong, khoiluong, xang);
        }
    }
    public static void main(String[] args) {
        BTTL4_P2 bttl4_p2 = new BTTL4_P2();
        Scanner scanner = new Scanner(System.in);
        XeMay xeMay = bttl4_p2.new XeMay(0, 0, 0);
        XeTai xeTai = bttl4_p2.new XeTai(0, 0, 0);
        System.out.println("Xe may: ");
        //Them mot luong hang len xe
        System.out.print("Nhap luong hang can them: ");
        double khoiluong = scanner.nextDouble();
        xeMay.themHang(khoiluong);
        //Bot mot luong hang xuong xe
        System.out.print("Nhap luong hang can bot: ");
        khoiluong = scanner.nextDouble();
        xeMay.botHang(khoiluong);
        //Do mot luong xang vao xe
        System.err.println("Nhap luong xang can do: ");
        double xang = scanner.nextDouble(); 
        xeMay.doXang(xang);
        //Cho xe chay mot doan duong
        System.out.print("Nhap quang duong can di: ");
        double quangduong = scanner.nextDouble();
        xeMay.choXeChay(quangduong);
        //Kiem tra xem xe da het xang chua
        if (xeMay.hetXang()) {
            System.out.println("Xe may da het xang!!!");
        } else {
            System.out.println("Xe may chua het xang!!!");
            System.out.println("Xang con lai: " + xeMay.xangConLai());
        }

        System.out.println("Xe tai: ");
        //Them mot luong hang len xe
        System.out.print("Nhap luong hang can them: ");
        khoiluong = scanner.nextDouble();
        xeTai.themHang(khoiluong);
        //Bot mot luong hang xuong xe
        System.out.print("Nhap luong hang can bot: ");
        khoiluong = scanner.nextDouble();
        xeTai.botHang(khoiluong);
        //Do mot luong xang vao xe
        System.err.println("Nhap luong xang can do: ");
        xang = scanner.nextDouble();
        xeTai.doXang(xang);
        //Cho xe chay mot doan duong
        System.out.print("Nhap quang duong can di: ");
        quangduong = scanner.nextDouble();
        xeTai.choXeChay(quangduong);
        //Kiem tra xem xe da het xang chua
        if (xeTai.hetXang()) {
            System.out.println("Xe tai da het xang!!!");
        } else {
            System.out.println("Xe tai chua het xang!!!");
            System.out.println("Xang con lai: " + xeTai.xangConLai());
        }
    }
}