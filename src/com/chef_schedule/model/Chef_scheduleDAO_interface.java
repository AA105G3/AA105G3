package com.chef_schedule.model;

import java.sql.Date;
import java.util.*;

public interface Chef_scheduleDAO_interface {
          public void insert(Chef_scheduleVO chef_scheduleVO);
          public void update(Chef_scheduleVO chef_scheduleVO);
          public void delete(String chef_no, Date chef_scd_date, String chef_scd_mae);
//          public void delete(String chef_no);
//          public List<Chef_scheduleVO> findByPrimaryKey(String chef_no);
          public Chef_scheduleVO findByPrimaryKey(String chef_no);
          public List<Chef_scheduleVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<Chef_scheduleVOVO> getAll(Map<String, String[]> map); 
}
