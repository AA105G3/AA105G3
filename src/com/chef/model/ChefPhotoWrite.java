package com.chef.model;

import java.sql.*;
import java.io.*;

public class ChefPhotoWrite {
	
	static {
        try {
             Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
          Connection con = null;
          PreparedStatement pstmt = null;
          String url = "jdbc:oracle:thin:@localhost:1521:XE";
          String userid = "foodtime";
          String passwd = "foodtime";
       	  
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "lic" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_lic=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "chef" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_image=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String video = "video" + i + ".mp4";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("C:\\project_picture\\chef_movie1", video); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_movie1=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String video = "video2nd" + i + ".mp4";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("C:\\project_picture\\chef_movie2", video); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_movie2=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "food1-" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_reci_image1=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "food2-" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_reci_image2=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "food3-" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_reci_image3=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "food4-" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_reci_image4=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
          
for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "food5-" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("WebContent\\images\\chef", photo); //相對路徑- picFrom
                  									 //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Chef set chef_reci_image5=? where chef_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "C0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }        	  
          }
	}

}
