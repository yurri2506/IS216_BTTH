import java.io.*;
import java.util.ArrayList;

public class DSSach {
    private ArrayList<Sach> danhSach;
    private final File file = new File("dssach.dat");

    public DSSach() {
        danhSach = new ArrayList<>();
        loadFromFile();
    }

    public boolean themSach(Sach s) {
        if (timSach(s.getMaSach()) != null) {
            return false; // trùng mã
        }
        danhSach.add(s);
        return true;
    }

    public Sach laySach(int i) {
        if (i < 0 || i >= danhSach.size()) return null;
        return danhSach.get(i);
    }

    public boolean xoaSach(String ma) {
        Sach s = timSach(ma);
        if (s != null) {
            danhSach.remove(s);
            return true;
        }
        return false;
    }

    public Sach timSach(String ma) {
        for (Sach s : danhSach) {
            if (s.getMaSach().equalsIgnoreCase(ma))
                return s;
        }
        return null;
    }

    public boolean capNhat(String ma, Sach moi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaSach().equalsIgnoreCase(ma)) {
                danhSach.set(i, moi);
                return true;
            }
        }
        return false;
    }

    public int getTongSo() {
        return danhSach.size();
    }

    public ArrayList<Sach> getAll() {
        return danhSach;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(danhSach);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            danhSach = (ArrayList<Sach>) ois.readObject();
        } catch (Exception e) {
            danhSach = new ArrayList<>();
        }
    }
}
