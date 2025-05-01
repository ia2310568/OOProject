import java.util.ArrayList;
import java.util.List;

public class Bill implements Payable {
    private int billId;
    private int patientId;
    private double amount;
    private ArrayList<String> services;
    private String date;

    public Bill(int billId , int patientId, String date) {
        this.billId = billId;
        this.patientId = patientId;
        this.date = date;
        this.services= new ArrayList<>();

    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addService(String service) {
        services.add(service);
        amount = amount + 50;

    }
    public void generateInvoice() {

        System.out.println("Bill ID : "+ billId + ", PatientId : "+ patientId + ", Amount : "+ amount + ", Services : ");
        for(String s: services) {
            System.out.println(s);

        }

    }

    public double calculatePayment(int billId) {
        return amount;


    }

}
