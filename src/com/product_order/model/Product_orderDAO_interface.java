package com.product_order.model;

import java.util.*;

import com.product_order_list.model.Product_order_listVO;

public interface Product_orderDAO_interface {
	public void insert(Product_orderVO prodVO);
	public void update(Product_orderVO prodVO);
	public void delete(String prod_ord_no);
	public Product_orderVO findByPrimaryKey(String prod_ord_no);
	public List<Product_orderVO> getAll();

	public List<Product_orderVO> findByMem_no(String mem_no);
	
	public Set<Product_order_listVO> findByPK(String prod_ord_no);
	
	//同時新增商品訂單與訂單明細
    public void insertWithProduct_order_list(Product_orderVO product_orderVO , List<Product_order_listVO> list);

}
