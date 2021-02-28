
package com.model;
public class Food {
    private int id;
    private String name;
    private String category;
    private Double price;
    private int totalQty;
    private String description;
    boolean todaySet;

    public Food(String name, String category, Double price, int totalQty, String description, boolean todaySet) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.totalQty = totalQty;
        this.description = description;
        this.todaySet = todaySet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTodaySet() {
        return todaySet;
    }

    public void setTodaySet(boolean todaySet) {
        this.todaySet = todaySet;
    }

   public Food(){
        
    }
}
