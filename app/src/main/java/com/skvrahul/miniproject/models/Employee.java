package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

/**
 * Created by skvrahul on 30/3/18.
 */
@Entity
public class Employee {
    @PrimaryKey
    private int e_id;

    @ColumnInfo(name = "start_date")
    private String startDate;
    @ColumnInfo(name = "phone_number")
    private String phoneNum;
    @ColumnInfo(name = "e_name")
    private String empName;
    @ColumnInfo(name = "salary")
    private double salary;

    public Employee(){

    }
    public Employee(String name){
        this.empName = name;
    }
    public Employee(String name, int emp_id){
        this.empName = name;
        this.e_id  = emp_id;

    }
    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
