package com.zhuhe.company.department.service.impl;

import java.util.List;

import com.zhuhe.company.department.dao.IDepartmentDao;
import com.zhuhe.company.department.dao.impl.Department2DaoImpl;
import com.zhuhe.company.department.model.DepartmentModel;
import com.zhuhe.company.department.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	private IDepartmentDao dao = new Department2DaoImpl();
	
	@Override
	public String insert(DepartmentModel model) {
		if(selectmodel(model) == null) {
			Integer res = dao.inster(model);
			return res == null ? null : res.toString();
		}
		return "repeat";
	}

	@Override
	public String delete(DepartmentModel model) {
		Integer res = dao.delete(model);
		return res == null ? null:res.toString();
	}

	@Override
	public String update(DepartmentModel model) {
		// TODO Auto-generated method stub
		return dao.updateActive(model) + "";
	}

	@Override
	public List<DepartmentModel> selectList(DepartmentModel model) {
		String code = model.getCode();
		model.setCode(code == null ? "%" : "%"+ code+ "%");
		String name = model.getName();
		model.setName(name == null ? "%" : "%"+ name +"%");
		return dao.selectList(model);
	}

	@Override
	public DepartmentModel selectmodel(DepartmentModel model) {
		// TODO Auto-generated method stub
		return dao.selectModel(new DepartmentModel(model.getCode()));
	}

	public Integer selectCount(DepartmentModel model) {
		DepartmentModel m1= new DepartmentModel();
		String code = model.getCode();
		m1.setCode(code == null ? "%" : "%"+ code+ "%");
		String name = model.getName();
		m1.setName(name == null ? "%" : "%"+ name +"%");
		return dao.selectCount(m1);
	}
	
}


//

//

//

//

//

//

//

//
