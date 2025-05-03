import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable {
    private int departmentId;
    private String departmentName;
    private ArrayList<Doctor> doctors;
    private static ArrayList<Integer> existingDepartmentIds = new ArrayList<>();

    public Department(int departmentId, String departmentName) {
        if (!isValidDepartmentId(departmentId)) {
            throw new IllegalArgumentException("Department ID already exists");
        }
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.doctors = new ArrayList<>();
        existingDepartmentIds.add(departmentId);
    }
    

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        if (!isValidDepartmentId(departmentId)) {
            throw new IllegalArgumentException("Department ID already exists");
        }
        existingDepartmentIds.remove(Integer.valueOf(this.departmentId));
        this.departmentId = departmentId;
        existingDepartmentIds.add(departmentId);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);

    }

    public void removeDoctor(int doctorid) {
        for(int i = 0;i<doctors.size() ;i++) {
            if (doctors.get(i).getId() == doctorid) {
                doctors.remove(i);
                break;
            }
        }

    }
    public void displayDepartmentDetails() {
        System.out.println("Department ID : "+ departmentId + "Department Name : "+ departmentName);
    }

    private boolean isValidDepartmentId(int departmentId) {
        return !existingDepartmentIds.contains(departmentId);
    }

}
