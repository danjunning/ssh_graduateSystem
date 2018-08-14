package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
public interface OrderUser {
	//会员下订单
	public int placeOrder(Orders order,Orderitem orderitem);
	
	//某一会员获取未付款订单列表
	public ArrayList<HashMap> showUserUnpayOrder(int user_id,int pageIndex);
	
	//某一会员支付订单，
	public int payOrder(String orderNumber,int userId,String creditNumber,String paypassword);
	
	//获取所有待发货的订单
    public ArrayList<HashMap> UserwaitSendOrder(int user_id,int pageIndex);
	
	//某一会员获取待收货订单列表
	public ArrayList<HashMap> showUserWaitReceiveOrder(int user_id,int pageIndex);
	
	//会员确认收货
	public int UserconfirmOrder(int user_id,String orderNumber);
	
	//某一会员获取已经收获待评价的订单列表
	public ArrayList<HashMap> showSucessReceiveOrder(int user_id,int pageIndex);
	
	//某一会员取消订单
	public int cancelOrder(String orderNumber,int user_id);
		
	//某一会员获取交易关闭订单
	//public ArrayList<HashMap> showUserCloseOrder(int user_id);
	
	//查看订单详情
	public HashMap<String, String> OrderDetail(int orderId);
	
	
	
	
	
	
	
	
	
	
	
}
