import java.util.ArrayList;
import java.util.Scanner;

public class BTTL4_P1 {
    public static class Xe{
        private String tenChuXe;
        private String loaiXe;
        private float triGia;
        private int  dungTichXylanh;
        public Xe (String tenChuXe, String loaiXe, float triGia, int dungTichXylanh){
            this.tenChuXe = tenChuXe;
            this.loaiXe = loaiXe;
            this.triGia= triGia;
            this.dungTichXylanh = dungTichXylanh;
        }
        public double tinhThue (){
            if(dungTichXylanh < 100)
                return 0.01*triGia;
            else if (100 <= dungTichXylanh && dungTichXylanh <175)
                return 0.03*triGia;
                else return 0.05*triGia;
        }
        @Override
        public String toString(){
            return tenChuXe + "\t" + loaiXe + "\t" +  
            String.format("%.2f\t%d\t%.2f", triGia,dungTichXylanh,tinhThue());

        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Xe> dsXe = new ArrayList<>();
        String tenChuXe;
        String loaiXe;
        float triGia;
        int  dungTichXylanh;
        while (true)
        {
            System.out.println("Nhap ten chu xe: ");
            tenChuXe = sc.nextLine();
            if (tenChuXe.isEmpty())
                break;
            System.out.println("Nhap ten loai xe: ");
            loaiXe = sc.nextLine();
            System.out.println("Nhap tri gia xe: ");
            triGia = sc.nextFloat();
            System.out.println("Nhap dung tich xylanh xe: ");
            dungTichXylanh = sc.nextInt();
            sc.nextLine();
            dsXe.add(new Xe(tenChuXe, loaiXe,triGia,dungTichXylanh));
        }
        System.out.println("\nDanh sach xe:");
        System.out.println("Ten chu xe\tLoai xe\tTri gia\tDung tich xylanh\tThue");
        System.out.println("------------------------------------------------------------");
        for (Xe xe : dsXe) {
            System.out.println(xe);
        }
        System.out.println("Nhap ten chu xe can tim: ");
        tenChuXe = sc.nextLine();
        System.out.println("Nhap loai xe can tim: ");
        loaiXe = sc.nextLine();
        boolean timThay = false;
        for (Xe xe : dsXe) {
            if (xe.tenChuXe.equalsIgnoreCase(tenChuXe) &&
                xe.loaiXe.equalsIgnoreCase(loaiXe)) {
                System.out.printf("Thue cua xe do la: %.2f\n", xe.tinhThue());
                timThay = true;
                break; // Dừng khi tìm thấy xe
            }
        }
        if (!timThay) {
            System.out.println("Not Found!");
        }

        sc.close();
    }
    
    
}
