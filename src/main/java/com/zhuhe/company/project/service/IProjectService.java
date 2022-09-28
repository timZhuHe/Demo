package com.zhuhe.company.project.service;

import java.util.List;

import com.zhuhe.company.project.model.ProjectModel;

public interface IProjectService {
	String insert(ProjectModel model);
	String delete(ProjectModel model);
	String deleteByCode(ProjectModel model);
	String update(ProjectModel model);
	List<ProjectModel> selectList(ProjectModel model);
	ProjectModel selectModel(ProjectModel model);
	
	
}
