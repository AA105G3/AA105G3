package com.recipe_type_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Recipe_type_infoDAO implements Recipe_type_infoDAO_interface
{
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
			"INSERT INTO  recipe_type_info (recipe_no,recipe_type_no,type_range) VALUES (?, ?, ?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,recipe_type_no,type_range from recipe_type_info order by recipe_no";
	private static final String GET_ONE_STMT = 
			"select recipe_no,recipe_type_no,type_range from recipe_type_info where recipe_no = ?";
	private static final String DELETE = 
			"DELETE FROM recipe_type_info where recipe_no= ?";
	private static final String DELETE_ONE_TYPE =
			"DELETE FROM recipe_type_info where (recipe_no=?) and (recipe_type_no=?)";
	private static final String UPDATE = 
			"UPDATE recipe_type_info set recipe_type_no = ? ,type_range = ? where (recipe_no = ? ) and (recipe_type_no = ?)";
	private static final String GET_BY_TYPE = 
			"select recipe_no,recipe_type_no,type_range from recipe_type_info where recipe_type_no = ?";
	
	
	@Override
	public void insert(Recipe_type_infoVO recipe_type_infoVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_type_infoVO.getRecipe_no());
			pstmt.setString(2, recipe_type_infoVO.getRecipe_type_no());
			pstmt.setString(3, recipe_type_infoVO.getType_range());
			pstmt.executeUpdate();

		
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException se)
				{
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Recipe_type_infoVO recipe_type_infoVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_type_infoVO.getRecipe_type_no());
			pstmt.setString(2, recipe_type_infoVO.getType_range());
			pstmt.setString(3, recipe_type_infoVO.getRecipe_no());
			pstmt.setString(4, recipe_type_infoVO.getTarget_recipe_type_no());

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
	public void delete(String recipe_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, recipe_no);
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteOneType(String recipe_no, String recipe_type_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONE_TYPE);
			
			pstmt.setString(1, recipe_no);
			pstmt.setString(2, recipe_type_no);
			
			pstmt.executeUpdate();
		}catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Recipe_type_infoVO> findByPrimaryKey(String recipe_no)
	{
		// TODO Auto-generated method stub
		List<Recipe_type_infoVO> list = new ArrayList<Recipe_type_infoVO>();
		Recipe_type_infoVO recipe_type_infoVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,recipe_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_type_infoVO.setRecipe_type_no(rs.getString("recipe_type_no"));
				recipe_type_infoVO.setType_range(rs.getString("type_range"));
				
				list.add(recipe_type_infoVO);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Recipe_type_infoVO> getAll()
	{
		// TODO Auto-generated method stub
		List<Recipe_type_infoVO> list = new ArrayList<Recipe_type_infoVO>();
		Recipe_type_infoVO recipe_type_infoVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_type_infoVO.setRecipe_type_no(rs.getString("recipe_type_no"));
				recipe_type_infoVO.setType_range(rs.getString("type_range"));
				
				list.add(recipe_type_infoVO);
			}
		}catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public Set<Recipe_type_infoVO> findByType_no(String recipe_type_no)
	{
		// TODO Auto-generated method stub
		Set<Recipe_type_infoVO> set = new LinkedHashSet<Recipe_type_infoVO>();
		Recipe_type_infoVO recipe_type_infoVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_TYPE);
			
			pstmt.setString(1,recipe_type_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_type_infoVO.setRecipe_type_no(rs.getString("recipe_type_no"));
				recipe_type_infoVO.setType_range(rs.getString("type_range"));
				
				set.add(recipe_type_infoVO);
			}
		}catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return set;
	}
	
	

}
