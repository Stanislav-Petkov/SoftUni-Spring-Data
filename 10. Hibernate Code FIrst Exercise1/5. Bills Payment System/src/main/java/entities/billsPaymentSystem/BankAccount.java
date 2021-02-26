package entities.billsPaymentSystem;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BankAccount extends BillingDetail {

    private final static String billing_type = "BANK_ACCOUNT";
   // private int id;
    private String bankName;
    private String swiftCode;

    public BankAccount(){
        super(billing_type);
    }


    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "swift_code")
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }


}
