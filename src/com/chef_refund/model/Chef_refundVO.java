package com.chef_refund.model;


public class Chef_refundVO implements java.io.Serializable{
	private String chef_rfd_no;
	private String chef_ord_no;
	private String emp_no;
	private Double chef_rfd_per;
	private String mem_bnk;
	private String mem_bnk_ac;
	private String chef_ord_chk_cond;
	private String chef_ord_red_cnt;
	
	
	public String getChef_rfd_no() {
		return chef_rfd_no;
	}
	public void setChef_rfd_no(String chef_rfd_no) {
		this.chef_rfd_no = chef_rfd_no;
	}
	public String getChef_ord_no() {
		return chef_ord_no;
	}
	public void setChef_ord_no(String chef_ord_no) {
		this.chef_ord_no = chef_ord_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Double getChef_rfd_per() {
		return chef_rfd_per;
	}
	public void setChef_rfd_per(Double chef_rfd_per) {
		this.chef_rfd_per = chef_rfd_per;
	}
	public String getMem_bnk() {
		return mem_bnk;
	}
	public void setMem_bnk(String mem_bnk) {
		this.mem_bnk = mem_bnk;
	}
	public String getMem_bnk_ac() {
		return mem_bnk_ac;
	}
	public void setMem_bnk_ac(String mem_bnk_ac) {
		this.mem_bnk_ac = mem_bnk_ac;
	}
	public String getChef_ord_chk_cond() {
		return chef_ord_chk_cond;
	}
	public void setChef_ord_chk_cond(String chef_ord_chk_cond) {
		this.chef_ord_chk_cond = chef_ord_chk_cond;
	}
	public String getChef_ord_red_cnt() {
		return chef_ord_red_cnt;
	}
	public void setChef_ord_red_cnt(String chef_ord_red_cnt) {
		this.chef_ord_red_cnt = chef_ord_red_cnt;
	}
}
