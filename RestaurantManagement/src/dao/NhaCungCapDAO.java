package dao;

import dto.HoaDonNhapDTO;
import dto.NhaCungCapDTO;
import util.MysqlDatabaseAccessHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NhaCungCapDAO {
	public static ArrayList<NhaCungCapDTO> getDsNhaCungCap(){
		ArrayList<NhaCungCapDTO> nhaCungCapList = new ArrayList<NhaCungCapDTO>();
		String query = "SELECT * FROM nhacungcap WHERE trangThai = '1'";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				NhaCungCapDTO nhaCungCap = new NhaCungCapDTO();
				nhaCungCap.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				nhaCungCap.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
				nhaCungCap.setTrangThai(rs.getBoolean("trangThai"));
				nhaCungCapList.add(nhaCungCap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return nhaCungCapList;
	}
	
	public static NhaCungCapDTO getNhaCungCap(String maNhaCungCap) {
		NhaCungCapDTO nhaCungCap = new NhaCungCapDTO();
		String query = "SELECT * FROM nhacungcap WHERE trangThai = '1' AND maNhaCungCap = '" + maNhaCungCap + "';";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(query);
			while(rs.next()) {
				nhaCungCap.setMaNhaCungCap(rs.getString("maNhaCungCap"));
				nhaCungCap.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
				nhaCungCap.setTrangThai(rs.getBoolean("trangThai"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myConn.close();
		}
		
		return nhaCungCap;
	}
}
