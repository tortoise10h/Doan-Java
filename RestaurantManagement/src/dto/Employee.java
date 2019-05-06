/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Employee {

    private String id, img, firstName, lastName;
    private Date dob, date_work;
    private int sex, salary;

    public Employee() {
    }

    public Employee(String id, String img, String firstName, String lastName, Date dob, Date date_work, int sex, int salary) {
        this.id = id;
        this.img = img;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.date_work = date_work;
        this.sex = sex;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDate_work() {
        return date_work;
    }

    public void setDate_work(Date date_work) {
        this.date_work = date_work;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
