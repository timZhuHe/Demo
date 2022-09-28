package com.zhuhe.company.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public final class FmtUpload {
	
	
	/**
	 * 实现服务器端保存上传多张图片的功能
	 * @return Map key=参数名（非文件域参数名） value=参数值（非文件域参数值） {key=filenames  values=保存图片文件名集合}
	 * @throws FileUploadException 
	 * @throws IOException 
	 * 在调用该方法是需要传进去请求参数 才能知道要传递的是什么东西，所以在定义这个方法时需要把HttpServletRequest request传进来
	 */
	public static Map<String,Object> upload(HttpServletRequest request) throws FileUploadException, IOException {
		//DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory); //得到servlet上传文件时要用到的对象 upload
		
		//在上传时需要对包含上传信息的请求进行解析
		//调用upload这个对象 用parseRequest方法解析传过来的请求 形成一个List<FileItem>类的集合对象
		List<FileItem> items = upload.parseRequest(request);
		int i = 1;
		Map<String,Object> result = new HashMap<>();
		List<String> names = new ArrayList<>();
		for(FileItem item:items) {
			if(item.isFormField()) {//是否是非文件域元素
				String name = item.getFieldName();
				//System.out.println("name="+name);
				String value = item.getString();
				value=new String(value.getBytes("ISO-8859-1"),"UTF-8"); //对编码方式进行更改
				//System.out.println("value="+value);
				result.put(name, value);
			}else {//文件域表单元素--保存文件
				//System.out.println(item.getFieldName() + " " + item.getName());
				String fn = getFName(item); //为了把名字留存下来 方便以后使用
				FileOutputStream fos = new FileOutputStream(getFPath()+"/"+fn); //要写出的目标位置 就是要保存的目标位置
				InputStream is = item.getInputStream();
				int length = -1;
				byte[] b = new byte [1024*1024];
				while(-1 !=(length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
				is.close();
				names.add(fn); //把文件名存到数据库里
				//因为key的值不能相等 所以在每次保存好一个文件时 这个key的值后缀会++ 
				//而value的值就是/temp11/（上级目录）拼接fn这个文件名 形成一个网络资源的路径 方便在新的jsp文件中通过 img src=”“ 的方式来获取该图片文件
				//request.setAttribute("key"+(i++), "/temp11/"+fn); //通过request域setAttribute的方法把这个文件传递进来 
				
			}
		}
		result.put("filename", names);
		return result;
	}
	
	//虚拟路径
	//http://127.0.0.1:8080/temp11/2b0fe601-bdc3-4e0b-a95a-0902df45ebc3.png 通过虚拟路径访问本地网络资源
	private static String getFName(FileItem item) {//获得唯一的文件名，加上上传文件的扩展名
		String fn = item.getName(); //获得这个集
		String uuid = UUID.randomUUID().toString();
		fn = uuid+fn.substring(fn.indexOf(".")); //字符串拼接 indexOf在.后面包括.进行索引 substring方法拼接图片后缀的.png
		return fn; //把String类的对象fn直接返回给这个方法 可以把这个方法直接当成一个参数来使用
	}
	
	private static File getFPath() {
		File f = new File("/Users/hehexiaoshuaige/Desktop/temp1");
		if(!f.exists()) { //增加一个判断 判断如果这个目录不存在
			f.mkdir(); //就用mkdir()方法通过上面的路径创建该目录
		}
		return f; //把参数返回给方法 可以把该方法当成一个参数使用
	}
	
	public static void delFile(String filename) {
		File f = new File(getFPath() + "/" + filename);
		f.delete();
	}
	
}

//

//

//

//

//

//

//
