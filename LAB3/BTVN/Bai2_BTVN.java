import java.util.*;

public class Cau_2 {

    private static HashMap<String, String> sdtToDiaChi = new HashMap<>();
    private static HashMap<String, ArrayList<String>> diaChiToSDT = new HashMap<>();

    public static void themDanhBa(String sdt, String diaChi) {
        if (sdtToDiaChi.containsKey(sdt)) {
            System.out.println("So dien thoai nay da ton tai trong danh ba. Khong the them.");
            return;
        }

        sdtToDiaChi.put(sdt, diaChi);

        diaChiToSDT.putIfAbsent(diaChi, new ArrayList<>());
        diaChiToSDT.get(diaChi).add(sdt);
    }

    public static void traTheoSDT(String sdt) {
        if (sdtToDiaChi.containsKey(sdt)) {
            System.out.println("Dia chi dang ky: " + sdtToDiaChi.get(sdt));
        } else {
            System.out.println("Khong tim thay so dien thoai.");
        }
    }

    // Search
    public static void traTheoDiaChi(String keyword) {
        boolean found = false;
        System.out.println("Ket qua tim kiem theo tu khoa: \"" + keyword + "\"");
        for (String diaChi : diaChiToSDT.keySet()) {
            if (diaChi.toLowerCase().contains(keyword.toLowerCase())) {
                ArrayList<String> ds = diaChiToSDT.get(diaChi);
                System.out.println("- Dia chi: " + diaChi);
                for (String sdt : ds) {
                    System.out.println("   + " + sdt);
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay dia chi nao chua tu khoa nay.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        themDanhBa("0901234567", "123 Binh Duong");
        themDanhBa("0912345678", "123 Binh Duong");
        themDanhBa("0987654321", "456 Thu Duc");

        while (true) {
            System.out.println("1. Tra cuu theo so dien thoai");
            System.out.println("2. Tim kiem dia chi (co the gan dung)");
            System.out.println("3. Them moi so dien thoai");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chon = Integer.parseInt(sc.nextLine());

            if (chon == 0) break;

            switch (chon) {
                case 1:
                    System.out.print("Nhap so dien thoai: ");
                    String sdt = sc.nextLine();
                    traTheoSDT(sdt);
                    break;
                case 2:
                    System.out.print("Nhap tu khoa dia chi: ");
                    String dc = sc.nextLine();
                    traTheoDiaChi(dc);
                    break;
                case 3:
                    System.out.print("Nhap so dien thoai moi: ");
                    String newSdt = sc.nextLine();
                    System.out.print("Nhap dia chi: ");
                    String newDiaChi = sc.nextLine();
                    themDanhBa(newSdt, newDiaChi);
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
