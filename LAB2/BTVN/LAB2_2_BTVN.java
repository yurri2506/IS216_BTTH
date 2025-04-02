package com.mycompany.lab2_2_btvn;


//import com.mycompany.lab2_2_btvn.HangHoa;
import java.util.Scanner;
import java.util.ArrayList;

public class LAB2_2_BTVN {

    // Hàm nhập hàng hóa vào một mảng
    public static void nhapHangHoa(ArrayList<HangHoa> danhSachHangHoa) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập mã hàng (nhập rỗng để kết thúc): ");
            String maHang = scanner.nextLine();
            if (maHang.isEmpty()) {
                break;
            }


// Kiểm tra xem mã hàng đã tồn tại trong danh sách chưa
        boolean daTonTai = false;
        for (HangHoa hangHoa : danhSachHangHoa) {
            if (hangHoa.maHang.equals(maHang)) {
                daTonTai = true;
                break;
            }
        }
        if (daTonTai) {
            System.out.println("Mã hàng đã tồn tại. Vui lòng nhập mã hàng khác.");
            continue;
        }

            System.out.print("Nhập tên hàng: ");
            String tenHang = scanner.nextLine();
            System.out.print("Nhập số lượng tồn: ");
            int soLuongTon = scanner.nextInt();
            System.out.print("Nhập đơn giá: ");
            double donGia = scanner.nextDouble();
            scanner.nextLine(); // Đọc bỏ dòng new line
            
            System.out.println("Chọn loại hàng (1: Điện máy, 2: Thực phẩm, 3: Gia dụng): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng new line
            
            switch (choice) {
                case 1:
                    System.out.print("Nhập thương hiệu: ");
                    String thuongHieu = scanner.nextLine();
                    System.out.print("Nhập loại máy: ");
                    String loaiMay = scanner.nextLine();
                    System.out.print("Nhập thời gian bảo hành (tháng): ");
                    int thoiGianBaoHanh = scanner.nextInt();
                    scanner.nextLine(); // Đọc bỏ dòng new line
                    danhSachHangHoa.add(new HangDienMay(maHang, tenHang, soLuongTon, donGia, thuongHieu, loaiMay, thoiGianBaoHanh));
                    break;
                case 2:
                    System.out.print("Nhập ngày sản xuất (dd/mm/yyyy): ");
                    String ngaySanXuat = scanner.nextLine();
                    System.out.print("Nhập ngày hết hạn (dd/mm/yyyy): ");
                    String ngayHetHan = scanner.nextLine();
                    System.out.print("Nhập nhà cung cấp: ");
                    String nhaCungCap = scanner.nextLine();
                    danhSachHangHoa.add(new HangThucPham(maHang, tenHang, soLuongTon, donGia, ngaySanXuat, ngayHetHan, nhaCungCap));
                    break;
                case 3:
                    System.out.print("Nhập nhà sản xuất: ");
                    String nhaSanXuat = scanner.nextLine();
                    System.out.print("Nhập ngày nhập (dd/mm/yyyy): ");
                    String ngayNhap = scanner.nextLine();
                    System.out.print("Nhập loại: ");
                    String loai = scanner.nextLine();
                    danhSachHangHoa.add(new HangGiaDung(maHang, tenHang, soLuongTon, donGia, nhaSanXuat, ngayNhap, loai));
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
            }
        }
    }

    // Hàm xuất mức độ đánh giá của tất cả các hàng hóa
    public static void xuatMucDoDanhGia(ArrayList<HangHoa> danhSachHangHoa) {
        for (HangHoa hangHoa : danhSachHangHoa) {
            System.out.println("Mã hàng: " + hangHoa.maHang);
            System.out.println("Tên hàng: " + hangHoa.tenHang);
            System.out.println("Số lượng tồn: " + hangHoa.soLuongTon);
            System.out.print("Mức độ đánh giá: ");
            if (hangHoa instanceof HangDienMay) {
                System.out.println(((HangDienMay) hangHoa).danhGiaMucDoBanBuon() ? "Bán được" : "Không đánh giá");
            } else if (hangHoa instanceof HangThucPham) {
                System.out.println(((HangThucPham) hangHoa).danhGiaMucDoBanBuon() ? "Khó bán" : "Không đánh giá");
            } else if (hangHoa instanceof HangGiaDung) {
                System.out.println(((HangGiaDung) hangHoa).danhGiaMucDoBanBuon(30) ? "Bán chậm" : "Không đánh giá");
            }
            System.out.println("------------------------");
        }
    }

    // Hàm tìm thương hiệu điện máy bán được
    public static void timThuongHieuBanDuoc(ArrayList<HangHoa> danhSachHangHoa) {
        for (HangHoa hangHoa : danhSachHangHoa) {
            if (hangHoa instanceof HangDienMay dienMay) {
                if (dienMay.danhGiaMucDoBanBuon()) {
                    System.out.println("Thương hiệu điện máy bán được: " + dienMay.getThuongHieu());
                    break;
                }
            }
        }
    }


    
    public static void main (String[] args) {
        ArrayList<HangHoa> danhSachHangHoa = new ArrayList<>();
        
        // Nhập hàng hóa vào mảng
        nhapHangHoa(danhSachHangHoa);
        
        // Xuất mức độ đánh giá của tất cả các hàng hóa
        xuatMucDoDanhGia(danhSachHangHoa);
        
        // Tìm thương hiệu điện máy bán được
        timThuongHieuBanDuoc(danhSachHangHoa);
    }
}








