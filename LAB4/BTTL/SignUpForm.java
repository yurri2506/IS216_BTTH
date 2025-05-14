import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmField;
    private JButton signUpButton, cancelButton;

    public SignUpForm() {
        // Set up the frame
        setTitle("Sign up for...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(300, 200);
        
        // Initialize components
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        JLabel confirmLabel = new JLabel("Confirm");
        confirmField = new JPasswordField();
        signUpButton = new JButton("Sign up");
        cancelButton = new JButton("Cancel");

        // Add components to frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(confirmLabel);
        add(confirmField);
        add(signUpButton);
        add(cancelButton);

        // Add action listeners
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirm = new String(confirmField.getPassword());

                if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Vui lòng nhập đầy đủ thông tin");
                } else if (password.length() < 8) {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Mật khẩu phải có ít nhất 8 ký tự");
                } else if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Mật khẩu và xác nhận không giống nhau");
                } else {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Đăng ký thành công!");
                    // Here you can add further processing, e.g., save to database
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignUpForm().setVisible(true);
            }
        });
    }
}