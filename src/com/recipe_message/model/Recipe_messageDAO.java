package com.recipe_message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Recipe_messageDAO implements Recipe_messageDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO recipe_message (recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time ) VALUES ('RMN'||recipe_msg_seq.NEXTVAL,?,?,?,?,sysdate)";
	private static final String GET_ALL_STMT = "SELECT recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time FROM recipe_message";
	private static final String GET_ONE_STMT = "SELECT recipe_msg_no, mem_no, recipe_no, msg_cont, recipe_msg_ref, msg_time FROM recipe_message where recipe_msg_no = ?";
	private static final String DELETE = "DELETE FROM recipe_message where recipe_msg_no = ?";
	private static final String UPDATE = "UPDATE recipe_message set msg_cont=? , msg_time=sysdate where recipe_msg_no = ?";
	
	public void insert(Recipe_messageVO recipe_messageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, recipe_messageVO.getMem_no());
			pstmt.setString(2, recipe_messageVO.getRecipe_no());
			pstmt.setString(3,recipe_messageVO.getMsg_cont());
			pstmt.setString(4, recipe_messageVO.getRecipe_msg_ref());	
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
	public void update(Recipe_messageVO recipe_messageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, recipe_messageVO.getMsg_cont());
			pstmt.setString(2, recipe_messageVO.getRecipe_msg_no());
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
	public void delete(String recipe_msg_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, recipe_msg_no);
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
	public Recipe_messageVO findByPrimaryKey(String recipe_msg_no) {

		Recipe_messageVO recipe_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, recipe_msg_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_messageVO �]�٬� Domain objects
				recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setMem_no(rs.getString("mem_no"));
				recipe_messageVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_messageVO.setMsg_cont(rs.getString("msg_cont"));
				recipe_messageVO.setRecipe_msg_ref(rs.getString("recipe_msg_ref"));
				recipe_messageVO.setRecipe_msg_no(rs.getString("recipe_msg_no"));
				recipe_messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_messageVO �]�٬� Domain objects
				recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setRecipe_msg_no(rs.getString("recipe_msg_no"));
				recipe_messageVO.setMem_no(rs.getString("mem_no"));
				recipe_messageVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_messageVO.setMsg_cont(rs.getString("msg_cont"));
				recipe_messageVO.setRecipe_msg_ref(rs.getString("recipe_msg_ref"));
				recipe_messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				list.add(recipe_messageVO); // Store the row in the list
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
