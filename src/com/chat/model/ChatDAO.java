package com.chat.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

import com.chat.model.ChatVO;
import com.chat.model.ChatDAO_interface;

public class ChatDAO implements ChatDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO chat (chat_no, send_no, get_no, chat_text, chat_date ) VALUES ('CTN'||CHAT_SEQ.NEXTVAL,?,?,?,sysdate)";
	private static final String GET_ALL_STMT = "SELECT chat_no, send_no, get_no, chat_text, chat_date FROM chat";
	private static final String GET_ONE_STMT = "SELECT chat_no, send_no, get_no, chat_text, chat_date FROM chat where chat_no = ?";
	private static final String DELETE = "DELETE FROM chat where chat_no = ?";
	private static final String UPDATE = "UPDATE chat set send_no=?, get_no=?, chat_text=?, chat_date=sysdate where chat_no = ?";
	
	
	@Override
	public int insert(ChatVO chatVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chatVO.getSend_no());
			pstmt.setString(2, chatVO.getGet_no());
			pstmt.setString(3, chatVO.getChat_text());
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
		return updateCount;
	}
	
	@Override
	public int update(ChatVO chatVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chatVO.getSend_no());
			pstmt.setString(2, chatVO.getGet_no());
			pstmt.setString(3, chatVO.getChat_text());
			pstmt.setString(4, chatVO.getChat_no());
		  //pstmt.setTimestamp(4,new Timestamp(new java.util.Date().getTime()));
			
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
		return updateCount;		
	}

	@Override
	public int delete(String chat_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chat_no);

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
		return updateCount;
	}

	@Override
	public ChatVO findByPrimaryKey(String chat_no) {
		
		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chat_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_MsgVO 也稱為 Domain objects
				chatVO = new ChatVO();
				chatVO.setChat_no(rs.getString("chat_no"));
				chatVO.setSend_no(rs.getString("send_no"));
				chatVO.setGet_no(rs.getString("get_no"));
				chatVO.setChat_text(rs.getString("chat_text"));
				chatVO.setChat_date(rs.getTimestamp("chat_date"));				
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
		return chatVO;		
	}

	@Override
	public List<ChatVO> getAll() {
		
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_MsgVO 也稱為 Domain objects
				chatVO = new ChatVO();
				chatVO.setChat_no(rs.getString("chat_no"));
				chatVO.setSend_no(rs.getString("send_no"));
				chatVO.setGet_no(rs.getString("get_no"));
				chatVO.setChat_text(rs.getString("chat_text"));
				chatVO.setChat_date(rs.getTimestamp("chat_date"));	
				list.add(chatVO); // Store the row in the list
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
