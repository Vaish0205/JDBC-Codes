package com.caps.jsp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class BatchStatement {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			String dbUrl="jdbc:mysql://localhost:3307/capsV3_db";
			String filePath = "F:/Files/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			con = DriverManager.getConnection(dbUrl, prop);
			System.out.println("Connected...");
			
			stmt = con.createStatement();
			
			String insertSQL="insert into students_info values (7,'Vinutha','KJ','N','vinu12')";
			String insertSQL1="insert into students_info values (8,'Anusha','Kashi','N','anu12')";
			stmt.addBatch(insertSQL);
			stmt.addBatch(insertSQL1);
		
			String updateSQL="update students_info set lastname='varma' where sid=9";
			stmt.addBatch(updateSQL);
			
			String deleteSQL="delete from students_info where sid=10";
			stmt.addBatch(deleteSQL);
			
			int[] a = stmt.executeBatch();
			for(int i:a) {
				if(i==1) {
					System.out.println("Successful..!!");
				}else {
					System.out.println("Failed..!!");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
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
			
		}
	}
}
