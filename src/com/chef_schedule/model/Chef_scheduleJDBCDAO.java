package com.chef_schedule.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class Chef_scheduleJDBCDAO implements Chef_scheduleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

	private static final String INSERT_STMT = 
		"INSERT INTO chef_schedule (chef_no,chef_scd_mae,chef_scd_cnt,chef_scd_date) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chef_no,chef_scd_mae,chef_scd_cnt,to_char(chef_scd_date,'yyyy-mm-dd') chef_scd_date FROM chef_schedule order by chef_no";
	private static final String GET_ONE_STMT = 
		"SELECT chef_no,chef_scd_mae,chef_scd_cnt,to_char(chef_scd_date,'yyyy-mm-dd') chef_scd_date FROM chef_schedule where chef_no = ?";
	private static final String DELETE = 
		"DELETE FROM chef_schedule where chef_no = ? and chef_scd_mae=? and chef_scd_date=?";
	private static final String UPDATE = 
		"UPDATE chef_schedule set chef_scd_cnt=?  where chef_no = ? and chef_scd_mae=? and chef_scd_date=?";

	@Override
	public void insert(Chef_scheduleVO chef_scheduleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, chef_scheduleVO.getChef_no());
			pstmt.setString(2, chef_scheduleVO.getChef_scd_mae());
			pstmt.setString(3, chef_scheduleVO.getChef_scd_cnt());
			pstmt.setDate(4, chef_scheduleVO.getChef_scd_date());


			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						String key = rs.getString(i);
						System.out.println("自增主鍵值(" + i + ") = " + key +"(剛新增成功的編號)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Chef_scheduleVO chef_scheduleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chef_scheduleVO.getChef_scd_cnt());
			pstmt.setString(2, chef_scheduleVO.getChef_no());
			pstmt.setString(3, chef_scheduleVO.getChef_scd_mae());
			pstmt.setDate(4, chef_scheduleVO.getChef_scd_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String chef_no, Date chef_scd_date, String chef_scd_mae) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
//	public List<Chef_scheduleVO> findByPrimaryKey(String chef_no) {
	public Chef_scheduleVO findByPrimaryKey(String chef_no) {
		
//		List<Chef_scheduleVO> list = new ArrayList<Chef_scheduleVO>();
		Chef_scheduleVO chef_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_scheduleVO 也稱為 Domain objects
				chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(rs.getString("chef_no"));
				chef_scheduleVO.setChef_scd_mae(rs.getString("chef_scd_mae"));
				chef_scheduleVO.setChef_scd_cnt(rs.getString("chef_scd_cnt"));
				chef_scheduleVO.setChef_scd_date(rs.getDate("chef_scd_date"));
//				list.add(chef_scheduleVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
//		return list;
		return chef_scheduleVO;
	}

	@Override
	public List<Chef_scheduleVO> getAll() {
		List<Chef_scheduleVO> list = new ArrayList<Chef_scheduleVO>();
		Chef_scheduleVO chef_scheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_scheduleVO 也稱為 Domain objects
				chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(rs.getString("chef_no"));
				chef_scheduleVO.setChef_scd_mae(rs.getString("chef_scd_mae"));
				chef_scheduleVO.setChef_scd_cnt(rs.getString("chef_scd_cnt"));
				chef_scheduleVO.setChef_scd_date(rs.getDate("chef_scd_date"));
				list.add(chef_scheduleVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Chef_scheduleJDBCDAO dao = new Chef_scheduleJDBCDAO();

		// 新增
//		Chef_scheduleVO chef_scheduleVO1 = new Chef_scheduleVO();
//		chef_scheduleVO1.setChef_no("C00000002");
//		chef_scheduleVO1.setChef_scd_mae("早");
//		chef_scheduleVO1.setChef_scd_cnt("不接案");
//		chef_scheduleVO1.setChef_scd_date(java.sql.Date.valueOf("2017-01-01"));
//		dao.insert(chef_scheduleVO1);

		// 修改
//		Chef_scheduleVO chef_scheduleVO2 = new Chef_scheduleVO();
//		chef_scheduleVO2.setChef_no("C00000001");
//		chef_scheduleVO2.setChef_scd_mae("早");
//		chef_scheduleVO2.setChef_scd_cnt("要去台北看電影");
//		chef_scheduleVO2.setChef_scd_date(java.sql.Date.valueOf("2017-01-01"));
//		dao.update(chef_scheduleVO2);

		// 刪除
		//dao.delete("C00000002");

		// 查詢
//		List<Chef_scheduleVO> list = dao.findByPrimaryKey("C00000001");
//		for (Chef_scheduleVO aChef_schedule : list) {
//			System.out.print(aChef_schedule.getChef_no() + ",");
//			System.out.print(aChef_schedule.getChef_scd_mae() + ",");
//			System.out.print(aChef_schedule.getChef_scd_cnt() + ",");
//			System.out.print(aChef_schedule.getChef_scd_date() + ",");
//
//			System.out.println();
//		}

		// 查詢
		List<Chef_scheduleVO> list = dao.getAll();
		for (Chef_scheduleVO aChef_schedule : list) {
			System.out.print(aChef_schedule.getChef_no() + ",");
			System.out.print(aChef_schedule.getChef_scd_mae() + ",");
			System.out.print(aChef_schedule.getChef_scd_cnt() + ",");
			System.out.print(aChef_schedule.getChef_scd_date() + ",");

			System.out.println();
		}
	}
}

