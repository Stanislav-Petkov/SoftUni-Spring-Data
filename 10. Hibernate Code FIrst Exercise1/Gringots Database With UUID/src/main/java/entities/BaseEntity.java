package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    //private long id;
    private String id;

    public BaseEntity(){
    }
//
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
