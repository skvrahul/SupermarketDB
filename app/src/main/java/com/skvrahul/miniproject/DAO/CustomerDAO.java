package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.skvrahul.miniproject.models.Customer;

import java.util.List;

/**
 * Created by pavan on 31/3/18.
 */
@Dao
public interface CustomerDAO {
    @Insert
    long insert(Customer customer);

    @Insert
    void insertAll(Customer... customer);

    @Query("SELECT * FROM customer")
    List<Customer> getAllCustomers();

    @Query("SELECT * FROM customer where c_id = :cid")
    List<Customer> getCustomer(int cid);

}
