/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab2_2_btvn;

// Lớp hàng điện máy
class HangDienMay extends HangHoa {
    private String thuongHieu;
    private String loaiMay;
    private int thoiGianBaoHanh; // tính theo tháng

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia, String thuongHieu, String loaiMay, int thoiGianBaoHanh) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.thuongHieu = thuongHieu;
        this.loaiMay = loaiMay;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }
    
    public String getThuongHieu() {
        return thuongHieu;
    }

    @Override
    public void inThongTin() {
        super.inThongTin();
        System.out.println("Thương hiệu: " + thuongHieu);
        System.out.println("Loại máy: " + loaiMay);
        System.out.println("Thời gian bảo hành: " + thoiGianBaoHanh + " tháng");
    }

    @Override
    public double tinhThanhTien() {
        double thanhTien = super.tinhThanhTien();
        double VAT = thanhTien * 0.1; // VAT của hàng điện máy là 10%
        return thanhTien + VAT;
    }

    public boolean danhGiaMucDoBanBuon() {
        return soLuongTon < 3;
    }
}
