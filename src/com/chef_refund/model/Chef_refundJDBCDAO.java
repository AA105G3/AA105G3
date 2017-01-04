package com.chef_refund.model;

import java.util.*;
import java.sql.*;

public class Chef_refundJDBCDAO implements Chef_refundDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chef_refundVO.getChef_ord_no());
			pstmt.setString(2, chef_refundVO.getEmp_no());
			pstmt.setDouble(3, chef_refundVO.getChef_rfd_per());
			pstmt.setString(4, chef_refundVO.getMem_bnk());
			pstmt.setString(5, chef_refundVO.getMem_bnk_ac());
			pstmt.setString(6, chef_refundVO.getChef_ord_chk_cond());
			pstmt.setString(7, chef_refundVO.getChef_ord_red_cnt());

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
	public void update(Chef_refundVO chef_refundVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update_back(Chef_refundVO chef_refundVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, chef_refundVO.getChef_rfd_per());
			pstmt.setString(2, chef_refundVO.getChef_ord_chk_cond());
			pstmt.setString(3, chef_refundVO.getChef_ord_red_cnt());
			pstmt.setString(4, chef_refundVO.getChef_rfd_no());

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
	public void delete(String chef_rfd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_rfd_no);

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
	public Chef_refundVO findByPrimaryKey(String chef_rfd_no) {

		Chef_refundVO chef_refundVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_rfd_no);

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		Chef_refundJDBCDAO dao = new Chef_refundJDBCDAO();

		// 新增
//		Chef_refundVO chef_refundVO1 = new Chef_refundVO();
//		chef_refundVO1.setChef_ord_no("COL000005");
//		chef_refundVO1.setEmp_no("1001");
//		chef_refundVO1.setChef_rfd_per(new Double(1));
//		chef_refundVO1.setMem_bnk(new String("700"));
//		chef_refundVO1.setMem_bnk_ac(new String("01175841012356"));
//		chef_refundVO1.setChef_ord_chk_cond("2");
//		chef_refundVO1.setChef_ord_red_cnt("因颱風天取消");
//		dao.insert(chef_refundVO1);

		// 修改
//		Chef_refundVO chef_refundVO2 = new Chef_refundVO();
//		chef_refundVO2.setChef_rfd_no("CR0000005");
//		chef_refundVO2.setChef_ord_no("COL000005");
//		chef_refundVO2.setEmp_no("1001");
//		chef_refundVO2.setChef_rfd_per(new Double(0));
//		chef_refundVO2.setMem_bnk(new String("700"));
//		chef_refundVO2.setMem_bnk_ac(new String("02537201863941"));
//		chef_refundVO2.setChef_ord_chk_cond("0");
//		chef_refundVO2.setChef_ord_red_cnt("客戶臨時取消");
//		dao.update(chef_refundVO2);

		// 刪除
//		dao.delete("CR0000005");

		// 查詢
//		Chef_refundVO chef_refundVO3 = dao.findByPrimaryKey("CR0000001");
//		System.out.print(chef_refundVO3.getChef_rfd_no() + ",");
//		System.out.print(chef_refundVO3.getChef_ord_no() + ",");
//		System.out.print(chef_refundVO3.getEmp_no() + ",");
//		System.out.print(chef_refundVO3.getChef_rfd_per() + ",");
//		System.out.print(chef_refundVO3.getMem_bnk() + ",");
//		System.out.print(chef_refundVO3.getMem_bnk_ac() + ",");
//		System.out.print(chef_refundVO3.getChef_ord_chk_cond() + ",");
//		System.out.println(chef_refundVO3.getChef_ord_red_cnt());
//		System.out.println("---------------------");

		// 查詢
		List<Chef_refundVO> list = dao.getAll();
		for (Chef_refundVO aChef_refund : list) {
			System.out.print(aChef_refund.getChef_rfd_no() + ",");
			System.out.print(aChef_refund.getChef_ord_no() + ",");
			System.out.print(aChef_refund.getEmp_no() + ",");
			System.out.print(aChef_refund.getChef_rfd_per() + ",");
			System.out.print(aChef_refund.getMem_bnk() + ",");
			System.out.print(aChef_refund.getMem_bnk_ac() + ",");
			System.out.print(aChef_refund.getChef_ord_chk_cond() + ",");
			System.out.print(aChef_refund.getChef_ord_red_cnt());
			System.out.println();
		}
	}
}