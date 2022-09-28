package com.zhuhe.company.employee.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhuhe.company.employee.dao.IEmployeeDao;
import com.zhuhe.company.employee.dao.impl.EmployeeDaoImpl;
import com.zhuhe.company.employee.model.EmployeeModel;
import com.zhuhe.company.employee.service.IEmployeeService;
import com.zhuhe.company.util.FmtUpload;
import com.zhuhe.company.util.MD5;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private IEmployeeDao dao = new EmployeeDaoImpl();
	
	@Override
	public String insert(EmployeeModel model) {
		EmployeeModel m1 = new EmployeeModel(model.getCode());
		EmployeeModel mdb = dao.selectModel(m1);
		if(mdb !=null) {
			return "repeat";
		}
		model.setPass(MD5.encode(model.getPass()));
		
		return dao.insert(model) + "";
	}

	@Override
	public String delete(EmployeeModel model) {
		// TODO Auto-generated method stub
		return dao.delete(model) + "";
	}
	

	@Override
	public String update(EmployeeModel model) {
		// TODO Auto-generated method stub
		return dao.updateActive(model) + " ";
	}

	@Override
	public List<EmployeeModel> selectList(EmployeeModel model) {
		String code = model.getCode();
		model.setCode(code == null ? "%" : "%"+ code+ "%");
		String name = model.getName();
		model.setName(name == null ? "%" : "%"+ name +"%");
		
		return dao.selectList(model);
	}

	@Override
	public EmployeeModel selectModel(EmployeeModel model) {
		EmployeeModel m1 = new EmployeeModel(model.getCode());
		return dao.selectModel(m1);
	}

	@Override
	public String login(EmployeeModel model) {
		EmployeeModel mdb = selectModel(model);
		if(mdb == null) {
			return "0";
		}
		//model.getPass=用户输入进来的密码 因为model是参数 所以这里代表的是网页端传过来的数据
		//mdb.getPass = 数据库查出来的密码 因为mdb注入的是通过code和selectModel方法从数据库查出来的数据 所以这个是从数据库查出来的密码
		//对model.getPass进行加密 就是为了和数据库中已经加密了的密码mdb.getPass进行比对
		String pass = MD5.encode(model.getPass());
		return mdb.getPass().equals(pass) ? "1" : "2";
	}

	@Override
	public String deleteByCode(EmployeeModel model) {
		
		return dao.deleteByCode(model) + "";
	}

	@Override
	public String delPic(EmployeeModel model) {
		EmployeeModel mdb = selectModel(model);
		String image = mdb.getImage();
		FmtUpload.delFile(image);
		model.setImage("");
		return update(model);
	}

	@Override
	public List<EmployeeModel> selectNameDept(EmployeeModel model) {
		String code = model.getCode();
		model.setCode(code == null ? "%" : "%"+ code+ "%");
		String name = model.getName();
		model.setName(name == null ? "%" : "%"+ name +"%");
		return dao.selectNameDept(model);
	}

}

//

//

//

//

//

//

//
