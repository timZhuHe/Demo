package com.zhuhe.company.score.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuhe.company.score.dao.IScoreDao;
import com.zhuhe.company.score.model.ScoreModel;
import com.zhuhe.company.util.JDBCUtil;

public class ScoreDaoImpl implements IScoreDao{

	private String table = "score";
	private String cols = "code_emp,code_pro,score";
	
	@Override
	public Integer insert(ScoreModel model) {
		//insert into score (code_emp, (select name from employee where code = code_emp)name_emp, code_pro, (select name from project where code = code_pro)name_pro,score) 
						// values(?,?,?,?,?)
		String sql = String.format("insert into %s(%s) values (?,?,?)", table,cols);
		return JDBCUtil.update(sql, model.getCodeEmp(),model.getCodePro(),model.getScore());
	}

	@Override
	public Integer delete(ScoreModel model) {
		StringBuffer sql = new StringBuffer("delete from ").append(table);
		List<Object> values = appendWhere(sql,model);
		
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendWhere(StringBuffer sql, ScoreModel model) {
		sql.append(" where 1 = 1");
		List<Object> values = new ArrayList<Object>();
		String codeEmp = model.getCodeEmp();
		if(codeEmp != null){
			sql.append(" and code_emp like ?");
			values.add(codeEmp);
		}
		String codePro = model.getCodePro();
		if(codePro != null) {
			sql.append(" and code_pro like ? ");
			values.add(codePro);
		}
		String score = model.getScore();
		if(score != null) {
			sql.append(" and score like ?");
			values.add(score);
		}
		
		return values;
	}

	@Override
	public Integer deleteByCode(ScoreModel model) {
		String sql = String.format("delete from %s where code_emp=? and code_pro = ?  ", table);
				
		return JDBCUtil.update(sql, model.getCodeEmp(),model.getCodePro());
				
		//		String sql = String.format("delete from %s where id = ?", table);
		//		return JDBCUtil.update(sql, model.getId());
	}

	@Override
	public Integer updateAll(ScoreModel model) {
		String sql = String.format("update %s set score = ? where code_emp=? and code_pro=?", table);                  
		
		return JDBCUtil.update(sql, model.getScore(),model.getCodeEmp(),model.getCodePro());
		
//		String sql = String.format(" update %s set code_emp=? , code_pro=?, score=? where id=?", table);
//		
//		return JDBCUtil.update(sql, model.getCodeEmp(),model.getCodePro(),model.getScore(),model.getId());
	}

	@Override
	public Integer updateActive(ScoreModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table).append(" set id = id");
		List<Object> values = appendSet(sql,model);
		return JDBCUtil.update(sql.toString(), values);
	}

	private List<Object> appendSet(StringBuffer sql, ScoreModel model) {
		List<Object> values = new ArrayList<Object>();
		String score = model.getScore();
		if(score != null) {
			sql.append(" ,score = ?");
			values.add(score);
		}
		sql.append(" where code_emp = ? and code_pro = ?");
		values.add(model.getCodeEmp());
		values.add(model.getCodePro());
		
//		String score = model.getScore();
//		if(score != null) {
//			sql.append(" ,score=?");
//			values.add(score);
//		}
//		String codeEmp = model.getCodeEmp();
//		if(codeEmp != null) {
//			sql.append(" ,code_emp=?");
//			values.add(codeEmp);
//		}
//		String codePro = model.getCodePro();
//		if(codePro != null) {
//			sql.append(" ,codePro=?");
//			values.add(codePro);
//		}
//		sql.append(" ,where id=?");
//		values.add(model.getId());
		
		return  values;
	}

	@Override
	public List<ScoreModel> selectList(ScoreModel model) {
		StringBuffer sql = new StringBuffer("select id,").append(cols).append(" from ").append(table);
		List<Object> values = appendWhere(sql, model);
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("codeEmp", "code_emp");
		fields.put("codePro", "code_pro");
		fields.put("score", "score");

		return JDBCUtil.queryList(sql.toString(), values, ScoreModel.class, fields);
	}

	@Override
	public ScoreModel selectModel(ScoreModel model) {
		StringBuffer sql = new StringBuffer("select id,").append(cols).append(" from ").append(table);
		sql.append(" where code_emp=? and code_pro = ? ");
		List<Object> values = Arrays.asList(model.getCodeEmp(),model.getCodePro());
		
//		sql.append(" where id = ? ");
//		List<Object> values = Arrays.asList(model.getId());
		
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("codeEmp", "code_emp");
		fields.put("codePro", "code_pro");
		fields.put("score", "score");
		
		return JDBCUtil.queryModel(sql.toString(), values, ScoreModel.class, fields);
	}

	@Override
	public List<ScoreModel> selectList1(ScoreModel model) {
		StringBuffer sql = new StringBuffer("select id, code_emp, (select name from employee where code = code_emp) name_emp, code_pro, (select name from project where code = code_pro) name_pro,score");            
		sql.append(" from ").append(table);
		List<Object> values = appendWhere(sql,model);
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "id");
		fields.put("codeEmp", "code_emp");
		fields.put("nameEmp", "name_emp");
		fields.put("codePro", "code_pro");
		fields.put("namePro", "name_pro");
		fields.put("score", "score");
		return JDBCUtil.queryList(sql.toString(), values, ScoreModel.class,fields);
	}

}
