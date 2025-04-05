import java.util.Scanner;

class SinhVien {
    private long masv;
    private String tensv;
    private double diem;

    public SinhVien(long masv, String tensv, double diem) {
        this.masv = masv;
        this.tensv = tensv;
        this.diem = diem;
    }

    public long getMasv() {
        return masv;
    }

    public void setMasv(long masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) throws Exception {
        if (diem < 0 || diem > 10) {
            throw new Exception("Diem phai nhap tu 0 den 10.");
        }
        this.diem = diem;
    }

    public String toString() {
        return "Ma sinh vien: " + masv + ", Ten sinh vien: " + tensv + ", Diem: " + diem;
    }
}

public class Cau_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long ma = 0;
        String ten = "";
        double diem = 0;


        while (true) {
            System.out.print("Nhap ma sinh vien: ");
            String masv = sc.nextLine();
            try {
                ma = Long.parseLong(masv);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Loi: Ma sinh vien phai la so.");
            }
        }


        while (true) {
            System.out.print("Nhap ten sinh vien: ");
            ten = sc.nextLine();
            if (ten.matches(".*\\d.*")) {
                System.out.println("Loi: Ten sinh vien khong duoc chua so.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Nhap diem: ");
            String diemInput = sc.nextLine();
            try {
                diem = Double.parseDouble(diemInput);
                if (diem < 0 || diem > 10) {
                    System.out.println("Loi: Diem phai trong khoang 0 - 10.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi: Diem phai la so.");
            }
        }

        try {
            SinhVien sv = new SinhVien(ma, ten, diem);
            sv.setDiem(diem);
            System.out.println("Thong tin sinh vien: " + sv.toString());
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}
