/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import com.model.Food;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author salinthapa
 */
public class FoodModelTable extends AbstractTableModel{
    List<Food> productList = new ArrayList<>();

    private final String[] columnNames = new String[]{
        "Id", "Food name","Category", "price", "Total QTY","Description","Today"
    };
    private final Class[] columnClass = new Class[]{
        Integer.class, String.class, String.class , String.class, String.class,String.class,String.class
    };

    public FoodModelTable(List<Food> productList) {
        this.productList = productList;
    }

    @Override
     public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getRowCount()
    {
        return productList.size();
    }
    @Override
    public int getColumnCount() {
         return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Food row = productList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getName();
        }
        else if(2 == columnIndex) {
             return row.getCategory();
        }
        else if(3 == columnIndex) {
            return row.getPrice();
        }
        else if(4 == columnIndex) {
            return row.getTotalQty();
        }
        else if(5 == columnIndex) {
            return row.getDescription();
        }
        else if(6== columnIndex) {
            
            if(row.isTodaySet()==true){
                return "YES";
            }else{
                return "NO";
            }
        }
        return null;
    }
}
