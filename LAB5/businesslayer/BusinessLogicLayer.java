package businesslayer;

import datalayer.DataAccessLayer;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BusinessLogicLayer {
    private DataAccessLayer dal;

    public BusinessLogicLayer() {
        dal = new DataAccessLayer();
    }

    public void addPatient(String id, String name, Date dob, String address, String phone, boolean gender) throws SQLException {
        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Thông tin bệnh nhân không được để trống");
        }
        dal.addPatient(id, name, dob, address, phone, gender);
    }

    public String getPatientName(String patientId) throws SQLException {
        return dal.getPatientName(patientId);
    }

    public List<String> getDoctors() throws SQLException {
        return dal.getDoctors();
    }

    public void scheduleExamination(String patientId, String doctorName, Date examDate, String request) throws SQLException {
        if (examDate.before(new Date())) {
            throw new IllegalArgumentException("Ngày khám phải lớn hơn hoặc bằng ngày hiện tại");
        }
        String doctorId = dal.getDoctorId(doctorName);
        String examId = "KB" + System.currentTimeMillis() % 1000000;
        dal.scheduleExamination(examId, patientId, doctorId, examDate, request);
    }

    public List<Object[]> getServices() throws SQLException {
        return dal.getServices();
    }

    public List<String> getPatientsForDoctorAndDate(String doctorName, Date examDate) throws SQLException {
        String doctorId = dal.getDoctorId(doctorName);
        return dal.getPatientsForDoctorAndDate(doctorId, examDate);
    }

    public String getRequestForPatient(String patientName) throws SQLException {
        String patientId = dal.getPatientId(patientName);
        return dal.getRequestForPatient(patientId);
    }

    public void updateExamination(String patientName, String doctorName, Date examDate, String conclusion, List<Object[]> selectedServices) throws SQLException {
        String patientId = dal.getPatientId(patientName);
        String doctorId = dal.getDoctorId(doctorName);
        dal.updateExamination(patientId, doctorId, examDate, conclusion, selectedServices);
    }

    public Object[] getExaminationDetails(String patientId, Date examDate) throws SQLException {
        return dal.getExaminationDetails(patientId, examDate);
    }

    public List<Object[]> getBillingDetails(String patientId, Date examDate) throws SQLException {
        return dal.getBillingDetails(patientId, examDate);
    }

    public void markAsPaid(String patientId, Date examDate) throws SQLException {
        dal.markAsPaid(patientId, examDate);
    }
}