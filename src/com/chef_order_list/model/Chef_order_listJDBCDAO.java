package com.chef_order_list.model;

import java.util.*;

import com.frd_list.model.Frd_listVO;

import java.sql.*;

public class Chef_order_listJDBCDAO implements Chef_order_listDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

	private static final String INSERT_STMT = 
		"INSERT INTO chef_order_list (chef_ord_no,mem_no,chef_no,chef_ord_cost,chef_act_date,chef_ord_place,chef_ord_cnt,chef_ord_con,chef_ord_date) VALUES ('COL' || lpad(chef_order_list_seq.NEXTVAL,6,0), ?, ?, ?, ?, ?, ?, 0,sysdate)";
	private static final String GET_ALL_STMT = 
		"SELECT chef_ord_no,mem_no,chef_no,chef_ord_cost,chef_act_date,chef_ord_place,chef_ord_cnt,chef_ord_con,chef_appr,chef_appr_cnt,chef_ord_date FROM chef_order_list order by chef_ord_no";
	private static final String GET_ONE_STMT = 
		"SELECT chef_ord_no,mem_no,chef_no,chef_ord_cost,chef_act_date,chef_ord_place,chef_ord_cnt,chef_ord_con,chef_appr,chef_appr_cnt,chef_ord_date FROM chef_order_list where chef_ord_no = ?";
	private static final String DELETE = 
		"DELETE FROM chef_order_list where chef_ord_no = ?";
	private static final String UPDATE = 
		"UPDATE chef_order_list set chef_ord_cost=?, chef_act_date=?, chef_ord_place=?, chef_ord_cnt=?, chef_ord_con=? where chef_ord_no = ?";

//	by cyh
	private static final String GET_ALL_BY_MEM_NO = "select chef_ord_no,mem_no,chef_no,chef_ord_cost,chef_act_date,chef_ord_place,chef_ord_cnt,chef_ord_con,chef_appr,chef_appr_cnt,chef_ord_date from chef_order_list where mem_no = ? order by chef_ord_date desc";
	private static final String GET_ALL_BY_CHEF_NO = "select chef_ord_no,mem_no,chef_no,chef_ord_cost,chef_act_date,chef_ord_place,chef_ord_cnt,chef_ord_con,chef_appr,chef_appr_cnt,chef_ord_date from chef_order_list where chef_no = ?";

	@Override
	public void insert(Chef_order_listVO chef_order_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chef_order_listVO.getMem_no());
			pstmt.setString(2, chef_order_listVO.getChef_no());
			pstmt.setDouble(3, chef_order_listVO.getChef_ord_cost());
			pstmt.setTimestamp(4, chef_order_listVO.getChef_act_date());
			pstmt.setString(5, chef_order_listVO.getChef_ord_place());
			pstmt.setString(6, chef_order_listVO.getChef_ord_cnt());
			

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
	public void update(Chef_order_listVO chef_order_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, chef_order_listVO.getChef_ord_cost());
			pstmt.setTimestamp(2, chef_order_listVO.getChef_act_date());
			pstmt.setString(3, chef_order_listVO.getChef_ord_place());
			pstmt.setString(4, chef_order_listVO.getChef_ord_cnt());
			pstmt.setString(5, chef_order_listVO.getChef_ord_con());
			pstmt.setString(6, chef_order_listVO.getChef_ord_no());

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
	public void delete(String chef_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_ord_no);

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
	public Chef_order_listVO findByPrimaryKey(String chef_ord_no) {

		Chef_order_listVO chef_order_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_order_listVO 也稱為 Domain objects
				chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_order_listVO.setMem_no(rs.getString("mem_no"));
				chef_order_listVO.setChef_no(rs.getString("chef_no"));
				chef_order_listVO.setChef_ord_cost(rs.getDouble("chef_ord_cost"));
				chef_order_listVO.setChef_act_date(rs.getTimestamp("chef_act_date"));
				chef_order_listVO.setChef_ord_place(rs.getString("chef_ord_place"));
				chef_order_listVO.setChef_ord_cnt(rs.getString("chef_ord_cnt"));
				chef_order_listVO.setChef_ord_con(rs.getString("chef_ord_con"));
				chef_order_listVO.setChef_appr(rs.getString("chef_appr"));
				chef_order_listVO.setChef_appr_cnt(rs.getString("chef_appr_cnt"));
				chef_order_listVO.setChef_ord_date(rs.getTimestamp("chef_ord_date"));
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
		return chef_order_listVO;
	}

	@Override
	public List<Chef_order_listVO> getAll() {
		List<Chef_order_listVO> list = new ArrayList<Chef_order_listVO>();
		Chef_order_listVO chef_order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chef_order_listVO 也稱為 Domain objects
				chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_order_listVO.setMem_no(rs.getString("mem_no"));
				chef_order_listVO.setChef_no(rs.getString("chef_no"));
				chef_order_listVO.setChef_ord_cost(rs.getDouble("chef_ord_cost"));
				chef_order_listVO.setChef_act_date(rs.getTimestamp("chef_act_date"));
				chef_order_listVO.setChef_ord_place(rs.getString("chef_ord_place"));
				chef_order_listVO.setChef_ord_cnt(rs.getString("chef_ord_cnt"));
				chef_order_listVO.setChef_ord_con(rs.getString("chef_ord_con"));
				chef_order_listVO.setChef_appr(rs.getString("chef_appr"));
				chef_order_listVO.setChef_appr_cnt(rs.getString("chef_appr_cnt"));
				chef_order_listVO.setChef_ord_date(rs.getTimestamp("chef_ord_date"));
				list.add(chef_order_listVO); // Store the row in the list
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
	
//	by cyh
	@Override
	public List<Chef_order_listVO> findByMem_no(String mem_no) {
		List<Chef_order_listVO> list = new ArrayList<Chef_order_listVO>();
		Chef_order_listVO chef_order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_order_listVO.setMem_no(rs.getString("mem_no"));
				chef_order_listVO.setChef_no(rs.getString("chef_no"));
				chef_order_listVO.setChef_ord_cost(rs.getDouble("chef_ord_cost"));
				chef_order_listVO.setChef_act_date(rs.getTimestamp("chef_act_date"));
				chef_order_listVO.setChef_ord_place(rs.getString("chef_ord_place"));
				chef_order_listVO.setChef_ord_cnt(rs.getString("chef_ord_cnt"));
				chef_order_listVO.setChef_ord_con(rs.getString("chef_ord_con"));
				chef_order_listVO.setChef_appr(rs.getString("chef_appr"));
				chef_order_listVO.setChef_appr_cnt(rs.getString("chef_appr_cnt"));
				chef_order_listVO.setChef_ord_date(rs.getTimestamp("chef_ord_date"));
				list.add(chef_order_listVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Chef_order_listVO> findByChef_no(String chef_no) {
		List<Chef_order_listVO> list = new ArrayList<Chef_order_listVO>();
		Chef_order_listVO chef_order_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_CHEF_NO);
			pstmt.setString(1, chef_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setChef_ord_no(rs.getString("chef_ord_no"));
				chef_order_listVO.setMem_no(rs.getString("mem_no"));
				chef_order_listVO.setChef_no(rs.getString("chef_no"));
				chef_order_listVO.setChef_ord_cost(rs.getDouble("chef_ord_cost"));
				chef_order_listVO.setChef_act_date(rs.getTimestamp("chef_act_date"));
				chef_order_listVO.setChef_ord_place(rs.getString("chef_ord_place"));
				chef_order_listVO.setChef_ord_cnt(rs.getString("chef_ord_cnt"));
				chef_order_listVO.setChef_ord_con(rs.getString("chef_ord_con"));
				chef_order_listVO.setChef_appr(rs.getString("chef_appr"));
				chef_order_listVO.setChef_appr_cnt(rs.getString("chef_appr_cnt"));
				chef_order_listVO.setChef_ord_date(rs.getTimestamp("chef_ord_date"));
				list.add(chef_order_listVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Chef_order_listJDBCDAO dao = new Chef_order_listJDBCDAO();

		// 新增
//		Chef_order_listVO chef_order_listVO1 = new Chef_order_listVO();
//		chef_order_listVO1.setMem_no("M00000001");
//		chef_order_listVO1.setChef_no("C00000003");
//		chef_order_listVO1.setChef_ord_cost(new Double(50000));
//		chef_order_listVO1.setChef_act_date(java.sql.Timestamp.valueOf("2017-01-01 12:22:33"));
//		chef_order_listVO1.setChef_ord_place(new String("台北市"));
//		chef_order_listVO1.setChef_ord_cnt(new String("左宗棠雞教學"));
//
//		dao.insert(chef_order_listVO1);

		// 修改
//		Chef_order_listVO chef_order_listVO2 = new Chef_order_listVO();
//		chef_order_listVO2.setChef_ord_no("COL000001");
//		chef_order_listVO2.setMem_no("M00000001");
//		chef_order_listVO2.setChef_no("C00000005");
//		chef_order_listVO2.setChef_ord_cost(new Double(20000));
//		chef_order_listVO2.setChef_act_date(java.sql.Date.valueOf("2017-01-02"));
//		chef_order_listVO2.setChef_ord_place(new String("新北市"));
//		chef_order_listVO2.setChef_ord_cnt(new String("咕咾肉教學"));
//		chef_order_listVO2.setChef_ord_con(new String("0"));
//		dao.update(chef_order_listVO2);

		// 刪除
//		dao.delete("COL000006");

		// 查詢
//		Chef_order_listVO chef_order_listVO3 = dao.findByPrimaryKey("COL000001");
//		System.out.print(chef_order_listVO3.getChef_ord_no() + ",");
//		System.out.print(chef_order_listVO3.getMem_no() + ",");
//		System.out.print(chef_order_listVO3.getChef_no() + ",");
//		System.out.print(chef_order_listVO3.getChef_ord_cost() + ",");
//		System.out.print(new java.text.SimpleDateFormat().format(chef_order_listVO3.getChef_act_date()) + ",");
//		System.out.print(chef_order_listVO3.getChef_ord_place() + ",");
//		System.out.print(chef_order_listVO3.getChef_ord_cnt() + ",");
//		System.out.print(chef_order_listVO3.getChef_ord_con() + ",");
//		System.out.print(chef_order_listVO3.getChef_appr() + ",");
//		System.out.print(chef_order_listVO3.getChef_appr_cnt()+",");
//		System.out.println(new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(chef_order_listVO3.getChef_ord_date()) + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<Chef_order_listVO> list = dao.getAll();
//		for (Chef_order_listVO aChef_order_list : list) {
//			System.out.print(aChef_order_list.getChef_ord_no() + ",");
//			System.out.print(aChef_order_list.getMem_no() + ",");
//			System.out.print(aChef_order_list.getChef_no() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cost() + ",");
//			System.out.print(new java.text.SimpleDateFormat().format(aChef_order_list.getChef_act_date()) + ",");
//			System.out.print(aChef_order_list.getChef_ord_place() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cnt() + ",");
//			System.out.print(aChef_order_list.getChef_ord_con() + ",");
//			System.out.print(aChef_order_list.getChef_appr() + ",");
//			System.out.print(aChef_order_list.getChef_appr_cnt()+",");
//			System.out.print(new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(aChef_order_list.getChef_ord_date()) + ",");
//			System.out.println();
//		}
		
//		by cyh
//		List<Chef_order_listVO> list = dao.findByMem_no("M00000002");
//		for (Chef_order_listVO aChef_order_list : list) {
//			System.out.print(aChef_order_list.getChef_ord_no() + ",");
//			System.out.print(aChef_order_list.getMem_no() + ",");
//			System.out.print(aChef_order_list.getChef_no() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cost() + ",");
//			System.out.print(new java.text.SimpleDateFormat().format(aChef_order_list.getChef_act_date()) + ",");
//			System.out.print(aChef_order_list.getChef_ord_place() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cnt() + ",");
//			System.out.print(aChef_order_list.getChef_ord_con() + ",");
//			System.out.print(aChef_order_list.getChef_appr() + ",");
//			System.out.print(aChef_order_list.getChef_appr_cnt()+",");
//			System.out.print(new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(aChef_order_list.getChef_ord_date()) + ",");
//			System.out.println();
//		}
		
//		List<Chef_order_listVO> list = dao.findByChef_no("C00000002");
//		for (Chef_order_listVO aChef_order_list : list) {
//			System.out.print(aChef_order_list.getChef_ord_no() + ",");
//			System.out.print(aChef_order_list.getMem_no() + ",");
//			System.out.print(aChef_order_list.getChef_no() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cost() + ",");
//			System.out.print(new java.text.SimpleDateFormat().format(aChef_order_list.getChef_act_date()) + ",");
//			System.out.print(aChef_order_list.getChef_ord_place() + ",");
//			System.out.print(aChef_order_list.getChef_ord_cnt() + ",");
//			System.out.print(aChef_order_list.getChef_ord_con() + ",");
//			System.out.print(aChef_order_list.getChef_appr() + ",");
//			System.out.print(aChef_order_list.getChef_appr_cnt()+",");
//			System.out.print(new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(aChef_order_list.getChef_ord_date()) + ",");
//			System.out.println();
//		}
	}
}