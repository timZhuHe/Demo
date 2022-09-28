package com.zhuhe.company.employee.dao;

import java.util.List;

import com.zhuhe.company.employee.model.EmployeeModel;

public interface IEmployeeDao {
	
	Integer insert(EmployeeModel model); 
	
	Integer delete (EmployeeModel model);
	
	Integer deleteByCode (EmployeeModel model);
	
	Integer updateAll(EmployeeModel model);
	
	Integer updateActive(EmployeeModel model);
	
	List<EmployeeModel> selectNameDept(EmployeeModel model);
	
	List<EmployeeModel> selectList (EmployeeModel model);
	
	EmployeeModel selectModel (EmployeeModel model);
	
	
}
