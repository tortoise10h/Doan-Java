package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import dto.ChiTietHoaDonNhapDTO;
import util.MysqlDatabaseAccessHelper;

public class ChiTietHoaDonNhapDAO {
	public static void saveChiTietHoaDonNhap(ChiTietHoaDonNhapDTO chiTietHoaDonNhap) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "INSERT INTO chitiethoadonnhap (maHoaDonNhap,maNguyenLieu,donGia,soLuong,donViTinh,thanhTien) VALUES ";
		
		query += "(" + "'" + chiTietHoaDonNhap.getMaHoaDonNhap() + "'" + ",";
		query += "'" + chiTietHoaDonNhap.getMaNguyenLieu() + "'" + ",";
		query += "'" + chiTietHoaDonNhap.getDonGia() + "'" + ",";
		query += "'" + chiTietHoaDonNhap.getSoLuong() + "'" + ",";
		query += "'" + chiTietHoaDonNhap.getDonViTinh() + "'" + ",";
		query += "'" + chiTietHoaDonNhap.getThanhTien() + "'" + ");";
		
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static void deleteExportBillDetail(int maHoaDonNhap) {
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "UPDATE chitiethoadonnhap SET trangThai = '0' WHERE maHoaDonNhap = " + "'" + maHoaDonNhap + "'";
		myConn.open();
		myConn.updateDatabase(query);
		myConn.close();
	}
	
	public static ArrayList<ChiTietHoaDonNhapDTO> getDsChiTietHoaDonNhap(){
		ArrayList<ChiTietHoaDonNhapDTO> chiTietHoaDonNhapList = new ArrayList<ChiTietHoaDonNhapDTO>();
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		String query = "SELECT * FROM chitiethoadonnhap WHERE trangThai = '1'";
		myConn.open();
		ResultSet rs = myConn.executeQuery(query);
		try {
			while(rs.next()) {
				ChiTietHoaDonNhapDTO chiTietHoaDonNhap = new ChiTietHoaDonNhapDTO();
				chiTietHoaDonNhap.setMaHoaDonNhap(rs.getInt("maHoaDonNhap"));
				chiTietHoaDonNhap.setMaNguyenLieu(rs.getString("maNguyenLieu"));
				chiTietHoaDonNhap.setDonGia(rs.getInt("donGia"));
				chiTietHoaDonNhap.setSoLuong(rs.getDouble("soLuong"));
				chiTietHoaDonNhap.setDonViTinh(rs.getString("donViTinh"));
				chiTietHoaDonNhap.setThanhTien(rs.getInt("thanhTien"));
				
				chiTietHoaDonNhapList.add(chiTietHoaDonNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return chiTietHoaDonNhapList;
	}
}
