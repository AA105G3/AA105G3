package com.chef_schedule.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Chef_scheduleJNDIDAO implements Chef_scheduleDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO chef_schedule (chef_no,chef_scd_mae,chef_scd_cnt,chef_scd_date) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chef_no,to_char(chef_scd_date,'yyyy-mm-dd') chef_scd_date,chef_scd_mae,chef_scd_cnt FROM chef_schedule order by chef_no";
	private static final String GET_ONE_STMT = 
		"SELECT chef_no,to_char(chef_scd_date,'yyyy-mm-dd') chef_scd_date,chef_scd_mae,chef_scd_cnt FROM chef_schedule where chef_no = ?";
	private static final String DELETE = 
		"DELETE FROM chef_schedule where chef_no = ? and chef_scd_mae=? and chef_scd_date=?";
	private static final String UPDATE = 
		"UPDATE chef_schedule set chef_scd_date=? where chef_no = ? and chef_scd_mae=? and chef_scd_date=?";

	@Override
	public void insert(Chef_scheduleVO chef_scheduleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chef_scheduleVO.getChef_no());
			pstmt.setString(2, chef_scheduleVO.getChef_scd_mae());
			pstmt.setString(3, chef_scheduleVO.getChef_scd_cnt());
			pstmt.setDate(4, chef_scheduleVO.getChef_scd_date());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chef_scheduleVO.getChef_scd_cnt());
			pstmt.setString(2, chef_scheduleVO.getChef_no());
			pstmt.setString(3, chef_scheduleVO.getChef_scd_mae());
			pstmt.setDate(4, chef_scheduleVO.getChef_scd_date());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Chef_scheduleVO findByPrimaryKey(String chef_no) {

		Chef_scheduleVO chef_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_scheduleVO 也稱為 Domain objects
				chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(rs.getString("chef_no"));
				chef_scheduleVO.setChef_scd_date(rs.getDate("chef_scd_date"));
				chef_scheduleVO.setChef_scd_mae(rs.getString("chef_scd_mae"));
				chef_scheduleVO.setChef_scd_cnt(rs.getString("chef_scd_cnt"));
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_scheduleVO 也稱為 Domain objects
				chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(rs.getString("chef_no"));
				chef_scheduleVO.setChef_scd_date(rs.getDate("chef_scd_date"));
				chef_scheduleVO.setChef_scd_mae(rs.getString("chef_scd_mae"));
				chef_scheduleVO.setChef_scd_cnt(rs.getString("chef_scd_cnt"));
				list.add(chef_scheduleVO); // Store the row in the list
			}

			// Handle any driver errors
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
}