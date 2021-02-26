package entities.shampoo;

import entities.shampoo.ingredients.AmmoniumChloride;
import entities.shampoo.ingredients.Mint;
import entities.shampoo.ingredients.Nettle;
import entities.shampoo.rootclasses.BasicIngredient;
import entities.shampoo.shampootypes.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory  managerFactory = Persistence
                .createEntityManagerFactory("shampoo_company");
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label =
                new BasicLabel("Fresh Nuke Shampoo",
                        "Contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        Set<BasicIngredient> ingredients = shampoo.getIngredients();
        ingredients.add(mint);

        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);
        shampoo.getIngredients().add(am);
        em.persist(shampoo);

        em.getTransaction().commit();
        em.close();
    }
}
