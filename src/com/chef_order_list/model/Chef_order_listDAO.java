package com.chef_order_list.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Chef_order_listDAO implements Chef_order_listDAO_interface {

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chef_order_listVO.getMem_no());
			pstmt.setString(2, chef_order_listVO.getChef_no());
			pstmt.setDouble(3, chef_order_listVO.getChef_ord_cost());
			pstmt.setTimestamp(4, chef_order_listVO.getChef_act_date());
			pstmt.setString(5, chef_order_listVO.getChef_ord_place());
			pstmt.setString(6, chef_order_listVO.getChef_ord_cnt());
		;

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
	public void update(Chef_order_listVO chef_order_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, chef_order_listVO.getChef_ord_cost());
			pstmt.setTimestamp(2, chef_order_listVO.getChef_act_date());
			pstmt.setString(3, chef_order_listVO.getChef_ord_place());
			pstmt.setString(4, chef_order_listVO.getChef_ord_cnt());
			pstmt.setString(5, chef_order_listVO.getChef_ord_con());
			pstmt.setString(6, chef_order_listVO.getChef_ord_no());

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
	public void delete(String chef_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chef_ord_no);

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
	public Chef_order_listVO findByPrimaryKey(String chef_ord_no) {

		Chef_order_listVO chef_order_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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


//by cyh
@Override
public List<Chef_order_listVO> findByMem_no(String mem_no) {
	List<Chef_order_listVO> list = new ArrayList<Chef_order_listVO>();
	Chef_order_listVO chef_order_listVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();
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

	}  catch (SQLException e) {
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
		con = ds.getConnection();
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

}