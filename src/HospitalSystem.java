import java.io.*;
import java.util.*;

public class HospitalSystem implements Payable, Serializable {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<Bill> bills;
    private ArrayList<Department> departments;
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

    public HospitalSystem(ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Appointment> appointments, ArrayList<Bill> bills, ArrayList<Department> departments) {
        this.patients = patients;
        this.doctors = doctors;
        this.appointments = appointments;
        this.bills = bills;
        this.departments = departments;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
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
        // Check if appointment already exists
        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getDoctorId() == appointment.getDoctorId()
                    && existingAppointment.getAppointmentDate().equals(appointment.getAppointmentDate())
                    && existingAppointment.getAppointmentStatus() != AppointmentStatus.CANCELLED) {
                return false;
            }
        }

        // Check if doctor is available
        if (!isDoctorAvailable(appointment.getDoctorId(), appointment.getAppointmentDate())) {
            return false;
        }

        appointments.add(appointment);
        return true;
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
                        System.out.println(" Doctor's Earnings: " + earnings);
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

            patients = (ArrayList<Patient>) patientsIn.readObject();
            doctors = (ArrayList<Doctor>) doctorsIn.readObject();
            appointments = (ArrayList<Appointment>) appointmentsIn.readObject();
            bills = (ArrayList<Bill>) billsIn.readObject();
            departments = (ArrayList<Department>) departmentsIn.readObject();

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
        } catch (Exception e ) {
            patients = new ArrayList<>();
            doctors = new ArrayList<>();
            appointments = new ArrayList<>();
            bills = new ArrayList<>();
            departments = new ArrayList<>();
        }
    }
    public boolean isDoctorAvailable(int doctorId, Date appointmentDate) {
        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getDoctorId() == doctorId && 
                existingAppointment.getAppointmentDate().equals(appointmentDate) &&
                existingAppointment.getAppointmentStatus() != AppointmentStatus.CANCELLED) {
                return false;
            }
        }
        return true;
    }
}