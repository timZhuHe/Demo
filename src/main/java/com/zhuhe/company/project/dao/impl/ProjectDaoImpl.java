package com.zhuhe.company.project.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuhe.company.project.dao.IProjectDao;
import com.zhuhe.company.project.model.ProjectModel;
import com.zhuhe.company.util.FmtEmpty;
import com.zhuhe.company.util.JDBCUtil;

public class ProjectDaoImpl implements IProjectDao {
	
	private String table = "project";
	private String cols = "code,name,time";
	
	
	@Override
	public Integer insert(ProjectModel model) {
		String sql = String.format("insert into %s(%s) values (?,?,?)", table,cols);                    
		return JDBCUtil.update(sql, model.getCode(), model.getName(), model.getTime());
	}

	@Override
	public Integer delete(ProjectModel model) {
		StringBuffer sql = new StringBuffer("delete from ").append(table);
		List<Object> values = appendWhere(sql,model);
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendWhere(StringBuffer sql, ProjectModel model) {
		sql.append(" where 1 = 1");
		List<Object> values = new ArrayList<Object>();
		String code = model.getCode();
		if(!FmtEmpty.isEmpty(code)) {
			sql.append(" and code like ? ");
			values.add(code);
		}
		String name = model.getName();
		if(!FmtEmpty.isEmpty(name)) {
			sql.append(" and name like ?");
			values.add(name);
		}
		String time = model.getTime();
		if(!FmtEmpty.isEmpty(time)) {
			sql.append(" and time like ?");
			values.add(time);
		}
		
		return values;
	}

	@Override
	public Integer deleteByCode(ProjectModel model) {
		String sql = String.format("delete from %s where code = ? ", table);
		return JDBCUtil.update(sql, model.getCode());
	}

	@Override
	public Integer updateAll(ProjectModel model) {
		String sql = String.format("update %s set name = ?, time = ? where code = ? ", table);
		
		return JDBCUtil.update(sql, model.getName(), model.getCode(), model.getTime());
	}

	@Override
	public Integer updateActive(ProjectModel model) {
		StringBuffer sql = new StringBuffer("update ").append( table ).append(" set id = id");
		List<Object> values = appendSet(sql,model);
		
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendSet(StringBuffer sql, ProjectModel model) {
		List<Object> values = new ArrayList<Object>();
		String name = model.getName();
		if(name != null) {
			sql.append(" ,name=? ");
			values.add(name);
		}
		String code = model.getCode();
		if( code != null) {
			sql.append(" ,code=? ");
			values.add(code);
		}
		String time = model.getTime();
		if(time != null) {
			sql.append(" ,time=?");
			values.add(time);
		}
		
		return values;
	}

	@Override
	public List<ProjectModel> selectList(ProjectModel model) {
		StringBuffer sql = new StringBuffer("select id,").append(cols).append(" from ").append(table);
		List<Object> values = appendWhere(sql, model);
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("name", "name");
		fields.put("code", "code");
		fields.put("time", "time");
		
		return JDBCUtil.queryList(sql.toString(), values, ProjectModel.class,fields);
	}

	@Override
	public ProjectModel selectModel(ProjectModel model) {
		StringBuffer sql = new StringBuffer("select id, ");
		sql.append(cols).append("from").append(table).append(" where code=? ");
		List<Object> values = Arrays.asList(model.getCode());
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("name", "name");
		fields.put("code", "code");
		fields.put("time", "time");	
		
		return JDBCUtil.queryModel(sql.toString(), values, ProjectModel.class);
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
