package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.EvaluateDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.UserDAO;
import cn.nsu.entity.Evaluate;
import cn.nsu.entity.Product;
import cn.nsu.entity.User;
import cn.nsu.interfaces.EvaluateManage;
@Transactional
@Service("evaiml")
public class EvaluateManageIml implements EvaluateManage {
    @Resource(name="evaluatedao")
    EvaluateDAO edao;
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="userdao")
	UserDAO udao;
	/**
	 * 用户添加一条评论
	 */
	@Override
	public int addEvaluate(int user_id, Evaluate evaluate) {
		if(edao.isExist(user_id, evaluate.getOrderId())){
			System.out.println("评价过");
			return 0;
		}else{
			System.out.println("没有评价过");
			Product product=pdao.findById(evaluate.getProductId());
			pdao.updateCommentCount(evaluate.getProductId(), product.getCommentCount()+1);
			edao.save(evaluate);
			return 1;
		}	
	}
    
	/**
	 * 用户更新评论
	 */
	@Override
	public int updateEvaluate(int evaluateId, String content) {
		edao.updateByevaId(evaluateId, content);
		return 1;
	}
    /**
     * 用户根据id删除评论
     */
	@Override
	public int deleteEvaluate(int evaluateId) {
		edao.deleteByevaId(evaluateId);
		return 1;
	}
	public int CountEvaluatelistByUser(int user_id){
		return edao.selectByUidOrderByDate(user_id).size();
	}
	public Evaluate selectByUidAndOId(int userId,int orderId){
		return edao.findByUidAndOid(userId, orderId);
	}
    /**
     * 用户查看自己的评论,根据评论时间来排序，最近评论的放在前面
     */
	@Override
	public ArrayList<HashMap> showEvaluatelistByUser(int user_id,int pageIndex) {
		List<Evaluate> elist=edao.selectByUidOrderByDate(user_id,pageIndex);
		ArrayList<HashMap> arrayList=new ArrayList<HashMap>();	
		for(int i=0;i<elist.size();i++){
			HashMap<String, Object> map= new HashMap<String, Object>();
			Product p=pdao.findById(elist.get(i).getProductId());
			map.put("evaId",elist.get(i).getEvaluateId());//评论Id
			map.put("proId",elist.get(i).getProductId());//商品Id
			map.put("proName", p.getProductName());//商品名
			map.put("content", elist.get(i).getContent());//评论内容
			map.put("evaDate", elist.get(i).getEvaluateDate());//评论日期
			arrayList.add(map);
		}
		return arrayList;
	}
    /**
     * 管理员或商品详情显示某种商品所有评论，包括评论人、时间、内容、评论的商品
     */
	@Override
	public ArrayList<HashMap> showEvaluateByProId(int product_id) {
		List<Evaluate> elist=edao.selectByPidOrderByDate(product_id);
		ArrayList<HashMap> arrayList=new ArrayList<HashMap>();		
		for(int i=0;i<elist.size();i++){
			HashMap<String, String> map= new HashMap<String, String>();
			Product p=pdao.findById(elist.get(i).getProductId());
			User u=udao.findById(elist.get(i).getUserId());
			map.put("proId", String.valueOf(p.getProductId()));//商品ID
			map.put("userName", u.getUname());//用户名
			map.put("proName", p.getProductName());//商品名
			map.put("content", elist.get(i).getContent());//评论内容
			map.put("evaDate", elist.get(i).getEvaluateDate());//评论日期
			arrayList.add(map);
		}
		return arrayList;
	}
	 
	/**
	 * 商家查看全部评论
	 */
	public ArrayList<HashMap> showAllEvaluate() {
		List<Evaluate> elist=edao.selectAll();
		ArrayList<HashMap> arrayList=new ArrayList<HashMap>();		
		for(int i=0;i<elist.size();i++){
			HashMap<String, Object> map= new HashMap<String, Object>();
			Product p=pdao.findById(elist.get(i).getProductId());
			User u=udao.findById(elist.get(i).getUserId());
			map.put("proId", p.getProductId());//商品id
			map.put("proName", p.getProductName());//商品名
			map.put("userName", u.getUname());//用户名	
			map.put("content", elist.get(i).getContent());//评论
			map.put("evaDate", elist.get(i).getEvaluateDate());//评论日期
			arrayList.add(map);
		}
		return arrayList;
	}

}
