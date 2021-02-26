package entities.shampoo;

import entities.shampoo.interfaces.Shampoo;
import entities.shampoo.rootclasses.BasicIngredient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {
    @Id
    private long id;

    @Basic
    private BigDecimal price;

    @Basic
    private String brand;

    @Enumerated
    private Size size;

    @OneToOne(optional = true, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    protected BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    protected BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel classicLabel) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = classicLabel;
        setIngredients(new HashSet<>());
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public BasicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        System.out.println();
        return ingredients;
    }

    //@Override
    public void setIngredients(HashSet<BasicIngredient> ingredients) {
        System.out.println();
        this.ingredients = ingredients;
        System.out.println();
    }
}
