package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.EvaluateDAO;
import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.ShopcartDAO;
import cn.nsu.entity.Credit;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.interfaces.OrderUser;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;
@Transactional
@Service("opiml")
public class OrderPlaceIml implements OrderUser {
	@Resource(name="getTime")
	GetCurrentDate gcd;
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="imagedao")
	ImageDAO idao;
	@Resource(name="ordersdao")
	OrdersDAO odao;
	@Resource(name="orderitemdao")
	OrderitemDAO oidao;
	@Resource(name="creditdao")
	CreditDAO cdao;
	@Resource(name="shopcartdao")
    ShopcartDAO scdao;
	@Resource(name="evaluatedao")
    EvaluateDAO edao;
	
	
	
	/**
	 * 会员下订单，从详情页面，此时订单状态为：待付款
	 * order：订单基本信息（订单号，下单时间）
	 * orderitem:订单配送信息（下单的商品，收货地址，价钱）
	 */
	@Override
	public int placeOrder(Orders order, Orderitem orderitem) {
		odao.save(order);
		oidao.save(orderitem);
		return 1;
	}
	public int countUserAllOrder(int user_id){
		return odao.selectOrderByUid(user_id).size();
	}
	/**
	 * 用户查看所有订单
	 * @param user_id
	 * @return
	 */
	public ArrayList<HashMap> UserallOrder(int user_id,int pageIndex){
		List<Orders> oList=odao.selectOrderByUid(user_id,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				if(edao.findByUidAndOid(user_id, oList.get(i).getOrderId())!=null){
					System.out.println("pignjia1guo1");
					map.put("orderStatus","已评价");//订单状态
				}else{
					map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				}
				map.put("proId", oList.get(i).getProductId());//商品Id
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalPrice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	public int CountUserUnpayOrder(int user_id){
		return odao.selectOrderByUserIdAndStatus(user_id,"待付款").size();
	}
	/**
	 * y用户获取自己待付款的订单信息列表
	 */
	@Override
	public ArrayList<HashMap> showUserUnpayOrder(int user_id,int pageIndex) {
		List<Orders> oList=odao.selectOrderByUserIdAndStatus(user_id,"待付款",pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				System.out.println(oList.get(i).getOrderNumber());
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号		
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getProductId()));//商品Id
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	/**
	 * 支付订单，修改订单的状态，并且扣除会员银行卡的金钱，此时订单状态为：待发货
	 * 检查银行卡的密码是否正确,检查余额是否足够
	 */
	@Override
	public int payOrder(String orderNumber ,int userId,String creditNumber,String paypassword) {
		System.out.println("payOrder:"+orderNumber);
		System.out.println("payOrder:"+userId);
		Credit credit=cdao.selectByCreNumAndUid(creditNumber,userId);
		Orders order=odao.selectByorderNum(orderNumber,userId);
		Double price=order.getTotalPrice();
		if(credit.getPayPassword().equals(paypassword)){
			if(credit.getBalance()>=price){
				odao.updateStatusByorderNumAndUId("待发货",gcd.getCurrentDate(),orderNumber,creditNumber,userId);//订单修改为待发货
				Float balance=(float) (credit.getBalance()-price);//计算新的余额
				cdao.updateBalanceBycreNum(balance, creditNumber);
				return 1;
			}else{
				System.out.println("余额不够");
				return -1;
			}
		}else{
			System.out.println("支付密码不对");
			return 0;
		}
	}
	/**
	 * 同时支付多个订单
	 * @param orderNumber
	 * @param userId
	 * @param creditNumber
	 * @param paypassword
	 * @param price
	 * @return
	 */
	public int payOrders(List<String> orderNumber ,int userId,String creditNumber,String paypassword,float price) {
		System.out.println("payOrders:"+orderNumber);
		System.out.println("payOrders:"+userId);
		Credit credit=cdao.selectByCreNumAndUid(creditNumber,userId);
		if(credit.getBalance()>=price){
			if(credit.getPayPassword().equals(paypassword)){
				for(int i=0;i<orderNumber.size();i++){
					odao.updateStatusByorderNumAndUId("待发货",gcd.getCurrentDate(),orderNumber.get(i),creditNumber,userId);//订单修改为待发货
				}
				Float balance=(float) (credit.getBalance()-price);//计算新的余额
				cdao.updateBalanceBycreNum(balance, creditNumber);
				return 1;
			}else{
				System.out.println("支付密码不对");
				return 0;
			}
			
		}else{
			System.out.println("余额不够");
			return -1;
		}
		
	}
	public int CountUserwaitSendOrder(int user_id){
		return odao.selectOrderByUserIdAndStatus(user_id,"待发货").size();
	}
	/**
	 * 会员获取自己待发货的订单列表
	 */
	@Override
    public ArrayList<HashMap> UserwaitSendOrder(int user_id,int pageIndex){
		List<Orders> oList=odao.selectOrderByUserIdAndStatus(user_id,"待发货",pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", oList.get(i).getProductId());//商品Id
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalPrice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	public int countUserWaitReceiveOrder(int user_id){
		return odao.selectOrderByUserIdAndStatus(user_id,"待收货").size();
	}
	/**
	 * 用户查看自己待收货订单列表
	 */
	@Override
	public ArrayList<HashMap> showUserWaitReceiveOrder(int user_id,int pageIndex) {
		List<Orders> oList=odao.selectOrderByUserIdAndStatus(user_id,"待收货",pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行卡
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", oList.get(i).getProductId());//商品Id
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	public int CountSucessReceiveOrder(int user_id){
		return odao.selectOrderByUserIdAndStatus(user_id,"已收货").size();
	}
	/**
	 * 用户获取已收货还没有评价的订单。
	 */
	@Override
	public ArrayList<HashMap> showSucessReceiveOrder(int user_id,int pageIndex) {
		List<Orders> oList=odao.selectOrderByUserIdAndStatus(user_id,"已收货",pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("orderId", oList.get(i).getOrderId());//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行卡
				if(edao.findByUidAndOid(user_id, oList.get(i).getOrderId())!=null){
					System.out.println("pignjia1guo1");
					map.put("orderStatus","已评价");//订单状态
				}else{
					map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				}
				map.put("proId", oList.get(i).getProductId());//商品Id
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 用户申请取消订单，如果是已经付款了的订单，就要申请退款
	 * @param orderNumber:订单唯一编号
	 * @return
	 * 
	 */
	@Override
	public int cancelOrder(String orderNumber,int user_id) {
		String statusString=odao.selectByorderNum(orderNumber, user_id).getOrderStatus();
		if(statusString.equals("待付款")){
			System.out.println("没有付款，直接取消订单");
			odao.updateStatusByorderNumAndUId("订单已取消",gcd.getCurrentDate(),orderNumber,user_id);//订单修改为：交易关闭
		}else{
			//申请退款
			System.out.println("付款了，要申请退款");
			odao.updateStatusByorderNum("退款申请中",orderNumber);	
			
		}	
		return 1;
	}
	/**
	 * 没有有付款，用户自己取消订单
	 * @param orderNumber
	 * @param user_id
	 * @return
	 */
	public int preCancel(String orderNumber,int user_id){
		System.out.println("没有付款，直接取消订单");
		odao.updateStatusByorderNumAndUId("订单已取消",gcd.getCurrentDate(),orderNumber,user_id);//订单修改为：交易关闭
		return 1;
		
	}
	/**
	 * 已经付款，已经收货，用户取消订单，商品库存、销量
	 * @param orderNumber
	 * @param user_id
	 * @return
	 */
	public int edCancel(String orderNumber,int user_id){
		odao.updateStatusByorderNum("退款申请中",orderNumber);	
		return 1;
		
	}
	/**
	 * 用户确认已经收到货品，此时该商品销量+1
	 */
	@Override
	public int UserconfirmOrder(int user_id, String orderNumber) {
		odao.updateStatusByorderNumAndUId("已收货",gcd.getCurrentDate(),orderNumber,user_id);//订单修改为已收货
		Orders orders=odao.selectByorderNum(orderNumber);
		Orderitem oi= oidao.selectByOrderId(orderNumber);
		Product p=pdao.findById(orders.getProductId());
		System.out.println("销售量："+p.getSaleCount()+"  购买数量"+oi.getProductQuantity());
		pdao.updateSaleCount(orders.getProductId(), p.getSaleCount()+oi.getProductQuantity());
		return 1;
	}
	/*
	 * 确认已经收到货品，此时该商品销量+1
	 */
	public int UserconfirmOrder(String orderNumber) {
		odao.updateStatusByorderNum("已收货", gcd.getCurrentDate(), orderNumber);
		//odao.updateStatusByorderNumAndUId("已收货",gcd.getCurrentDate(),orderNumber,user_id);//订单修改为已收货
		Orders orders=odao.selectByorderNum(orderNumber);
		Orderitem oi= oidao.selectByOrderId(orderNumber);
		Product p=pdao.findById(orders.getProductId());
		pdao.updateSaleCount(orders.getProductId(), p.getSaleCount()+oi.getProductQuantity());
		return 1;
	}
	/**
	 * 删除订单
	 * @param orderNumber
	 * @return
	 */
	public int deleteOrder(String orderNumber){
		Orders orders=odao.selectByorderNum(orderNumber);
		odao.delete(orders);
		oidao.deleteByorderId(orderNumber);
		return 1;
	}
	/**
	 * 获取用户交易关闭的订单列表
	 * @return ArrayList<HashMap>:把用户订单的信息封装到集合中
	 */
	/*
	@Override
	public ArrayList<HashMap> showUserCloseOrder(int user_id) {
		List<Orders> oList=odao.selectOrderByStatus("交易关闭");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderId());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行卡
				map.put("ordestatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proImg", orderitem.getProductImage());//商品图片
				map.put("proName", orderitem.getProductName());//商品名
				map.put("color", orderitem.getProductColor());//商品颜色
				map.put("style", orderitem.getProductStyle());//商品款式
				map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
				map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
				aList.add(map);			
			}
			return aList;
		}else{
			return null;
		}
	}
	*/
	/**
	 * 
	 * 查看订单详情
	 */
	@Override
	public HashMap<String, String> OrderDetail(int orderId) {
		Orders order=odao.findById(orderId);
		Orderitem orderitem=oidao.selectByOrderId(order.getOrderNumber());
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("orderId", String.valueOf(order.getOrderId()));//订单主键
		map.put("orderNum",order.getOrderNumber());//订单编号
		map.put("address",orderitem.getAddress());//收货人信息	
		map.put("proImg", orderitem.getProductImage());//商品图片
		map.put("proName", orderitem.getProductName());//商品名
		map.put("color", orderitem.getProductColor());//商品颜色
		map.put("style", orderitem.getProductStyle());//商品款式
		map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
		map.put("orderstatus",order.getOrderStatus());//订单状态
		map.put("orderdate",order.getOrderDate());//订单日期
		map.put("orderdealdate",order.getDealDate());//订单处理日期
		map.put("payBank",order.getPayBank());//订单支付银行
		map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
		return map;
	}
	public Orders selectOrders(int orderId){
		return odao.findById(orderId);
	}
	public Orderitem selectOrderitem(String orderNumber){
		return oidao.selectByOrderId(orderNumber);
	}
	public int orderId(String orderNumber){	
		return odao.selectByorderNum(orderNumber).getOrderId();
	}
	

	

}
