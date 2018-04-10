package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.skvrahul.miniproject.models.Category;

import java.util.List;

/**
 * Created by pavan on 31/3/18.
 */
@Dao
public interface CategoryDAO {
    @Insert
    long insert(Category category);

    @Insert
    void insertAll(Category... categories);

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM category where cat_id = :catId")
    List<Category> getCategory(int catId);

    @Query("DELETE FROM category where cat_id=:catId")
    void delCategory(int catId);

}
