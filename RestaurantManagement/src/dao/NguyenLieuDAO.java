package dao;

import dto.HoaDonNhapDTO;
import dto.NguyenLieuDTO;
import dto.NguyenLieuTamDTO;
import util.MysqlDatabaseAccessHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NguyenLieuDAO {
	public static ArrayList<NguyenLieuDTO> getDsNguyenLieu(){
		ArrayList<NguyenLieuDTO> nguyenLieuList = new ArrayList<NguyenLieuDTO>();
		String query = "SELECT * FROM nguyenlieu WHERE trangThai = '1'";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				NguyenLieuDTO nguyenLieu = new NguyenLieuDTO();
				
				nguyenLieu.setMaNguyenLieu(rs.getString("maNguyenLieu"));
				nguyenLieu.setTen(rs.getString("ten"));
				nguyenLieu.setLoai(rs.getBoolean("loai"));
				nguyenLieu.setDonViTinh(rs.getString("donViTinh"));
				nguyenLieu.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				nguyenLieu.setSoLuong(rs.getDouble("soLuong"));
				nguyenLieu.setGia(rs.getInt("gia"));
				nguyenLieu.setTrangThai(rs.getBoolean("trangThai"));
				
				nguyenLieuList.add(nguyenLieu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return nguyenLieuList;
	}
	
	public static NguyenLieuDTO getNguyenLieu(String maNguyenLieu) {
		NguyenLieuDTO nguyenLieu = new NguyenLieuDTO();
		String query = "SELECT * FROM nguyenlieu WHERE trangThai = '1' AND maNguyenLieu = '" + maNguyenLieu + "'";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				nguyenLieu.setMaNguyenLieu(rs.getString("maNguyenLieu"));
				nguyenLieu.setTen(rs.getString("ten"));
				nguyenLieu.setLoai(rs.getBoolean("loai"));
				nguyenLieu.setDonViTinh(rs.getString("donViTinh"));
				nguyenLieu.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				nguyenLieu.setSoLuong(rs.getDouble("soLuong"));
				nguyenLieu.setGia(rs.getInt("gia"));
				nguyenLieu.setTrangThai(rs.getBoolean("trangThai"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return nguyenLieu;
	}
	
	public static void updateSoLuong(String maNguyenLieu, double soLuong) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		//get newest nguyenLieuList to use for update database
		ArrayList<NguyenLieuDTO> nguyenLieuList = getDsNguyenLieu();
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		double quantityUpdate = 0;
		
		//execute sum here to avoid double type problem sql
		for(int i = 0; i < nguyenLieuList.size(); i++) {
			if(nguyenLieuList.get(i).getMaNguyenLieu().equals(maNguyenLieu)) {
				quantityUpdate = Double.parseDouble(df.format(nguyenLieuList.get(i).getSoLuong() + soLuong));
				break;
			}
		}
		
		String query = "UPDATE nguyenlieu SET soLuong = '" + quantityUpdate + "' WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void updateMultipleRowSoluong(ArrayList<NguyenLieuDTO> nguyenLieuTamList) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		
		//get newest nguyenLieuList to use for update database
		ArrayList<NguyenLieuDTO> nguyenLieuList = getDsNguyenLieu();
		
		//because we need to loop through nguyenLieuTamList and update, so we create list of query and send to  updateDatabaseMultipleTime
		//function in MysqlDatabaseAccessHelper class
		ArrayList<String> queryList = new ArrayList<String>();
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		for(int i = 0; i < nguyenLieuTamList.size(); i++) {
			for(int j = 0; j < nguyenLieuList.size(); j++) {
				if(nguyenLieuTamList.get(i).getMaNguyenLieu().equals(nguyenLieuList.get(j).getMaNguyenLieu())) {
					String query = "";
					double nguyenLieuNeedToUpate = Double.parseDouble(df.format(nguyenLieuList.get(j).getSoLuong() - nguyenLieuTamList.get(i).getSoLuong()));
					query += "UPDATE nguyenLieu SET soLuong = '" + nguyenLieuNeedToUpate + "'";
					query +=  " WHERE maNguyenLieu = '" + nguyenLieuTamList.get(i).getMaNguyenLieu() + "'; ";
					queryList.add(query);
					break;
				}
			}
		}
		
		myConn.open();
		myConn.updateDatabaseMultipleTime(queryList);
		myConn.close();
		
	}
	
	public static void decreaseSoluong(String maNguyenLieu, double saleQuantity) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE nguyenlieu SET soLuong = soLuong - '" + saleQuantity + "' WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void deleteNguyenLieu(String maNguyenLieu) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE nguyenlieu SET trangThai = '0' WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static int checkAvailableNguyenLieu(String maNguyenLieu) {
		String sql = "SELECT COUNT(*) as nguyenLieuRow FROM nguyenlieu WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		int rowResult = 0;
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				rowResult = rs.getInt("nguyenLieuRow");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return rowResult;
	}
	
	public static void addNewNguyenLieu(NguyenLieuDTO nguyenLieu) {
		String sql = "INSERT INTO nguyenlieu (maNguyenLieu, ten, loai, maNhaCungCap, soLuong, gia, donViTinh) VALUES ";
		sql += "('" + nguyenLieu.getMaNguyenLieu() + "','" + nguyenLieu.getTen() + "','" + 0 + "','";
		sql += nguyenLieu.getMaNhaCungCap() + "','" + nguyenLieu.getSoLuong() + "','" + nguyenLieu.getGia() + "','" + nguyenLieu.getDonViTinh() + "');";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		myConn.open();
		myConn.updateDatabase(sql);
		myConn.close();
	}
	
	public static void updateNguyenLieu(NguyenLieuDTO nguyenLieu) {
		String sql = "UPDATE nguyenlieu SET ten = '" + nguyenLieu.getTen() + "', maNhaCungCap = '" + nguyenLieu.getMaNhaCungCap() + "', ";
		sql += "gia = '" + nguyenLieu.getGia() + "', donViTinh = '" + nguyenLieu.getDonViTinh() + "'";
		sql += " WHERE maNguyenLieu = '" + nguyenLieu.getMaNguyenLieu() + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		myConn.open();
		myConn.updateDatabase(sql);
		myConn.close();
	}
}
