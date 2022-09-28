package com.zhuhe.company.department.dao;

import java.util.List;

import com.zhuhe.company.department.model.DepartmentModel;

//指定标准 + 统一规范
//返回类型= String int Integer DepartmentModel list map 
public interface IDepartmentDao {
	
	/**
	 * 添加记录
	 * @param model
	 * @return Integer
	 */
	Integer inster(DepartmentModel model); 
	//List<Integer> inster(DepartmentModel model); //批量添加
	
	/**
	 * 根据查询条件删除
	 * @param model
	 * @return Integer
	 */
	Integer delete(DepartmentModel model); 
	
	/**
	 * 根据主键删除
	 * @param model
	 * @return Integer
	 */
	Integer deleteByCode(DepartmentModel model);
	
	/**
	 * 根据主键修改全部字段
	 * @param model
	 * @return Integer
	 */
	Integer updateAll(DepartmentModel model); 
	
	/**
	 * 根据主键修改部分字段
	 * @param model
	 * @return Integer
	 */
	Integer updateActive(DepartmentModel model);
	
	/**
	 * 根据多条件模糊查询记录
	 * @param model
	 * @return List<DepartmentModel>
	 */
	List<DepartmentModel>selectList(DepartmentModel model);
	
	/**
	 * 根据主键查询一条记录
	 * @param model
	 * @return DepartmentModel
	 */
	DepartmentModel selectModel(DepartmentModel model);
	
	/**
	 * 根据条件查询得到记录条数
	 * @param model
	 * @return
	 */
	Integer selectCount(DepartmentModel model);
}

//

//

//

//

//
//

//
