package com.zhuhe.company.department.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuhe.company.department.model.DepartmentModel;
import com.zhuhe.company.department.service.IDepartmentService;
import com.zhuhe.company.department.service.impl.DepartmentServiceImpl;
import com.zhuhe.company.util.FmtRequest;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private IDepartmentService service = new DepartmentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Object res = null;
		switch (req.getParameter("action")) {
		case "list":
			res = list(req);
			break;
			
		case "page":
			res = page(req);
			break;
		
		case "add":
			res = add(req);
			break;
			
		case "del":
			res = del(req);
			break;
			
		case "get":
			res = get(req);
			break;
			
		case "get2":
			get2(req);
			req.getRequestDispatcher("/web/page/department/upd.jsp").forward(req, resp);
			//如果发生了页面跳转 请求转发 就直接结束该方法 不用继续向下走到write的那一步
			return;
			
		case "upd":
			res = upd(req);
			break;
			
			
		default:
			break;
		}
		
		FmtRequest.write(resp.getWriter(), res);
		
	}

	private Object page(HttpServletRequest req) {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String pageIndex = req.getParameter("pageIndex");
		String pageLimit = req.getParameter("pageLimit");
		DepartmentModel model = new DepartmentModel();
		model.setCode(code);
		model.setName(name);
		model.setPageIndex(Integer.parseInt(pageIndex));
		model.setPageLimit(Integer.parseInt(pageLimit));
		model.setPageOn(true);
		List<DepartmentModel>list = service.selectList(model); 
		//带有查询条件和分页条件，得到的当前页面要显示的数据记录
		//带有查询条件，不带有分页条件，得到的记录条数
		Integer count = service.selectCount(model);
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		
		return map;
	}

	private void get2(HttpServletRequest req) {
		DepartmentModel mdb = service.selectmodel(FmtRequest.parseModel(req, DepartmentModel.class));
		
		req.setAttribute("mdb", mdb);
	}

	private String upd(HttpServletRequest req) {
		
		return service.update(FmtRequest.parseModel(req, DepartmentModel.class));
	}

	private DepartmentModel get(HttpServletRequest req) {
		
		return service.selectmodel(FmtRequest.parseModel(req, DepartmentModel.class));
	}

	private String del(HttpServletRequest req) {
		String code = req.getParameter("code");
		DepartmentModel model = new DepartmentModel();
		model.setCode(code);
		return service.delete(model);
		
		//return service.delete(FmtRequest.parseModel(req, DepartmentModel.class));
	}

	private String add(HttpServletRequest req) {
		DepartmentModel model = FmtRequest.parseModel(req, DepartmentModel.class);
		return service.insert(model);
	}

	private List<DepartmentModel> list(HttpServletRequest req) {
//		DepartmentModel model = new DepartmentModel();
//		model.setCode(req.getParameter("code"));
//		model.setName(req.getParameter("name"));
		DepartmentModel model = FmtRequest.parseModel(req, DepartmentModel.class);
		List<DepartmentModel> list = service.selectList(model);
		
		return list;
	}

}
