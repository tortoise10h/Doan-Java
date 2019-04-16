package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import util.MysqlDatabaseAccessHelper;
import dto.ChiTietHoaDonXuatDTO;

public class ChiTietHoaDonXuatDAO {
	public static void saveChiTietHoaDonXuat(ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO chitiethoadonxuat (maMon,maHoaDonXuat,donGia,soLuong,thanhTien) VALUES ";
		for(int i = 0; i < chiTietHoaDonXuatList.size(); i++) {
			query += "(" + "'" + chiTietHoaDonXuatList.get(i).getMaMon() + "'" + ",";
			query += "'" + chiTietHoaDonXuatList.get(i).getMaHoaDonXuat() + "'" + ",";
			query += "'" + chiTietHoaDonXuatList.get(i).getDonGia() + "'" + ",";
			query += "'" + chiTietHoaDonXuatList.get(i).getSoLuong() + "'" + ",";
			query += "'" + chiTietHoaDonXuatList.get(i).getThanhTien() + "'" + ")";
			
			if(i == (chiTietHoaDonXuatList.size() - 1)) { //that's mean the last element
				query += ";";
			}else {
				query += ",";
			}
 		}
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void deleteExportBillDetail(int maHoaDonXuat) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE chitiethoadonxuat SET trangThai = '0' WHERE maHoaDonXuat = " + "'" + maHoaDonXuat + "'";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static ArrayList<ChiTietHoaDonXuatDTO> getDsChiTietHoaDonXuat(){
		ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList = new ArrayList<ChiTietHoaDonXuatDTO>();
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "SELECT * FROM chitiethoadonxuat WHERE trangThai = '1'";
		myConn.open();
		ResultSet rs = myConn.executeQuery(query);
		try {
			while(rs.next()) {
				ChiTietHoaDonXuatDTO chiTietHoaDonXuat = new ChiTietHoaDonXuatDTO();
				chiTietHoaDonXuat.setMaHoaDonXuat(rs.getInt("maHoaDonXuat"));
				chiTietHoaDonXuat.setMaMon(rs.getString("maMon"));
				chiTietHoaDonXuat.setDonGia(rs.getInt("donGia"));
				chiTietHoaDonXuat.setSoLuong(rs.getInt("soLuong"));
				chiTietHoaDonXuat.setThanhTien(rs.getInt("thanhTien"));
				
				chiTietHoaDonXuatList.add(chiTietHoaDonXuat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return chiTietHoaDonXuatList;
	}
}
