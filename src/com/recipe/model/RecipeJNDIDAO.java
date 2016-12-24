package com.recipe.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.recipe_cont.model.*;


public class RecipeJNDIDAO implements RecipeDAO_interface
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
			"INSERT INTO  recipe (recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_edit) "
			+ "VALUES ('R'||lpad(recipe_seq.NEXTVAL,8,0),?,?,?,?,?,?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe order by recipe_no";
	private static final String GET_ONE_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe where recipe_no = ?";
	private static final String DELETE = 
			"DELETE FROM recipe where recipe_no = ?";
	private static final String UPDATE = 
			"UPDATE recipe set recipe_name=?,recipe_intro=?,food_mater=?,recipe_pic=?,recipe_time=sysdate,recipe_edit = ? where recipe_no = ?";
	private static final String UPDATEVIEWS = 
			"UPDATE recipe set recipe_total_views=?,recipe_week_views=? where recipe_no = ?";
	private static final String UPDATELIKE =
			"UPDATE recipe set recipe_like=? where recipe_no = ?";
	private static final String WeekViewsZero =
			"UPDATE recipe set recipe_week_views = 0 where recipe_no = ?";
	
	
	@Override
	public void insert(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			if(recipe_pic !=null){
				long piclen = recipe_pic.length;
				InputStream bais = new ByteArrayInputStream(recipe_pic);
				pstmt.setBinaryStream(5, bais, piclen);
			}else{
				pstmt.setBinaryStream(5, null);
			}

			
			pstmt.setString(1, recipeVO.getMem_no());
			pstmt.setString(2, recipeVO.getRecipe_name());
			pstmt.setString(3, recipeVO.getRecipe_intro());
			pstmt.setString(4, recipeVO.getFood_mater());


			
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
	public void update(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			long piclen = recipe_pic.length;
			InputStream bais = new ByteArrayInputStream(recipe_pic);
			
			
			pstmt.setString(1, recipeVO.getRecipe_name());
			pstmt.setString(2, recipeVO.getRecipe_intro());
			pstmt.setString(3, recipeVO.getFood_mater());
			pstmt.setBinaryStream(4, bais, piclen);
			pstmt.setString(5, recipeVO.getRecipe_edit());
			pstmt.setString(6, recipeVO.getRecipe_no());
			

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
	public RecipeVO findByPrimaryKey(String recipe_no)
	{
		// TODO Auto-generated method stub
		RecipeVO recipeVO =null;
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
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(rs.getString("recipe_no"));
				recipeVO.setMem_no(rs.getString("mem_no"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_intro(rs.getString("recipe_intro"));
				recipeVO.setFood_mater(rs.getString("food_mater"));
				recipeVO.setRecipe_pic(rs.getBytes("recipe_pic"));
				recipeVO.setRecipe_like(rs.getInt("recipe_like"));
				recipeVO.setRecipe_total_views(rs.getInt("recipe_total_views"));
				recipeVO.setRecipe_week_views(rs.getInt("recipe_week_views"));
				recipeVO.setRecipe_time(rs.getTimestamp("recipe_time"));
				recipeVO.setRecipe_edit(rs.getString("recipe_edit"));
				recipeVO.setRecipe_classify(rs.getString("recipe_classify"));
				
				
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
		return recipeVO;
	}

	@Override
	public List<RecipeVO> getAll()
	{
		// TODO Auto-generated method stub
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(rs.getString("recipe_no"));
				recipeVO.setMem_no(rs.getString("mem_no"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_intro(rs.getString("recipe_intro"));
				recipeVO.setFood_mater(rs.getString("food_mater"));
				recipeVO.setRecipe_pic(rs.getBytes("recipe_pic"));
				recipeVO.setRecipe_like(rs.getInt("recipe_like"));
				recipeVO.setRecipe_total_views(rs.getInt("recipe_total_views"));
				recipeVO.setRecipe_week_views(rs.getInt("recipe_week_views"));
				recipeVO.setRecipe_time(rs.getTimestamp("recipe_time"));
				recipeVO.setRecipe_edit(rs.getString("recipe_edit"));
				recipeVO.setRecipe_classify(rs.getString("recipe_classify"));
				
				list.add(recipeVO);
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
	public void updateViews(RecipeVO recipeVO)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEVIEWS);

			pstmt.setInt(1, recipeVO.getRecipe_total_views());
			pstmt.setInt(2, recipeVO.getRecipe_week_views());
			pstmt.setString(3, recipeVO.getRecipe_no());
			

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
	public void updateLike(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATELIKE);

			pstmt.setInt(1, recipeVO.getRecipe_like());
			pstmt.setString(2, recipeVO.getRecipe_no());
			

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
	public void changeWeekViewsZero(String recipe_no)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(WeekViewsZero);

			pstmt.setString(1,recipe_no );
			

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
	public void insertWithRecipe_conts(RecipeVO recipeVO, List<Recipe_contVO> list)
	{
		PreparedStatement pstmt = null;

		Connection con = null;
		try {
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增食譜
			String cols[] = {"RECIPE_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			if(recipe_pic !=null){
				long piclen = recipe_pic.length;
				InputStream bais = new ByteArrayInputStream(recipe_pic);
				pstmt.setBinaryStream(5, bais, piclen);
			}else{
				pstmt.setBinaryStream(5, null);
			}

			pstmt.setString(1, recipeVO.getMem_no());
			pstmt.setString(2, recipeVO.getRecipe_name());
			pstmt.setString(3, recipeVO.getRecipe_intro());
			pstmt.setString(4, recipeVO.getFood_mater());
			pstmt.setString(6, recipeVO.getRecipe_edit());
			
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			String next_recipe_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_recipe_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_recipe_no +"(剛新增成功的食譜編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增食譜內容
			Recipe_contJNDIDAO dao = new Recipe_contJNDIDAO();
			System.out.println("list.size()-A="+list.size());
			for (Recipe_contVO aRecipe_cont : list) {
				aRecipe_cont.setRecipe_no(next_recipe_no);;
				dao.insert2(aRecipe_cont,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增食譜編號" + next_recipe_no + "時,共有步驟" + list.size()
					+ "條同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-recipe");
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
