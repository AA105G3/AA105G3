package com.chef_order_list.model;

import java.sql.Timestamp;
import java.util.List;

public class Chef_order_listService {

	private Chef_order_listDAO_interface dao;

	public Chef_order_listService() {
		dao = new Chef_order_listDAO();
	}

	public Chef_order_listVO addChef_order_list(String mem_no, String chef_no, Double chef_ord_cost, 
			Timestamp chef_act_date,String chef_ord_place, String chef_ord_cnt) {

		Chef_order_listVO chef_order_listVO = new Chef_order_listVO();

		chef_order_listVO.setMem_no(mem_no);
		chef_order_listVO.setChef_no(chef_no);
		chef_order_listVO.setChef_ord_cost(chef_ord_cost);
		chef_order_listVO.setChef_act_date(chef_act_date);
		chef_order_listVO.setChef_ord_place(chef_ord_place);
		chef_order_listVO.setChef_ord_cnt(chef_ord_cnt);
	
		dao.insert(chef_order_listVO);

		return chef_order_listVO;
	}

	public Chef_order_listVO updateChef_order_list(String chef_ord_no, Double chef_ord_cost, 
			Timestamp chef_act_date,String chef_ord_place, String chef_ord_cnt, String chef_ord_con) {

		Chef_order_listVO chef_order_listVO = new Chef_order_listVO();

		chef_order_listVO.setChef_ord_no(chef_ord_no);
		chef_order_listVO.setChef_ord_cost(chef_ord_cost);
		chef_order_listVO.setChef_act_date(chef_act_date);
		chef_order_listVO.setChef_ord_place(chef_ord_place);
		chef_order_listVO.setChef_ord_cnt(chef_ord_cnt);
		chef_order_listVO.setChef_ord_con(chef_ord_con);
		dao.update(chef_order_listVO);

		return chef_order_listVO;
	}

	public void deleteChef_order_list(String chef_ord_no) {
		dao.delete(chef_ord_no);
	}

	public Chef_order_listVO getOneChef_order_list(String chef_ord_no) {
		return dao.findByPrimaryKey(chef_ord_no);
	}

	public List<Chef_order_listVO> getAll() {
		return dao.getAll();
	}
}
