package com.zhuhe.company.util;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;
import com.zhuhe.company.employee.model.EmployeeModel;

public class FmtRequest {
	
	/**
	 * 根据请求的参数情况反射得到实体类的对象，请求的参数与实体类的属性名一致,一个参数对应一个参数值
	 * 
	 * @param <T>
	 * @param req
	 * @param clazz
	 * @return
	 */
	public static <T> T parseModel(HttpServletRequest req, Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Map<String, String[]> map = req.getParameterMap();
		for(Entry<String,String[]> entry : map.entrySet()) {
			String name = entry.getKey();
			if("action".equals(name)) {
				continue;
			}
			try {
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				field.set(obj, entry.getValue()[0]);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * 当请求中的参数名和实体类的属性名不一样时，根据映射关系 来实现填充数据->
	 * 即 Map<属性名，参数名> 
	 * 根据映射关系来得到实体类的对象
	 * 
	 * @param <T>
	 * @param req
	 * @param clazz
	 * @param fields key=属性名 value=参数名
	 * @return
	 */
	public static <T> T parseModel(HttpServletRequest req, Class<T> clazz, Map<String, String> fields) {
		T obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		for(Entry<String,String> entry : fields.entrySet()) {
			String name = entry.getKey();
			String val = req.getParameter(entry.getValue());
			try {
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				field.set(obj,val);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	public static void write(Writer wr, String res) {
		try {
			wr.write(res);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wr=null;
	}
	public static void write(Writer wr, Object res) {
		if(res instanceof Collection<?>) {
			write(wr, new JSONArray((Collection<?>)res).toString());
		}else if(res instanceof String){
			write(wr, res.toString());
		}else if (res instanceof Map<?,?>) {
			write(wr, new JSONObject((Map<?,?>)res).toString());
		}else {
			System.out.println(res);
			write(wr, new JSONObject(res).toString());	
		}
	}
	
	
}
