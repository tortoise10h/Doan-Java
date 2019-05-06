package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dto.HoaDonNhapDTO;
import util.MysqlDatabaseAccessHelper;

public class HoaDonNhapDAO {
	public static ArrayList<HoaDonNhapDTO> getDsHoaDonNhap(){
		ArrayList<HoaDonNhapDTO> hdnList = new ArrayList<HoaDonNhapDTO>();
		String query = "SELECT * FROM hoadonnhap WHERE trangThai = '1'";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				HoaDonNhapDTO hoaDonNhap = new HoaDonNhapDTO();
				hoaDonNhap.setMaHoaDonNhap(rs.getInt("maHoaDonNhap"));
				try {
					hoaDonNhap.setGioXuat(dateFormat.parse(rs.getString("gioXuat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				hoaDonNhap.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				hoaDonNhap.setNhanVien(rs.getString("nhanVien"));
				hoaDonNhap.setTongTien(rs.getInt("tongTien"));
				hoaDonNhap.setTrangThai(rs.getBoolean("trangThai"));
				hdnList.add(hoaDonNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return hdnList;
	}
	
	public static void deleteImportBill(int maHoaDon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE hoadonnhap SET trangThai = '0' WHERE maHoaDonNhap = " + "'" + maHoaDon + "'";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void saveImportBill(HoaDonNhapDTO hoaDonNhap, String gioXuatStr) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO hoadonnhap (gioXuat,nhanVien,maNhaCungCap,tongTien) VALUES ";
		query += "(" + "'" + gioXuatStr + "'" + ",";
		query += "'" + hoaDonNhap.getNhanVien() + "'" + ",";
		query += "'" + hoaDonNhap.getMaNhaCungCap() + "'" + ",";
		query += "'" + hoaDonNhap.getTongTien() + "'" + ");";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static HoaDonNhapDTO getHoaDonNhap(int maHoaDonNhap) {
		String query = "SELECT * FROM hoadonnhap WHERE trangThai = '1' AND maHoaDonNhap = '" + maHoaDonNhap + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HoaDonNhapDTO hoaDonNhap = new HoaDonNhapDTO();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				hoaDonNhap.setMaHoaDonNhap(rs.getInt("maHoaDonNhap"));
				try {
					hoaDonNhap.setGioXuat(dateFormat.parse(rs.getString("gioXuat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				hoaDonNhap.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				hoaDonNhap.setNhanVien(rs.getString("nhanVien"));
				hoaDonNhap.setTongTien(rs.getInt("tongTien"));
				hoaDonNhap.setTrangThai(rs.getBoolean("trangThai"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return hoaDonNhap;
	}
}
