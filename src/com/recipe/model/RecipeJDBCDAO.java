package com.recipe.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;
import com.recipe_cont.model.*;


public class RecipeJDBCDAO implements RecipeDAO_interface
{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = 
			"INSERT INTO  recipe (recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_edit) "
			+ "VALUES ('R'||lpad(recipe_seq.NEXTVAL,8,0),?,?,?,?,?,?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe order by recipe_time desc";
	private static final String Get_ALL_STMT_By_Views = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe order by recipe_total_views desc";
	private static final String GET_ONE_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe where recipe_no = ?";
	private static final String GET_BY_Mem_No = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe where mem_no = ?";
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
	private static final String SearchByName = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe where recipe_name like ?";
	private static final String SearchByFoodMater = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
					+ ",recipe_week_views,recipe_time,recipe_edit,recipe_classify from recipe where food_mater like ?";
	// for android by cyh
		private static final String GET_IMAGE_STMT = "SELECT recipe_pic FROM recipe where recipe_no=?"; 
	
	@Override
	public void insert(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
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

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
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
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			long piclen = recipe_pic.length;
			InputStream bais = new ByteArrayInputStream(recipe_pic);
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipeVO.getRecipe_name());
			pstmt.setString(2, recipeVO.getRecipe_intro());
			pstmt.setString(3, recipeVO.getFood_mater());
			pstmt.setBinaryStream(4, bais, piclen);
			pstmt.setString(5, recipeVO.getRecipe_edit());
			pstmt.setString(6, recipeVO.getRecipe_no());
			

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
	public void delete(String recipe_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, recipe_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<RecipeVO> findByMem_no(String mem_no)
	{
		// TODO Auto-generated method stub
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(GET_BY_Mem_No);
			pstmt.setString(1, mem_no);
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<RecipeVO> getAllOrderByViews()
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(Get_ALL_STMT_By_Views);
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATEVIEWS);

			pstmt.setInt(1, recipeVO.getRecipe_total_views());
			pstmt.setInt(2, recipeVO.getRecipe_week_views());
			pstmt.setString(3, recipeVO.getRecipe_no());
			

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
	public void updateLike(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATELIKE);

			pstmt.setInt(1, recipeVO.getRecipe_like());
			pstmt.setString(2, recipeVO.getRecipe_no());
			

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
	public void changeWeekViewsZero(String recipe_no)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(WeekViewsZero);

			pstmt.setString(1, recipe_no);
			

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
	public void insertWithRecipe_conts(RecipeVO recipeVO, List<Recipe_contVO> list)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			
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
			Recipe_contJDBCDAO dao = new Recipe_contJDBCDAO();
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	@Override
	public List<RecipeVO> serachByRecipe_name(String recipe_name)
	{
		// TODO Auto-generated method stub
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(SearchByName);
			pstmt.setString(1, "%"+recipe_name+"%");
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<RecipeVO> serachByFood_Mater(String food_mater)
	{
		// TODO Auto-generated method stub
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(SearchByFoodMater);
			pstmt.setString(1, "%"+food_mater+"%");
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
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main(String[] args) throws Exception
	{
		RecipeJDBCDAO dao = new RecipeJDBCDAO();
		
		//insertWithRecipe_conts
//		File recipePic = new File("WebContent/images/recipe","1.jpg");
//		InputStream rfis = new FileInputStream(recipePic);
//		byte[] buffer = new byte[rfis.available()];
//		
//		RecipeVO recipeVO = new RecipeVO();
//		recipeVO.setMem_no("M00000004");
//		recipeVO.setRecipe_name("柳橙水果茶");
//		recipeVO.setRecipe_intro("在這寒冷的低溫裡，運用時令盛產的柳橙來泡壺暖呼呼的水果茶吧，以新鮮柳橙汁為基底，很是淡雅、舒服＾＾");
//		recipeVO.setFood_mater("錫蘭紅茶-2包+新鮮柳橙汁-100ml+水-300ml+砂糖（視喜愛甜度調整）-30g+喜愛的水果切丁-適量");
//		recipeVO.setRecipe_edit("已發布");
//		recipeVO.setRecipe_pic(buffer);
//		
//		List<Recipe_contVO> testList = new ArrayList<Recipe_contVO>();  //準備置入食譜步驟項目
//		
//		File recipe_cont1_pic = new File("WebContent/images/recipe_cont","1.jpg"); //第一個步驟
//		InputStream rcfis = new FileInputStream(recipe_cont1_pic);
//		buffer = new byte[rcfis.available()];
//		
//		Recipe_contVO recipe_contVO1 = new Recipe_contVO();
//		recipe_contVO1.setStep(1);
//		recipe_contVO1.setStep_pic(buffer);
//		recipe_contVO1.setStep_cont("321321");
//		
//		File recipe_cont2_pic = new File("WebContent/images/recipe_cont","2.jpg"); //第二個步驟
//		rcfis = new FileInputStream(recipe_cont1_pic);
//		buffer = new byte[rcfis.available()];
//		
//		Recipe_contVO recipe_contVO2 = new Recipe_contVO();
//		recipe_contVO2.setStep(2);
//		recipe_contVO2.setStep_pic(buffer);
//		recipe_contVO2.setStep_cont("123321");
//		
//		testList.add(recipe_contVO1);
//		testList.add(recipe_contVO2);
//		
//		dao.insertWithRecipe_conts(recipeVO, testList);
		
		
		//insert
		
//		File pic = new File("WebContent/images/recipe","1.jpg");
//		InputStream fis = new FileInputStream(pic);
//		byte[] buffer = new byte[fis.available()];
//		
//		RecipeVO recipeVO1 = new RecipeVO();
//		recipeVO1.setMem_no("M00000004");
//		recipeVO1.setRecipe_name("柳橙水果茶");
//		recipeVO1.setRecipe_intro("在這寒冷的低溫裡，運用時令盛產的柳橙來泡壺暖呼呼的水果茶吧，以新鮮柳橙汁為基底，很是淡雅、舒服＾＾");
//		recipeVO1.setFood_mater("錫蘭紅茶-2包+新鮮柳橙汁-100ml+水-300ml+砂糖（視喜愛甜度調整）-30g+喜愛的水果切丁-適量");
//		recipeVO1.setRecipe_pic(buffer);
//		
//		dao.insert(recipeVO1);
//		fis.close();
		
		//update
//		File pic = new File("WebContent/images","cat.jpg");
//		InputStream fis = new FileInputStream(pic);
//		byte[] buffer = new byte[fis.available()];
//		
//		RecipeVO recipeVO2 = new RecipeVO();
//		recipeVO2.setRecipe_name("修改過的名字");
//		recipeVO2.setRecipe_intro("修改過的簡介");
//		recipeVO2.setFood_mater("食材1-100g+食材2-200g");
//		recipeVO2.setRecipe_pic(buffer);
//		recipeVO2.setRecipe_no("R00000002");
//		recipeVO2.setRecipe_edit("編輯中");
//		
//		dao.update(recipeVO2);
		
		//update Views
//		RecipeVO recipeVO5 = new RecipeVO();
//		recipeVO5.setRecipe_total_views(20000);
//		recipeVO5.setRecipe_week_views(5000);
//		recipeVO5.setRecipe_no("R00000003");
//		
//		dao.updateViews(recipeVO5);
		
		//update Like
		
//		RecipeVO recipeVO6 = new RecipeVO();
//		recipeVO6.setRecipe_like(888);
//		recipeVO6.setRecipe_no("R00000004");
//		dao.updateLike(recipeVO6);
		
		//WeekViewsZero
//		RecipeVO recipeVO7 = new RecipeVO();
//		dao.changeWeekViewsZero("R00000002");
		//delete
//		dao.delete("R00000002");
		
//		search target
//		RecipeVO recipeVO3 = dao.findByPrimaryKey("R00000001");
//		System.out.print("| "+recipeVO3.getRecipe_no()+" | ");
//		System.out.print(recipeVO3.getMem_no()+" | ");
//		System.out.print(recipeVO3.getRecipe_name()+" | ");
//		System.out.print(recipeVO3.getRecipe_intro()+" | ");
//		System.out.print(recipeVO3.getFood_mater()+" | ");
//		System.out.print(recipeVO3.getRecipe_like()+" | ");
//		System.out.print(recipeVO3.getRecipe_total_views()+" | ");
//		System.out.print(recipeVO3.getRecipe_week_views()+" | ");
//		System.out.print(new java.text.SimpleDateFormat().format(recipeVO3.getRecipe_time())+" | ");
//		System.out.print(recipeVO3.getRecipe_edit()+" | ");
//		System.out.print(recipeVO3.getRecipe_classify()+" | ");

		//search by mem_no
		
//		List<RecipeVO> list = dao.findByMem_no("M00000005");
//		for(RecipeVO recipeVO8: list){
//			System.out.print("| "+recipeVO8.getRecipe_no()+" | ");
//		System.out.print(recipeVO8.getMem_no()+" | ");
//		System.out.print(recipeVO8.getRecipe_name()+" | ");
//		System.out.print(recipeVO8.getRecipe_intro()+" | ");
//		System.out.print(recipeVO8.getFood_mater()+" | ");
//		System.out.print(recipeVO8.getRecipe_like()+" | ");
//		System.out.print(recipeVO8.getRecipe_total_views()+" | ");
//		System.out.print(recipeVO8.getRecipe_week_views()+" | ");
//		System.out.print(recipeVO8.getRecipe_time()+" | ");
//		System.out.print(recipeVO8.getRecipe_pic()+" | ");
//		System.out.print(recipeVO8.getRecipe_edit()+" | ");
//		System.out.print(recipeVO8.getRecipe_classify()+" | ");
//			System.out.println();
//		}
		
		
		//search by recipe_name
		
//		List<RecipeVO> list = dao.serachByFood_Mater("蘿蔔");
//		for(RecipeVO recipeVO8: list){
//			System.out.print("| "+recipeVO8.getRecipe_no()+" | ");
//		System.out.print(recipeVO8.getMem_no()+" | ");
//		System.out.print(recipeVO8.getRecipe_name()+" | ");
//		System.out.print(recipeVO8.getRecipe_intro()+" | ");
//		System.out.print(recipeVO8.getFood_mater()+" | ");
//		System.out.print(recipeVO8.getRecipe_like()+" | ");
//		System.out.print(recipeVO8.getRecipe_total_views()+" | ");
//		System.out.print(recipeVO8.getRecipe_week_views()+" | ");
//		System.out.print(recipeVO8.getRecipe_time()+" | ");
//		System.out.print(recipeVO8.getRecipe_pic()+" | ");
//		System.out.print(recipeVO8.getRecipe_edit()+" | ");
//		System.out.print(recipeVO8.getRecipe_classify()+" | ");
//			System.out.println();
//		}
		
		//search all
//		List<RecipeVO> list = dao.getAllOrderByViews();
//		for(RecipeVO recipeVO4: list){
//			System.out.print("| "+recipeVO4.getRecipe_no()+" | ");
//		System.out.print(recipeVO4.getMem_no()+" | ");
//		System.out.print(recipeVO4.getRecipe_name()+" | ");
//		System.out.print(recipeVO4.getRecipe_intro()+" | ");
//		System.out.print(recipeVO4.getFood_mater()+" | ");
//		System.out.print(recipeVO4.getRecipe_like()+" | ");
//		System.out.print(recipeVO4.getRecipe_total_views()+" | ");
//		System.out.print(recipeVO4.getRecipe_week_views()+" | ");
//		System.out.print(recipeVO4.getRecipe_time()+" | ");
//		System.out.print(recipeVO4.getRecipe_pic()+" | ");
//		System.out.print(recipeVO4.getRecipe_edit()+" | ");
//		System.out.print(recipeVO4.getRecipe_classify()+" | ");
//			System.out.println();
//		}
	}

	@Override
	public byte[] getImage(String recipe_no) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

	

	

	
}
