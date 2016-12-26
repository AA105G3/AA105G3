package com.product_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order_list.model.Product_order_listJDBCDAO;
import com.product_order_list.model.Product_order_listVO;

public class Product_orderJNDIDAO implements Product_orderDAO_interface {

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
		"INSERT INTO product_order (prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone) "
		//+ "VALUES ('20161208'||'-'||LPAD(prod_ord_seq.NEXTVAL,8,0), ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		+ "VALUES (TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(prod_ord_seq.NEXTVAL,8,0), ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone FROM product_order order by prod_ord_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone FROM product_order where prod_ord_no = ?";
	private static final String DELETE = 
		"DELETE FROM product_order where prod_ord_no = ?";
	private static final String UPDATE = 
		"UPDATE product_order set mem_no=?,"
		+ " cred_card_no=?,"
		+ " valid_date=?,"
		+ " valid_no=?,"
		+ " cred_card_type=?,"
		+ " total_money=?,"
		+ " ship_name=?,"
		+ " post_code=?,"
		+ " mem_adrs=?,"
		+ " cell_phone=?,"
		+ " tel_phone=? where prod_ord_no = ?";
	
	private static final String GET_STMT_BY_ONE_PK = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list where prod_ord_no = ? order by prod_ord_no";
	
	private static final String GET_STMT_BY_MEM_NO = 
		"SELECT prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone FROM product_order where mem_no = ?";
	
	@Override
	public void insert(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prod_ordVO.getMem_no());
			pstmt.setString(2, prod_ordVO.getCred_card_no());
			pstmt.setDate(3, prod_ordVO.getValid_date());
			pstmt.setString(4, prod_ordVO.getValid_no());
			pstmt.setString(5, prod_ordVO.getCred_card_type());
			pstmt.setInt(6, prod_ordVO.getTotal_money());
			pstmt.setString(7, prod_ordVO.getShip_name());
			pstmt.setString(8, prod_ordVO.getPost_code());
			pstmt.setString(9, prod_ordVO.getMem_adrs());
			pstmt.setString(10, prod_ordVO.getCell_phone());
			pstmt.setString(11, prod_ordVO.getTel_phone());

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
	public void update(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prod_ordVO.getMem_no());
			pstmt.setString(2, prod_ordVO.getCred_card_no());
			pstmt.setDate(3, prod_ordVO.getValid_date());
			pstmt.setString(4, prod_ordVO.getValid_no());
			pstmt.setString(5, prod_ordVO.getCred_card_type());
			pstmt.setInt(6, prod_ordVO.getTotal_money());
			pstmt.setString(7, prod_ordVO.getShip_name());
			pstmt.setString(8, prod_ordVO.getPost_code());
			pstmt.setString(9, prod_ordVO.getMem_adrs());
			pstmt.setString(10, prod_ordVO.getCell_phone());
			pstmt.setString(11, prod_ordVO.getTel_phone());
			pstmt.setString(12, prod_ordVO.getProd_ord_no());


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
	public void delete(String prod_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_ord_no);

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
	public Product_orderVO findByPrimaryKey(String prod_ord_no) {

		Product_orderVO prod_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prod_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ordVO 也稱為 Domain objects
				prod_ordVO = new Product_orderVO();
				prod_ordVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ordVO.setMem_no(rs.getString("mem_no"));
				prod_ordVO.setProd_ord_time(rs.getTimestamp("prod_ord_time"));
				prod_ordVO.setCred_card_no(rs.getString("cred_card_no"));
				prod_ordVO.setValid_date(rs.getDate("valid_date"));
				prod_ordVO.setValid_no(rs.getString("valid_no"));
				prod_ordVO.setCred_card_type(rs.getString("cred_card_type"));
				prod_ordVO.setTotal_money(rs.getInt("total_money"));
				prod_ordVO.setShip_name(rs.getString("ship_name"));
				prod_ordVO.setPost_code(rs.getString("post_code"));
				prod_ordVO.setMem_adrs(rs.getString("mem_adrs"));
				prod_ordVO.setCell_phone(rs.getString("cell_phone"));
				prod_ordVO.setTel_phone(rs.getString("tel_phone"));
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
		return prod_ordVO;
	}

	@Override
	public List<Product_orderVO> getAll() {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO prod_ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ordVO 也稱為 Domain objects
				prod_ordVO = new Product_orderVO();
				prod_ordVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ordVO.setMem_no(rs.getString("mem_no"));
				prod_ordVO.setProd_ord_time(rs.getTimestamp("prod_ord_time"));
				prod_ordVO.setCred_card_no(rs.getString("cred_card_no"));
				prod_ordVO.setValid_date(rs.getDate("valid_date"));
				prod_ordVO.setValid_no(rs.getString("valid_no"));
				prod_ordVO.setCred_card_type(rs.getString("cred_card_type"));
				prod_ordVO.setTotal_money(rs.getInt("total_money"));
				prod_ordVO.setShip_name(rs.getString("ship_name"));
				prod_ordVO.setPost_code(rs.getString("post_code"));
				prod_ordVO.setMem_adrs(rs.getString("mem_adrs"));
				prod_ordVO.setCell_phone(rs.getString("cell_phone"));
				prod_ordVO.setTel_phone(rs.getString("tel_phone"));
				list.add(prod_ordVO); // Store the row in the list
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
	public Set<Product_order_listVO> findByPK(String prod_ord_no) {
		Set<Product_order_listVO> set = new LinkedHashSet<Product_order_listVO>();
		Product_order_listVO prod_ord_listVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_BY_ONE_PK);
			pstmt.setString(1, prod_ord_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				prod_ord_listVO = new Product_order_listVO();
				prod_ord_listVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ord_listVO.setProd_no(rs.getString("prod_no"));
				prod_ord_listVO.setUnit_price(rs.getInt("unit_price"));
				prod_ord_listVO.setProd_quantity(rs.getInt("prod_quantity"));
				prod_ord_listVO.setDeli_status(rs.getString("deli_status"));
				prod_ord_listVO.setDeli_time(rs.getDate("deli_time"));
				set.add(prod_ord_listVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
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
	
	@Override
	public List<Product_orderVO> findByMem_no(String mem_no) {
		List<Product_orderVO> list2 = new ArrayList<Product_orderVO>();
		Product_orderVO prod_ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_BY_MEM_NO);
			
			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ord_listVO 也稱為 Domain objects
				prod_ordVO = new Product_orderVO();
				prod_ordVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ordVO.setMem_no(rs.getString("mem_no"));
				prod_ordVO.setProd_ord_time(rs.getTimestamp("prod_ord_time"));
				prod_ordVO.setCred_card_no(rs.getString("cred_card_no"));
				prod_ordVO.setValid_date(rs.getDate("valid_date"));
				prod_ordVO.setValid_no(rs.getString("valid_no"));
				prod_ordVO.setCred_card_type(rs.getString("cred_card_type"));
				prod_ordVO.setTotal_money(rs.getInt("total_money"));
				prod_ordVO.setShip_name(rs.getString("ship_name"));
				prod_ordVO.setPost_code(rs.getString("post_code"));
				prod_ordVO.setMem_adrs(rs.getString("mem_adrs"));
				prod_ordVO.setCell_phone(rs.getString("cell_phone"));
				prod_ordVO.setTel_phone(rs.getString("tel_phone"));
				list2.add(prod_ordVO); // Store the row in the list
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

		return list2;
	}
	
	@Override
	public void insertWithProduct_order_list(Product_orderVO product_orderVO , List<Product_order_listVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增商品訂單
			String cols[] = {"prod_ord_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, product_orderVO.getMem_no());
			pstmt.setString(2, product_orderVO.getCred_card_no());
			pstmt.setDate(3, product_orderVO.getValid_date());
			pstmt.setString(4, product_orderVO.getValid_no());
			pstmt.setString(5, product_orderVO.getCred_card_type());
			pstmt.setInt(6, product_orderVO.getTotal_money());
			pstmt.setString(7, product_orderVO.getShip_name());
			pstmt.setString(8, product_orderVO.getPost_code());
			pstmt.setString(9, product_orderVO.getMem_adrs());
			pstmt.setString(10, product_orderVO.getCell_phone());
			pstmt.setString(11, product_orderVO.getTel_phone());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_prod_ord_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_prod_ord_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_prod_ord_no +"(剛新增成功的商品訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			Product_order_listJDBCDAO dao = new Product_order_listJDBCDAO();
			for (Product_order_listVO aProduct_order_list : list) {
				aProduct_order_list.setProd_ord_no(new String(next_prod_ord_no)) ;
				dao.insertWithProduct_order(aProduct_order_list,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-product_order");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

}
