
package com.mycompany.lab2_2_btvn;

// Lớp hàng gia dụng
class HangGiaDung extends HangHoa {
    private String nhaSanXuat;
    private String ngayNhap;
    private String loai;

    public HangGiaDung(String maHang, String tenHang, int soLuongTon, double donGia, String nhaSanXuat, String ngayNhap, String loai) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.nhaSanXuat = nhaSanXuat;
        this.ngayNhap = ngayNhap;
        this.loai = loai;
    }

    @Override
    public void inThongTin() {
        super.inThongTin();
        System.out.println("Nhà sản xuất: " + nhaSanXuat);
        System.out.println("Ngày nhập: " + ngayNhap);
        System.out.println("Loại: " + loai);
    }

    @Override
    public double tinhThanhTien() {
        double thanhTien = super.tinhThanhTien();
        double VAT = thanhTien * 0.1; // VAT của hàng gia dụng là 10%
        return thanhTien + VAT;
    }

    public boolean danhGiaMucDoBanBuon(int thoiGianLuu) {
        return soLuongTon > 10 && thoiGianLuu > 20;
    }
}