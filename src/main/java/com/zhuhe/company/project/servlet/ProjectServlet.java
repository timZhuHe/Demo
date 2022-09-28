package com.zhuhe.company.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuhe.company.project.model.ProjectModel;
import com.zhuhe.company.project.service.IProjectService;
import com.zhuhe.company.project.service.impl.ProjectServiceImpl;
import com.zhuhe.company.util.FmtRequest;


@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private IProjectService service = new ProjectServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object res = null;
		
		switch (req.getParameter("action")) {
		case "add":
			res = add(req);
			break;
			
		case "list":
			res = list(req);
			break;
			
		case "del":
			res = del(req);
			break;

		default:
			break;
		}
		
		FmtRequest.write(resp.getWriter(), res);
		
	}

	private Object del(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return service.deleteByCode(FmtRequest.parseModel(req, ProjectModel.class));
	}

	private  List<ProjectModel> list(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return service.selectList(FmtRequest.parseModel(req, ProjectModel.class));
	}

	private String add(HttpServletRequest req) {
		ProjectModel model = FmtRequest.parseModel(req, ProjectModel.class);
		return service.insert(model);
	}
}
