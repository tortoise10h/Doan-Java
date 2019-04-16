package util;

import java.sql.*;
public class MysqlDatabaseAccessHelper {
	public Connection conn = null;
	
	public MysqlDatabaseAccessHelper() {}
	
	//handle exception when connect to database
	public void displayError(SQLException ex){ 
	   System.out.println(" Error Message:" + ex.getMessage()); 
	   System.out.println(" SQL State:" + ex.getSQLState()); 
	   System.out.println(" Error Code:" + ex.getErrorCode()); 
    } 

	
	//open connect to database
	public void open() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/restaurantproject?useUnicode=true&characterEncoding=UTF-8&user=root&password=tanhuydb99";
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				displayError(e);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//close connect to database
	public void close() {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			displayError(e);
		}
		
		
	}
	
	public ResultSet executeQuery(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	
		}catch(SQLException e) {
			displayError(e);
		}
		return rs;
	}
	
	public void updateDatabase(String query) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
