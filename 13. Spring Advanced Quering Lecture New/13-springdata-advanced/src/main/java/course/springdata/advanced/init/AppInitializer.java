package course.springdata.advanced.init;

import course.springdata.advanced.dao.IngredientRepository;
import course.springdata.advanced.dao.LabelRepository;
import course.springdata.advanced.dao.ShampooRepository;
import course.springdata.advanced.entity.Ingredient;
import course.springdata.advanced.entity.Shampoo;
import course.springdata.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static course.springdata.advanced.entity.Size.MEDIUM;

@Component
public class AppInitializer implements CommandLineRunner {

    private final ShampooRepository shampooRepo;
    private final LabelRepository labelRepo;
    private final IngredientRepository ingredientRepo;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepo, LabelRepository labelRepo,
                          IngredientRepository ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        // Ex 2.	Select Shampoos by Size or Label
//        selectShampoosBySizeOrLabel();

//        System.out.println("-".repeat(150) + "%n");

        //Ex 3.	Select Shampoos by Price greater than or equal
//        shampooRepo.findAllByPriceGreaterThanEqualOrderByPriceDesc(5)
//                .forEach(PrintUtil::printShampoo);

//        System.out.println("-".repeat(150) + "%n");

//        shampooRepo.findAllByPriceBetween(5,8)
//                .forEach(PrintUtil::printShampoo);

        //Ex 5.	Select Ingredients by Names
//        ingredientRepo.findAllByNameIn(Set.of("Lavender","Herbs","Apple"))
//                .forEach(PrintUtil::printIngredient);

        //6.	Count Shampoos by Price
//        System.out.println(shampooRepo.findAllByPriceLessThan(8.5).size());
//        double maxPrice = 8.5;
//        System.out.printf( "Number of shampoos with price less than %5.2f is: %d",
//                maxPrice,
//                shampooRepo.countShampooByPriceLessThan(maxPrice));

//        7.	Select Shampoos by Ingredients
//        findShampoosByIngredientsNames();

        //8.	Select Shampoos by Ingredients Count
//        shampooRepo.findAllByIngredientsCountLessThan(2)
//                .forEach(PrintUtil::printShampoo);
////
//        shampooRepo.findAllByCountOfIngredientsLowerThan(2)
//                .forEach(PrintUtil::printShampoo);

        //Ex9 deletes ingredients by a given name.
//        String nameToDelete = "Macadamia Oil";
//        //First find the ingredient with this name from the repository
//        Ingredient ingredientToRemove = ingredientRepo.findByName(nameToDelete).get();
//
//        //Secondly find which shampoos have this ingredient and remove the ingredient from them
//        List<Shampoo> shampoosByIngredient = shampooRepo.findByIngredient(ingredientToRemove);
//        shampoosByIngredient.forEach(PrintUtil::printShampoo);
//        shampoosByIngredient.forEach(shampoo -> shampoo.getIngredients().remove(ingredientToRemove));
//
//        // Thirdly delete the ingredient
//        System.out.printf("Number of deleted ingredients with name= %s is : %d %n",
//                nameToDelete,
//                ingredientRepo.deleteAllByName(nameToDelete));
//
//        System.out.println("-".repeat(150) + "%n");
//        // Print all shampoos
//        shampooRepo.findAll().forEach(PrintUtil::printShampoo);
        System.out.println("-".repeat(150) + "%n");

        //Ex 10 and 11 Increase price of ingredients in list by percentage
        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);
        System.out.println("-".repeat(150) + "%n");
        System.out.printf("Number of ingredients updated: %d%n",
                ingredientRepo.updatePriceOfIngredientsInList(
                        Set.of("Lavender","Herbs","Apple"), 0.1));
        System.out.println("-".repeat(150) + "%n");
        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);

    }

    private void selectShampoosBySizeOrLabel() {
        shampooRepo.findBySizeOrLabelOrderByPriceDesc(MEDIUM,
                labelRepo.findByTitle("Vital").get())
                .forEach(PrintUtil::printShampoo);
    }

    //Ex1 from presentation fetch shampoo by ingredients
    private void findShampoosByIngredientsNames() {
        shampooRepo.findWithIngredientsIn(Set.of("Berry", "Mineral-Collagen"))
                .forEach(PrintUtil::printShampoo);
    }

    private void selectShampooBySizeEx1WithFormatting() {
        shampooRepo.findBySizeOrderById(MEDIUM)
                .forEach(PrintUtil::printShampoo);
    }

    private void selectShampooBySizeEx1() {
        shampooRepo.findBySizeOrderById(MEDIUM)
                .forEach(shampoo -> {
                    System.out.printf("%s %s %.2flv.%n", shampoo.getBrand(),
                            shampoo.getSize(),
                            shampoo.getPrice());
                });
    }
}
