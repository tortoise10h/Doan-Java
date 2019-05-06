/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.DAO_Employee;
import dto.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class BUS_Employee {

    private ArrayList<Employee> dsnv = new ArrayList<>();

    public BUS_Employee() {

    }

    public ArrayList<Employee> fecthDatabase() {
        DAO_Employee daoEmployee = new DAO_Employee();
        return daoEmployee.fecthDatabase();
    }

    public ArrayList<Employee> filterTable(String search, String follow, int gender, Date date_work) {
        dsnv = fecthDatabase();
        ArrayList<Employee> ds1 = new ArrayList<>();
        ArrayList<Employee> ds2 = new ArrayList<>();
        ArrayList<Employee> ds3 = new ArrayList<>();
//        System.out.println(search);
        if (follow.equals("Tất cả")) {
            for (int i = 0; i < dsnv.size(); i++) {
                if (dsnv.get(i).getFirstName().toLowerCase().contains(search.toLowerCase()) || dsnv.get(i).getLastName().toLowerCase().contains(search.toLowerCase()) || dsnv.get(i).getId().contains(search)) {
                    ds1.add(dsnv.get(i));
                }
            }
        } else if (follow.equals("Mã")) {
            for (int i = 0; i < dsnv.size(); i++) {
                if (dsnv.get(i).getId().contains(search)) {
                    ds1.add(dsnv.get(i));
                }
            }
        } else if (follow.equals("Tên nhân viên")) {
            for (int i = 0; i < dsnv.size(); i++) {
                if (dsnv.get(i).getFirstName().toLowerCase().contains(search.toLowerCase()) || dsnv.get(i).getLastName().toLowerCase().contains(search.toLowerCase())) {
                    ds1.add(dsnv.get(i));
                }
            }
        }
        // ra duoc danh sach 1 da duoc loc gio loc tiep tu danh sach 1
        if (gender == -1) {
            ds2 = ds1;
        }

        for (int i = 0; i < ds1.size(); i++) {
            if (ds1.get(i).getSex() == gender) {
                ds2.add(ds1.get(i));
            }
        }
        // loc theo ngay bay gio danh sach 2 dang giu gia tri
        if (date_work != null) {
            for (int i = 0; i < ds2.size(); i++) {
                if (ds2.get(i).getDate_work().equals(date_work)) {
                    ds3.add(ds2.get(i));
                }
            }
        }
        if (date_work == null) {
            ds3 = ds2;
        }
//        for (int i = 0; i < ds3.size(); i++) {
//            System.out.println(ds3.get(i).getFirstName() + " " + ds3.get(i).getLastName());
//        }
        return ds3;
    }

    public void InsertDatabase(Employee e) {
        DAO_Employee daoEmployee = new DAO_Employee();
        daoEmployee.InsertDatabase(e);
    }

    public boolean deleteEmployee(String id) {
        DAO_Employee daoEmployee = new DAO_Employee();
        try {
            return daoEmployee.deleteEmployee(id);
        } catch (SQLException ex) {
            Logger.getLogger(BUS_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editEmployee(Employee e) throws SQLException {
        DAO_Employee daoEmployee = new DAO_Employee();
        return daoEmployee.editEmployee(e);
    }
}
