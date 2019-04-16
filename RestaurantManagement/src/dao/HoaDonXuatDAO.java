package dao;

import util.MysqlDatabaseAccessHelper;
import dto.HoaDonTamDTO;
import dto.HoaDonXuatDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HoaDonXuatDAO {
	public static ArrayList<HoaDonXuatDTO> getDsHoaDonXuat(){
		ArrayList<HoaDonXuatDTO> hdxList = new ArrayList<HoaDonXuatDTO>();
		String query = "SELECT * FROM hoadonxuat WHERE trangThai = '1'";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				HoaDonXuatDTO hdx = new HoaDonXuatDTO();
				hdx.setMaHoaDon(rs.getInt("maHoaDon"));
				try {
					hdx.setGioNhap(dateFormat.parse(rs.getString("gioNhap")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					hdx.setGioXuat(dateFormat.parse(rs.getString("gioXuat")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				hdx.setMaBan(rs.getString("maBan"));
				hdx.setMaNv(rs.getString("maNv"));
				hdx.setTongTien(rs.getInt("tongTien"));
				hdx.setTrangThai(rs.getBoolean("trangThai"));
				hdxList.add(hdx);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return hdxList;
	}
	
	public static void deleteExportBill(int maHoaDon) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE hoadonxuat SET trangThai = '0' WHERE maHoaDon = " + "'" + maHoaDon + "'";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void saveExportBill(HoaDonTamDTO hoaDonTam, String maBan, String maNv, String gioXuat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO hoadonxuat (gioNhap,gioXuat,maBan,maNv,tongTien) VALUES ";
		query += "(" + "'" + dateFormat.format(hoaDonTam.getGioVao()) + "'" + ",";
		query += "'" + gioXuat + "'" + ",";
		query += "'" + maBan + "'" + ",";
		query += "'" + maNv + "'" + ",";
		query += "'" + hoaDonTam.getTongTien() + "'" + ");";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
}
