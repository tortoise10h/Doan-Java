package dao;

import dto.MonDTO;
import util.MysqlDatabaseAccessHelper;

import java.util.*;
import java.sql.*;


public class MonDAO {
	public static ArrayList<MonDTO> monAll(){
		ArrayList<MonDTO> dsMon = new ArrayList<MonDTO>();
		String sql = "SELECT *  FROM mon";
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
}
