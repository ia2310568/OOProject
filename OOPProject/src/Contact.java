import java.io.Serializable;
public class Contact extends Person implements Serializable {

    private String email;
    private String phoneNumber;

    public Contact() {
        super();
    }

    public Contact(String email, String phoneNumber) {
        super();
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void displayContactInfo() {
        System.out.println("Email: " + email + ", Phone: " + phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact [email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}
