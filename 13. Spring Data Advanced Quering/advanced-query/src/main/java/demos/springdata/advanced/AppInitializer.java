package demos.springdata.advanced;

import demos.springdata.advanced.dao.IngredientRepository;
import demos.springdata.advanced.dao.LabelRepository;
import demos.springdata.advanced.dao.ShampooRepository;
import demos.springdata.advanced.entity.Ingredient;
import demos.springdata.advanced.entity.Label;
import demos.springdata.advanced.entity.Shampoo;
import demos.springdata.advanced.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static demos.springdata.advanced.entity.Size.*;

@Component
public class AppInitializer implements ApplicationRunner {
    private final ShampooRepository shampooRepo;
    private final LabelRepository labelRepo;
    private final IngredientRepository ingredientRepo;

    // Constructor without dependency injection

    @Autowired
    public AppInitializer(ShampooRepository shampooRepo, LabelRepository labelRepo,
                          IngredientRepository ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Problem 1 Select shampoos by size
//        shampooRepo.findBySize(Size.MEDIUM)
//                .forEach(s ->
//                        System.out.printf("%s %s %.2f%n",
//                                s.getBrand(),s.getSize(),s.getPrice()));

        // Problem 2 Create a method that selects all shampoos with a given
        // size or label id. Sort the result ascending by price.
//        Label label = labelRepo.findOneById(10L);
//        shampooRepo.findBySizeOrLabelOrderByPriceAsc(MEDIUM,label)
//                .forEach(s ->
//                        System.out.printf("%s %s %.2f %d%n",
//                                s.getBrand(),s.getSize(),s.getPrice(),
//                                s.getLabel().getId()));

        // Problem 3Create a method that selects all shampoos, which price is higher than a given price.
        // Sort the result descending by price.
//        shampooRepo.findByPriceGreaterThanOrderByPriceDesc(5.0)
//                .forEach(s ->
//                        System.out.printf("%s %s %.2f %d%n",
//                                s.getBrand(),s.getSize(),s.getPrice(),
//                                s.getLabel().getId()));

        //problem 4 Create a method that selects all ingredients, which name starts with given letters.
//        ingredientRepo.findByNameStartingWith("M")
//                .forEach(i -> System.out.printf("%s%n",
//                        i.getName()));

        //Problem 5 Create a method that selects all ingredients, which are contained in a given list.
        // Sort the result ascending by price.
//        List<String> names = List.of("Lavender","Herbs","Apple");
//        ingredientRepo.findByNameInOrderByPriceAsc(names)
//                .forEach(i -> System.out.printf("%s%n",
//                        i.getName()));

        //problem 6 Create a method that counts all shampoos with price lower than a given price.
        int countOfShampoos = shampooRepo.countAllByPriceLessThan(8.5);
        System.out.println(countOfShampoos);

        //problem 7 Create a method that selects all shampoos with ingredients included in a given list.
//        shampooRepo.findWithIngredientsInList(
//                List.of(ingredientRepo.findByName("Berry"),
//                        ingredientRepo.findByName("Mineral-Collagen")))
//                        .forEach(s ->
//                        System.out.printf("%s %s %s %.2f %s %n",
//                                s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
//                                s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())
//                               ));

        // Problem 8 Create a method that selects all shampoos with ingredients less than a given number.
//        shampooRepo.findByIngredientsCountLessThan(2)
//                .forEach(s -> System.out.printf("%s%n",s.getBrand()));
//        shampooRepo.findByIngredientsCountLessThan(2)
//                .forEach(s -> System.out.printf("%s %s %s %.2f %s%n",
//                        s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
//                        s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())));



        //problem 9 Create a method that deletes ingredients by a given name. Use named query.
//        ingredientRepo.updateIngredientIdInShampoos_Ingredients();
//        ingredientRepo.deleteAllByNameEquals("Apple");

        // problem 10
//        ingredientRepo.updateAllIngredientsPriceWith10Percent();

        //Problem 11 Create a method that updates the price of all ingredients, which names are in a given list.

//        System.out.println("-------  BEFORE UPDATE ----------");
//        ingredientRepo.findByNameInOrderByPriceAsc(List.of("Lavender","Herbs","Apple"))
//                .forEach(i -> System.out.printf("%s %s %.2f%n",
//                        i.getId(), i.getName(), i.getPrice()));
//        System.out.printf("Updated records: %d%n",
//        ingredientRepo.updatePriceIngredientsInListBy10Percent(List.of("Lavender","Herbs","Apple")));
//        System.out.println("-------  AFTER UPDATE ---------");
//        ingredientRepo.findAll()
//                .forEach(i -> System.out.printf("%s %s %.2f%n",
//                        i.getId(), i.getName(), i.getPrice()));

        //Print in pages of 5
        // the starting page is the first parameter
//        Page<Shampoo> page = shampooRepo.findAll(PageRequest.of(0,5));
//        System.out.printf("Page %d of %d:%n-------------------%n", page.getNumber(),  page.getTotalPages());
//        page.forEach(s -> System.out.printf("%s %s %s %.2f %s%n",
//                s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
//                s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())));
//
//        while(page.hasNext()){
//            page = shampooRepo.findAll(page.nextPageable());
//            System.out.printf("Page %d of %d:%n-------------------%n", page.getNumber(),  page.getTotalPages());
//            page.forEach(s -> System.out.printf("%s %s %s %.2f %s%n",
//                    s.getBrand(), s.getSize(), s.getLabel().getTitle(), s.getPrice(),
//                    s.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList())));
//        }
    }
}


















