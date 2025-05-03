import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Serializable {
    private int id;
    private String name;
    private Contact contact;
    private Gender gender;


    public Person() {
        super();
    }

    public Person(int id, String name, Contact contact, Gender gender) {
        super();
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void displayDetails() {
        System.out.println(this.toString());
    }

    public boolean validateId() {
        return id > 0;
    }
    public void updateContactInfo(Contact newContact){
        this.setContact(newContact);
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", contact=" + contact ;
    }


}
