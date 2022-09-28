package com.zhuhe.company.employee.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhuhe.company.employee.model.EmployeeModel;

public interface IEmployeeService {

	String insert(EmployeeModel model);	
	
	String delete(EmployeeModel model);
	
	String deleteByCode(EmployeeModel model);
	
	String update(EmployeeModel model);
	
	List<EmployeeModel> selectList(EmployeeModel model);
	
	EmployeeModel selectModel(EmployeeModel model );

	/**
	 * 登陆功能
	 * @param model
	 * @return String 0 = 账号不存在 1=登陆成功 2=密码错误
	 */
	String login(EmployeeModel model);
	
	List<EmployeeModel> selectNameDept(EmployeeModel model);

	String delPic(EmployeeModel model);
	
	
}
