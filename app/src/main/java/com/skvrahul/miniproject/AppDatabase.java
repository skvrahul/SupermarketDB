package com.skvrahul.miniproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.skvrahul.miniproject.DAO.EmployeeDAO;
import com.skvrahul.miniproject.models.Employee;

/**
 * Created by skvrahul on 30/3/18.
 */
@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();
}
