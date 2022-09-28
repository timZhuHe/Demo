package com.zhuhe.company.score.dao;

import java.util.List;

import com.zhuhe.company.score.model.ScoreModel;

public interface IScoreDao {
	
	Integer insert (ScoreModel model);
	
	Integer delete (ScoreModel model);
	
	Integer deleteByCode (ScoreModel model);
	
	Integer updateAll (ScoreModel model);
	
	Integer updateActive (ScoreModel model);

	List<ScoreModel> selectList(ScoreModel model);
	
	ScoreModel selectModel(ScoreModel model);
	
	List<ScoreModel> selectList1(ScoreModel model);
	
}
