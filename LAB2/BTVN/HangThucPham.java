/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab2_2_btvn;

// Lớp hàng thực phẩm
class HangThucPham extends HangHoa {
    private String ngaySanXuat;
    private String ngayHetHan;
    private String nhaCungCap;

    public HangThucPham(String maHang, String tenHang, int soLuongTon, double donGia, String ngaySanXuat, String ngayHetHan, String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.ngaySanXuat = ngaySanXuat;
        this.ngayHetHan = ngayHetHan;
        this.nhaCungCap = nhaCungCap;
    }

    @Override
    public void inThongTin() {
        super.inThongTin();
        System.out.println("Ngày sản xuất: " + ngaySanXuat);
        System.out.println("Ngày hết hạn: " + ngayHetHan);
        System.out.println("Nhà cung cấp: " + nhaCungCap);
    }

    @Override
    public double tinhThanhTien() {
        double thanhTien = super.tinhThanhTien();
        double VAT = thanhTien * 0.05; // VAT của hàng thực phẩm là 5%
        return thanhTien + VAT;
    }

    public boolean danhGiaMucDoBanBuon() {
        return ngayHetHan.isEmpty() && soLuongTon > 2;
    }
}
