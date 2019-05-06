package dao;

import dto.MonDTO;
import util.MysqlDatabaseAccessHelper;

import java.util.*;
import java.sql.*;


public class MonDAO {
	public static ArrayList<MonDTO> monAll(){
		ArrayList<MonDTO> dsMon = new ArrayList<MonDTO>();
		String sql = "SELECT *  FROM mon WHERE trangThai = '1';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				MonDTO mon = new MonDTO();
				mon.setMaMon(rs.getString("maMon"));
				mon.setMaLoaiMon(rs.getString("maLoaiMon"));
				mon.setTenMon(rs.getString("tenMon"));
				mon.setGia(rs.getInt("gia"));
				mon.setDonViTinh(rs.getString("donViTinh"));
				mon.setImgLink(rs.getString("imgLink"));
				mon.setTrangThai(rs.getBoolean("trangThai"));
				
				dsMon.add(mon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return dsMon;
	}
	
	public static MonDTO getMon(String maMon) {
		String sql = "SELECT * FROM mon WHERE maMon = '" + maMon + "' AND trangThai = '1';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		MonDTO mon = new MonDTO();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				mon.setMaMon(rs.getString("maMon"));
				mon.setMaLoaiMon(rs.getString("maLoaiMon"));
				mon.setTenMon(rs.getString("tenMon"));
				mon.setGia(rs.getInt("gia"));
				mon.setDonViTinh(rs.getString("donViTinh"));
				mon.setImgLink(rs.getString("imgLink"));
				mon.setTrangThai(rs.getBoolean("trangThai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return mon;
	}
	
	public static void updateMon(MonDTO mon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE mon SET maLoaiMon = '" + mon.getMaLoaiMon() + "', ";
		query += "tenMon = '" + mon.getTenMon() + "', gia = '" + mon.getGia() + "', donViTinh = '" + mon.getDonViTinh() + "', ";
		query += "imgLink = '" + mon.getImgLink() + "' WHERE maMon = '" + mon.getMaMon() + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void addNewMon(MonDTO mon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO mon (maMon, maLoaiMon, tenMon, gia, donViTinh, imgLink) VALUES ";
		query += "(" + "'" + mon.getMaMon() + "'" + ",";
		query += "'" + mon.getMaLoaiMon() + "'" + ",";
		query += "'" + mon.getTenMon() + "'" + ",";
		query += "'" + mon.getGia() + "'" + ",";
		query += "'" + mon.getDonViTinh() + "'" + ",";
		query += "'" + mon.getImgLink() + "'" + ");";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void deleteMon(String maMon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		String query = "UPDATE mon SET trangThai = '0' WHERE maMon ='" + maMon + "';";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static int checkAvailableMon(String maMon) {
		String sql = "SELECT COUNT(*) as soMon FROM mon WHERE maMon = '" + maMon + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		int rowResult = 0;
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				rowResult = rs.getInt("soMon");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return rowResult;
	}

	
}
