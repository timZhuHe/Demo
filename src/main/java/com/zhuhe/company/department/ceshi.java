package com.zhuhe.company.department;

import org.junit.Test;

import com.zhuhe.company.department.dao.impl.Department2DaoImpl;
import com.zhuhe.company.department.model.DepartmentModel;

public class ceshi {
	private Department2DaoImpl dao = new Department2DaoImpl();
	private DepartmentModel model = new DepartmentModel();
	
	
	public void inster() {
		model.setCode("1001");;
		model.setName("zhuhe");
		model.setTel("1315648");
		dao.inster(model);
		System.out.println("执行插入");
	}
	public static void main(String[] args) {
		ceshi t1 = new ceshi();
		t1.inster();
	}
	
}
