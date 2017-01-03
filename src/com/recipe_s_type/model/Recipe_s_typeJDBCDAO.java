package com.recipe_s_type.model;

import java.util.*;

import java.sql.*;

public class Recipe_s_typeJDBCDAO implements Recipe_s_typeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO recipe_s_type (recipe_s_type_no, s_type_name, parent_type) VALUES ('RS'||lpad(RECIPE_S_TYPE_SEQ.NEXTVAL,4,0), ?, ?)";
	private static final String Get_ALL_STMT = "select recipe_s_type_no, s_type_name, parent_type from recipe_s_type order by recipe_s_type_no";
	private static final String GET_ONE_STMT = "select recipe_s_type_no, s_type_name, parent_type from recipe_s_type where recipe_s_type_no = ?";
	private static final String DELETE = "DELETE FROM recipe_s_type where recipe_s_type_no = ?";
	private static final String UPDATE = "UPDATE recipe_s_type set s_type_name = ?, parent_type = ? where recipe_s_type_no = ? ";

	@Override
	public void insert(Recipe_s_typeVO recipe_s_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_s_typeVO.getS_type_name());
			pstmt.setString(2, recipe_s_typeVO.getParent_type());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Recipe_s_typeVO recipe_s_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_s_typeVO.getS_type_name());
			pstmt.setString(2, recipe_s_typeVO.getParent_type());			
			pstmt.setString(3, recipe_s_typeVO.getRecipe_s_type_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String recipe_s_type_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_s_type_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	@Override
//	public void deleteOne(Recipe_s_typeVO recipe_s_typeVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, psw);
//			pstmt = con.prepareStatement(DELETE_ONE);
//
//			pstmt.setString(1, recipe_s_typeVO.getRecipe_s_type_no());
//			pstmt.setString(2, recipe_s_typeVO.getS_type_name());
//			
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}


	@Override
	public Recipe_s_typeVO findByPrimaryKey(String recipe_s_type_no) {
		Recipe_s_typeVO recipe_s_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recipe_s_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_s_typeVO = new Recipe_s_typeVO();
				
				recipe_s_typeVO.setS_type_name(rs.getString("s_type_name"));
				recipe_s_typeVO.setParent_type(rs.getString("parent_type"));
				recipe_s_typeVO.setRecipe_s_type_no(rs.getString("recipe_s_type_no"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return recipe_s_typeVO;
	}

	@Override
	public List<Recipe_s_typeVO> getAll() {
		List<Recipe_s_typeVO> list = new ArrayList<Recipe_s_typeVO>();
		Recipe_s_typeVO recipe_s_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_s_typeVO = new Recipe_s_typeVO();
				recipe_s_typeVO.setRecipe_s_type_no(rs.getString("recipe_s_type_no"));
				recipe_s_typeVO.setS_type_name(rs.getString("s_type_name"));
				recipe_s_typeVO.setParent_type(rs.getString("parent_type"));
				list.add(recipe_s_typeVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Recipe_s_typeJDBCDAO dao = new Recipe_s_typeJDBCDAO();
		//
		// insert
		 Recipe_s_typeVO recipe_s_typeVO1 = new Recipe_s_typeVO();
		
//		 recipe_s_typeVO1.setS_type_name("新的小類別");
//		 recipe_s_typeVO1.setParent_type("RM0006");
//		 dao.insert(recipe_s_typeVO1);

		// update
//		 Recipe_s_typeVO recipe_s_typeVO2 = new Recipe_s_typeVO();
//		 recipe_s_typeVO2.setRecipe_s_type_no("RS0007");
//		 recipe_s_typeVO2.setS_type_name("被更改的小類別");
//		 recipe_s_typeVO2.setParent_type("RM0001");
//		 dao.update(recipe_s_typeVO2);

		// delete
//		 dao.delete("RS0007");
		 
		 //delete one
//		 Recipe_s_typeVO recipe_s_typeVO2 = new Recipe_s_typeVO();
//		 recipe_s_typeVO2.setRecipe_s_type_no("M00000001");
//		 recipe_s_typeVO2.setS_type_name("M00000002");
//		 dao.deleteOne(recipe_s_typeVO2);

		// // search target
//		 Recipe_s_typeVO recipe_s_typeVO3 = dao.findByPrimaryKey("RS0001");
//		 System.out.print(recipe_s_typeVO3.getRecipe_s_type_no() + " | ");
//		 System.out.print(recipe_s_typeVO3.getS_type_name() + " | ");
//		 System.out.print(recipe_s_typeVO3.getParent_type() + " | ");

		// search all
//		List<Recipe_s_typeVO> list = dao.getAll();
//		for (Recipe_s_typeVO recipe_s_typeVO4 : list) {
//			System.out.print(recipe_s_typeVO4.getRecipe_s_type_no() + " | ");
//			System.out.print(recipe_s_typeVO4.getS_type_name() + " | ");
//			System.out.print(recipe_s_typeVO4.getParent_type() + " | ");
//			System.out.println();
//		}
	}
}
