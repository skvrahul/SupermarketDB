package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.skvrahul.miniproject.models.Contains;
import com.skvrahul.miniproject.models.Inventory;
import com.skvrahul.miniproject.models.Invoice;

import java.util.List;

/**
 * Created by pavan on 31/3/18.
 */
@Dao
public interface InvoiceDAO {
    @Insert
    long insert(Invoice invoice);

    @Query("Select max(invoice_id) from invoice")
    int getMaxID();



}
