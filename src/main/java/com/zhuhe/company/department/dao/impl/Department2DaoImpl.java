package com.zhuhe.company.department.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zhuhe.company.department.dao.IDepartmentDao;
import com.zhuhe.company.department.model.DepartmentModel;
import com.zhuhe.company.util.FmtEmpty;
import com.zhuhe.company.util.JDBCUtil;

//数据层=sql+参数+映射（dql）
public class Department2DaoImpl implements IDepartmentDao{
	private static String table = "department";
	private static String cols = "code,name,tel";
	
	@Override
	public Integer inster(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(table).append('(').append(cols).append(")values(?,?,?)");
//		List<Object> values = Arrays.asList(model.getCode(),model.getName(),model.getTel());
//		return JDBCUtil.update(sql.toString(), values);
		
		//通过不定参数可以不用定义集合数组 
		return JDBCUtil.update(sql.toString(), model.getCode(),model.getName(),model.getTel());
	}

	@Override
	public Integer delete(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);
		List<Object> values = appendWhere(sql, model);
		return JDBCUtil.update(sql.toString(), values);
	}
	private List<Object> appendWhere(StringBuffer sql, DepartmentModel model) {
		sql.append(" where 1 = 1");
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
		String tel = model.getTel();
		if(!FmtEmpty.isEmpty(tel)) {
			sql.append(" and tel like ?");
			values.add(tel);
		}
		if(model.isPageOn()) {
			sql.append(" limit ?,? ");
			//1+10=0,10
			//2+10=10,10
			//3+10=20,10
			//pageIndex+pageLimit=(pageIndex-1)*10+pageLimit
			values.add(model.getRowStrat());
			values.add(model.getPageLimit());
		}
		return values;
	}
	

	@Override
	public Integer deleteByCode(DepartmentModel model) { //spring-jdbc
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table).append(" where code = ? ");
//		List<Object> values = Arrays.asList(model.getCode());
//		return JDBCUtil.update(sql.toString(), values);
		return JDBCUtil.update(sql.toString(), model.getCode());
	}

	@Override
	public Integer updateAll(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table).append(" set name=?,tel=? where code=?");
		return JDBCUtil.update(sql.toString(),model.getName(),model.getTel(),model.getCode());
	}
	
	/**
	 * 修改部分字段 （逻辑判断 拼接字段）
	 */
	@Override
	public Integer updateActive(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table).append(" set id = id ");
		List<Object> values = appendSet(sql, model);
		return JDBCUtil.update(sql.toString(), values);
	}
	/**
	 * appendSet 修改部分字段
	 * @param sql
	 * @param model
	 * @return
	 */
	private List<Object> appendSet(StringBuffer sql, DepartmentModel model){
			
			List<Object> values = new ArrayList<Object>();
			
			String name = model.getName();
			if(name != null) {
				sql.append(" ,name=? ");
				values.add(name);
			}
			String tel = model.getTel();
			if (tel != null) {
				sql.append(" ,tel = ?");
				values.add(tel);
			}
			sql.append(" where code = ?");
			values.add(model.getCode());
			
			return values;
		}

	@Override
	public List<DepartmentModel> selectList(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("select id,");
		sql.append(cols).append(" ,(select count(1) from employee where code_dept = department.code) count ").append(" from ").append(table);
		List<Object> values = appendWhere(sql,model);
		
		return JDBCUtil.queryList(sql.toString(), values, DepartmentModel.class);
	}

	@Override
	public DepartmentModel selectModel(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("select id,");
		sql.append(cols).append(" from ").append(table).append(" where code = ?");
		List<Object> values = Arrays.asList(model.getCode());
		return JDBCUtil.queryModel(sql.toString(), values,DepartmentModel.class);
	}

	@Override
	public Integer selectCount(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("select count(1) from department ");
		List<Object> vals = appendWhere(sql, model);
		return JDBCUtil.queryInt(sql.toString(),vals);
	}

}
