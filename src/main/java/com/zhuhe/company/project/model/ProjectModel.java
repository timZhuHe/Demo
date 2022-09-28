package com.zhuhe.company.project.model;

import com.zhuhe.company.util.PagerModel;

public class ProjectModel extends PagerModel{
	private Integer id;
	private String code;
	private String name;
	private String time;
	
	
	
	
	public ProjectModel() {
		super();
	}
	public ProjectModel(String code) {
		super();
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
