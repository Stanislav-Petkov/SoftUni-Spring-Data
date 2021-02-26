package entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntityIdentity {

    private long id;

    public BaseEntityIdentity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
