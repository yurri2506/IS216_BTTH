package datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataAccessLayer {
    private Connection conn;

    public DataAccessLayer() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/QLPK?user=root&password=123456&useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BACSI (MABS CHAR(4) PRIMARY KEY, TENBS VARCHAR(20))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BENHNHAN (MABN CHAR(4) PRIMARY KEY, TENBN VARCHAR(20), NGSINH DATE, DCHI VARCHAR(50), DTHOAI VARCHAR(10), GIOITINH BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS KHAMBENH (MAKB CHAR(6) PRIMARY KEY, MABN CHAR(4), MABS CHAR(4), NGAYKHAM DATE, YEUCAUKHAM VARCHAR(50), KETLUAN VARCHAR(100), THANHTOAN BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DICHVU (MADV CHAR(6) PRIMARY KEY, TENDV VARCHAR(20), DONGIA BIGINT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS THUPHI (MAKB CHAR(6), MADV CHAR(6), SOLUONG INT, THANHTIEN BIGINT, PRIMARY KEY (MAKB, MADV))");
            stmt.executeUpdate("INSERT IGNORE INTO BACSI VALUES ('BS01', 'Tên bác sĩ')");
            stmt.executeUpdate("INSERT IGNORE INTO DICHVU VALUES ('DV01', 'Dịch vụ a', 300000)");
            stmt.executeUpdate("INSERT IGNORE INTO DICHVU VALUES ('DV02', 'Dịch vụ b', 500000)");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void addPatient(String id, String name, Date dob, String address, String phone, boolean gender) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BENHNHAN VALUES (?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setDate(3, new java.sql.Date(dob.getTime()));
        pstmt.setString(4, address);
        pstmt.setString(5, phone);
        pstmt.setBoolean(6, gender);
        pstmt.executeUpdate();
    }

    public String getPatientName(String patientId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT TENBN FROM BENHNHAN WHERE MABN = ?");
        pstmt.setString(1, patientId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("TENBN");
        }
        return null;
    }

    public List<String> getDoctors() throws SQLException {
        List<String> doctors = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT TENBS FROM BACSI");
        while (rs.next()) {
            doctors.add(rs.getString("TENBS"));
        }
        return doctors;
    }

    public String getDoctorId(String doctorName) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT MABS FROM BACSI WHERE TENBS = ?");
        pstmt.setString(1, doctorName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("MABS");
        }
        return null;
    }

    public String getPatientId(String patientName) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT MABN FROM BENHNHAN WHERE TENBN = ?");
        pstmt.setString(1, patientName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("MABN");
        }
        return null;
    }

    public void scheduleExamination(String examId, String patientId, String doctorId, Date examDate, String request) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO KHAMBENH (MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, examId);
        pstmt.setString(2, patientId);
        pstmt.setString(3, doctorId);
        pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
        pstmt.setString(5, request);
        pstmt.setString(6, "");
        pstmt.setBoolean(7, false);
        pstmt.executeUpdate();
    }

    public List<Object[]> getServices() throws SQLException {
        List<Object[]> services = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DICHVU");
        while (rs.next()) {
            services.add(new Object[]{rs.getString("MADV"), rs.getString("TENDV"), rs.getLong("DONGIA")});
        }
        return services;
    }

    public List<String> getPatientsForDoctorAndDate(String doctorId, Date examDate) throws SQLException {
        List<String> patients = new ArrayList<>();
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
                patients.add(rs2.getString("TENBN"));
            }
        }
        return patients;
    }

    public String getRequestForPatient(String patientId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT YEUCAUKHAM FROM KHAMBENH WHERE MABN = ?");
        pstmt.setString(1, patientId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("YEUCAUKHAM");
        }
        return null;
    }

    public void updateExamination(String patientId, String doctorId, Date examDate, String conclusion, List<Object[]> selectedServices) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE KHAMBENH SET KETLUAN = ? WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?");
        pstmt.setString(1, conclusion);
        pstmt.setString(2, patientId);
        pstmt.setString(3, doctorId);
        pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
        pstmt.executeUpdate();

        pstmt = conn.prepareStatement("SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND MABS = ? AND NGAYKHAM = ?");
        pstmt.setString(1, patientId);
        pstmt.setString(2, doctorId);
        pstmt.setDate(3, new java.sql.Date(examDate.getTime()));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String examId = rs.getString("MAKB");
            for (Object[] service : selectedServices) {
                String serviceId = (String) service[0];
                int quantity = Integer.parseInt((String) service[2]);
                long price = getServicePrice(serviceId);
                long totalPrice = quantity * price;
                pstmt = conn.prepareStatement("INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, examId);
                pstmt.setString(2, serviceId);
                pstmt.setInt(3, quantity);
                pstmt.setLong(4, totalPrice);
                pstmt.executeUpdate();
            }
        }
    }

    public Object[] getExaminationDetails(String patientId, Date examDate) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT YEUCAUKHAM, KETLUAN, THANHTOAN FROM KHAMBENH WHERE MABN = ? AND NGAYKHAM = ?");
        pstmt.setString(1, patientId);
        pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Object[]{rs.getString("YEUCAUKHAM"), rs.getString("KETLUAN"), rs.getBoolean("THANHTOAN")};
        }
        return null;
    }

    public List<Object[]> getBillingDetails(String patientId, Date examDate) throws SQLException {
        List<Object[]> details = new ArrayList<>();
        PreparedStatement pstmt = conn.prepareStatement("SELECT MADV, SOLUONG, THANHTIEN FROM THUPHI WHERE MAKB = (SELECT MAKB FROM KHAMBENH WHERE MABN = ? AND NGAYKHAM = ?)");
        pstmt.setString(1, patientId);
        pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String serviceId = rs.getString("MADV");
            int quantity = rs.getInt("SOLUONG");
            long amount = rs.getLong("THANHTIEN");
            String serviceName = getServiceName(serviceId);
            details.add(new Object[]{serviceName, quantity, amount});
        }
        return details;
    }

    public void markAsPaid(String patientId, Date examDate) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE KHAMBENH SET THANHTOAN = TRUE WHERE MABN = ? AND NGAYKHAM = ?");
        pstmt.setString(1, patientId);
        pstmt.setDate(2, new java.sql.Date(examDate.getTime()));
        pstmt.executeUpdate();
    }

    private long getServicePrice(String serviceId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT DONGIA FROM DICHVU WHERE MADV = ?");
        pstmt.setString(1, serviceId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong("DONGIA");
        }
        return 0;
    }

    private String getServiceName(String serviceId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT TENDV FROM DICHVU WHERE MADV = ?");
        pstmt.setString(1, serviceId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("TENDV");
        }
        return "";
    }
}