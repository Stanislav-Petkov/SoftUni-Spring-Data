package entities.billsPaymentSystem;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {

    private static final String billing_type = "CREDIT_CARD";
    private String card_type;
    private Month expiration_month;
    private Year expiration_year;

    public CreditCard(){
        super(billing_type);
    }

    @Column(name = "expiration_month")
    public Month getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(Month expiration_month) {
        this.expiration_month = expiration_month;
    }

    @Column(name = "expiration_year")
    public Year getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(Year expiration_year) {
        this.expiration_year = expiration_year;
    }

    @Column(name = "card_type")
    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
}
