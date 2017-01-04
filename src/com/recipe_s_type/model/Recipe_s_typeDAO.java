package com.recipe_s_type.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Recipe_s_typeDAO implements Recipe_s_typeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, recipe_s_typeVO.getS_type_name());
			pstmt.setString(2, recipe_s_typeVO.getParent_type());
			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, recipe_s_typeVO.getS_type_name());
			pstmt.setString(2, recipe_s_typeVO.getParent_type());
			pstmt.setString(3, recipe_s_typeVO.getRecipe_s_type_no());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, recipe_s_type_no);
			pstmt.executeUpdate();

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
	}

	@Override
	public Recipe_s_typeVO findByPrimaryKey(String recipe_s_type_no) {
		Recipe_s_typeVO recipe_s_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, recipe_s_type_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_s_typeVO = new Recipe_s_typeVO();
				recipe_s_typeVO.setS_type_name(rs.getString("s_type_name"));
				recipe_s_typeVO.setParent_type(rs.getString("parent_type"));
				recipe_s_typeVO.setRecipe_s_type_no(rs.getString("recipe_s_type_no"));
			}

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recipe_s_typeVO = new Recipe_s_typeVO();
				recipe_s_typeVO.setRecipe_s_type_no(rs.getString("recipe_s_type_no"));
				recipe_s_typeVO.setS_type_name(rs.getString("s_type_name"));
				recipe_s_typeVO.setParent_type(rs.getString("parent_type"));
				list.add(recipe_s_typeVO);
			}

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

}
