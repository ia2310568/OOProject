
import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private Date appointmentDate;
    private AppointmentStatus appointmentStatus;


    public Appointment(int appointmentId, int patientId, int doctorId, Date appointmentDate, AppointmentStatus appointmentStatus){
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentStatus = appointmentStatus;
        this.appointmentDate = appointmentDate;
    }


    public int getAppointmentId() {
        return appointmentId;
    }


    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }


    public int getPatientId() {
        return patientId;
    }


    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    public int getDoctorId() {
        return doctorId;
    }


    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public Date getAppointmentDate() {
        return appointmentDate;
    }


    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }


    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }


    public void confirmAppointment() {
        this.appointmentStatus = AppointmentStatus.CONFIRMED;
    }

    public void cancelAppointment() {
        this.appointmentStatus = AppointmentStatus.CANCELLED;
    }

    public void rescheduleAppointment(Date newDate) {
        this.appointmentDate = newDate;
    }


    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
                + "]";
    }
}