package com.zhuhe.company.score.service;

import java.util.List;

import com.zhuhe.company.score.model.ScoreModel;

public interface IScoreService {
	
	String insert(ScoreModel model);
	String delete(ScoreModel model);
	String deleteByCode(ScoreModel model);
	String update(ScoreModel model);
	List<ScoreModel> selectlist(ScoreModel model);
	ScoreModel selectModel(ScoreModel model);
	
	List<ScoreModel> selectlist1(ScoreModel model);
	
}
