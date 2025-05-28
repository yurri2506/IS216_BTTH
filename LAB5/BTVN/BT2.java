import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BT2 {
    private Connection conn;
    private JFrame addPatientFrame, scheduleFrame, examinationFrame, billingFrame;
    private JTextField patientIdField, patientNameField, addressField, phoneField, requestField, conclusionField;
    private JComboBox<String> doctorComboBox, genderComboBox, patientComboBox;
    private JComboBox<String> doctorComboBoxExam, patientComboBoxExam;
    private JTable serviceTable, selectedServiceTable;
    private JCheckBox paidCheckBox;

    public BT2() {
        connectToDatabase();
        createAddPatientFrame();
        createScheduleFrame();
        createExaminationFrame();
        createBillingFrame();
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/QLPK?user=root&password=123456&useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            // Tạo các bảng nếu chưa tồn tại
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BACSI (MABS CHAR(4) PRIMARY KEY, TENBS VARCHAR(20))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BENHNHAN (MABN CHAR(4) PRIMARY KEY, TENBN VARCHAR(20), NGSINH DATE, DCHI VARCHAR(50), DTHOAI VARCHAR(10), GIOITINH BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS KHAMBENH (MAKB CHAR(6) PRIMARY KEY, MABN CHAR(4), MABS CHAR(4), NGAYKHAM DATE, YEUCAUKHAM VARCHAR(50), KETLUAN VARCHAR(100), THANHTOAN BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DICHVU (MADV CHAR(6) PRIMARY KEY, TENDV VARCHAR(20), DONGIA BIGINT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS THUPHI (MAKB CHAR(6), MADV CHAR(6), SOLUONG INT, THANHTIEN BIGINT, PRIMARY KEY (MAKB, MADV))");
            // Thêm dữ liệu mẫu cho bác sĩ và dịch vụ
            stmt.executeUpdate("INSERT IGNORE INTO BACSI VALUES ('BS01', 'Tên bác sĩ')");
            stmt.executeUpdate("INSERT IGNORE INTO DICHVU VALUES ('DV01', 'Dịch vụ a', 300000)");
            stmt.executeUpdate("INSERT IGNORE INTO DICHVU VALUES ('DV02', 'Dịch vụ b', 500000)");
            System.out.println("Kết nối cơ sở dữ liệu thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    // Giao diện 1: Thêm thông tin bệnh nhân
    private void createAddPatientFrame() {
        addPatientFrame = new JFrame("Thêm thông tin bệnh nhân");
        addPatientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addPatientFrame.setSize(300, 400);
        addPatientFrame.setLayout(new GridLayout(6, 1, 1, 10));

        JLabel idLabel = new JLabel("Mã bệnh nhân");
        patientIdField = new JTextField();
        JLabel nameLabel = new JLabel("Tên bệnh nhân");
        patientNameField = new JTextField();
        JLabel dobLabel = new JLabel("Ngày sinh");
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dobEditor = new JSpinner.DateEditor(dobSpinner, "MMM d, yyyy");
        dobSpinner.setEditor(dobEditor);
        JLabel addressLabel = new JLabel("Địa chỉ");
        addressField = new JTextField();
        JLabel phoneLabel = new JLabel("Điện thoại");
        phoneField = new JTextField();
        JLabel genderLabel = new JLabel("Giới tính");
        genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JButton addButton = new JButton("Thêm");

        addPatientFrame.add(idLabel);
        addPatientFrame.add(patientIdField);
        addPatientFrame.add(nameLabel);
        addPatientFrame.add(patientNameField);
        addPatientFrame.add(dobLabel);
        addPatientFrame.add(dobSpinner);
        addPatientFrame.add(addressLabel);
        addPatientFrame.add(addressField);
        addPatientFrame.add(phoneLabel);
        addPatientFrame.add(phoneField);
        addPatientFrame.add(genderLabel);
        addPatientFrame.add(genderComboBox);
        addPatientFrame.add(new JLabel());
        addPatientFrame.add(addButton);

        addButton.addActionListener(e -> {
            String id = patientIdField.getText();
            String name = patientNameField.getText();
            Date dob = (Date) dobSpinner.getValue();
            String address = addressField.getText();
            String phone = phoneField.getText();
            boolean gender = genderComboBox.getSelectedItem().equals("Nam");

            try {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BENHNHAN VALUES (?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setDate(3, new java.sql.Date(dob.getTime()));
                pstmt.setString(4, address);
                pstmt.setString(5, phone);
                pstmt.setBoolean(6, gender);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(addPatientFrame, "Thêm bệnh nhân thành công");
                } else {
                    JOptionPane.showMessageDialog(addPatientFrame, "Thêm bệnh nhân không thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(addPatientFrame, "Lỗi: " + ex.getMessage());
            }
        });

        addPatientFrame.setLocationRelativeTo(null);
        addPatientFrame.setVisible(true);
    }

    // Giao diện 2: Đặt lịch khám
    private void createScheduleFrame() {
        scheduleFrame = new JFrame("Đặt lịch khám");
        scheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scheduleFrame.setSize(300, 250);
        scheduleFrame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Mã bệnh nhân");
        JTextField patientIdField = new JTextField();
        JLabel nameLabel = new JLabel("Tên bệnh nhân");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);
        JLabel dateLabel = new JLabel("Ngày khám");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MMM d, yyyy");
        dateSpinner.setEditor(dateEditor);
        JLabel requestLabel = new JLabel("Yêu cầu khám");
        requestField = new JTextField();
        JLabel doctorLabel = new JLabel("Tên bác sĩ");
        doctorComboBox = new JComboBox<>();
        loadDoctors(doctorComboBox);
        JButton scheduleButton = new JButton("Đặt lịch khám");

        scheduleFrame.add(idLabel);
        scheduleFrame.add(patientIdField);
        scheduleFrame.add(nameLabel);
        scheduleFrame.add(nameField);
        scheduleFrame.add(dateLabel);
        scheduleFrame.add(dateSpinner);
        scheduleFrame.add(requestLabel);
        scheduleFrame.add(requestField);
        scheduleFrame.add(doctorLabel);
        scheduleFrame.add(doctorComboBox);
        scheduleFrame.add(new JLabel());
        scheduleFrame.add(scheduleButton);

        patientIdField.addActionListener(e -> {
            String id = patientIdField.getText();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT TENBN FROM BENHNHAN WHERE MABN = ?");
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    nameField.setText(rs.getString("TENBN"));
                } else {
                    JOptionPane.showMessageDialog(scheduleFrame, "Bệnh nhân chưa đăng ký");
                    nameField.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(scheduleFrame, "Lỗi: " + ex.getMessage());
            }
        });

        scheduleButton.addActionListener(e -> {
            String patientId = patientIdField.getText();
            Date examDate = (Date) dateSpinner.getValue();
            Date today = new Date();
            if (examDate.before(today)) {
                JOptionPane.showMessageDialog(scheduleFrame, "Ngày khám phải lớn hơn hoặc bằng ngày hiện tại");
                return;
            }
            String request = requestField.getText();
            String doctorName = (String) doctorComboBox.getSelectedItem();
            String doctorId = getDoctorId(doctorName);
            String examId = "KB" + System.currentTimeMillis() % 1000000;

            try {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO KHAMBENH (MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) VALUES (?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, examId);
                pstmt.setString(2, patientId);
                pstmt.setString(3, doctorId);
                pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
                pstmt.setString(5, request);
                pstmt.setString(6, "");
                pstmt.setBoolean(7, false);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(scheduleFrame, "Đặt lịch khám thành công");
                } else {
                    JOptionPane.showMessageDialog(scheduleFrame, "Đặt lịch khám không thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(scheduleFrame, "Lỗi: " + ex.getMessage());
            }
        });

        scheduleFrame.setLocationRelativeTo(null);
        scheduleFrame.setVisible(true);
    }

    // Giao diện 3: Thêm chi tiết khám bệnh
    private void createExaminationFrame() {
        examinationFrame = new JFrame("Khám bệnh");
        examinationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examinationFrame.setSize(600, 400);
        examinationFrame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel doctorLabel = new JLabel("Bác sĩ khám");
        doctorComboBoxExam = new JComboBox<>();
        loadDoctors(doctorComboBoxExam);
        JLabel dateLabel = new JLabel("Ngày khám");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MMM d, yyyy");
        dateSpinner.setEditor(dateEditor);
        JLabel patientLabel = new JLabel("Tên bệnh nhân");
        patientComboBoxExam = new JComboBox<>();
        JLabel requestLabel = new JLabel("Yêu cầu khám");
        JTextField requestFieldExam = new JTextField();
        requestFieldExam.setEditable(false);
        JLabel conclusionLabel = new JLabel("Kết luận");
        conclusionField = new JTextField();

        topPanel.add(doctorLabel);
        topPanel.add(doctorComboBoxExam);
        topPanel.add(dateLabel);
        topPanel.add(dateSpinner);
        topPanel.add(patientLabel);
        topPanel.add(patientComboBoxExam);
        topPanel.add(requestLabel);
        topPanel.add(requestFieldExam);
        topPanel.add(conclusionLabel);
        topPanel.add(conclusionField);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        serviceTable = new JTable();
        selectedServiceTable = new JTable();
        loadServices();
        centerPanel.add(new JScrollPane(serviceTable));
        centerPanel.add(new JScrollPane(selectedServiceTable));

        JButton addButton = new JButton("Thêm");

        examinationFrame.add(topPanel, BorderLayout.NORTH);
        examinationFrame.add(centerPanel, BorderLayout.CENTER);
        examinationFrame.add(addButton, BorderLayout.SOUTH);

        dateSpinner.addChangeListener(e -> loadPatientsForDoctorAndDate((String) doctorComboBoxExam.getSelectedItem(), (Date) dateSpinner.getValue()));
        doctorComboBoxExam.addActionListener(e -> loadPatientsForDoctorAndDate((String) doctorComboBoxExam.getSelectedItem(), (Date) dateSpinner.getValue()));
        patientComboBoxExam.addActionListener(e -> {
            String patientName = (String) patientComboBoxExam.getSelectedItem();
            if (patientName != null) {
                try {
                    PreparedStatement pstmt = conn.prepareStatement("SELECT YEUCAUKHAM FROM KHAMBENH WHERE MABN = (SELECT MABN FROM BENHNHAN WHERE TENBN = ?)");
                    pstmt.setString(1, patientName);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        requestFieldExam.setText(rs.getString("YEUCAUKHAM"));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + ex.getMessage());
                }
            }
        });

        serviceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = serviceTable.getSelectedRow();
                if (row >= 0) {
                    String serviceId = (String) serviceTable.getValueAt(row, 0);
                    String serviceName = (String) serviceTable.getValueAt(row, 1);
                    long price = (long) serviceTable.getValueAt(row, 2);
                    String quantity = JOptionPane.showInputDialog("Nhập số lượng:");
                    if (quantity != null && !quantity.isEmpty()) {
                        DefaultTableModel selectedModel = (DefaultTableModel) selectedServiceTable.getModel();
                        selectedModel.addRow(new Object[]{serviceId, serviceName, quantity});
                        DefaultTableModel serviceModel = (DefaultTableModel) serviceTable.getModel();
                        serviceModel.removeRow(row);
                    }
                }
            }
        });

        addButton.addActionListener(e -> {
            String doctorName = (String) doctorComboBoxExam.getSelectedItem();
            String doctorId = getDoctorId(doctorName);
            String patientName = (String) patientComboBoxExam.getSelectedItem();
            String patientId = getPatientId(patientName);
            String conclusion = conclusionField.getText();
            Date examDate = (Date) dateSpinner.getValue();

            try {
                // Cập nhật kết luận
                PreparedStatement pstmt = conn.prepareStatement("UPDATE KHAMBENH SET KETLUAN = ? WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?");
                pstmt.setString(1, conclusion);
                pstmt.setString(2, patientId);
                pstmt.setString(3, doctorId);
                pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
                pstmt.executeUpdate();

                // Lấy MAKB
                pstmt = conn.prepareStatement("SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?");
                pstmt.setString(1, patientId);
                pstmt.setString(3, doctorId);
                pstmt.setDate(3, new java.sql.Date(examDate.getTime()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String examId = rs.getString("MAKB");
                    // Thêm dịch vụ vào THUPHI
                    DefaultTableModel selectedModel = (DefaultTableModel) selectedServiceTable.getModel();
                    for (int i = 0; i < selectedModel.getRowCount(); i++) {
                        String serviceId = (String) selectedModel.getValueAt(i, 0);
                        int quantity = Integer.parseInt((String) selectedModel.getValueAt(i, 2));
                        long price = getServicePrice(serviceId);
                        long totalPrice = quantity * price;
                        pstmt = conn.prepareStatement("INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES (?, ?, ?, ?)");
                        pstmt.setString(1, examId);
                        pstmt.setString(2, serviceId);
                        pstmt.setInt(3, quantity);
                        pstmt.setLong(4, totalPrice);
                        pstmt.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(examinationFrame, "Thêm chi tiết khám bệnh thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + ex.getMessage());
            }
        });

        examinationFrame.setLocationRelativeTo(null);
        examinationFrame.setVisible(true);
    }

    // Giao diện 4: Thanh toán tiền khám bệnh
    private void createBillingFrame() {
        billingFrame = new JFrame("Thanh toán khám bệnh");
        billingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billingFrame.setSize(600, 400);
        billingFrame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel idLabel = new JLabel("Mã bệnh nhân");
        JTextField patientIdField = new JTextField();
        JLabel nameLabel = new JLabel("Tên bệnh nhân");
        JTextField nameField = new JTextField();
        nameField.setEditable(false);
        JLabel dateLabel = new JLabel("Ngày khám");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MMM d, yyyy");
        dateSpinner.setEditor(dateEditor);
        JLabel requestLabel = new JLabel("Yêu cầu khám");
        JTextField requestField = new JTextField();
        requestField.setEditable(false);
        JLabel conclusionLabel = new JLabel("Kết luận");
        JTextField conclusionField = new JTextField();
        conclusionField.setEditable(false);
        JLabel totalLabel = new JLabel("Tổng tiền");
        JTextField totalField = new JTextField();
        totalField.setEditable(false);

        topPanel.add(idLabel);
        topPanel.add(patientIdField);
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(dateLabel);
        topPanel.add(dateSpinner);
        topPanel.add(requestLabel);
        topPanel.add(requestField);
        topPanel.add(conclusionLabel);
        topPanel.add(conclusionField);
        topPanel.add(totalLabel);
        topPanel.add(totalField);

        JTable billingTable = new JTable();
        paidCheckBox = new JCheckBox("Đã thanh toán");
        JButton payButton = new JButton("Thanh toán");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(paidCheckBox);
        bottomPanel.add(payButton);

        billingFrame.add(topPanel, BorderLayout.NORTH);
        billingFrame.add(new JScrollPane(billingTable), BorderLayout.CENTER);
        billingFrame.add(bottomPanel, BorderLayout.SOUTH);

        patientIdField.addActionListener(e -> {
            String id = patientIdField.getText();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT TENBN FROM BENHNHAN WHERE MABN = ?");
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    nameField.setText(rs.getString("TENBN"));
                } else {
                    JOptionPane.showMessageDialog(billingFrame, "Bệnh nhân chưa đăng ký");
                    nameField.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        dateSpinner.addChangeListener(e -> {
            String patientId = patientIdField.getText();
            Date examDate = (Date) dateSpinner.getValue();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT YEUCAUKHAM, KETLUAN, THANHTOAN FROM KHAMBENH WHERE MABN = ? AND NGAYKHAM = ?");
                pstmt.setString(1, patientId);
                pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    requestField.setText(rs.getString("YEUCAUKHAM"));
                    conclusionField.setText(rs.getString("KETLUAN"));
                    paidCheckBox.setSelected(rs.getBoolean("THANHTOAN"));
                    payButton.setEnabled(!paidCheckBox.isSelected());
                }

                pstmt = conn.prepareStatement("SELECT MADV, SOLUONG, THANHTIEN FROM THUPHI WHERE MAKB = (SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND NGAYKHAM = ?)");
                pstmt.setString(1, patientId);
                pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
                rs = pstmt.executeQuery();
                DefaultTableModel model = new DefaultTableModel(new String[]{"Tên dịch vụ", "Số lượng", "Thành tiền"}, 0);
                long total = 0;
                while (rs.next()) {
                    String serviceId = rs.getString("MADV");
                    int quantity = rs.getInt("SOLUONG");
                    long amount = rs.getLong("THANHTIEN");
                    String serviceName = getServiceName(serviceId);
                    model.addRow(new Object[]{serviceName, quantity, amount});
                    total += amount;
                }
                billingTable.setModel(model);
                totalField.setText(String.valueOf(total));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        payButton.addActionListener(e -> {
            String patientId = patientIdField.getText();
            Date examDate = (Date) dateSpinner.getValue();
            try {
                PreparedStatement pstmt = conn.prepareStatement("UPDATE KHAMBENH SET THANHTOAN = TRUE WHERE MABN = ? AND NGAYKHAM = ?");
                pstmt.setString(1, patientId);
                pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
                pstmt.executeUpdate();
                paidCheckBox.setSelected(true);
                payButton.setEnabled(false);
                JOptionPane.showMessageDialog(billingFrame, "Thanh toán thành công");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        billingFrame.setLocationRelativeTo(null);
        billingFrame.setVisible(true);
    }

    private void loadDoctors(JComboBox<String> comboBox) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TENBS FROM BACSI");
            while (rs.next()) {
                comboBox.addItem(rs.getString("TENBS"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }

    private String getDoctorId(String doctorName) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MABS FROM BACSI WHERE TENBS = ?");
            pstmt.setString(1, doctorName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("MABS");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return null;
    }

    private String getPatientId(String patientName) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MABN FROM BENHNHAN WHERE TENBN = ?");
            pstmt.setString(1, patientName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("MABN");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return null;
    }

    private void loadServices() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"MADV", "Tên dịch vụ", "Đơn giá"}, 0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DICHVU");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("MADV"), rs.getString("TENDV"), rs.getLong("DONGIA")});
            }
            serviceTable.setModel(model);
            selectedServiceTable.setModel(new DefaultTableModel(new String[]{"MADV", "Tên dịch vụ", "Số lượng"}, 0));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }

    private void loadPatientsForDoctorAndDate(String doctorName, Date examDate) {
        String doctorId = getDoctorId(doctorName);
        patientComboBoxExam.removeAllItems();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT MABN FROM KHAMBENH WHERE MABS = ? AND NGAYKHAM = ? AND THANHTOAN = FALSE");
            pstmt.setString(1, doctorId);
            pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String patientId = rs.getString("MABN");
                pstmt = conn.prepareStatement("SELECT TENBN FROM BENHNHAN WHERE MABN = ?");
                pstmt.setString(1, patientId);
                ResultSet rs2 = pstmt.executeQuery();
                if (rs2.next()) {
                    patientComboBoxExam.addItem(rs2.getString("TENBN"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }

    private long getServicePrice(String serviceId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT DONGIA FROM DICHVU WHERE MADV = ?");
            pstmt.setString(1, serviceId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("DONGIA");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return 0;
    }

    private String getServiceName(String serviceId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT TENDV FROM DICHVU WHERE MADV = ?");
            pstmt.setString(1, serviceId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TENDV");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        new BT2();
    }
}