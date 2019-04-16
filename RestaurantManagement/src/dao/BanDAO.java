package dao;

import dto.BanDTO;
import util.MysqlDatabaseAccessHelper;
import java.sql.*;
import java.util.ArrayList;

public class BanDAO {
	public static ArrayList<BanDTO> tableAll(){
		ArrayList<BanDTO> tableArr = new ArrayList<BanDTO>();
		String sql = "SELECT * FROM  ban";
		MysqlDatabaseAccessHelper myConn = new MysqlDatabaseAccessHelper();
		try {
			myConn.open();
			ResultSet rs = myConn.executeQuery(sql);
			while(rs.next()) {
				BanDTO table = new BanDTO();
				table.setmaBan(rs.getString(1));
				table.setmaKhuVuc(rs.getString(2));
				tableArr.add(table);
			}
		} catch (SQLException e) {
			myConn.displayError(e);
		} finally {
			myConn.close();
		}
		return tableArr;
	}
}
