public class Patient extends Person { // you must implements Serializable.

    private int age;
    private String medicalHistory;

    public Patient() {
        super();
    }

    public Patient(int id, String name, Contact contact, int age, Gender gender, String medicalHistory) {
        super(id, name, contact, gender);
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient: " + toString());
    }

    public void updateMedicalHistory(String newMedicalInfo) {
        this.medicalHistory = newMedicalInfo;
    }

    public String toString() {
        return "age: " + age + ", medicalHistory: " + medicalHistory + ", Patint : " + super.toString();
    }



}