package entities.shampoo.interfaces;

public interface ChemicalIngredient extends Ingredient {
    void setChemicalFormula(String chemicalFormula);
    String getChemicalFormula();
}

