package com.dao;

import com.model.Food;
import java.util.List;

public interface FoodDao {

    String saveUpdate(Food food);

    List<Food> getAllFood();
    
    List<String> getProductItem();

    List<Food> getAllProductSearch(String name, String type);

    boolean deleteProduct(int productId);

    Food getById(int productId);

    boolean existingProduct(String name, String type);
    

}
