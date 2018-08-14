package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.nsu.entity.Orderitem;


public interface TransactionManage {
	
	//卖家发货
	public int sendGood(String orderNumber,String dealDate);
	
	//获取所有待处理发货的订单
    public ArrayList<HashMap> waitSendOrders();
    
    //查看所有待收货的订单
    public ArrayList<HashMap> waitReceiveOrders();
	
	
    
    //查看订单详情
	

}
