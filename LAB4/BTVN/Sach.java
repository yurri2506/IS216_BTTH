import java.io.Serializable;

public class Sach implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String maSach;
    private String tenSach;
    private String tacGia;
    private String nhaXuatBan;
    private double gia;

    public Sach(String maSach, String tenSach, String tacGia, String nhaXuatBan, double gia) {
        if (maSach == null || maSach.isEmpty())
            throw new IllegalArgumentException("Mã sách không được để trống");
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.gia = gia;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public double getGia() {
        return gia;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return maSach + "," + tenSach + "," + tacGia + "," + nhaXuatBan + "," + gia;
    }
}
