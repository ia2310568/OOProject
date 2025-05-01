import java.io.*;
import java.util.*;

public class HospitalSystem implements Payable, Serializable {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private List<Department> departments;
    private int nextPatientId = 1000;
    private int nextDoctorId = 2000;
    private int nextAppointmentId = 3000;
    private int nextBillId = 4000;
    private int nextDepartmentId = 1;

    public HospitalSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    public HospitalSystem(List<Patient> patients, List<Doctor> doctors, List<Appointment> appointments, List<Bill> bills, List<Department> departments) {
        this.patients = patients;
        this.doctors = doctors;
        this.appointments = appointments;
        this.bills = bills;
        this.departments = departments;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public int getNextPatientId() {
        return nextPatientId++;
    }

    public int getNextDoctorId() {
        return nextDoctorId++;
    }

    public int getNextAppointmentId() {
        return nextAppointmentId++;
    }

    public int getNextBillId() {
        return nextBillId++;
    }

    public int getNextDepartmentId() {
        return nextDepartmentId++;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void deletePatient(int patientId) {
        patients.remove(patientId);
    }

    public void deleteDoctor(int doctorId) {
        doctors.remove(doctorId);
    }

    public Patient searchPatientById(int patientId) {
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }
        return null;
    }

    public Doctor searchDoctorById(int doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                return doctor;
            }
        }
        return null;
    }

    public boolean scheduleAppointment(Appointment appointment) {
        if (!appointments.contains(appointment)) {
            appointments.add(appointment);
            return true;
        }
        return false;
    }

    public boolean cancelAppointmentById(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointments.remove(appointment);
                return true;
            }
        }
        return false;
    }

    public Appointment searchAppointmentById(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }

    public void generateBill(Bill bill) {
        bills.add(bill);
        bill.generateInvoice();
    }

    public Bill searchBillById(int billId) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                return bill;
            }
        }
        return null;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void assignDoctorToDepartment(int doctorId, int departmentId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                doctor.setDepartmentId(departmentId);
            }
        }
    }


    @Override
    public double calculatePayment(int billId) {
        return searchBillById(billId).getAmount()*0.3;
    }

    public void generatePatientReport() {
        System.out.println("Patient Report:");
        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Medical History: " + patient.getMedicalHistory());
            System.out.println("--------------------");
        }
    }

    public void generateDoctorReport() {
        System.out.println("Doctor Report:");
        for (Doctor doctor : doctors) {
            System.out.println("ID: " + doctor.getId());
            System.out.println("Name: " + doctor.getName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("Department ID: " + doctor.getDepartmentId());
            System.out.println("--------------------");
        }
    }

    public void generateAppointmentReport() {
        System.out.println("Appointment Report:");
        for (Appointment appointment : appointments) {
            System.out.println("ID: " + appointment.getAppointmentId());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("Status: " + appointment.getAppointmentStatus());
            System.out.println("--------------------");
        }
    }

    public void generateBillingReport() {
        double total = 0;
        System.out.println(getBills());
        for (Bill bill : bills) {
            total+=bill.getAmount();
        }
        System.out.printf("Total revenue: %.2f", total);
        for (Doctor doctor : doctors) {
            double earnings = 0;

            for (Bill bill : bills) {
                int patientId = bill.getPatientId();

                for (Appointment appointment : appointments) {
                    if (patientId == appointment.getPatientId() && appointment.getDoctorId() == doctor.getId()) {
                        earnings=bill.getAmount()*0.3;
                        System.out.println("Doctor's Earnings: " + earnings);
                    }
                }
            }
        }
    }

    public void generateDepartmentReport() {
        System.out.println("Department Report:");
        for (Department department : departments) {
            System.out.println("Department ID: " + department.getDepartmentId());
            System.out.println("Name: " + department.getDepartmentName());
            System.out.println("Doctors assigned:");
            for (Doctor doctor : doctors) {
                if (doctor.getDepartmentId() == department.getDepartmentId()) {
                    System.out.println("- " + doctor.getName() + " (ID: " + doctor.getId() + ")");
                }
            }
            System.out.println("--------------------");
        }
    }

    public void saveDataToFiles() {
        try {
            ObjectOutputStream patientsOut = new ObjectOutputStream(new FileOutputStream("patients.dat"));
            ObjectOutputStream doctorsOut = new ObjectOutputStream(new FileOutputStream("doctors.dat"));
            ObjectOutputStream appointmentsOut = new ObjectOutputStream(new FileOutputStream("appointments.dat"));
            ObjectOutputStream billsOut = new ObjectOutputStream(new FileOutputStream("bills.dat"));
            ObjectOutputStream departmentsOut = new ObjectOutputStream(new FileOutputStream("departments.dat"));
            ObjectOutputStream countersOut = new ObjectOutputStream(new FileOutputStream("counters.dat"));

            patientsOut.writeObject(patients);
            doctorsOut.writeObject(doctors);
            appointmentsOut.writeObject(appointments);
            billsOut.writeObject(bills);
            departmentsOut.writeObject(departments);

            int[] counters = {nextPatientId, nextDoctorId, nextAppointmentId, nextBillId, nextDepartmentId};
            countersOut.writeObject(counters);

            patientsOut.close();
            doctorsOut.close();
            appointmentsOut.close();
            billsOut.close();
            departmentsOut.close();
            countersOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFiles() {
        try {
            ObjectInputStream patientsIn = new ObjectInputStream(new FileInputStream("patients.dat"));
            ObjectInputStream doctorsIn = new ObjectInputStream(new FileInputStream("doctors.dat"));
            ObjectInputStream appointmentsIn = new ObjectInputStream(new FileInputStream("appointments.dat"));
            ObjectInputStream billsIn = new ObjectInputStream(new FileInputStream("bills.dat"));
            ObjectInputStream departmentsIn = new ObjectInputStream(new FileInputStream("departments.dat"));
            ObjectInputStream countersIn = new ObjectInputStream(new FileInputStream("counters.dat"));

            patients = (List<Patient>) patientsIn.readObject();
            doctors = (List<Doctor>) doctorsIn.readObject();
            appointments = (List<Appointment>) appointmentsIn.readObject();
            bills = (List<Bill>) billsIn.readObject();
            departments = (List<Department>) departmentsIn.readObject();

            int[] counters = (int[]) countersIn.readObject();
            nextPatientId = counters[0];
            nextDoctorId = counters[1];
            nextAppointmentId = counters[2];
            nextBillId = counters[3];
            nextDepartmentId = counters[4];

            patientsIn.close();
            doctorsIn.close();
            appointmentsIn.close();
            billsIn.close();
            departmentsIn.close();
            countersIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}