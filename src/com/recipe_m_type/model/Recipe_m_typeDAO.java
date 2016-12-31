package com.recipe_m_type.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class Recipe_m_typeDAO implements Recipe_m_typeDAO_interface {

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
			"INSERT INTO recipe_m_type (recipe_m_type_no,"
			+ " m_type_name,"
			+ " parent_type) "
			+ "VALUES ('RM'||LPAD(recipe_m_type_seq.NEXTVAL,4,0), ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT recipe_m_type_no,"
			+ " m_type_name,"
			+ " parent_type FROM recipe_m_type order by recipe_m_type_no";
		private static final String GET_ONE_STMT = 
			"SELECT recipe_m_type_no,"
			+ " m_type_name,"
			+ " parent_type FROM recipe_m_type where recipe_m_type_no = ?";
		private static final String DELETE = 
			"DELETE FROM recipe_m_type where recipe_m_type_no = ?";
		private static final String UPDATE = 
			"UPDATE recipe_m_type set m_type_name=?,"
			+ " parent_type=? where recipe_m_type_no = ?";
		
	@Override
	public void insert(Recipe_m_typeVO recipe_m_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, recipe_m_typeVO.getM_type_name());
			pstmt.setString(2, recipe_m_typeVO.getParent_type());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Recipe_m_typeVO recipe_m_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, recipe_m_typeVO.getM_type_name());
			pstmt.setString(2, recipe_m_typeVO.getParent_type());
			pstmt.setString(3, recipe_m_typeVO.getRecipe_m_type_no());
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
	public void delete(String recipe_m_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, recipe_m_type_no);
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
	public Recipe_m_typeVO findByPrimaryKey(String recipe_m_type_no) {
		Recipe_m_typeVO recipe_m_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, recipe_m_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_m_typeVO 也稱為 Domain objects
				recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setRecipe_m_type_no(rs.getString("recipe_m_type_no"));
				recipe_m_typeVO.setM_type_name(rs.getString("m_type_name"));
				recipe_m_typeVO.setParent_type(rs.getString("parent_type"));
			}

		
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
		return recipe_m_typeVO;
	}

	@Override
	public List<Recipe_m_typeVO> getAll() {
		List<Recipe_m_typeVO> list = new ArrayList<Recipe_m_typeVO>();
		Recipe_m_typeVO recipe_m_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_m_typeVO 也稱為 Domain objects
				recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setRecipe_m_type_no(rs.getString("recipe_m_type_no"));
				recipe_m_typeVO.setM_type_name(rs.getString("m_type_name"));
				recipe_m_typeVO.setParent_type(rs.getString("parent_type"));
				list.add(recipe_m_typeVO); // Store the row in the list
			}
	
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

}
