package com.caps.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Driver; 

public class DeleteStudent {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver class loaded and registered...");
			String dbUrl="jdbc:mysql://localhost:3307/capsV3_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connected...");
			String sql = "delete from students_info where sid=?";
			System.out.println("Enter a regno: ");
			Scanner in = new Scanner(System.in);
			int sid = Integer.parseInt(in.nextLine());
			in.close();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,sid);
			int count = pstmt.executeUpdate();

			if(count > 0) {
				System.out.println("Data Deleted...");
			}else {
				System.out.println("No such data found");
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