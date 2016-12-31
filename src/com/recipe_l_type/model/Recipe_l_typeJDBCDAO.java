package com.recipe_l_type.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.recipe_l_type.model.Recipe_l_typeDAO_interface;
import com.recipe_l_type.model.Recipe_l_typeVO;

public class Recipe_l_typeJDBCDAO implements Recipe_l_typeDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = "INSERT INTO recipe_l_type (recipe_l_type_no, l_type_name) VALUES ('RL'||LPAD(RECIPE_L_TYPE_SEQ.NEXTVAL,4,0),?)";
	private static final String GET_ALL_STMT = "SELECT recipe_l_type_no, l_type_name from recipe_l_type order by recipe_l_type_no";
	private static final String GET_ONE_STMT = "SELECT recipe_l_type_no, l_type_name FROM recipe_l_type where recipe_l_type_no = ?";
	private static final String DELETE = "DELETE FROM recipe_l_type where recipe_l_type_no = ?";
	private static final String UPDATE = "UPDATE recipe_l_type set l_type_name = ? where recipe_l_type_no = ?";
	
	@Override
	public void insert(Recipe_l_typeVO recipe_l_typeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, recipe_l_typeVO.getL_type_name());
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
	public void update(Recipe_l_typeVO recipe_l_typeVO) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_l_typeVO.getL_type_name());
			pstmt.setString(2, recipe_l_typeVO.getRecipe_l_type_no());
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
	public void delete(String recipe_l_type_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_l_type_no);

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
	public Recipe_l_typeVO findByPrimaryKey(String recipe_l_type_no) {
		
		Recipe_l_typeVO recipe_l_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recipe_l_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_MsgVO 也稱為 Domain objects
				recipe_l_typeVO = new Recipe_l_typeVO();
				recipe_l_typeVO.setRecipe_l_type_no(rs.getString("recipe_l_type_no"));
				recipe_l_typeVO.setL_type_name(rs.getString("l_type_name"));			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_l_typeVO = new Recipe_l_typeVO();
				recipe_l_typeVO.setRecipe_l_type_no(rs.getString("recipe_l_type_no"));
				recipe_l_typeVO.setL_type_name(rs.getString("l_type_name"));
				list.add(recipe_l_typeVO);
			}

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return list;		
		
	}
	
	
	public static void main(String[] args) {
		
		Recipe_l_typeJDBCDAO dao = new Recipe_l_typeJDBCDAO();
		
		//insert
//		 Recipe_l_typeVO recipe_l_typeVO1 = new Recipe_l_typeVO();
//		
//		 recipe_l_typeVO1.setL_type_name("時間場合");
//		 dao.insert(recipe_l_typeVO1);

		// update
//		 Recipe_l_typeVO recipe_l_typeVO2 = new Recipe_l_typeVO();
		
//		 recipe_l_typeVO2.setRecipe_l_type_no("RL0002");
//		 recipe_l_typeVO2.setL_type_name("異國料理");
//		 dao.update(recipe_l_typeVO2);
//		 int updateCount_update = dao.update(recipe_l_typeVO2);
		 

		// delete
//		 dao.delete("RL0007");
		 
		 
		 // search target
//		 Recipe_l_typeVO recipe_l_typeVO3 = dao.findByPrimaryKey("RL0001");
//		 System.out.print(recipe_l_typeVO3.getRecipe_l_type_no() + " | ");
//		 System.out.print(recipe_l_typeVO3.getL_type_name() + " | ");
		 

		// search all
//		List<Recipe_l_typeVO> list = dao.getAll();
//		for (Recipe_l_typeVO recipe_l_typeVO4 : list) {
//			System.out.print(recipe_l_typeVO4.getRecipe_l_type_no() + " | ");
//			System.out.print(recipe_l_typeVO4.getL_type_name() + " | ");
//			System.out.println();
//		}
	}

}
