package com.caps.jsp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class StatementInterFace {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...Step1");
//						String dbUrl="jdbc:mysql://localhost:3307/capsV3_db"
//								+ "?user=root&password=root";

			//3rd Way to get a DB Connection
			String dbUrl="jdbc:mysql://localhost:3307/capsV3_db";
			String filePath = "F:/Files/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			con = DriverManager.getConnection(dbUrl, prop);
			System.out.println("Connected...Step2");
			
			
			
/*			int val = 0;
			System.out.println("Enter a regno: ");
			Scanner in = new Scanner(System.in);
			val = Integer.parseInt(in.nextLine());*/
			System.out.println("Step3... Issue Sql Query");
			String sql = "select * from students_info where sid = 6";//+ val;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("STep4... Process the result");
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
			System.out.println("Step5...Closed All connection");
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
	}//end of main

}