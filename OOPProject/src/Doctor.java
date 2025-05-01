public class Doctor extends Person{

    private String specialization;
    private int departmentId;

    public Doctor(int id, String name, Contact contact, Gender gender, String specialization) {
        super(id, name, contact, gender);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public void displayDetails() {

        System.out.println("Doctor " + toString());
    }

    @Override
    public String toString() {
        return super.toString()+ ", specialization: "
                + getSpecialization() ;
    }

}
