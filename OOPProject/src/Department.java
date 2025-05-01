import java.util.ArrayList;

public class Department {
    private int departmentId;
    private String departmentName;
    private ArrayList<Doctor> doctors;

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.doctors= new ArrayList<>();

    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

}
