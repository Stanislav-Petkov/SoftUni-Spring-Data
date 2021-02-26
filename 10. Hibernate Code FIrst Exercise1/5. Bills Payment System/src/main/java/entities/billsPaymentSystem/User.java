package entities.billsPaymentSystem;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BillingDetail billingDetailsId;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "billing_details_id", referencedColumnName = "id")
    public BillingDetail getBillingDetailsId() {
        return billingDetailsId;
    }

    public void setBillingDetailsId(BillingDetail billingDetailsId) {
        this.billingDetailsId = billingDetailsId;
    }

}
