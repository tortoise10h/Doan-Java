/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAO_Employee {

    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pre;

    final String username = "root";
    final String password = "";
    final String server = "jdbc:mysql://localhost:8080/restaurantproject";

    public DAO_Employee() {

    }

    public void getConnection() {
        try {
			String url = "jdbc:mysql://localhost/restaurantproject?useUnicode=true&characterEncoding=UTF-8&user=root&password=tanhuydb99";
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Get connection success");
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Employee> fecthDatabase() {
        getConnection();
        ArrayList<Employee> dsnv = new ArrayList<>();
        if (conn != null) {
            try {
                String sql = "select * from employee";
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String id = rs.getString("id");
                    String img = rs.getString("img");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    Date dob = rs.getDate("dob");
                    int gender = rs.getInt("sex");
                    Date date_work = null;
                    date_work = rs.getDate("date_work");
                    int salary = rs.getInt("salary");
                    Employee e = new Employee(id, img, firstname, lastname, dob, date_work, gender, salary);
                    dsnv.add(e);
                }
                return dsnv;
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        closeConnection();
        return null;
    }

    public void InsertDatabase(Employee e) {
        getConnection();
        String sql = "insert into employee(img,firstname,lastname,dob,sex,date_work,salary) values (?,?,?,?,?,?,?)";
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, e.getImg());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getLastName());
            pre.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(e.getDob()));
            pre.setString(5, String.valueOf(e.getSex()));
            pre.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(e.getDate_work()));
            pre.setString(7, String.valueOf(e.getSalary()));
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeConnection();
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean deleteEmployee(String id) throws SQLException {
        String sql = "delete from employee where id = ?";
        getConnection();

        pre = conn.prepareStatement(sql);
        pre.setString(1, id);
        if (pre.executeUpdate() > 0) {
            return true;
        }
        closeConnection();
        return false;
    }

    public boolean editEmployee(Employee e) throws SQLException {
        getConnection();

        String sql = "update employee set img=?,firstname=?,lastname=?,dob=?,sex=?,date_work=?,salary=? where id = ?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, e.getImg());
        pre.setString(2, e.getFirstName());
        pre.setString(3, e.getLastName());
        pre.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(e.getDob()));
        pre.setString(5, String.valueOf(e.getSex()));
        pre.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(e.getDate_work()));
        pre.setString(7, String.valueOf(e.getSalary()));
        pre.setString(8, e.getId());
        //System.out.println(pre);
        if (pre.executeUpdate() > 0) {
            return true;
        }
//        System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getId() + " " + e.getDate_work() + " " + e.getDob() + " " + e.getSalary() + " " + e.getSex());

        closeConnection();
        return false;
    }

    public static void main(String[] args) {
        DAO_Employee d = new DAO_Employee();
        System.out.println("`12");
        d.getConnection();
    }
}
