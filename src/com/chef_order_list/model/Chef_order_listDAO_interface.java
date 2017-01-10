package com.chef_order_list.model;

import java.util.*;


public interface Chef_order_listDAO_interface {
          public void insert(Chef_order_listVO chef_order_listVO);
          public void update(Chef_order_listVO chef_order_listVO);
          public void delete(String chef_ord_no);
          public Chef_order_listVO findByPrimaryKey(String chef_ord_no);
          public List<Chef_order_listVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Chef_order_listVO> getAll(Map<String, String[]> map); 
          
          //by cyh
          public List<Chef_order_listVO> findByMem_no(String mem_no);
          public List<Chef_order_listVO> findByChef_no(String chef_no);
}
