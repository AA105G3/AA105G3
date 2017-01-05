package com.product_order.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.product_order_list.model.Product_order_listVO;



public class Product_orderService {
	
	private Product_orderDAO_interface dao;

	public Product_orderService() {
		dao = new Product_orderDAO();
	}

	public Product_orderVO addProduct_order(String mem_no, String cred_card_no,
			java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, String ship_name,
			String post_code, String mem_adrs, String cell_phone, String tel_phone) {

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setMem_no(mem_no);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		dao.insert(product_orderVO);

		return product_orderVO;
	}

	public Product_orderVO updateProduct_order(String prod_ord_no ,String mem_no, java.sql.Timestamp prod_ord_time,
			String cred_card_no, java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, 
			String ship_name, String post_code, String mem_adrs, String cell_phone, String tel_phone) {

		Product_orderVO product_orderVO = new Product_orderVO();
		
		product_orderVO.setProd_ord_no(prod_ord_no);
		product_orderVO.setMem_no(mem_no);
		product_orderVO.setProd_ord_time(prod_ord_time);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		dao.update(product_orderVO);

		return product_orderVO;
	}

	public Product_orderVO getOneProduct_order(String prod_ord_no) {
		return dao.findByPrimaryKey(prod_ord_no);
	}

	public List<Product_orderVO> getAll() {
		return dao.getAll();
	}

	public Set<Product_order_listVO> getProduct_order_list_By_One_PK(String prod_ord_no) {
		return dao.findByPK(prod_ord_no);
	}
	
	public List<Product_orderVO> getProduct_order_By_Mem_no(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	
	/*public Product_orderVO addProduct_orderWithList(String mem_no, String cred_card_no,
			java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, String ship_name,
			String post_code, String mem_adrs, String cell_phone, String tel_phone, 
			String prod_no, Integer unit_price, Integer prod_quantity, String deli_status, java.sql.Date deli_time) {

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setMem_no(mem_no);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		
		List<Product_order_listVO> list = new ArrayList <Product_order_listVO>();
		
		Product_order_listVO product_order_listVO = new Product_order_listVO();
		product_order_listVO.setProd_no(prod_no);
		product_order_listVO.setUnit_price(unit_price);
		product_order_listVO.setProd_quantity(prod_quantity);
		product_order_listVO.setDeli_status(deli_status);
		product_order_listVO.setDeli_time(deli_time);
		
		list.add(product_order_listVO);
		
		dao.insertWithProduct_order_list(product_orderVO, list);

		return product_orderVO;
	}*/
	
	public Product_orderVO addProduct_orderWithList(String mem_no, String cred_card_no,
			java.sql.Date valid_date, String valid_no, String cred_card_type, Integer total_money, String ship_name,
			String post_code, String mem_adrs, String cell_phone, String tel_phone, List<Product_order_listVO> list){

		Product_orderVO product_orderVO = new Product_orderVO();

		product_orderVO.setMem_no(mem_no);
		product_orderVO.setCred_card_no(cred_card_no);
		product_orderVO.setValid_date(valid_date);
		product_orderVO.setValid_no(valid_no);
		product_orderVO.setCred_card_type(cred_card_type);
		product_orderVO.setTotal_money(total_money);
		product_orderVO.setShip_name(ship_name);
		product_orderVO.setPost_code(post_code);
		product_orderVO.setMem_adrs(mem_adrs);
		product_orderVO.setCell_phone(cell_phone);
		product_orderVO.setTel_phone(tel_phone);
		
		dao.insertWithProduct_order_list(product_orderVO, list);
		
		return product_orderVO;
	}

}
