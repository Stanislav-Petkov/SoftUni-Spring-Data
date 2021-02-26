package course.springdata.codefirst.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {
    @Id
    private BigInteger id;
    private String name;
    // For CascadeType.ALL When I delete the company , I delete the planes,
    //When I persist the company I persist the planes
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Plane> planes = new HashSet<>();

    public Company() {
    }

    public Company(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(BigInteger id, String name, Set<Plane> planes) {
        this.id = id;
        this.name = name;
        this.planes = planes;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(Set<Plane> planes) {
        this.planes = planes;
    }
}





















