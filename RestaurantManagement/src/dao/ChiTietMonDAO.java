package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ChiTietMonDTO;
import dto.NguyenLieuDTO;
import util.MysqlDatabaseAccessHelper;

public class ChiTietMonDAO {
	
	public static ArrayList<ChiTietMonDTO> getDsChiTietMon(){
		ArrayList<ChiTietMonDTO> chiTietMonList = new ArrayList<ChiTietMonDTO>();
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "SELECT * FROM chitietmon WHERE trangThai = '1'";
		myConn.open();
		ResultSet rs = myConn.executeQuery(query);
		try {
			while(rs.next()) {
				ChiTietMonDTO chiTietMon = new ChiTietMonDTO();
				chiTietMon.setMaMon(rs.getString("maMon"));
				chiTietMon.setMaNguyenLieu(rs.getString("maNguyenLieu"));
				chiTietMon.setSoNguyenLieu(rs.getDouble("soNguyenLieu"));
				chiTietMon.setTrangThai(rs.getBoolean("trangThai"));
				
				chiTietMonList.add(chiTietMon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return chiTietMonList;
	}
	
	public static void addChiTietMonOfMon(ArrayList<ChiTietMonDTO> chiTietMonList) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO chitietmon (maMon, maNguyenLieu, soNguyenLieu) VALUES ";
		for(int i = 0; i < chiTietMonList.size(); i++) {
			query += "(" + "'" + chiTietMonList.get(i).getMaMon() + "'" + ",";
			query += "'" + chiTietMonList.get(i).getMaNguyenLieu() + "'" + ",";
			query += "'" + chiTietMonList.get(i).getSoNguyenLieu() + "'" + ")";
			if(i == (chiTietMonList.size() - 1)) { //that's mean the last element
				query += ";";
			}else {
				query += ",";
			}
		}
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void updateMultipleChiTietMon(ArrayList<ChiTietMonDTO> chiTietMonList) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		ArrayList<String> queryList = new ArrayList<String>();
		
		for(int i = 0; i < chiTietMonList.size(); i++) {
			String query = "UPDATE chiTietMon SET soNguyenLieu = '" + chiTietMonList.get(i).getSoNguyenLieu() + "' ";
			query += "WHERE maMon = '" + chiTietMonList.get(i).getMaMon() + "' AND maNguyenLieu = '" + chiTietMonList.get(i).getMaNguyenLieu() + "';";
			queryList.add(query);
		}
		
		myConn.open();
		myConn.updateDatabaseMultipleTime(queryList);
		myConn.close();
	}
	
	public static ArrayList<ChiTietMonDTO> getChiTietOfMon(String maMon){
		ArrayList<ChiTietMonDTO> chiTietMonList = new ArrayList<ChiTietMonDTO>();
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "SELECT * FROM chitietmon WHERE trangThai = '1' AND maMon = '" + maMon + "';";
		myConn.open();
		ResultSet rs = myConn.executeQuery(query);
		try {
			while(rs.next()) {
				ChiTietMonDTO chiTietMon = new ChiTietMonDTO();
				chiTietMon.setMaMon(rs.getString("maMon"));
				chiTietMon.setMaNguyenLieu(rs.getString("maNguyenLieu"));
				chiTietMon.setSoNguyenLieu(rs.getDouble("soNguyenLieu"));
				chiTietMon.setTrangThai(rs.getBoolean("trangThai"));
				
				chiTietMonList.add(chiTietMon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return chiTietMonList;
	}
	
	public static void deleteParamentlyChiTietMonByMaMon(String maMon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		String query = "DELETE FROM chitietmon WHERE maMon = '" + maMon + "';";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void deleteChiTietMonByMaMon(String maMon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		String query = "UPDATE chitietmon SET trangThai = '0' WHERE maMon = '" + maMon + "';";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static int countRowOfNguyenLieu(String maNguyenLieu) {
		String sql = "SELECT COUNT(*) as nguyenLieuCol FROM chitietmon WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		int rowResult = 0;
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				rowResult = rs.getInt("nguyenLieuCol");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return rowResult;
	}
}
