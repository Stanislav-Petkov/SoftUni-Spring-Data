package entities.bills_payment_system_5;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
public abstract class BillingDetail extends BaseEntity {
    private int number;
    private User user;

    public BillingDetail(){

    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
