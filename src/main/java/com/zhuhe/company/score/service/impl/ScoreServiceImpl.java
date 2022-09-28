package com.zhuhe.company.score.service.impl;

import java.util.List;

import com.zhuhe.company.score.dao.IScoreDao;
import com.zhuhe.company.score.dao.impl.ScoreDaoImpl;
import com.zhuhe.company.score.model.ScoreModel;
import com.zhuhe.company.score.service.IScoreService;

public class ScoreServiceImpl implements IScoreService{

	private IScoreDao dao = new ScoreDaoImpl();
	
	@Override
	public String insert(ScoreModel model) {
		ScoreModel m1 = new ScoreModel(model.getCodeEmp(),model.getCodePro());
		ScoreModel mdb = dao.selectModel(m1);
		if(mdb!=null) {
			return "repeat";
		}
		
		return dao.insert(model) + "";
	}

	@Override
	public String delete(ScoreModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteByCode(ScoreModel model) {
		
		return dao.deleteByCode(model) + "";
	}

	@Override
	public String update(ScoreModel model) {
		
		return dao.updateActive(model) + "";
	}

	@Override
	public List<ScoreModel> selectlist(ScoreModel model) {
		
		return null;
	}

	@Override
	public ScoreModel selectModel(ScoreModel model) {
		
		return dao.selectModel(model);
	}

	@Override
	public List<ScoreModel> selectlist1(ScoreModel model) {
		String codeEmp = model.getCodeEmp();
		model.setCodeEmp(codeEmp == null ? "%" : "%"+ codeEmp+ "%");
		String codePro = model.getCodePro();
		model.setCodePro(codePro == null ? "%" : "%"+ codePro +"%");
		return dao.selectList1(model);
	}

}
