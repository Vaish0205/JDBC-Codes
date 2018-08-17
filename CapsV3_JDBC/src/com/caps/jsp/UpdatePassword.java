package com.caps.jsp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class UpdatePassword {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the regno: ");
		int sid = Integer.parseInt(in.nextLine());
		System.out.println("Enter the old Password: ");
		String oldP = in.nextLine();
		System.out.println("Enter a New Password: ");
		String newP = in.nextLine();
		System.out.println("Enter a New Password Again: ");
		String reNewP = in.nextLine();
		
		in.close();
		if(!newP.equals(reNewP))
			throw new PasswordDintMatchException("New Password not matching");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
			
			String sql = "update students_info set password=? where sid=? and password=?";
			int count = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newP);
			pstmt.setInt(2,sid);
			pstmt.setString(3, oldP);
			
			count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println("Password Updated...");
			}else {
				System.out.println("Password Updation Failed");
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
			if(pstmt != null){
				try {
					pstmt.close();
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