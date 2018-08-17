package com.caps.jsp;

 import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 public class BatchPrepareStatement {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		
		Connection con = null;
		PreparedStatement insertPstmt = null;
		PreparedStatement updatePstmt = null;
		PreparedStatement deletePstmt = null;
		
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
			System.out.println();
			System.out.println("********************************");
			
			System.out.println("Insert query");
			String insertSQL = "insert into students_info values(?,?,?,?,?)";
			Scanner in = new Scanner(System.in);
			System.out.println("Enter regno : ");
			int sid= Integer.parseInt(in.nextLine());
			System.out.println("Enter First Name: ");
			String fname = in.nextLine();
			System.out.println("Enter Last Name: ");
			String lname = in.nextLine();
			System.out.println("Enter is Admin: ");
			String isAdmin = in.nextLine();
			System.out.println("Enter Password: ");
			String passwd = in.nextLine();
			insertPstmt = con.prepareStatement(insertSQL);
			
			insertPstmt.setInt(1, sid);
			insertPstmt.setString(2,fname);
			insertPstmt.setString(3, lname);
			insertPstmt.setString(4, isAdmin.toUpperCase());
			insertPstmt.setString(5, passwd);
			insertPstmt.addBatch();
			int[] inCount = insertPstmt.executeBatch();
			for(int i:inCount) {
				if(i==1) {
					System.out.println("Successfully inserted..!!");
				}else {
					System.out.println("Insert Failed..!!");
				}
			}
			System.out.println();
			System.out.println("********************************");
			
			String insertSQL1 = "insert into students_info values(?,?,?,?,?)";
			System.out.println("Enter regno : ");
			int sid3= Integer.parseInt(in.nextLine());
			System.out.println("Enter First Name: ");
			String fname1 = in.nextLine();
			System.out.println("Enter Last Name: ");
			String lname1 = in.nextLine();
			System.out.println("Enter is Admin: ");
			String isAdmin1 = in.nextLine();
			System.out.println("Enter Password: ");
			String passwd1 = in.nextLine();
			insertPstmt = con.prepareStatement(insertSQL1);
			
			insertPstmt.setInt(1, sid3);
			insertPstmt.setString(2,fname1);
			insertPstmt.setString(3, lname1);
			insertPstmt.setString(4, isAdmin1.toUpperCase());
			insertPstmt.setString(5, passwd1);
			insertPstmt.addBatch();
			int[] inCount1 = insertPstmt.executeBatch();
			for(int i:inCount1) {
				if(i==1) {
					System.out.println("Successfully inserted..!!");
				}else {
					System.out.println("Insert Failed..!!");
				}
			}
			System.out.println();
			System.out.println("********************************");
			
			System.out.println("Update Query");
			String updateSQL="update students_info set password=? where sid=? and password=?";
			System.out.println("Enter regno : ");
			int sid1= Integer.parseInt(in.nextLine());
			System.out.println("Enter old Password: ");
			String oldP = in.nextLine();
			System.out.println("Enter a New Password: ");
			String newP = in.nextLine();
			System.out.println("Enter a New Password Again: ");
			String reNewP = in.nextLine();
			
			if(!newP.equals(reNewP))
				throw new PasswordDintMatchException("New Password not matching");
			
			updatePstmt = con.prepareStatement(updateSQL);
			updatePstmt.setString(1, newP);
			updatePstmt.setInt(2,sid1);
			updatePstmt.setString(3, oldP);
			
			updatePstmt.addBatch();
			int[] upCount = updatePstmt.executeBatch();
			for(int i:upCount) {
				if(i==1) {
					System.out.println("Successfully updated..!!");
				}else {
					System.out.println("Update Failed..!!");
				}
			}
			
			System.out.println();
			System.out.println("********************************");
			
			System.out.println("Delete Query");
			String deleteSQL = "delete from students_info where sid=? and password=?";
			System.out.println("Enter a regno: ");
			int sid2 = Integer.parseInt(in.nextLine());
			System.out.println("Enter Password: ");
			String oldP1 = in.nextLine();
			in.close();
			
			deletePstmt = con.prepareStatement(deleteSQL);
			deletePstmt.setInt(1,sid2);
			deletePstmt.setString(2, oldP1);
			
			deletePstmt.addBatch();
			int[] deCount = deletePstmt.executeBatch();
			for(int i:deCount) {
				if(i==1) {
					System.out.println("Successfully deleted..!!");
				}else {
					System.out.println("Delete Failed..!!");
				}
			}
			System.out.println();
			System.out.println("********************************");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			/*
			 * 5. Close all the JDBC Objects
			 */
 			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(insertPstmt != null){
				try {
					insertPstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(updatePstmt != null){
				try {
					updatePstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(deletePstmt != null){
				try {
					updatePstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
 		}
		
	}
}