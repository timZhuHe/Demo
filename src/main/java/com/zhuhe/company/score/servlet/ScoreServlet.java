package com.zhuhe.company.score.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuhe.company.score.model.ScoreModel;
import com.zhuhe.company.score.service.IScoreService;
import com.zhuhe.company.score.service.impl.ScoreServiceImpl;
import com.zhuhe.company.util.FmtRequest;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private IScoreService service = new ScoreServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
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
			
		case "del" :
			res = del(req);
			break;
			
		case "upd":
			res = upd(req);
			break;
			
		case "get":
			res = get(req);
			break;

		default:
			break;
		}
		
		FmtRequest.write(resp.getWriter(), res);
		
	}

	private Object get(HttpServletRequest req) {
		
		return service.selectModel(FmtRequest.parseModel(req, ScoreModel.class));
	}

	private Object upd(HttpServletRequest req) {
		
		return service.update(FmtRequest.parseModel(req, ScoreModel.class));
	}

	private Object del(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return service.deleteByCode(FmtRequest.parseModel(req, ScoreModel.class));
	}

	private Object list(HttpServletRequest req) {
		//System.out.println("查询成功");
		return service.selectlist1(FmtRequest.parseModel(req, ScoreModel.class));
	}

	private Object add(HttpServletRequest req) {
		
		return service.insert(FmtRequest.parseModel(req, ScoreModel.class));
	}

}
