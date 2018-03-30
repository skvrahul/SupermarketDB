package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;
/**
 * Created by skvrahul on 30/3/18.
 */

@Entity
public class Inventory{
    @PrimaryKey
    private int item_id;

    @ColumnInfo(name = "item_name")
    private String itemName;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "stock")
    private int stock;

    @ForeignKey(entity = Category.class,parentColumns = "cat_id",childColumns = "cat_id", onDelete = CASCADE, onUpdate = CASCADE)
    private int cat_id;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCat_id() {return cat_id;}

    public void setCat_id(int cat_id) {this.cat_id = cat_id;}
}
