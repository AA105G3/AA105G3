package com.chef.model;

import java.util.*;

public interface ChefDAO_interface {
          public void insert(ChefVO chefVO);
          public void update(ChefVO chefVO);
          public void delete(String chef_no);
          public ChefVO findByPrimaryKey(String chef_no);
          public ChefVO findByChef_name(String chef_name);
          public List<ChefVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<ChefVO> getAll(Map<String, String[]> map); 
}