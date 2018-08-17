package com.caps.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class MyFirstJDBCProgramV1 {
		public static void main(String[] args) {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				java.sql.Driver driverRef = new Driver();
				DriverManager.registerDriver(driverRef);
				System.out.println("Driver Loaded...");
				String dbUrl="jdbc:mysql://localhost:3307/capsV3_db"
								+ "?user=root&password=root";
				con = DriverManager.getConnection(dbUrl);
				System.out.println("Connected...");
				
				String sql = "select * from students_info";

				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()){
					int regno = rs.getInt("sid");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					String isAdmin = rs.getString("isadmin");
					String passwd = rs.getString("password");

					System.out.println(regno);
					System.out.println(firstname);
					System.out.println(lastname);
					System.out.println(isAdmin);
					System.out.println(passwd);
					System.out.println("*********************");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally{

				if(con != null){
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(stmt != null){
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		}
}
