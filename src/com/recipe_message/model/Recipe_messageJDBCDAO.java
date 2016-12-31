package com.recipe_message.model;

import java.util.*;

import com.recipe_message.model.Recipe_messageVO;

import java.sql.*;

public class Recipe_messageJDBCDAO implements Recipe_messageDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = "INSERT INTO recipe_message (recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time ) VALUES ('RMN'||recipe_msg_seq.NEXTVAL,?,?,?,?,sysdate)";
	private static final String GET_ALL_STMT = "SELECT recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time FROM recipe_message";
	private static final String GET_ONE_STMT = "SELECT recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time FROM recipe_message where recipe_msg_no = ?";
	private static final String DELETE = "DELETE FROM recipe_message where recipe_msg_no = ?";
	private static final String UPDATE = "UPDATE recipe_message set msg_cont=? , msg_time=sysdate where recipe_msg_no = ?";
	
	
	public void insert(Recipe_messageVO recipe_messageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_messageVO.getMem_no());
			pstmt.setString(2, recipe_messageVO.getRecipe_no());
			pstmt.setString(3,recipe_messageVO.getMsg_cont());
			pstmt.setString(4, recipe_messageVO.getRecipe_msg_ref());
			
			
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
	public void update(Recipe_messageVO recipe_messageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_messageVO.getMsg_cont());
			pstmt.setString(2, recipe_messageVO.getRecipe_msg_no());

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
	public void delete(String recipe_msg_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_msg_no);

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
	public Recipe_messageVO findByPrimaryKey(String recipe_msg_no) {

		Recipe_messageVO recipe_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recipe_msg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_messageVO 也稱為 Domain objects
				recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setMem_no(rs.getString("mem_no"));
				recipe_messageVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_messageVO.setMsg_cont(rs.getString("msg_cont"));
				recipe_messageVO.setRecipe_msg_ref(rs.getString("recipe_msg_ref"));
				recipe_messageVO.setRecipe_msg_no(rs.getString("recipe_msg_no"));
				recipe_messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				
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
		return recipe_messageVO;
	}

	@Override
	public List<Recipe_messageVO> getAll() {
		List<Recipe_messageVO> list = new ArrayList<Recipe_messageVO>();
		Recipe_messageVO recipe_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_messageVO 也稱為 Domain objects
				recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setRecipe_msg_no(rs.getString("recipe_msg_no"));
				recipe_messageVO.setMem_no(rs.getString("mem_no"));
				recipe_messageVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_messageVO.setMsg_cont(rs.getString("msg_cont"));
				recipe_messageVO.setRecipe_msg_ref(rs.getString("recipe_msg_ref"));
				recipe_messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				list.add(recipe_messageVO); // Store the row in the list
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
	
		Recipe_messageJDBCDAO dao = new Recipe_messageJDBCDAO();
	
//		insert
//		Recipe_messageVO recipe_messageVO1 =new Recipe_messageVO();
//		recipe_messageVO1.setMem_no("M00000001");
//		recipe_messageVO1.setRecipe_no("R00000001");
//		recipe_messageVO1.setMsg_cont("好好吃~~");
//		recipe_messageVO1.setRecipe_msg_ref("RMN1");
//		recipe_messageVO1.setMsg_time(Timestamp.valueOf("2016-12-01 15:33:22"));
//		dao.insert(recipe_messageVO1);

		
				
		
//		update
//		Recipe_messageVO recipe_messageVO2 = new Recipe_messageVO();
//		recipe_messageVO2.setMsg_cont("讚!!!");
//		recipe_messageVO2.setRecipe_msg_no("RMN2");
//		
//		dao.update(recipe_messageVO2);
		
		
//		delete
		dao.delete("RMN6");
		
		
//		search target
		Recipe_messageVO recipe_messageVO3 = dao.findByPrimaryKey("RMN2");
		System.out.print(recipe_messageVO3.getRecipe_msg_no() + " | ");
		System.out.print(recipe_messageVO3.getMem_no() + " | ");
		System.out.print(recipe_messageVO3.getRecipe_no() + " | ");
		System.out.print(recipe_messageVO3.getMsg_cont() + " | ");
		System.out.print(recipe_messageVO3.getRecipe_msg_ref() + " | ");
		System.out.print(recipe_messageVO3.getMsg_time() + " | ");


//		search all
//		List<Recipe_messageVO> list = dao.getAll();
//		for (Recipe_messageVO recipe_messageVO4 : list) {
//			System.out.print(recipe_messageVO4.getRecipe_msg_no() + " | ");
//			System.out.print(recipe_messageVO4.getMem_no() + " | ");
//			System.out.print(recipe_messageVO4.getRecipe_no() + " | ");
//			System.out.print(recipe_messageVO4.getMsg_cont() + " | ");
//			System.out.print(recipe_messageVO4.getRecipe_msg_ref() + " | ");
//			System.out.print(recipe_messageVO4.getMsg_time() + " | ");
//			System.out.println();
//		}
		
	}	
}
