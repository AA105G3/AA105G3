package com.chat.model;

import java.util.*;
import java.sql.*;

import com.chat.model.ChatVO;
import com.chat.model.ChatDAO_interface;


public class ChatJDBCDAO implements ChatDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chatVO.getSend_no());
			pstmt.setString(2, chatVO.getGet_no());
			pstmt.setString(3, chatVO.getChat_text());
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
		return updateCount;
	}
	
	@Override
	public int update(ChatVO chatVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chatVO.getSend_no());
			pstmt.setString(2, chatVO.getGet_no());
			pstmt.setString(3, chatVO.getChat_text());
			pstmt.setString(4, chatVO.getChat_no());
		  //pstmt.setTimestamp(4,new Timestamp(new java.util.Date().getTime()));
			
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
		return updateCount;		
	}

	@Override
	public int delete(String chat_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chat_no);

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
		return updateCount;
	}

	@Override
	public ChatVO findByPrimaryKey(String chat_no) {
		
		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chat_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_MsgVO 也稱為 Domain objects
				chatVO = new ChatVO();
				chatVO.setSend_no(rs.getString("send_no"));
				chatVO.setGet_no(rs.getString("get_no"));
				chatVO.setChat_text(rs.getString("chat_text"));
				chatVO.setChat_date(rs.getTimestamp("chat_date"));				
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
		ChatJDBCDAO dao = new ChatJDBCDAO();
		
//		insert
		/*ChatVO chatVO1 = new ChatVO();
		chatVO1.setSend_no("M00000001");
		chatVO1.setGet_no("M00000002");
		chatVO1.setChat_text("你好~~");
		dao.insert(chatVO1);*/
		
		
		
//		update
		/*ChatVO chatVO2 = new ChatVO();
		chatVO2.setSend_no("M00000001");
		chatVO2.setGet_no("M00000002");
		chatVO2.setChat_text("你在嗎~~");
		chatVO2.setChat_no("CTN6");
		dao.update(chatVO2);*/
		
		
//		delete
		/*dao.delete("CTN6");*/
		
		
//		search target
		/*ChatVO chatVO3 = dao.findByPrimaryKey("CTN1");
		System.out.print(chatVO3.getSend_no() + " | ");
		System.out.print(chatVO3.getGet_no() + " | ");
		System.out.print(chatVO3.getChat_text() + " | ");
		System.out.print(chatVO3.getChat_date() + " | ");*/

		
//		search all
		List<ChatVO> list = dao.getAll();
		for (ChatVO chatVO4 : list) {
			System.out.print(chatVO4.getChat_no() + " | ");
			System.out.print(chatVO4.getSend_no() + " | ");
			System.out.print(chatVO4.getGet_no() + " | ");
			System.out.print(chatVO4.getChat_text() + " | ");
			System.out.print(chatVO4.getChat_date() + " | ");
			System.out.println();
		}
		
	}	

}
