package se.experis.com.musicapplication.models;

public class CustomerSpender {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    private String firstName;
    private String lastName;
    private int totalAmount;

    public CustomerSpender(String firstName, String lastName, int totalAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalAmount = totalAmount;
    }
}
