package dao;

import dto.HoaDonNhapDTO;
import dto.NguyenLieuDTO;
import util.MysqlDatabaseAccessHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		String query = "UPDATE nguyenlieu SET soLuong = '" + soLuong + "' WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void decreaseSoluong(String maNguyenLieu, double saleQuantity) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE nguyenlieu SET soLuong = soLuong - '" + saleQuantity + "' WHERE maNguyenLieu = '" + maNguyenLieu + "';";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
}
