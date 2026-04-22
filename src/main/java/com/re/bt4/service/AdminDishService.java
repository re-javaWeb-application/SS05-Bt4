package com.re.bt4.service;

import com.re.bt4.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDishService {
    private final List<Dish> dishes = new ArrayList<>();

    public AdminDishService() {
        dishes.add(new Dish(1, "Dish-01", 10.00, true));
        dishes.add(new Dish(2, "Dish-02", 20.00, false));
        dishes.add(new Dish(3, "Dish-03", 30.00, true));
        dishes.add(new Dish(4, "Dish-04", 40.00, false));
    }

    public List<Dish> findAll() {
        return dishes;
    }

    public Optional<Dish> findById(int id) {
        return dishes.stream()
                .filter(dish -> dish.getId() == id)
                .findFirst();
    }

    public void updateDish(int id, Dish updatedDish) {
        findById(id).ifPresent(dish -> {
            dish.setName(updatedDish.getName());
            dish.setPrice(updatedDish.getPrice());
            dish.setAvailable(updatedDish.isAvailable());
        });
    }
}
