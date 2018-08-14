package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface RefundManage {
	//用户申请退款
	public int applyRefund(String orderNumber);
	
	//商家查看所有退款订单列表
	public ArrayList<HashMap> showWaitRefundlist();
	
	//用户查看个人退款记录
	public ArrayList<HashMap> showUserRefundlist(int user_id);
	
	//商家处理退款
	public int refund(int orderId,String payBank,String dealDate);
	
	//商家查看所有退款成功的订单
	public ArrayList<HashMap> showSuccRefundlist();
	
	//用户查看自己退款成功的订单
	public ArrayList<HashMap> showUserSuccRefundlist(int user_id);

}
