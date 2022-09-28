package com.zhuhe.company.employee.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.zhuhe.company.employee.model.EmployeeModel;
import com.zhuhe.company.employee.service.IEmployeeService;
import com.zhuhe.company.employee.service.impl.EmployeeServiceImpl;
import com.zhuhe.company.util.FmtRequest;
import com.zhuhe.company.util.FmtUpload;

@WebServlet("/EmployeeUploadServlet")
public class EmployeeUploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IEmployeeService service = new EmployeeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String,Object> map = null;
		try {
			map = FmtUpload.upload(req);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String code = map.get("code").toString();
		List<String> filename = (List<String>) map.get("filename");
		String image = filename.get(0);
		
		EmployeeModel model = new EmployeeModel(code);
		model.setImage(image);
		
		String res = service.update(model);
		Map<String,String> result = new HashMap<String, String>();
		result.put("code",res);
		result.put("image",image);
		FmtRequest.write(resp.getWriter(), result);
		
	}
	
}

//

//

//

//

//

//