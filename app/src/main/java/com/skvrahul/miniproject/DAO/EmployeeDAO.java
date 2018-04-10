package com.skvrahul.miniproject.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch .persistence.room.Delete;

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

    @Query("select max(e_id) from employee")
    int getLastId();

    @Query("SELECT * FROM employee")
    List<Employee> getAllEmployees();

    @Query("SELECT * FROM employee where e_id = :eid")
    Employee getEmployee(int eid);

    @Query("delete from employee where e_id=:eid")
    void delEmployee(int eid);

    @Query("select count(*) from employee")
    int getEmployeeCount();

    @Query("select password from employee where e_id=:eid")
    String getPassword(int eid);





}
