package com.chef_refund.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Chef_refundDAO implements Chef_refundDAO_interface {

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
		"INSERT INTO chef_refund (chef_rfd_no,chef_ord_no,emp_no,chef_rfd_per,mem_bnk,mem_bnk_ac,chef_ord_chk_cond,chef_ord_red_cnt) VALUES ('CR' || lpad(chef_refund_seq.NEXTVAL,7,0), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chef_rfd_no,chef_ord_no,emp_no,chef_rfd_per,mem_bnk,mem_bnk_ac,chef_ord_chk_cond,chef_ord_red_cnt FROM chef_refund order by chef_rfd_no";
	private static final String GET_ONE_STMT = 
		"SELECT chef_rfd_no,chef_ord_no,emp_no,chef_rfd_per,mem_bnk,mem_bnk_ac,chef_ord_chk_cond,chef_ord_red_cnt FROM chef_refund where chef_rfd_no = ?";
	private static final String DELETE = 
		"DELETE FROM chef_refund where chef_rfd_no = ?";
	private static final String UPDATE = 
		"UPDATE chef_refund set chef_ord_no=?, emp_no=?, chef_rfd_per=?, mem_bnk=?, mem_bnk_ac=?, chef_ord_chk_cond=?, chef_ord_red_cnt=? where chef_rfd_no = ?";
	private static final String UPDATE_BACK = 
		"UPDATE chef_refund set chef_rfd_per=?, chef_ord_chk_cond=?, chef_ord_red_cnt=? where chef_rfd_no = ?";

	@Override
	public void insert(Chef_refundVO chef_refundVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chef_refundVO.getChef_ord_no());
			pstmt.setString(2, chef_refundVO.getEmp_no());
			pstmt.setDouble(3, chef_refundVO.getChef_rfd_per());
			pstmt.setString(4, chef_refundVO.getMem_bnk());
			pstmt.setString(5, chef_refundVO.getMem_bnk_ac());
			pstmt.setString(6, chef_refundVO.getChef_ord_chk_cond());
			pstmt.setString(7, chef_refundVO.getChef_ord_red_cnt());

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
	public void update(Chef_refundVO chef_refundVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chef_refundVO.getChef_ord_no());
			pstmt.setString(2, chef_refundVO.getEmp_no());
			pstmt.setDouble(3, chef_refundVO.getChef_rfd_per());
			pstmt.setString(4, chef_refundVO.getMem_bnk());
			pstmt.setString(5, chef_refundVO.getMem_bnk_ac());
			pstmt.setString(6, chef_refundVO.getChef_ord_chk_cond());
			pstmt.setString(7, chef_refundVO.getChef_ord_red_cnt());
			pstmt.setString(8, chef_refundVO.getChef_rfd_no());

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
	public void update_back(Chef_refundVO chef_refundVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BACK);

			pstmt.setDouble(1, chef_refundVO.getChef_rfd_per());
			pstmt.setString(2, chef_refundVO.getChef_ord_chk_cond());
			pstmt.setString(3, chef_refundVO.getChef_ord_red_cnt());
			pstmt.setString(4, chef_refundVO.getChef_rfd_no());

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
	public void delete(String chef_rfd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_rfd_no);

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
	public Chef_refundVO findByPrimaryKey(String chef_rfd_no) {

		Chef_refundVO chef_refundVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_rfd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				chef_refundVO = new Chef_refundVO();
				chef_refundVO.setChef_rfd_no(rs.getString("chef_rfd_no"));
				chef_refundVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_refundVO.setEmp_no(rs.getString("emp_no"));
				chef_refundVO.setChef_rfd_per(rs.getDouble("chef_rfd_per"));
				chef_refundVO.setMem_bnk(rs.getString("mem_bnk"));
				chef_refundVO.setMem_bnk_ac(rs.getString("mem_bnk_ac"));
				chef_refundVO.setChef_ord_chk_cond(rs.getString("chef_ord_chk_cond"));
				chef_refundVO.setChef_ord_red_cnt(rs.getString("chef_ord_red_cnt"));
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
		return chef_refundVO;
	}

	@Override
	public List<Chef_refundVO> getAll() {
		List<Chef_refundVO> list = new ArrayList<Chef_refundVO>();
		Chef_refundVO chef_refundVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_refundVO 也稱為 Domain objects
				chef_refundVO = new Chef_refundVO();
				chef_refundVO.setChef_rfd_no(rs.getString("chef_rfd_no"));
				chef_refundVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_refundVO.setEmp_no(rs.getString("emp_no"));
				chef_refundVO.setChef_rfd_per(rs.getDouble("chef_rfd_per"));
				chef_refundVO.setMem_bnk(rs.getString("mem_bnk"));
				chef_refundVO.setMem_bnk_ac(rs.getString("mem_bnk_ac"));
				chef_refundVO.setChef_ord_chk_cond(rs.getString("chef_ord_chk_cond"));
				chef_refundVO.setChef_ord_red_cnt(rs.getString("chef_ord_red_cnt"));
				list.add(chef_refundVO); // Store the row in the list
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