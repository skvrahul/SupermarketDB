package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.skvrahul.miniproject.models.Contains;

import java.util.List;

/**
 * Created by pavan on 31/3/18.
 */
@Dao
public interface ContainsDAO {
    @Insert
    long insert(Contains contains);

    @Insert
    void insertAll(Contains ... contains);

    @Query("SELECT * FROM contains")
    List<Contains> getAllContains();

    @Query("SELECT * FROM contains where invoice_id = :invoiceId and item_id = :itemId")
    List<Contains> getContains(int invoiceId,int itemId);

}
