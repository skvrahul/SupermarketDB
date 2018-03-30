package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.skvrahul.miniproject.models.Employee;

import java.util.List;

/**
 * Created by skvrahul on 30/3/18.
 */
@Dao
public interface EmployeeDAO {
    @Insert
    long insert(Employee employee);

    @Insert
    void insertAll(Employee...employees);

    @Query("SELECT * FROM employee")
    List<Employee> getAllEmployees();

    @Query("SELECT * FROM employee where e_id = :eid")
    List<Employee> getEmployee(int eid);

}
