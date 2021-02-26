package entities.shampoo.ingredients;

import entities.shampoo.rootclasses.BasicChemicalIngredient;
import entities.shampoo.rootclasses.BasicIngredient;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {
    private static final BigDecimal PRICE =  new BigDecimal("6.12");
    private static final String NAME = "Ammonium Chloride";
    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride(){
        super(NAME,PRICE,CHEMICAL_FORMULA);
    }

    public  BigDecimal getPRICE() {
        return PRICE;
    }

    public  String getNAME() {
        return NAME;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        super.setChemicalFormula(chemicalFormula);
    }

    public  String getChemicalFormula() {
        return CHEMICAL_FORMULA;
    }
}
