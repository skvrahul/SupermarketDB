package com.skvrahul.miniproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.database.SQLException;

import com.skvrahul.miniproject.DAO.CustomerDAO;
import com.skvrahul.miniproject.DAO.EmployeeDAO;
import com.skvrahul.miniproject.models.Category;
import com.skvrahul.miniproject.models.Contains;
import com.skvrahul.miniproject.models.Customer;
import com.skvrahul.miniproject.models.Employee;
import com.skvrahul.miniproject.models.Inventory;
import com.skvrahul.miniproject.models.Invoice;
import com.skvrahul.miniproject.DAO.*;

/**
 * Created by skvrahul on 30/3/18.
 */
@Database(entities = {Employee.class, Customer.class, Invoice.class, Inventory.class, Contains.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();

    public abstract CustomerDAO customerDAO();

    public abstract InventoryDAO inventoryDAO();

    public abstract ContainsDAO containsDAO();

    public abstract CategoryDAO categoryDAO();

    public abstract InvoiceDAO invoiceDAO();

    public boolean execQuery(String query) {
        SupportSQLiteDatabase sqliteDB = this.getOpenHelper().getWritableDatabase();
        try {
            sqliteDB.execSQL(query);
            return true;
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }
}
