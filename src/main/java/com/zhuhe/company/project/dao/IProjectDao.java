package com.zhuhe.company.project.dao;

import java.util.List;

import com.zhuhe.company.project.model.ProjectModel;

public interface IProjectDao {
	
	Integer insert (ProjectModel model);
	
	Integer delete(ProjectModel model);
	
	Integer deleteByCode (ProjectModel model);
	
	Integer updateAll(ProjectModel model);
	
	Integer updateActive(ProjectModel model);
	
	List<ProjectModel> selectList (ProjectModel model);
	
	ProjectModel selectModel (ProjectModel model);
	
	
}
