package com.chef_refund.model;

import java.util.*;

public interface Chef_refundDAO_interface {
          public void insert(Chef_refundVO chef_refundVO);
          public void update(Chef_refundVO chef_refundVO);
          public void delete(String chef_rfd_no);
          public Chef_refundVO findByPrimaryKey(String chef_rfd_no);
          public List<Chef_refundVO> getAll();
          public void update_back(Chef_refundVO chef_refundVO);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Chef_refundVO> getAll(Map<String, String[]> map); 
		
}
