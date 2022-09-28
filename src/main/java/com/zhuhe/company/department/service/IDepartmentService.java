package com.zhuhe.company.department.service;

import java.util.List;

import com.zhuhe.company.department.model.DepartmentModel;

public interface IDepartmentService {
	String insert(DepartmentModel model);
	
	String delete(DepartmentModel model);
	
	String update(DepartmentModel model);
	
	List<DepartmentModel> selectList(DepartmentModel model);
	
	DepartmentModel selectmodel(DepartmentModel model);

	Integer selectCount(DepartmentModel model);
}
