
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class BookManagementUI extends JFrame {
    private JTextField txtMa, txtTen, txtTG, txtNXB, txtGia;
    private DefaultTableModel tblModel;
    private JTable table;
    private DSSach ds;

    public BookManagementUI() {
        ds = new DSSach();
        initComponents();
        loadTable();
    }

    private void initComponents() {
        setTitle("Chương trình quản lý sách");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Tiêu đề
        JLabel lblTitle = new JLabel("THÔNG TIN SÁCH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa form nhập liệu
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Mã sách
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Mã sách:"), gbc);
        gbc.gridx = 1;
        txtMa = new JTextField(20);
        formPanel.add(txtMa, gbc);

        // Tác giả
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("Tác giả:"), gbc);
        gbc.gridx = 3;
        txtTG = new JTextField(20);
        formPanel.add(txtTG, gbc);

        // Tên sách
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Tên sách:"), gbc);
        gbc.gridx = 1;
        txtTen = new JTextField(20);
        formPanel.add(txtTen, gbc);

        // Nhà xuất bản
        gbc.gridx = 2; gbc.gridy = 1;
        formPanel.add(new JLabel("Nhà xuất bản:"), gbc);
        gbc.gridx = 3;
        txtNXB = new JTextField(20);
        formPanel.add(txtNXB, gbc);

        // Giá
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Giá:"), gbc);
        gbc.gridx = 1;
        txtGia = new JTextField(20);
        formPanel.add(txtGia, gbc);

        // Panel chứa bảng
        JPanel tablePanel = new JPanel(new BorderLayout());
       // tablePanel.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
        tblModel = new DefaultTableModel(new String[]{"Mã sách", "Tên sách", "Tác giả", "NXB", "Giá"}, 0);
        table = new JTable(tblModel);
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650, 200));
        tablePanel.add(scroll, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        String[] names = {"Thêm", "Xóa", "Sửa", "Lưu", "Tìm", "Clear", "Thoát"};
        for (String name : names) {
            JButton btn = new JButton(name);
            btn.setPreferredSize(new Dimension(80, 30));
            btn.addActionListener(this::onButtonClick);
            buttonPanel.add(btn);
        }

        // Sắp xếp các thành phần chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Sự kiện click trên bảng
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) fillFormFromTable(r);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void onButtonClick(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();
        switch (cmd) {
            case "Thêm": addBook(); break;
            case "Xóa": deleteBook(); break;
            case "Sửa": editBook(); break;
            case "Lưu": saveData(); break;
            case "Tìm": findBook(); break;
            case "Clear": clearForm(); break;
            case "Thoát": System.exit(0);

        }
    }

    private void loadTable() {
        tblModel.setRowCount(0);
        for (Sach s : ds.getAll()) {
            tblModel.addRow(new Object[]{
                    s.getMaSach(), s.getTenSach(), s.getTacGia(), s.getNhaXuatBan(), s.getGia()
            });
        }
    }

    private void fillFormFromTable(int r) {
        txtMa.setText(table.getValueAt(r, 0).toString());
        txtTen.setText(table.getValueAt(r, 1).toString());
        txtTG.setText(table.getValueAt(r, 2).toString());
        txtNXB.setText(table.getValueAt(r, 3).toString());
        txtGia.setText(table.getValueAt(r, 4).toString());
        txtMa.setEnabled(false);
    }

    private void clearForm() {
        txtMa.setText(""); txtTen.setText(""); txtTG.setText(""); txtNXB.setText(""); txtGia.setText("");
        txtMa.setEnabled(true);
    }

    private void addBook() {
        try {
            validateInput(true);
            Sach s = new Sach(txtMa.getText(), txtTen.getText(), txtTG.getText(),
                    txtNXB.getText(), Double.parseDouble(txtGia.getText()));
            if (!ds.themSach(s))
                throw new IllegalArgumentException("Mã sách đã tồn tại");
            tblModel.addRow(new Object[]{s.getMaSach(), s.getTenSach(), s.getTacGia(), s.getNhaXuatBan(), s.getGia()});
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        String ma = txtMa.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập hoặc chọn mã sách để xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (ds.xoaSach(ma)) {
                loadTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sách cần xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editBook() {
        try {
            validateInput(false);
            String ma = txtMa.getText();
            Sach moi = new Sach(ma, txtTen.getText(), txtTG.getText(),
                    txtNXB.getText(), Double.parseDouble(txtGia.getText()));
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (ds.capNhat(ma, moi)) {
                    loadTable();
                    clearForm();
                } else throw new IllegalArgumentException("Không tìm thấy sách cần sửa");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveData() {
        ds.saveToFile();
        JOptionPane.showMessageDialog(this, "Lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void findBook() {
        String ma = txtMa.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã sách để tìm.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Sach s = ds.timSach(ma);
        if (s != null) {
            txtMa.setText(s.getMaSach());
            txtTen.setText(s.getTenSach());
            txtTG.setText(s.getTacGia());
            txtNXB.setText(s.getNhaXuatBan());
            txtGia.setText(String.valueOf(s.getGia()));
            txtMa.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sách.", "Kết quả", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void validateInput(boolean isNew) {
        if (txtMa.getText().trim().isEmpty() || txtTen.getText().trim().isEmpty()
                || txtTG.getText().trim().isEmpty() || txtNXB.getText().trim().isEmpty()
                || txtGia.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đủ thông tin");
        }
        try {
            Double.parseDouble(txtGia.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Giá phải là số hợp lệ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookManagementUI ui = new BookManagementUI();
            ui.setVisible(true);
            int tongSo = ui.getTongSo();
            System.out.println("Tổng số sách: " + tongSo);
        });
    }

    private int getTongSo() {
        return ds.getTongSo();
    }
}
