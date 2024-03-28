package org.example.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing(){
        boolean[] Proteins = {false};
        boolean[] Fats = {false};
        boolean[] Carbohydrates = {false};

        foodstuffs.
                stream().
                    forEach(food -> {
                        Proteins[0] = Proteins[0] && food.getProteins() ? true : Proteins[0];
                        Fats[0] = Fats[0] && food.getProteins() ? true : Fats[0];
                        Carbohydrates[0] = Carbohydrates[0] && food.getProteins() ? true : Carbohydrates[0];
                    });

        if(Proteins[0] && Fats[0] && Carbohydrates[0]) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        Proteins[0] = market.getThings(clazz).
                stream().filter(thing -> !Proteins[0] && thing.getProteins()).
                    findFirst().
                        orElse(null)
                            != null;

        Fats[0] = market.getThings(clazz).
                stream().filter(thing -> !Fats[0] && thing.getFats()).
                    findFirst().
                        orElse(null)
                            != null;

        Carbohydrates[0] = market.getThings(clazz).
                stream().filter(thing -> !Carbohydrates[0] && thing.getCarbohydrates()).
                    findFirst().
                        orElse(null)
                            != null;

        if(Proteins[0] && Fats[0] && Carbohydrates[0]) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }

    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }


}
