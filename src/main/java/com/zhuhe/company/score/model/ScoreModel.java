package com.zhuhe.company.score.model;

import com.zhuhe.company.util.PagerModel;

public class ScoreModel extends PagerModel{
	private Integer id;
	private String codeEmp;
	private String nameEmp;
	private String codePro;
	private String namePro;
	private String score;
	
	
	
	
	
	
	public ScoreModel() {
		super();
	}


	public ScoreModel(String codeEmp, String codePro) {
		super();
		this.codeEmp = codeEmp;
		this.codePro = codePro;
	}
	
	
	public String getNameEmp() {
		return nameEmp;
	}
	public void setNameEmp(String nameEmp) {
		this.nameEmp = nameEmp;
	}
	public String getNamePro() {
		return namePro;
	}
	public void setNamePro(String namePro) {
		this.namePro = namePro;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodeEmp() {
		return codeEmp;
	}
	public void setCodeEmp(String codeEmp) {
		this.codeEmp = codeEmp;
	}
	public String getCodePro() {
		return codePro;
	}
	public void setCodePro(String codePro) {
		this.codePro = codePro;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
