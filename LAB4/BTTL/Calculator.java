import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JFrame implements ActionListener {

    // Ô hiển thị biểu thức và kết quả
    private JTextField display;

    // Dùng để lưu biểu thức cần tính
    private StringBuilder expression = new StringBuilder();

    // Các nút máy tính
    private final String[] buttons = {
        "7", "8", "9", "/", 
        "4", "5", "6", "X", 
        "1", "2", "3", "-", 
        "C", "0", "=", "+"
    };

    // Constructor - giao diện chính
    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa

        // Tạo ô hiển thị kết quả
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Tạo panel chứa các nút
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    // Xử lý sự kiện khi người dùng nhấn nút
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "C":
                expression.setLength(0);
                display.setText("0");
                break;

            case "=":
                try {
                    String expr = expression.toString().replaceAll("X", "*");
                    double result = evaluateExpression(expr);
                    display.setText(String.valueOf(result));
                    expression.setLength(0);
                    expression.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    expression.setLength(0);
                }
                break;

            default:
                // Nếu là số đầu tiên và đang hiển thị 0, thì thay thế
                if (display.getText().equals("0") && cmd.matches("[0-9]")) {
                    display.setText(cmd);
                } else {
                    display.setText(display.getText() + cmd);
                }
                expression.append(cmd);
                break;
        }
    }

    // Hàm xử lý biểu thức đơn giản (chỉ hỗ trợ + - * /)
    private double evaluateExpression(String expr) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            } else {
                numbers.add(Double.parseDouble(num.toString()));
                num.setLength(0);
                operators.add(c);
            }
        }
        numbers.add(Double.parseDouble(num.toString()));

        // Xử lý *, /
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == '*' || op == '/') {
                double a = numbers.get(i);
                double b = numbers.get(i + 1);
                double res = (op == '*') ? a * b : a / b;
                numbers.set(i, res);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }

        // Xử lý +, -
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            double b = numbers.get(i + 1);
            result = (op == '+') ? result + b : result - b;
        }

        return result;
    }

    // Main - chạy chương trình
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}
