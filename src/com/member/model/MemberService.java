package com.member.model;

import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String mem_name, String mem_ac, String mem_pw, byte[] mem_image, String mem_sex, String mem_phone, 
			String mem_email, String mem_adrs, String mem_own, String mem_history, String mem_online){
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_name(mem_name);
		memberVO.setMem_ac(mem_ac);
		memberVO.setMem_pw(mem_pw);
		memberVO.setMem_image(mem_image);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_adrs(mem_adrs);
		memberVO.setMem_own(mem_own);
		memberVO.setMem_history(mem_history);
		memberVO.setMem_online(mem_online);
		dao.insert(memberVO);
		
		return memberVO;
	}	
	
	public MemberVO updateMember(String mem_no, String mem_name, String mem_ac, String mem_pw, byte[] mem_image, 
			String mem_sex, String mem_phone, String mem_email, String mem_adrs, String mem_own, String mem_history, String mem_online){
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_no(mem_no);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_ac(mem_ac);
		memberVO.setMem_pw(mem_pw);
		memberVO.setMem_image(mem_image);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_adrs(mem_adrs);
		memberVO.setMem_own(mem_own);
		memberVO.setMem_history(mem_history);
		memberVO.setMem_online(mem_online);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMember(String mem_name, byte[] mem_image){
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_name(mem_name);
		memberVO.setMem_image(mem_image);
		dao.update(memberVO, 1);
		
		return memberVO;
	}
	
	public void deleteMember(String mem_no) {
		dao.delete(mem_no);
	}

	public MemberVO getOneMember(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public MemberVO getOneMember(String mem_no, Integer android) {
		return dao.findByPrimaryKey(mem_no, android);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public byte[] getImage(String mem_no) {
		return dao.getImage(mem_no);
	}
	
	public MemberVO getOneByMem_ac(String mem_ac) {
		return dao.findByAC(mem_ac);
	}

	public MemberVO updateMemOwn(String mem_own, String mem_email){
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_own(mem_own);
		memberVO.setMem_email(mem_email);
		dao.updateMemOwn(memberVO);
		
		return memberVO;
	}

}
