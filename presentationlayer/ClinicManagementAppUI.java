package presentationlayer;

import businesslayer.BusinessLogicLayer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClinicManagementAppUI {
    private BusinessLogicLayer bll;
    private JFrame addPatientFrame, scheduleFrame, examinationFrame, billingFrame;
    private JTextField patientIdField, patientNameField, addressField, phoneField, requestField, conclusionField;
    private JComboBox<String> doctorComboBox, genderComboBox, patientComboBox;
    private JComboBox<String> doctorComboBoxExam, patientComboBoxExam;
    private JTable serviceTable, selectedServiceTable;
    private JCheckBox paidCheckBox;

    public ClinicManagementAppUI() {
        bll = new BusinessLogicLayer();
        createAddPatientFrame();
        createScheduleFrame();
        createExaminationFrame();
        createBillingFrame();
    }

    private void createAddPatientFrame() {
        addPatientFrame = new JFrame("Thêm thông tin bệnh nhân");
        addPatientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addPatientFrame.setSize(300, 300);
        addPatientFrame.setLayout(new GridLayout(6, 2, 10, 10));

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
            try {
                String id = patientIdField.getText();
                String name = patientNameField.getText();
                Date dob = (Date) dobSpinner.getValue();
                String address = addressField.getText();
                String phone = phoneField.getText();
                boolean gender = genderComboBox.getSelectedItem().equals("Nam");
                bll.addPatient(id, name, dob, address, phone, gender);
                JOptionPane.showMessageDialog(addPatientFrame, "Thêm bệnh nhân thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addPatientFrame, "Thêm bệnh nhân không thành công: " + ex.getMessage());
            }
        });

        addPatientFrame.setLocationRelativeTo(null);
        addPatientFrame.setVisible(true);
    }

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
        try {
            for (String doctor : bll.getDoctors()) {
                doctorComboBox.addItem(doctor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(scheduleFrame, "Lỗi: " + e.getMessage());
        }
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
            try {
                String id = patientIdField.getText();
                String name = bll.getPatientName(id);
                if (name != null) {
                    nameField.setText(name);
                } else {
                    JOptionPane.showMessageDialog(scheduleFrame, "Bệnh nhân chưa đăng ký");
                    nameField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(scheduleFrame, "Lỗi: " + ex.getMessage());
            }
        });

        scheduleButton.addActionListener(e -> {
            try {
                String patientId = patientIdField.getText();
                Date examDate = (Date) dateSpinner.getValue();
                String request = requestField.getText();
                String doctorName = (String) doctorComboBox.getSelectedItem();
                bll.scheduleExamination(patientId, doctorName, examDate, request);
                JOptionPane.showMessageDialog(scheduleFrame, "Đặt lịch khám thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(scheduleFrame, "Đặt lịch khám không thành công: " + ex.getMessage());
            }
        });

        scheduleFrame.setLocationRelativeTo(null);
        scheduleFrame.setVisible(true);
    }

    private void createExaminationFrame() {
        examinationFrame = new JFrame("Khám bệnh");
        examinationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examinationFrame.setSize(600, 400);
        examinationFrame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel doctorLabel = new JLabel("Bác sĩ khám");
        doctorComboBoxExam = new JComboBox<>();
        try {
            for (String doctor : bll.getDoctors()) {
                doctorComboBoxExam.addItem(doctor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + e.getMessage());
        }
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
        try {
            DefaultTableModel model = new DefaultTableModel(new String[]{"MADV", "Tên dịch vụ", "Đơn giá"}, 0);
            for (Object[] service : bll.getServices()) {
                model.addRow(service);
            }
            serviceTable.setModel(model);
            selectedServiceTable.setModel(new DefaultTableModel(new String[]{"MADV", "Tên dịch vụ", "Số lượng"}, 0));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + e.getMessage());
        }
        centerPanel.add(new JScrollPane(serviceTable));
        centerPanel.add(new JScrollPane(selectedServiceTable));

        JButton addButton = new JButton("Thêm");

        examinationFrame.add(topPanel, BorderLayout.NORTH);
        examinationFrame.add(centerPanel, BorderLayout.CENTER);
        examinationFrame.add(addButton, BorderLayout.SOUTH);

        dateSpinner.addChangeListener(e -> {
            try {
                patientComboBoxExam.removeAllItems();
                for (String patient : bll.getPatientsForDoctorAndDate((String) doctorComboBoxExam.getSelectedItem(), (Date) dateSpinner.getValue())) {
                    patientComboBoxExam.addItem(patient);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + ex.getMessage());
            }
        });

        doctorComboBoxExam.addActionListener(e -> {
            try {
                patientComboBoxExam.removeAllItems();
                for (String patient : bll.getPatientsForDoctorAndDate((String) doctorComboBoxExam.getSelectedItem(), (Date) dateSpinner.getValue())) {
                    patientComboBoxExam.addItem(patient);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + ex.getMessage());
            }
        });

        patientComboBoxExam.addActionListener(e -> {
            String patientName = (String) patientComboBoxExam.getSelectedItem();
            if (patientName != null) {
                try {
                    requestFieldExam.setText(bll.getRequestForPatient(patientName));
                } catch (Exception ex) {
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
            try {
                String doctorName = (String) doctorComboBoxExam.getSelectedItem();
                String patientName = (String) patientComboBoxExam.getSelectedItem();
                Date examDate = (Date) dateSpinner.getValue();
                String conclusion = conclusionField.getText();
                DefaultTableModel selectedModel = (DefaultTableModel) selectedServiceTable.getModel();
                List<Object[]> selectedServices = new ArrayList<>();
                for (int i = 0; i < selectedModel.getRowCount(); i++) {
                    selectedServices.add(new Object[]{selectedModel.getValueAt(i, 0), selectedModel.getValueAt(i, 1), selectedModel.getValueAt(i, 2)});
                }
                bll.updateExamination(patientName, doctorName, examDate, conclusion, selectedServices);
                JOptionPane.showMessageDialog(examinationFrame, "Thêm chi tiết khám bệnh thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(examinationFrame, "Lỗi: " + ex.getMessage());
            }
        });

        examinationFrame.setLocationRelativeTo(null);
        examinationFrame.setVisible(true);
    }

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
            try {
                String id = patientIdField.getText();
                String name = bll.getPatientName(id);
                if (name != null) {
                    nameField.setText(name);
                } else {
                    JOptionPane.showMessageDialog(billingFrame, "Bệnh nhân chưa đăng ký");
                    nameField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        dateSpinner.addChangeListener(e -> {
            try {
                String patientId = patientIdField.getText();
                Date examDate = (Date) dateSpinner.getValue();
                Object[] details = bll.getExaminationDetails(patientId, examDate);
                if (details != null) {
                    requestField.setText((String) details[0]);
                    conclusionField.setText((String) details[1]);
                    paidCheckBox.setSelected((Boolean) details[2]);
                    payButton.setEnabled(!paidCheckBox.isSelected());
                }
                List<Object[]> billingDetails = bll.getBillingDetails(patientId, examDate);
                DefaultTableModel model = new DefaultTableModel(new String[]{"Tên dịch vụ", "Số lượng", "Thành tiền"}, 0);
                long total = 0;
                for (Object[] detail : billingDetails) {
                    model.addRow(detail);
                    total += (Long) detail[2];
                }
                billingTable.setModel(model);
                totalField.setText(String.valueOf(total));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        payButton.addActionListener(e -> {
            try {
                String patientId = patientIdField.getText();
                Date examDate = (Date) dateSpinner.getValue();
                bll.markAsPaid(patientId, examDate);
                paidCheckBox.setSelected(true);
                payButton.setEnabled(false);
                JOptionPane.showMessageDialog(billingFrame, "Thanh toán thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(billingFrame, "Lỗi: " + ex.getMessage());
            }
        });

        billingFrame.setLocationRelativeTo(null);
        billingFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClinicManagementAppUI();
    }
}