package com.chef_refund.model;

import java.util.List;

public class Chef_refundService {

	private Chef_refundDAO_interface dao;

	public Chef_refundService() {
		dao = new Chef_refundDAO();
	}

	public Chef_refundVO addChef_refund(String chef_ord_no, String emp_no, Double chef_rfd_per,
			String mem_bnk, String mem_bnk_ac, String chef_ord_chk_cond, String chef_ord_red_cnt) {

		Chef_refundVO chef_refundVO = new Chef_refundVO();

		chef_refundVO.setChef_ord_no(chef_ord_no);
		chef_refundVO.setEmp_no(emp_no);
		chef_refundVO.setChef_rfd_per(chef_rfd_per);
		chef_refundVO.setMem_bnk(mem_bnk);
		chef_refundVO.setMem_bnk_ac(mem_bnk_ac);
		chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
		chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);
		dao.insert(chef_refundVO);

		return chef_refundVO;
	}

	public Chef_refundVO updateChef_refund(String chef_rfd_no, String chef_ord_no, String emp_no,
			Double chef_rfd_per, String mem_bnk, String mem_bnk_ac, String chef_ord_chk_cond, String chef_ord_red_cnt) {

		Chef_refundVO chef_refundVO = new Chef_refundVO();

		chef_refundVO.setChef_rfd_no(chef_rfd_no);
		chef_refundVO.setChef_ord_no(chef_ord_no);
		chef_refundVO.setEmp_no(emp_no);
		chef_refundVO.setChef_rfd_per(chef_rfd_per);
		chef_refundVO.setMem_bnk(mem_bnk);
		chef_refundVO.setMem_bnk_ac(mem_bnk_ac);
		chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
		chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);
		dao.update(chef_refundVO);

		return chef_refundVO;
	}
	
	public Chef_refundVO update_backChef_refund(String chef_rfd_no, Double chef_rfd_per, String chef_ord_chk_cond, String chef_ord_red_cnt) {

		Chef_refundVO chef_refundVO = new Chef_refundVO();

		chef_refundVO.setChef_rfd_per(chef_rfd_per);
		chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
		chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);
		dao.update_back(chef_refundVO);

		return chef_refundVO;
	}

	public void deleteChef_refund(String chef_rfd_no) {
		dao.delete(chef_rfd_no);
	}

	public Chef_refundVO getOneChef_refund(String chef_rfd_no) {
		return dao.findByPrimaryKey(chef_rfd_no);
	}

	public List<Chef_refundVO> getAll() {
		return dao.getAll();
	}
}
