package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by pavan on 31/3/18.
 */
@Entity
public class Category {
    @PrimaryKey
    private int cat_id;

    @ColumnInfo(name="cat_name")
    private String catName;

    public Category() {}

    public Category(int cat_id, String catName) {
        this.cat_id=cat_id;
        this.catName = catName;
    }
    public int getCat_id(){return cat_id;}
    public void setCat_id(int cat_id) {this.cat_id=cat_id;}

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
