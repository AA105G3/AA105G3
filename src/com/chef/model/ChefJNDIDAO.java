package com.chef.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChefJNDIDAO implements ChefDAO_interface {

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
		"INSERT INTO chef (chef_no,mem_no,chef_chk_cond,chef_bnk,chef_bnk_ac,chef_skill,chef_lic,chef_image,chef_movie1,chef_movie2,chef_id,chef_name,chef_area,chef_intr,chef_menu,chef_reci_image1,chef_reci_image2,chef_reci_image3,chef_reci_image4,chef_reci_image5) VALUES ('C' || lpad(chef_seq.NEXTVAL,8,0), ?, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chef_no,mem_no,chef_chk_cond,chef_bnk,chef_bnk_ac,chef_skill,chef_lic,chef_image,chef_movie1,chef_movie2,chef_id,chef_name,chef_area,chef_intr,chef_menu,chef_reci_image1,chef_reci_image2,chef_reci_image3,chef_reci_image4,chef_reci_image5 FROM chef order by chef_no";
	private static final String GET_ONE_STMT = 
		"SELECT chef_no,mem_no,chef_chk_cond,chef_bnk,chef_bnk_ac,chef_skill,chef_lic,chef_image,chef_movie1,chef_movie2,chef_id,chef_name,chef_area,chef_intr,chef_menu,chef_reci_image1,chef_reci_image2,chef_reci_image3,chef_reci_image4,chef_reci_image5 FROM chef where chef_no = ?";
	private static final String DELETE = 
		"DELETE FROM chef where chef_no = ?";
	private static final String UPDATE = 
		"UPDATE chef set chef_bnk=?, chef_bnk_ac=?, chef_skill=?, chef_lic=?, chef_image=?, chef_movie1=?, chef_movie2=?, chef_id=?, chef_name=?, chef_area=?, chef_intr=?, chef_menu=?, chef_reci_image1=?, chef_reci_image2=?, chef_reci_image3=?, chef_reci_image4=?, chef_reci_image5=? where chef_no = ?";
	private static final String GET_ONE_BY_MEM_NO = 
			"SELECT chef_no,mem_no,chef_chk_cond,chef_bnk,chef_bnk_ac,chef_skill,chef_lic,chef_image,chef_movie1,chef_movie2,chef_id,chef_name,chef_area,chef_intr,chef_menu,chef_reci_image1,chef_reci_image2,chef_reci_image3,chef_reci_image4,chef_reci_image5 FROM chef where mem_no = ?";
	
	@Override
	public void insert(ChefVO chefVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chefVO.getMem_no());
			pstmt.setString(2, chefVO.getChef_bnk());
			pstmt.setString(3, chefVO.getChef_bnk_ac());
			pstmt.setString(4, chefVO.getChef_skill());
			pstmt.setBytes(5, chefVO.getChef_lic());
			pstmt.setBytes(6, chefVO.getChef_image());
			pstmt.setBytes(7, chefVO.getChef_movie1());
			pstmt.setBytes(8, chefVO.getChef_movie2());
			pstmt.setString(9, chefVO.getChef_id());
			pstmt.setString(10, chefVO.getChef_name());
			pstmt.setString(11, chefVO.getChef_area());
			pstmt.setString(12, chefVO.getChef_intr());
			pstmt.setString(13, chefVO.getChef_menu());
			pstmt.setBytes(14, chefVO.getChef_reci_image1());
			pstmt.setBytes(15, chefVO.getChef_reci_image2());
			pstmt.setBytes(16, chefVO.getChef_reci_image3());
			pstmt.setBytes(17, chefVO.getChef_reci_image4());
			pstmt.setBytes(18, chefVO.getChef_reci_image5());

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
	public void update(ChefVO chefVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chefVO.getChef_bnk());
			pstmt.setString(2, chefVO.getChef_bnk_ac());
			pstmt.setString(3, chefVO.getChef_skill());
			pstmt.setBytes(4, chefVO.getChef_lic());
			pstmt.setBytes(5, chefVO.getChef_image());
			pstmt.setBytes(6, chefVO.getChef_movie1());
			pstmt.setBytes(7, chefVO.getChef_movie2());
			pstmt.setString(8, chefVO.getChef_id());
			pstmt.setString(9, chefVO.getChef_name());
			pstmt.setString(10, chefVO.getChef_area());
			pstmt.setString(11, chefVO.getChef_intr());
			pstmt.setString(12, chefVO.getChef_menu());
			pstmt.setBytes(13, chefVO.getChef_reci_image1());
			pstmt.setBytes(14, chefVO.getChef_reci_image2());
			pstmt.setBytes(15, chefVO.getChef_reci_image3());
			pstmt.setBytes(16, chefVO.getChef_reci_image4());
			pstmt.setBytes(17, chefVO.getChef_reci_image5());
			pstmt.setString(18, chefVO.getChef_no());

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
	public void delete(String chef_no) {

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
	public ChefVO findByPrimaryKey(String chef_no) {

		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chefVO 也稱為 Domain objects
				chefVO = new ChefVO();
				chefVO.setChef_no(rs.getString("chef_no"));
				chefVO.setMem_no(rs.getString("mem_no"));
				chefVO.setChef_chk_cond(rs.getString("chef_chk_cond"));
				chefVO.setChef_bnk(rs.getString("chef_bnk"));
				chefVO.setChef_bnk_ac(rs.getString("chef_bnk_ac"));
				chefVO.setChef_skill(rs.getString("chef_skill"));
				chefVO.setChef_lic(rs.getBytes("chef_lic"));
				chefVO.setChef_image(rs.getBytes("chef_image"));
				chefVO.setChef_movie1(rs.getBytes("chef_movie1"));
				chefVO.setChef_movie2(rs.getBytes("chef_movie2"));
				chefVO.setChef_id(rs.getString("chef_id"));
				chefVO.setChef_name(rs.getString("chef_name"));
				chefVO.setChef_area(rs.getString("chef_area"));
				chefVO.setChef_intr(rs.getString("chef_intr"));
				chefVO.setChef_menu(rs.getString("chef_menu"));
				chefVO.setChef_reci_image1(rs.getBytes("chef_reci_image1"));
				chefVO.setChef_reci_image2(rs.getBytes("chef_reci_image2"));
				chefVO.setChef_reci_image3(rs.getBytes("chef_reci_image3"));
				chefVO.setChef_reci_image4(rs.getBytes("chef_reci_image4"));
				chefVO.setChef_reci_image5(rs.getBytes("chef_reci_image5"));			
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
		return chefVO;
	}

	@Override
	public List<ChefVO> getAll() {
		List<ChefVO> list = new ArrayList<ChefVO>();
		ChefVO chefVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chefVO 也稱為 Domain objects
				chefVO = new ChefVO();
				chefVO.setChef_no(rs.getString("chef_no"));
				chefVO.setMem_no(rs.getString("mem_no"));
				chefVO.setChef_chk_cond(rs.getString("chef_chk_cond"));
				chefVO.setChef_bnk(rs.getString("chef_bnk"));
				chefVO.setChef_bnk_ac(rs.getString("chef_bnk_ac"));
				chefVO.setChef_skill(rs.getString("chef_skill"));
				chefVO.setChef_lic(rs.getBytes("chef_lic"));
				chefVO.setChef_image(rs.getBytes("chef_image"));
				chefVO.setChef_movie1(rs.getBytes("chef_movie1"));
				chefVO.setChef_movie2(rs.getBytes("chef_movie2"));
				chefVO.setChef_id(rs.getString("chef_id"));
				chefVO.setChef_name(rs.getString("chef_name"));
				chefVO.setChef_area(rs.getString("chef_area"));
				chefVO.setChef_intr(rs.getString("chef_intr"));
				chefVO.setChef_menu(rs.getString("chef_menu"));
				chefVO.setChef_reci_image1(rs.getBytes("chef_reci_image1"));
				chefVO.setChef_reci_image2(rs.getBytes("chef_reci_image2"));
				chefVO.setChef_reci_image3(rs.getBytes("chef_reci_image3"));
				chefVO.setChef_reci_image4(rs.getBytes("chef_reci_image4"));
				chefVO.setChef_reci_image5(rs.getBytes("chef_reci_image5"));
				list.add(chefVO); // Store the row in the list
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
	@Override
	public ChefVO findByMem_no(String mem_no)
	{
		// TODO Auto-generated method stub
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MEM_NO);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chefVO 也稱為 Domain objects
				chefVO = new ChefVO();
				chefVO.setChef_no(rs.getString("chef_no"));
				chefVO.setMem_no(rs.getString("mem_no"));
				chefVO.setChef_chk_cond(rs.getString("chef_chk_cond"));
				chefVO.setChef_bnk(rs.getString("chef_bnk"));
				chefVO.setChef_bnk_ac(rs.getString("chef_bnk_ac"));
				chefVO.setChef_skill(rs.getString("chef_skill"));
				chefVO.setChef_lic(rs.getBytes("chef_lic"));
				chefVO.setChef_image(rs.getBytes("chef_image"));
				chefVO.setChef_movie1(rs.getBytes("chef_movie1"));
				chefVO.setChef_movie2(rs.getBytes("chef_movie2"));
				chefVO.setChef_id(rs.getString("chef_id"));
				chefVO.setChef_name(rs.getString("chef_name"));
				chefVO.setChef_area(rs.getString("chef_area"));
				chefVO.setChef_intr(rs.getString("chef_intr"));
				chefVO.setChef_menu(rs.getString("chef_menu"));
				chefVO.setChef_reci_image1(rs.getBytes("chef_reci_image1"));
				chefVO.setChef_reci_image2(rs.getBytes("chef_reci_image2"));
				chefVO.setChef_reci_image3(rs.getBytes("chef_reci_image3"));
				chefVO.setChef_reci_image4(rs.getBytes("chef_reci_image4"));
				chefVO.setChef_reci_image5(rs.getBytes("chef_reci_image5"));
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
		return chefVO;
	}
}