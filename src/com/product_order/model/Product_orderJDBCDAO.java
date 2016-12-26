package com.product_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order_list.model.Product_order_listJDBCDAO;
import com.product_order_list.model.Product_order_listVO;

public class Product_orderJDBCDAO implements Product_orderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String prod_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_ord_no);

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
	public Product_orderVO findByPrimaryKey(String prod_ord_no) {

		Product_orderVO prod_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public Set<Product_order_listVO> findByPK(String prod_ord_no) {
		Set<Product_order_listVO> set = new LinkedHashSet<Product_order_listVO>();
		Product_order_listVO prod_ord_listVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = DriverManager.getConnection(url, userid, passwd);
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

			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
			System.out.println("list.size()-A="+list.size());
			for (Product_order_listVO aProduct_order_list : list) {
				aProduct_order_list.setProd_ord_no(new String(next_prod_ord_no)) ;
				dao.insertWithProduct_order(aProduct_order_list,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增商品訂單編號" + next_prod_ord_no + "時,共有訂單明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {

		Product_orderJDBCDAO dao = new Product_orderJDBCDAO();
		
		//新增商品訂單同時新增商品訂單明細
		Product_orderVO product_orderVO = new Product_orderVO();
		product_orderVO.setMem_no("M00000002");
		product_orderVO.setCred_card_no("1A2B3C4D5E6F7G8H");
		product_orderVO.setValid_date(java.sql.Date.valueOf("2022-12-31"));
		product_orderVO.setValid_no("147");
		product_orderVO.setCred_card_type("2");
		product_orderVO.setTotal_money(8700);
		product_orderVO.setShip_name("KITTEN");
		product_orderVO.setPost_code("37066");
		product_orderVO.setMem_adrs("YOUR HOUSE");
		product_orderVO.setCell_phone("0963258741");
		product_orderVO.setTel_phone("07-8912345");
		
		List<Product_order_listVO> testList = new ArrayList <Product_order_listVO>();
		Product_order_listVO product_order_listVO1 = new Product_order_listVO();
		product_order_listVO1.setProd_no("1");
		product_order_listVO1.setUnit_price(50);
		product_order_listVO1.setProd_quantity(100);
		product_order_listVO1.setDeli_status("0");
		product_order_listVO1.setDeli_time(java.sql.Date.valueOf("2016-12-06"));
		
		Product_order_listVO product_order_listVO2 = new Product_order_listVO();
		product_order_listVO2.setProd_no("2");
		product_order_listVO2.setUnit_price(30);
		product_order_listVO2.setProd_quantity(50);
		product_order_listVO2.setDeli_status("0");
		product_order_listVO2.setDeli_time(java.sql.Date.valueOf("2016-12-06"));
		
		testList.add(product_order_listVO1);
		testList.add(product_order_listVO2);
		
		dao.insertWithProduct_order_list(product_orderVO , testList);
		
		//新增
		/*Product_orderVO prod_ordVO1 = new Product_orderVO();
		prod_ordVO1.setMem_no("M00000002");
		prod_ordVO1.setCred_card_no("1A2B3C4D5E6F7G8H");
		prod_ordVO1.setValid_date(java.sql.Date.valueOf("2022-12-31"));
		prod_ordVO1.setValid_no("147");
		prod_ordVO1.setCred_card_type("2");
		prod_ordVO1.setTotal_money(8700);
		prod_ordVO1.setShip_name("KITTEN");
		prod_ordVO1.setPost_code("37066");
		prod_ordVO1.setMem_adrs("YOUR HOUSE");
		prod_ordVO1.setCell_phone("0963258741");
		prod_ordVO1.setTel_phone("07-8912345");
		dao.insert(prod_ordVO1);*/
		
		//修改
		/*Product_orderVO prod_ordVO2 = new Product_orderVO();
		prod_ordVO2.setProd_ord_no("20161208-00000006");
		prod_ordVO2.setMem_no("M00000002");
		prod_ordVO2.setCred_card_no("1A2B3C4D5E6F7G8H");
		prod_ordVO2.setValid_date(java.sql.Date.valueOf("2022-12-31"));
		prod_ordVO2.setValid_no("147");
		prod_ordVO2.setCred_card_type("2");
		prod_ordVO2.setTotal_money(78000);
		prod_ordVO2.setShip_name("KITTEN");
		prod_ordVO2.setPost_code("37066");
		prod_ordVO2.setMem_adrs("YOUR HOUSE");
		prod_ordVO2.setCell_phone("0963258741");
		prod_ordVO2.setTel_phone("07-8912345");
		dao.update(prod_ordVO2);*/
		
		//刪除
		/*dao.delete("20161208-00000006");*/
		
		// 查詢 - 單一
		/*Product_orderVO prod_ordVO3 = dao.findByPrimaryKey("20160630-00000001");
		System.out.print(prod_ordVO3.getProd_ord_no() + ",	");
		System.out.print(prod_ordVO3.getMem_no() + ",	");
		System.out.print(prod_ordVO3.getProd_ord_time() + ",	");
		System.out.print(prod_ordVO3.getCred_card_no() + ",	");
		System.out.print(prod_ordVO3.getValid_date() + ",	");
		System.out.print(prod_ordVO3.getValid_no() + ",	");
		System.out.print(prod_ordVO3.getCred_card_type() + ",	");
		System.out.print(prod_ordVO3.getTotal_money() + ",	");
		System.out.print(prod_ordVO3.getShip_name() + ",	");
		System.out.print(prod_ordVO3.getPost_code() + ",	");
		System.out.print(prod_ordVO3.getMem_adrs() + ",	");
		System.out.print(prod_ordVO3.getCell_phone() + ",	");
		System.out.print(prod_ordVO3.getTel_phone());
		System.out.println();*/
		
		// 查詢 - 經由會員編號
		/*List<Product_orderVO> list2 = dao.findByMem_no("M00000001");
		for (Product_orderVO partProduct_order : list2) {
			System.out.print(partProduct_order.getProd_ord_no() + ",	");
			System.out.print(partProduct_order.getMem_no() + ",	");
			System.out.print(partProduct_order.getProd_ord_time() + ",	");
			System.out.print(partProduct_order.getCred_card_no() + ",	");
			System.out.print(partProduct_order.getValid_date() + ",	");
			System.out.print(partProduct_order.getValid_no() + ",	");
			System.out.print(partProduct_order.getCred_card_type() + ",	");
			System.out.print(partProduct_order.getTotal_money() + ",	");
			System.out.print(partProduct_order.getShip_name() + ",	");
			System.out.print(partProduct_order.getPost_code() + ",	");
			System.out.print(partProduct_order.getMem_adrs() + ",	");
			System.out.print(partProduct_order.getCell_phone() + ",	");
			System.out.print(partProduct_order.getTel_phone());	
			System.out.println();
		}*/

		// 查詢 - 全部
		List<Product_orderVO> list = dao.getAll();
		for (Product_orderVO aProd : list) {
			System.out.print(aProd.getProd_ord_no() + ",	");
			System.out.print(aProd.getMem_no() + ",	");
			System.out.print(aProd.getProd_ord_time() + ",	");
			System.out.print(aProd.getCred_card_no() + ",	");
			System.out.print(aProd.getValid_date() + ",	");
			System.out.print(aProd.getValid_no() + ",	");
			System.out.print(aProd.getCred_card_type() + ",	");
			System.out.print(aProd.getTotal_money() + ",	");
			System.out.print(aProd.getShip_name() + ",	");
			System.out.print(aProd.getPost_code() + ",	");
			System.out.print(aProd.getMem_adrs() + ",	");
			System.out.print(aProd.getCell_phone() + ",	");
			System.out.print(aProd.getTel_phone());
			System.out.println();
		}
		
	}
	
}
