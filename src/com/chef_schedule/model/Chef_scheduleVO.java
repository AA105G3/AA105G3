package com.chef_schedule.model;
import java.sql.Date;

public class Chef_scheduleVO implements java.io.Serializable{
	private String chef_no;
	private String chef_scd_mae;
	private String chef_scd_cnt;
	private Date chef_scd_date;
	
	public String getChef_no() {
		return chef_no;
	}
	public void setChef_no(String chef_no) {
		this.chef_no = chef_no;
	}
	public String getChef_scd_mae() {
		return chef_scd_mae;
	}
	public void setChef_scd_mae(String chef_scd_mae) {
		this.chef_scd_mae = chef_scd_mae;
	}
	public String getChef_scd_cnt() {
		return chef_scd_cnt;
	}
	public void setChef_scd_cnt(String chef_scd_cnt) {
		this.chef_scd_cnt = chef_scd_cnt;
	}
	public Date getChef_scd_date() {
		return chef_scd_date;
	}
	public void setChef_scd_date(Date chef_scd_date) {
		this.chef_scd_date = chef_scd_date;
	}
}
