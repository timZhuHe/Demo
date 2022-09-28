package com.zhuhe.company.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhuhe.company.employee.model.EmployeeModel;
import com.zhuhe.company.employee.service.IEmployeeService;
import com.zhuhe.company.employee.service.impl.EmployeeServiceImpl;
import com.zhuhe.company.util.FmtRequest;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//定义接口类型的变量 注入的是实现类的对象
	private IEmployeeService service = new EmployeeServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object res = null;
		switch (req.getParameter("action")) {
		case "reg":
			res = reg(req);
			break;
			
		case "login":
			res = login(req);
			break;
			
		case "logout":
			req.getSession().removeAttribute("user");
			resp.sendRedirect(req.getContextPath()+"/web/login.jsp");
			return;
		
		case "add":
			res = add(req);
			break;
			
		case "list":
			res = list(req);
			break;
		
		case "del":
			res = del(req);
			break;
			
		case "get":
			res = get(req);
			break;
			
		case "upd":
			res = upd(req);
			break;
		
		case "setDept":
			res = setDept(req);
			break;
			
		case "updPass":
			res = updPass(req);
			break;	
			
		case "delPic":
			res = delPic(req);
			break;
		
			

		default:
			break;
		}
		//把东西写会给网页这一端
		FmtRequest.write(resp.getWriter(), res);
	}


	private Object delPic(HttpServletRequest req) {
//		EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
//		model.setImage(" ");
//		return service.update(model);
		return service.delPic(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private Object updPass(HttpServletRequest req) {

		return service.update(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private Object setDept(HttpServletRequest req) {
		
		return service.update(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private Object upd(HttpServletRequest req) {
		
		return service.update(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private Object get(HttpServletRequest req) {
		
		return service.selectModel(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private String del(HttpServletRequest req) {
		EmployeeModel model = new EmployeeModel();
		model.setCode(req.getParameter("code"));
		return service.deleteByCode(model);
	}


	private List<EmployeeModel> list(HttpServletRequest req) {
//		EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
//		List<EmployeeModel> list = service.selectNameDept(model);
		return service.selectNameDept(FmtRequest.parseModel(req, EmployeeModel.class));
	}


	private String add(HttpServletRequest req) {
		EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
		return service.insert(model);
	}


	private String login(HttpServletRequest req) {
		EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
		String res = service.login(model);
		if("1".equals(res)) {
			req.getSession().setAttribute("user", service.selectModel(model));
		}
		
		return res;
	}


	private String reg(HttpServletRequest req) {
//		String code = req.getParameter("code");
//		String pass = req.getParameter("pass");
//		String name = req.getParameter("name");
//		EmployeeModel model = new EmployeeModel(code,name,pass);
		//反射实体类里的数据（属性名） （要和请求的参数名保持一致） 填充回方法里
//		//EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
		Map<String, String> fields = new HashMap<>();
		fields.put("code", "code");
		fields.put("pass", "pass");
		fields.put("name", "name");
		EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class, fields);
		
		return service.insert(model);
		
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
