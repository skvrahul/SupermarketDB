package com.skvrahul.miniproject.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by skvrahul on 30/3/18.
 */
@Entity
public class Employee implements Serializable {
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

    @ColumnInfo(name="password")
    private String password="admin";



    public Employee() {

    }

    @Ignore
    public Employee(String name) {

        this.empName = name;
    }

    @Ignore
    public Employee(String name, int emp_id) {
        this.empName = name;
        this.e_id = emp_id;

    }

    @Ignore
    public Employee(String empName,int e_id,String phoneNum) {
        this.empName = empName;
        this.e_id = e_id;
        this.phoneNum = phoneNum;
    }

    @Ignore
    public Employee(String empName, int e_id, String phoneNum, double salary){
        this.empName = empName;
        this.e_id = e_id;
        this.phoneNum = phoneNum;
        this.salary = salary;
        this.password="admin";
    }

    /*public Employee(String name, int emp_id, String startDate, double salary) {
        this.empName = name;
        this.e_id = emp_id;
        this.startDate = startDate;
        this.salary = salary;
    }*

    /*
    public Employee(String name, String startDate, double salary){
        this.empName = name;
        this.e_id  = emp_id;
        this.startDate = startDate;
        this.salary = salary;
    }
    */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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