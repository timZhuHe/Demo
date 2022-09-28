package com.zhuhe.company.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.zhuhe.company.department.model.DepartmentModel;


//工具类
//配置文件values为蓝色才显示配置成功
//全都是静态的方法可以用final修饰这个工具类//只能用这个类来调用静态提供出来的方法
public final class JDBCUtil {
	//加上了final和privtae这个类只能用.的方式来使用，不可以用new的方式来继承
	private JDBCUtil() {
		
	}
	
	//读取配置文件的过程
	private static ResourceBundle rb = ResourceBundle.getBundle("com.zhuhe.company.util.jdbc3");
	private static String driver = rb.getString("driver");
	private static String user = rb.getString("user");
	private static String pass = rb.getString("pass");
	private static String url = rb.getString("url");
	//不需要开放出去，能用小的范围就用小的范围
	
	//1-加载驱动
	static {//在静态代码块中使用要把代码块定义成静态的
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//2-获得链接
	//import Connection 抛出异常
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,pass);
	}
	public static Connection getConnection1() {//用try catch解决异常需要在finally返回为空
		try {
			return DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	//3-获得状态集
	
	//4-执行DML DQL代码
	
	
	/**
	 * 	通过不定参数执行方法
	 * 执行DML语句
	 * 方法调用 含有集合的update方法
	 * @param sql
	 * @param values
	 * @return
	 */
	public static Integer update(String sql, Object... values) {
		return update(sql, Arrays.asList(values));
		//调用同类下的update方法 把不定参数数组转换成集合 
	}
	//Arrays.asListArrays.asList() /如果数组中元素类型是引用类型 =》 asList返回的 List元素就是数组中的元素           
	//                          /如果数组中元素类型是基本数据类型 =》 asList返回的 List元素中只有一个，即传入进来的这个数组
	
	/**
	 *  执行dml语句
	 *  提炼出公共的东西
	 * @return Inteher
	 *
	 */
	public static Integer update(String sql, List<Object> values) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
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
	
	/**
	 *  执行DQL
	 * @param <T> （泛型）返回的集合中的元素类型（即表对应的实体类）
	 * @param sql - 即将执行的sql语句
	 * @param values - 执行sql语句中？的值
	 * @param clazz - 即将反射得到的实体类的类的描述
	 * @param fields - 映射关系 Map<key(属性名) , value(字段名)>
	 * @return List<T> 返回查询得到的记录对象的集合
	 */
	public static <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz, Map<String,String> fields){           
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> result = new ArrayList<>();
			
		try {
			conn=getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				T model = clazz.newInstance();
				for(Entry<String, String> entry : fields.entrySet()) {
					Field field =  clazz.getDeclaredField(entry.getKey());
					field.setAccessible(true);
					field.set(model, rs.getObject(entry.getValue()));
				}
				result.add(model);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn,ps,rs);
		}
		
		
		return result;          
	}
	
	/**
	 * 执行sql时 表的字段名与实体类属性名一样的情况 可以用该方法
	 * @param <T>
	 * @param sql
	 * @param values
	 * @param clazz
	 * @param props
	 * @return
	 */
	public static <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz, List<String> props){
		
		Map<String, String> fields = new HashMap<>();
		for(String p:props) {
			fields.put(p, p);
		}
		return queryList(sql, values, clazz, fields);
	}
	/**
	 * 执行dql，实体类属性名与字段名一样的情况 传递三个参数 通过类的描述getName()
	 * @param <T>
	 * @param sql
	 * @param values
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz){
			
			Map<String, String> fields = new HashMap<>();
			for(Field p:clazz.getDeclaredFields()) {
				fields.put(p.getName(), p.getName());
			}
			return queryList(sql, values, clazz,fields);
		}
	
	
	/**
	 * 执行DQL语句 返回一个结果对象
	 * @param <T>
	 * @param sql
	 * @param values
	 * @param clazz
	 * @param fields
	 * @return
	 */
	public static <T> T queryModel(String sql, List<Object> values, Class<T> clazz, Map<String, String> fields){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		try {
			conn=getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				T model = clazz.newInstance();
				for(Entry<String, String> entry : fields.entrySet()) {
					Field field =  clazz.getDeclaredField(entry.getKey());
					field.setAccessible(true);
					field.set(model, rs.getObject(entry.getValue()));
				}
				return model;
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn,ps,rs);
		}
		
		
		return null; 
	}
	
	public static <T> T queryModel(String sql, List<Object> values, Class<T> clazz, List<String> props){
	
		Map<String, String> fields = new HashMap<>();
		for(String p:props) {
			fields.put(p, p);
		}
		return queryModel(sql, values, clazz, fields);
	}
	public static <T> T queryModel(String sql, List<Object> values, Class<T> clazz) {
		Map<String, String> fields = new HashMap<>();
		for(Field p:clazz.getDeclaredFields()) {
			fields.put(p.getName(), p.getName());
		}
		return queryModel(sql, values, clazz, fields);
	}
	
	/**
	 * 根据条件查询得到记录条数
	 * @param sql
	 * @param values
	 * @return
	 */
	public static Integer queryInt(String sql, List<Object>values) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i+1, values.get(i));
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps);
		}
		return null;
	}
	
	
	
	/**
	 * 关闭流
	 * @param conn
	 * @param st
	 */
	public static void close(Connection conn, Statement st) {
		close(conn,st,null);
	}
	
	
	public static void close(Connection conn, Statement st , ResultSet rs ) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(st != null) {
			    st.close();
				st = null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		//conn.createStatement();
		//conn.prepareStatement(" ");
		System.out.println(conn);
		//JDBCUtil.close();
		
	}
	
}
//

//

//

//


//
