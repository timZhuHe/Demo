package com.zhuhe.company.department.model;

import com.zhuhe.company.util.PagerModel;

public class DepartmentModel extends PagerModel{
	
	private Integer id;
	private String code;
	private String name;
	private String tel;
	private Long count;
	
	
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public DepartmentModel() {
		
	}
	public DepartmentModel(String code) {
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
