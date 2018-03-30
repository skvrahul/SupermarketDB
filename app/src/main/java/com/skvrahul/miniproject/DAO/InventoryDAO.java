package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.skvrahul.miniproject.models.Inventory;

import java.util.List;

/**
 * Created by pavan on 31/3/18.
 */
@Dao
public interface InventoryDAO {
    @Insert
    long insert(Inventory inventory);

    @Insert
    void insertAll(Inventory... inventories);

    @Query("SELECT * FROM inventory")
    List<Inventory> getAllInventories();

    @Query("SELECT * FROM inventory where item_id = :itemId")
    List<Inventory> getInventory(int itemId);

}
