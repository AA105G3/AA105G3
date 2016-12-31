package com.recipe_l_type.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.recipe_m_type.model.Recipe_m_typeVO;

import java.sql.*;

public class Recipe_l_typeJNDIDAO implements Recipe_l_typeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO recipe_l_type (recipe_l_type_no, l_type_name) VALUES ('RL'||LPAD(RECIPE_L_TYPE_SEQ.NEXTVAL,4,0),?)";
	private static final String GET_ALL_STMT = "SELECT recipe_l_type_no, l_type_name from recipe_l_type order by recipe_l_type_no";
	private static final String GET_ONE_STMT = "SELECT recipe_l_type_no, l_type_name FROM recipe_l_type where recipe_l_type_no = ?";
	private static final String DELETE = "DELETE FROM recipe_l_type where recipe_l_type_no = ?";
	private static final String UPDATE = "UPDATE recipe_l_type set l_type_name = ? where recipe_l_type_no = ?";
	private static final String GET_M_Types_ByL_Type_NO = "SELECT recipe_m_type_no, m_type_name,"
			+ " parent_type FROM recipe_m_type where parent_type = ?";

	public void insert(Recipe_l_typeVO recipe_l_typeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, recipe_l_typeVO.getL_type_name());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(Recipe_l_typeVO recipe_l_typeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, recipe_l_typeVO.getL_type_name());
			pstmt.setString(2, recipe_l_typeVO.getRecipe_l_type_no());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void delete(String recipe_l_type_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, recipe_l_type_no);
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public Recipe_l_typeVO findByPrimaryKey(String recipe_l_type_no) {

		Recipe_l_typeVO recipe_l_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, recipe_l_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_MsgVO �]�٬� Domain objects
				recipe_l_typeVO = new Recipe_l_typeVO();
				recipe_l_typeVO.setRecipe_l_type_no(rs.getString("recipe_l_type_no"));
				recipe_l_typeVO.setL_type_name(rs.getString("l_type_name"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return recipe_l_typeVO;
	}

	@Override
	public List<Recipe_l_typeVO> getAll() {

		List<Recipe_l_typeVO> list = new ArrayList<Recipe_l_typeVO>();
		Recipe_l_typeVO recipe_l_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_l_typeVO = new Recipe_l_typeVO();
				recipe_l_typeVO.setRecipe_l_type_no(rs.getString("recipe_l_type_no"));
				recipe_l_typeVO.setL_type_name(rs.getString("l_type_name"));
				list.add(recipe_l_typeVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<Recipe_m_typeVO> getM_typesByL_Type_No(String parent_type)
	{
		Set<Recipe_m_typeVO> set = new LinkedHashSet<Recipe_m_typeVO>();
		Recipe_m_typeVO recipe_m_typeVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_M_Types_ByL_Type_NO);
			pstmt.setString(1, parent_type);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setM_type_name(rs.getString("m_type_name"));
				recipe_m_typeVO.setRecipe_m_type_no(rs.getString("recipe_m_type_no"));
				recipe_m_typeVO.setParent_type(rs.getString("parent_type"));
				set.add(recipe_m_typeVO); // Store the row in the vector
			}
	
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}
