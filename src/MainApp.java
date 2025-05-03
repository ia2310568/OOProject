import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/*
 * Author: Ammar Mohammad
 * Date: April 27, 2025
 * Version: 1.0
 */
public class MainApp {
    private static HospitalSystem hospital;
    private static Scanner scanner = new Scanner(System.in);
    private static void menu() {
        System.out.println("\n--- Hospital Management System ---");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. Add Department");
        System.out.println("4. Assign Doctor to Department");
        System.out.println("5. Schedule Appointment");
        System.out.println("6. Generate Bill");
        System.out.println("7. Show Reports");
        System.out.println("8. Show All Doctors");
        System.out.println("9. Show All Departments");
        System.out.println("10. Save And Exit");
    }

    /*
     * Author: Ammar Mohammad
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static int getDepartmentId() {
        int departmentId = -1;
        while (departmentId <= 0) {
            System.out.print("Enter the department ID: ");
            String input = scanner.nextLine();
            try {
                departmentId = Integer.parseInt(input);
                if (departmentId <= 0) {
                    System.out.println("Department ID must be a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid department ID. Please enter a valid number.");
            }
        }
        return departmentId;
    }
    /*
     * Author: Ammar Mohammad
     * Date: April 27, 2025
     * Version: 1.0
     */
    private static String validatePhoneNumber() {
        String phone;
        boolean validPhone = false;
        do {
            System.out.print("Enter phone number: ");
            phone = scanner.nextLine();
            try {
                Long.parseLong(phone);
                validPhone = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid phone number. Enter again");
            }
        }
        while (!validPhone);
        return phone;
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        String phone = validatePhoneNumber();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Contact contact = new Contact(phone, email);

        System.out.print("Enter gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter medical history: ");
        String medicalHistory = scanner.nextLine();

        int id = hospital.getNextPatientId();
        Patient patient = new Patient(id, name, contact, age, gender, medicalHistory);
        hospital.addPatient(patient);
        System.out.println("Patient added successfully with ID: " + id);
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        String phone = validatePhoneNumber();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Contact contact = new Contact(phone, email);

        System.out.print("Enter gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        int id = hospital.getNextDoctorId();
        Doctor doctor = new Doctor(id, name, contact, gender, specialization);
        hospital.addDoctor(doctor);
        System.out.println("Doctor added successfully with ID: " + id);
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void addDepartment() {
        System.out.print("Enter department ID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();

        Department department = new Department(departmentId, departmentName);
        hospital.addDepartment(department);
        System.out.println("Department added successfully!");
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void scheduleAppointment() {
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter doctor ID: ");
        int doctorId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);
        java.util.Date appointmentDate = java.sql.Date.valueOf(date);

        // Check if doctor is available on the requested date
        if (!hospital.isDoctorAvailable(doctorId, appointmentDate)) {
            System.out.println("Doctor is not available on this date. Please choose another date.");
            return;
        }

        int appointmentId = hospital.getNextAppointmentId();
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, AppointmentStatus.CONFIRMED);

        if (hospital.scheduleAppointment(appointment)) {
            System.out.println("Appointment scheduled successfully with ID: " + appointmentId);
        } else {
            System.out.println("Failed to schedule appointment. Please try again.");
        }
    }
    /*
     * Author: Omar Al Emadi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void generateBill() {
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter bill date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        int billId = hospital.getNextBillId();
        Bill bill = new Bill(billId, patientId, date);

        while (true) {
            System.out.print("Enter service (or 'done' to finish): ");
            String service = scanner.nextLine();
            if (service.equalsIgnoreCase("done")) break;
            bill.addService(service);
        }

        hospital.generateBill(bill);
        System.out.println("Bill generated successfully with ID: " + billId);
    }
    /*
     * Author: Omar Al Emadi
     * Date: April 27, 2025
     * Version: 1.0
     */
    private static void ReportOptions() {
        System.out.println("--- Reports Menu ---");
        System.out.println("1. Patient Report");
        System.out.println("2. Doctor Report");
        System.out.println("3. Appointment Report");
        System.out.println("4. Bill Report");
        System.out.println("5. Department Report");
        System.out.println("6. Exit");
    }

    /*
     * Author: Omar Al Emadi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void showReports() {
        while (true) {
            ReportOptions();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        hospital.generatePatientReport();
                        break;
                    case 2:
                        hospital.generateDoctorReport();
                        break;
                    case 3:
                        hospital.generateAppointmentReport();
                        break;
                    case 4:
                        hospital.generateBillingReport();
                        break;
                    case 5:
                        hospital.generateDepartmentReport();
                        break;
                    case 6: {
                        System.out.println("Exiting report menu...");
                        return;
                    }
                    default:
                        System.out.println("Invalid option. Please choose from 1 to 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /*
     * Author: Omar Al Emadi
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void showAllDoctors() {
        List<Doctor> doctorList = hospital.getDoctors();

        if (doctorList == null || doctorList.isEmpty()) {
            System.out.println("There are no doctors saved in the system.");
            return;
        }

        System.out.println("------ List of Doctors ------");

        int count = 1;
        for (Doctor doc : doctorList) {
            System.out.println("Doctor " + count + ":");
            System.out.println("  ID: " + doc.getId());
            System.out.println("  Name: " + doc.getName());
            System.out.println("  Specialization: " + doc.getSpecialization());
            System.out.println("  Department ID: " + doc.getDepartmentId());
            System.out.println();
            count++;
        }

        System.out.println("Total doctors: " + doctorList.size());
    }

    /*
     * Author: Ammar Mohammad
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void showAllDepartments() {
        List<Department> departmentList = hospital.getDepartments();

        if (departmentList == null || departmentList.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }

        System.out.println("------ Departments ------");

        for (Department dep : departmentList) {
            System.out.println("ID: " + dep.getDepartmentId() + " | Name: " + dep.getDepartmentName());
        }

        System.out.println("Total: " + departmentList.size() + " departments.");
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */

    public static void assignDoctorToDepartment() {
        if (hospital.getDepartments().isEmpty()) {
            System.out.println("No departments found.");
            return;
        }

        if (hospital.getDoctors().isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        int departmentId = getValidDepartmentId();
        int doctorId = getValidDoctorId();

        hospital.assignDoctorToDepartment(doctorId, departmentId);
        System.out.println("Doctor has been added to the department.");
    }
    /*
     * Author: Omar Al Emadi
     * Date: April 27, 2025
     * Version: 1.0
     */
    private static int getValidDepartmentId() {
        while (true) {
            int id = getDepartmentId();
            for (Department d : hospital.getDepartments()) {
                if (d.getDepartmentId() == id) return id;
            }
            System.out.println("That department does not exist. Try again.");
        }
    }
    /*
     * Author: Ibrahim Alrumaihi
     * Date: April 27, 2025
     * Version: 1.0
     */
    private static int getValidDoctorId() {
        while (true) {
            System.out.print("Enter the doctor ID: ");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                for (Doctor d : hospital.getDoctors()) {
                    if (d.getId() == id) return id;
                }
                System.out.println("That doctor does not exist. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
    /*
     * Author: Ammar Mohammad
     * Date: April 27, 2025
     * Version: 1.0
     */
    public static void main(String[] args) {
        hospital = new HospitalSystem();
        hospital.loadDataFromFiles();
        while (true) {
            try {
                menu();
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        addDoctor();
                        break;
                    case 3:
                        addDepartment();
                        break;
                    case 4:
                        assignDoctorToDepartment();
                        break;
                    case 5:
                        scheduleAppointment();
                        break;
                    case 6:
                        generateBill();
                        break;
                    case 7:
                        showReports();
                        break;
                    case 8:
                        showAllDoctors();
                        break;
                    case 9:
                        showAllDepartments();
                        break;
                    case 10:
                        System.out.println("Saving and exiting...");
                        hospital.saveDataToFiles();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

}