import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class BT1 extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmField;
    private Connection conn;

    public BT1() {
        // Thiết lập frame
        setTitle("Sign in & Sign up form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new BorderLayout(10, 10));

        // Khởi tạo components
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        JLabel confirmLabel = new JLabel("Confirm");
        confirmField = new JPasswordField();
        JButton signInButton = new JButton("Sign in");
        JButton signUpButton = new JButton("Sign up");
        JButton cancelButton = new JButton("Cancel");

        // Tạo layout1 (JPanel) cho các JLabel và field
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(3, 2, 10, 10));
        layout1.add(usernameLabel);
        layout1.add(usernameField);
        layout1.add(passwordLabel);
        layout1.add(passwordField);
        layout1.add(confirmLabel);
        layout1.add(confirmField);

        // Tạo layout2 (JPanel) cho các JButton
        JPanel layout2 = new JPanel();
        layout2.setLayout(new GridLayout(1, 3, 10, 10));
        layout2.add(signInButton);
        layout2.add(signUpButton);
        layout2.add(cancelButton);

        // Thêm hai panel vào JFrame
        add(layout1, BorderLayout.CENTER);
        add(layout2, BorderLayout.SOUTH);

        // Gán sự kiện cho các nút
        signInButton.addActionListener(e -> signIn());
        signUpButton.addActionListener(e -> signUp());
        cancelButton.addActionListener(e -> {
            closeConnection();
            System.exit(0);
        });

        // Kết nối cơ sở dữ liệu
        connectToDatabase();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/QLSP?user=root&password=123456&useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            System.out.println("Kết nối cơ sở dữ liệu thành công");

            // Tạo bảng USER nếu chưa tồn tại
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS USER (USERNAME VARCHAR(10) PRIMARY KEY, PASSWORD VARCHAR(20))");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void signIn() {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến cơ sở dữ liệu");
            return;
        }

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Đăng nhập không thành công. Vui lòng nhập lại");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void signUp() {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Chưa kết nối đến cơ sở dữ liệu");
            return;
        }

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu và Xác nhận phải giống nhau");
            return;
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USER (USERNAME, PASSWORD) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Bạn đã đăng ký tài khoản thành công");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đóng kết nối cơ sở dữ liệu thành công");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi đóng kết nối: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BT1();
    }
}