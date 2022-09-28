package com.zhuhe.company.employee.model;

import com.zhuhe.company.util.PagerModel;

public class EmployeeModel extends PagerModel{
	
	private Integer id;
	private String code;
	private String name;
	private String pass;
	private String codeDept;
	private String nameDept;
	private String image;
	
	
	
	public String getNameDept() {
		return nameDept;
	}
	public void setNameDept(String nameDept) {
		this.nameDept = nameDept;
	}
	public EmployeeModel(String code) {
		super();
		this.code = code;
	}
	public EmployeeModel(String code, String name, String pass) {
		super();
		this.code = code;
		this.name = name;
		this.pass = pass;
	}
	public EmployeeModel() {
		super();
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCodeDept() {
		return codeDept;
	}
	public void setCodeDept(String codeDept) {
		this.codeDept = codeDept;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
