package entities.bills_payment_system;

import entities.BaseEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillingDetail extends BaseEntity {
    private int number;
    private User user;

    public BillingDetail(){

    }//TODO

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
