package entities.shampoo.interfaces;


import entities.shampoo.rootclasses.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.Size;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public interface Shampoo {
    long getId();
    void setId(long id);
    String getBrand();
    void setBrand(String brand);
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
    Size getSize();
    void setSize(Size size);
    BasicLabel getLabel();
    void setLabel(BasicLabel label);
    Set<BasicIngredient> getIngredients();
    void setIngredients(HashSet<BasicIngredient> ingredients);
}
