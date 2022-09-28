package com.zhuhe.company.department.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuhe.company.department.dao.IDepartmentDao;
import com.zhuhe.company.department.model.DepartmentModel;
import com.zhuhe.company.util.FmtEmpty;
import com.zhuhe.company.util.JDBCUtil;

public class Department1DaoImpl implements IDepartmentDao {
	
	private IDepartmentDao dao = new Department1DaoImpl();
	
	private static String table = "department";
	private static String cols = "code,name,tel";
	private Connection conn;
	private PreparedStatement ps;
	
	
	@Override
	public Integer inster(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(table).append('(').append(cols).append(")values(?,?,?)");
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCode());
			ps.setString(2, model.getName());
			ps.setString(3, model.getTel());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		
		return null;
	}

	@Override
	public Integer delete(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);
		List<Object> values = appendWhere(sql, model);
		try {
			conn=JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		
		
		return null;
	}

	@Override
	public Integer deleteByCode(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table).append(" where code = ? ");
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCode());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		
		return null;
	}

	@Override
	public Integer updateAll(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table).append(" set name=?,tel=? where code=?");
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getName());
			ps.setString(2, model.getTel());
			ps.setString(3, model.getCode());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		
		return null;
	}

	@Override
	public Integer updateActive(DepartmentModel model) {
		//update department set name =? , tel = ? where code =?
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table).append(" set id = id ");
		List<Object> values = appendSet(sql, model);
		try {
			conn=JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		
		
		return null;
	}
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
		StringBuffer sql = new StringBuffer("select id = ?,");
		sql.append(cols).append(" from ").append(table);
		List<Object> values = appendWhere(sql,model);
		List<DepartmentModel> result = new ArrayList();
		ResultSet rs = null;
		try {
			conn=JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			for(int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {//ORM-映射-反射
				DepartmentModel m1 = new DepartmentModel();
				m1.setId(rs.getInt("id"));
				m1.setCode(rs.getString("code"));
				m1.setName(rs.getString("name"));
				m1.setTel(rs.getString("tel"));
				result.add(m1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps, rs);
		}
		
		
		return result;
	}

	

	private List<Object> appendWhere(StringBuffer sql, DepartmentModel model) {
		sql.append(" where 1 = 1");
		List<Object> values = new ArrayList<Object>();
		String code = model.getCode();
		if(!FmtEmpty.isEmpty(code)) {
			sql.append(" and code like?");
			values.add(code);
		}
		String name = model.getName();
		if(!FmtEmpty.isEmpty(name)) {
			sql.append(" and name like?");
			values.add(name);
		}
		String tel = model.getTel();
		if(!FmtEmpty.isEmpty(tel)) {
			sql.append(" and tel like?");
			values.add(tel);
		}
		return values;
	}

	@Override
	public DepartmentModel selectModel(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("select id = ?,");
		sql.append(cols).append(" from ").append(table).append(" where code = ?");
		ResultSet rs = null;
		try {
			conn=JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCode());
			rs = ps.executeQuery();
			if(rs.next()) {
				DepartmentModel m1 = new DepartmentModel();
				m1.setId(rs.getInt("id"));
				m1.setCode(rs.getString("code"));
				m1.setName(rs.getString("name"));
				m1.setTel(rs.getString("tel"));
				return m1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps, rs);
		}
		
		return null;
	}

	@Override
	public Integer selectCount(DepartmentModel model) {
		// TODO Auto-generated method stub
		return null;
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

