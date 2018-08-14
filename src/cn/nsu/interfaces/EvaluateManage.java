package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.nsu.entity.Evaluate;

public interface EvaluateManage {
	//用户添加一条评论
	public int addEvaluate(int user_id, Evaluate evaluate);
		
	//用户修改评论
	public int updateEvaluate(int evaluateId,String content);
		
	//用户删除自己的评论
	public int deleteEvaluate(int evaluateId);
	
	//用户获取自己所有的评论
	public ArrayList<HashMap> showEvaluatelistByUser(int user_id,int pageIndex);
	//获取该商品的所有评论
	public ArrayList<HashMap> showEvaluateByProId(int product_id);
	
	//
	
	
	//
	
	
	//

}
