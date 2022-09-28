package com.zhuhe.company.employee.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuhe.company.employee.dao.IEmployeeDao;
import com.zhuhe.company.employee.model.EmployeeModel;
import com.zhuhe.company.util.FmtEmpty;
import com.zhuhe.company.util.JDBCUtil;

public class EmployeeDaoImpl implements IEmployeeDao {
	
	private String table = "employee";
	private String cols = "code,name,pass,code_dept,image";
	

	@Override
	public Integer insert(EmployeeModel model) {
		// inster into employee (code,name,pass,code_dept) values (?,?,?,?)                
		String sql = String.format(" insert into %s(%s) values (?,?,?,?,?) ", table,cols);
		
		
		return JDBCUtil.update(sql.toString(), model.getCode(), model.getName(), model.getPass(), model.getCodeDept(),model.getImage());                         
		
	}

	@Override
	public Integer delete(EmployeeModel model) {
		StringBuffer sql = new StringBuffer(" delete from ").append(table);
		List<Object> values = appendWhere(sql,model);
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendWhere(StringBuffer sql, EmployeeModel model) {
		sql.append(" where 1 = 1 ");
		List<Object> values = new ArrayList<Object>();
		
		String code = model.getCode();
		if(!FmtEmpty.isEmpty(code)) {
			sql.append(" and code like ?");
			values.add(code);
		}
		String name = model.getName();
		if(!FmtEmpty.isEmpty(name)) {
			sql.append(" and name like ?");
			values.add(name);
		}
		String pass = model.getPass();
		if(!FmtEmpty.isEmpty(pass)) {
			sql.append(" and pass like ?");
			values.add(pass);
		}
		String codeDept = model.getCodeDept();
		if(!FmtEmpty.isEmpty(codeDept)) {
			sql.append(" and code_dept like ?");
			values.add(codeDept);
		}
		String image = model.getImage();
		if(!FmtEmpty.isEmpty(image)) {
			sql.append(" and image like ?");
			values.add(image);
		}
		
		return values;
	}
	
	private List<Object> appendWhereNameDept(StringBuffer sql, EmployeeModel model) {
		sql.append(" where 1 = 1 ");
		List<Object> values = new ArrayList<Object>();
		
		String code = model.getCode();
		if(!FmtEmpty.isEmpty(code)) {
			sql.append(" and code like ?");
			values.add(code);
		}
		String name = model.getName();
		if(!FmtEmpty.isEmpty(name)) {
			sql.append(" and name like ?");
			values.add(name);
		}
		String pass = model.getPass();
		if(!FmtEmpty.isEmpty(pass)) {
			sql.append(" and pass like ?");
			values.add(pass);
		}
		String codeDept = model.getCodeDept();
		if(!FmtEmpty.isEmpty(codeDept)) {
			sql.append(" and code_dept like ?");
			values.add(codeDept);
		}
//		String nameDept = model.getNameDept();
//		if(!FmtEmpty.isEmpty(nameDept)) {
//			sql.append(" and name_dept like ?");
//			values.add(nameDept);
//		}
		String image = model.getImage();
		if(!FmtEmpty.isEmpty(image)) {
			sql.append(" and image like ?");
			values.add(image);
		}
		
		return values;
	}

	@Override
	public Integer deleteByCode(EmployeeModel model) {
		// delete from employee %s where code = ?
		String sql = String.format("delete from %s where code = ? ", table);
		
		return JDBCUtil.update(sql.toString(), model.getCode());
	}

	@Override
	public Integer updateAll(EmployeeModel model) {
		String sql = String.format(" update %s set name = ? , code_dept = ? , where code =? ", table);
		return JDBCUtil.update(sql.toString(), model.getName(), model.getCodeDept(), model.getCode());
	}

	@Override
	public Integer updateActive(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("update ").append(table).append(" set id = id");
		List<Object> values = appendSet(sql,model);
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendSet(StringBuffer sql, EmployeeModel model) {
		List<Object> values = new ArrayList<Object>();
		
		String name = model.getName();
		if(name != null) {
			sql.append(" ,name = ?");
			values.add(name);
		}
		String codeDept = model.getCodeDept();
		if(codeDept != null) {
			sql.append(" ,code_dept = ?");
			values.add(codeDept);
		}
		String image = model.getImage();
		if(image != null) {
			sql.append(" ,image = ? ");
			values.add(image);
		}
		sql.append(" where code = ?");
		values.add(model.getCode());
		
		return values;
	}

	@Override
	public List<EmployeeModel> selectList(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("select id, ").append(cols).append(" from ").append(table);     
		List<Object> values = appendWhere(sql, model);
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("code", "code");
		fields.put("name", "name");
		fields.put("pass", "pass");
		fields.put("codeDept", "code_dept");
		fields.put("image", "image");
		return JDBCUtil.queryList(sql.toString(), values, EmployeeModel.class, fields);
	}

	@Override
	public EmployeeModel selectModel(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("select id, ");
		sql.append(cols).append(" from ").append(table).append(" where code = ? ");
		List<Object> values = Arrays.asList(model.getCode());
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("code", "code");
		fields.put("name", "name");
		fields.put("pass", "pass");
		fields.put("codeDept", "code_dept");
		fields.put("image", "image");
		
		return JDBCUtil.queryModel(sql.toString(), values, EmployeeModel.class, fields);
	}

	@Override
	public List<EmployeeModel> selectNameDept(EmployeeModel model) {
		StringBuffer sql =  new StringBuffer("select id, code, name, pass, code_dept, (select name from department where code = code_dept) name_dept,image ");
		sql.append(" from ").append(table);
		List<Object> values = appendWhere(sql, model);
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("code", "code");
		fields.put("name", "name");
		fields.put("pass", "pass");
		fields.put("codeDept", "code_dept");
		fields.put("nameDept", "name_dept");
		fields.put("image", "image");
		return JDBCUtil.queryList(sql.toString(), values, EmployeeModel.class, fields);
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

//

//

//

//

//
