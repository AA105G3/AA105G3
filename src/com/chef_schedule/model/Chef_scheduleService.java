package com.chef_schedule.model;

import java.util.List;


public class Chef_scheduleService {

	private Chef_scheduleDAO_interface dao;

	public Chef_scheduleService() {
		dao = new Chef_scheduleDAO();
	}

	public Chef_scheduleVO addChef_schedule(String chef_no, java.sql.Date chef_scd_date, String chef_scd_mae, String chef_scd_cnt) {

		Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();
		chef_scheduleVO.setChef_no(chef_no);
		chef_scheduleVO.setChef_scd_date(chef_scd_date);
		chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
		chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
		dao.insert(chef_scheduleVO);

		return chef_scheduleVO;
	}

	public Chef_scheduleVO updateChef_schedule(String chef_no, String chef_scd_mae, java.sql.Date chef_scd_date,
			String chef_scd_cnt) {

		Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();

		chef_scheduleVO.setChef_no(chef_no);		
		chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
		chef_scheduleVO.setChef_scd_date(chef_scd_date);
		chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
		dao.update(chef_scheduleVO);

		return chef_scheduleVO;
	}

	public void deleteChef_schedule(String chef_no, java.sql.Date chef_scd_date, String chef_scd_mae) {
		dao.delete(chef_no,chef_scd_date,chef_scd_mae);
	}

	public Chef_scheduleVO getOneChef_schedule(String chef_no) {
		return dao.findByPrimaryKey(chef_no);
	}

	public List<Chef_scheduleVO> getAll() {
		return dao.getAll();
	}
}
