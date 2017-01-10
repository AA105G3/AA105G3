package com.chef.model;

import java.util.List;

public class ChefService {

	private ChefDAO_interface dao;

	public ChefService() {
		dao = new ChefDAO();
	}

	public ChefVO addChef(String mem_no, String chef_bnk, String chef_bnk_ac, 
			String chef_skill, byte[] chef_lic, byte[] chef_image, byte[] chef_movie1, byte[] chef_movie2, 
			String chef_id, String chef_name, String chef_area, String chef_intr, String chef_menu, 
			byte[] chef_reci_image1, byte[] chef_reci_image2, byte[] chef_reci_image3, 
			byte[] chef_reci_image4, byte[] chef_reci_image5) {

		ChefVO chefVO = new ChefVO();

		chefVO.setMem_no(mem_no);
		chefVO.setChef_bnk(chef_bnk);
		chefVO.setChef_bnk_ac(chef_bnk_ac);
		chefVO.setChef_skill(chef_skill);
		chefVO.setChef_lic(chef_lic);
		chefVO.setChef_image(chef_image);
		chefVO.setChef_movie1(chef_movie1);
		chefVO.setChef_movie2(chef_movie2);
		chefVO.setChef_id(chef_id);
		chefVO.setChef_name(chef_name);
		chefVO.setChef_area(chef_area);
		chefVO.setChef_intr(chef_intr);
		chefVO.setChef_menu(chef_menu);
		chefVO.setChef_reci_image1(chef_reci_image1);
		chefVO.setChef_reci_image2(chef_reci_image2);
		chefVO.setChef_reci_image3(chef_reci_image3);
		chefVO.setChef_reci_image4(chef_reci_image4);
		chefVO.setChef_reci_image5(chef_reci_image5);
		
		dao.insert(chefVO);

		return chefVO;
	}

	public ChefVO updateChef(String chef_no, String chef_bnk, 
			String chef_bnk_ac, String chef_skill, byte[] chef_lic, byte[] chef_image, byte[] chef_movie1, 
			byte[] chef_movie2, String chef_id, String chef_name, String chef_area, String chef_intr, 
			String chef_menu, byte[] chef_reci_image1, byte[] chef_reci_image2, byte[] chef_reci_image3, 
			byte[] chef_reci_image4, byte[] chef_reci_image5,String chef_chk_cond) {

		ChefVO chefVO = new ChefVO();

		chefVO.setChef_no(chef_no);
		chefVO.setChef_bnk(chef_bnk);
		chefVO.setChef_bnk_ac(chef_bnk_ac);
		chefVO.setChef_skill(chef_skill);
		chefVO.setChef_lic(chef_lic);
		chefVO.setChef_image(chef_image);
		chefVO.setChef_movie1(chef_movie1);
		chefVO.setChef_movie2(chef_movie2);
		chefVO.setChef_id(chef_id);
		chefVO.setChef_name(chef_name);
		chefVO.setChef_area(chef_area);
		chefVO.setChef_intr(chef_intr);
		chefVO.setChef_menu(chef_menu);
		chefVO.setChef_reci_image1(chef_reci_image1);
		chefVO.setChef_reci_image2(chef_reci_image2);
		chefVO.setChef_reci_image3(chef_reci_image3);
		chefVO.setChef_reci_image4(chef_reci_image4);
		chefVO.setChef_reci_image5(chef_reci_image5);
		chefVO.setChef_chk_cond(chef_chk_cond);
		dao.update(chefVO);

		return chefVO;
	}

	public void deleteChef(String chef_no) {
		dao.delete(chef_no);
	}

	public ChefVO getOneChef(String chef_no) {
		return dao.findByPrimaryKey(chef_no);
	}

	public List<ChefVO> getAll() {
		return dao.getAll();
	}
	public ChefVO getOneChefByMem_no(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
}
