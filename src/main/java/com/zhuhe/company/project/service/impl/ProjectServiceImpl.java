package com.zhuhe.company.project.service.impl;

import java.util.List;

import com.zhuhe.company.project.dao.IProjectDao;
import com.zhuhe.company.project.dao.impl.ProjectDaoImpl;
import com.zhuhe.company.project.model.ProjectModel;
import com.zhuhe.company.project.service.IProjectService;

public class ProjectServiceImpl implements IProjectService{

	private IProjectDao dao = new ProjectDaoImpl();
	
	@Override
	public String insert(ProjectModel model) {
		
//		ProjectModel m1 = new ProjectModel(model.getCode());
//		ProjectModel pm = dao.selectModel(m1);
//		if(pm != null) {
//			return "repeat";
//		}
		
		return dao.insert(model) + "";
	}

	@Override
	public String delete(ProjectModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteByCode(ProjectModel model) {
		// TODO Auto-generated method stub
		return dao.deleteByCode(model)+"";
	}

	@Override
	public String update(ProjectModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectModel> selectList(ProjectModel model) {
		String code = model.getCode();
		model.setCode(code == null ? "%" : "%"+ code+ "%");
		String name = model.getName();
		model.setName(name == null ? "%" : "%"+ name +"%");
		return dao.selectList(model);
	}

	@Override
	public ProjectModel selectModel(ProjectModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
