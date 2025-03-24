import java.util.Scanner;

public class BTTL3_P1 {
    
    public static class SinhVien{
        private int MSSV;
        private String hoTen;
        private float diemLT, diemTH;
        public SinhVien(){
            this.MSSV = 0;
            this.diemLT = this.diemTH = 0;
            this.hoTen = "";
        }
        public SinhVien(int MSSV, String hoTen,  float diemLT, float diemTH)
        {
            this.MSSV = MSSV;
            this.hoTen = hoTen;
            this.diemLT = diemLT;
            this.diemTH = diemTH;
        }
        public int getMSSV()
        {   
            return MSSV;
        }
        public String getHoTen()
        {   
            return hoTen;
        }
        public float getDiemLT()
        {   
            return diemLT;
        }
        public float getDiemTH()
        {   
            return diemTH;
        }
        public void setMSSV(int MSSV)
        {
            this.MSSV = MSSV;
        }
        public void setHoTen(String hoTen)
        {
            this.hoTen = hoTen;
        }
        public void setDiemLT(float diemLT)
        {
            this.diemLT = diemLT;
        }
        public void setDiemTH(float diemTH)
        {
            this.diemTH = diemTH;
        }
        public double diemTB()
        {
            return (diemLT + diemTH)/2;
        }
        @Override
        public String toString() 
        {
            return " "+MSSV + "\t" + hoTen + "\t" +
                   String.format("%.2f\t%.2f\t%.2f", diemLT, diemTH, diemTB());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MSSV;
        String hoTen;
        float diemLT, diemTH;
        SinhVien dsSinhVien[] = new SinhVien[3];
        for (int i = 0; i< 3; i++){
            System.out.println("Nhap thong tin cua sinh vien thu " + (i+1)+ ": ");
            MSSV = sc.nextInt();
            sc.nextLine();
            hoTen = sc.nextLine();
            diemLT = sc.nextFloat();
            diemTH = sc.nextFloat();
            sc.nextLine();
            dsSinhVien[i] = new SinhVien(MSSV,hoTen,diemLT,diemTH);
        }
        System.out.println("Thong tin cua cac sinh vien la: ");
        System.out.println("|MSSV\t|thoTen\t|DiemLT\t|DiemTH\t|DiemTB|");
        for (int i = 0; i< 3;i++)
        {
            System.out.println(dsSinhVien[i]);
        }
        sc.close();
    }
}
