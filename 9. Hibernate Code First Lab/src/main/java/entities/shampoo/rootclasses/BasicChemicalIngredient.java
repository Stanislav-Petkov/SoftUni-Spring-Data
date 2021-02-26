package entities.shampoo.rootclasses;

import entities.shampoo.interfaces.ChemicalIngredient;
import entities.shampoo.rootclasses.BasicIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient
        implements ChemicalIngredient {
    @Column(name = "chemical_formula")
    String chemicalFormula;

    protected BasicChemicalIngredient(){
    }

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula){
        super(name, price);
        this.setChemicalFormula(chemicalFormula);
    }

    @Override
    public void setChemicalFormula(String chemicalFormula){
        this.chemicalFormula = chemicalFormula;
    }
}
