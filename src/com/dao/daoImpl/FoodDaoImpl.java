package com.dao.daoImpl;

import com.db.DataBaseConnection;
import com.model.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dao.FoodDao;

public class FoodDaoImpl implements FoodDao {

    @Override
    public String saveUpdate(Food food) {
        Connection conn = null;
        int id = food.getId();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (id == 0) {
            boolean existingUser = existingProduct(food.getName(), food.getCategory());
            if (existingUser == true) {
                return food.getName()+ " and " + food.getCategory()+ " category Food is already exist please make unique. ";
            } else {
                try {
                    conn = DataBaseConnection.getInstance().getConnection();
                    String query = "INSERT INTO food(name,category,price,total_quantity,description,created_date,today) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, food.getName().toUpperCase());
                    preparedStmt.setString(2, food.getCategory());
                    preparedStmt.setDouble(3, food.getPrice());
                    preparedStmt.setInt(4, food.getTotalQty());
                    preparedStmt.setString(5, food.getDescription());
                    preparedStmt.setDate(6, date);
                    preparedStmt.setBoolean(7,food.isTodaySet());
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                }
                return food.getName() + " Product Successfully Saved";
            }

        } else {
            try {
                conn = DataBaseConnection.getInstance().getConnection();
                String query = "update food set name=?,category=?,price=?,total_quantity=?,description=?,today=?,updated_date=? where id=?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, food.getName());
                preparedStmt.setString(2, food.getCategory());
                preparedStmt.setDouble(3, food.getPrice());
                preparedStmt.setInt(4, food.getTotalQty());
                preparedStmt.setString(5, food.getDescription());
                preparedStmt.setBoolean(6, food.isTodaySet());
                preparedStmt.setDate(7, date);
                preparedStmt.setInt(8, food.getId());
                preparedStmt.executeUpdate();
                preparedStmt.close();
                System.out.println("Update");
            } catch (Exception e) {
                System.out.println("error:" + e.getMessage());
            }
            return food.getName()+ " food Successfully Updated";
        }
    }

    @Override
    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,name,category,price,total_quantity,description,today FROM food");
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt(1));
                food.setName(rs.getString(2));
                food.setCategory(rs.getString(3));
                food.setPrice(rs.getDouble(4));
                food.setTotalQty(rs.getInt(5));
                food.setDescription(rs.getString(6));
                food.setTodaySet(rs.getBoolean(7));
                foodList.add(food);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return foodList;
    }

    @Override
    public boolean deleteProduct(int productId) {
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "delete from food where id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, productId);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public Food getById(int productId) {
        Connection conn = null;
        Food food = new Food();
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "select id,name,category,price,total_quantity,description,today FROM food where id=? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, productId);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                food.setId(rs.getInt(1));
                food.setName(rs.getString(2));
                food.setCategory(rs.getString(3));
                food.setPrice(rs.getDouble(4));
                food.setTotalQty(rs.getInt(5));
                food.setDescription(rs.getString(6));
                food.setTodaySet(rs.getBoolean(7));
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return food;
        
    }

    @Override
    public boolean existingProduct(String name, String type) {
        Connection conn = null;
        Integer id = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            String query = "SELECT id FROM Food WHERE NAME =? AND CATEGORY=? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name.toUpperCase());
            preparedStmt.setString(2, type);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (id != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Food> getAllProductSearch(String name, String type) {
        List<Food> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,name,category,price,total_quantity,description FROM food where name like '%" + name + "%' and category like '%" + type + "%' ");
            while (rs.next()) {
                Food product = new Food();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setTotalQty(rs.getInt(5));
                product.setDescription(rs.getString(6));
                productList.add(product);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }

    @Override
    public List<String> getProductItem() {
        List<String> foodList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DataBaseConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select id,name,category,price,total_quantity,description FROM food where total_quantity >0 and today =true");
            while (rs.next()) {
                String finalItem = String.valueOf(rs.getInt(1)) + "-" + rs.getString(2) + "(" + rs.getString(3) + ")";
                foodList.add(finalItem);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return foodList;
    }

}
