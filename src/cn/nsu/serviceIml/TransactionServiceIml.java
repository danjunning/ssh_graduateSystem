package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.UserDAO;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.interfaces.TransactionManage;
@Transactional
@Service("tranmaniml")
public class TransactionServiceIml implements TransactionManage {
	@Resource(name="ordersdao")
	OrdersDAO odao;
	@Resource(name="orderitemdao")
	OrderitemDAO oidao;
	@Resource(name="creditdao")
	CreditDAO cdao;
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="userdao")
	UserDAO udao;
	/**
	 * 商家发货,商品库存减少，
	 */
	@Override
	public int sendGood(String orderNumber,String dealDate) {	
		int proId=odao.selectByorderNum(orderNumber).getProductId();
		int buyCount=oidao.selectByOrderId(orderNumber).getProductQuantity();
		Product p=pdao.findById(proId);
		int productStock=p.getProductStock();
		int saleCount=p.getSaleCount();
		saleCount=saleCount+buyCount;
		productStock=productStock-buyCount;
		if(productStock>=0){
			pdao.updateSaleAndStock(proId, productStock, saleCount);
			odao.updateStatusByorderNum("待收货",dealDate,orderNumber);
			System.out.println("购买数量小于库存，成功");
			return 1;
		}else{
			System.out.println("购买数量大于库存");
			return 0;
		}
		
		
	}
	/**
	 * 商家查看所有待发货状态的订单
	 */
	@Override
	public ArrayList<HashMap> waitSendOrders() {
		List<Orders> oList=odao.selectOrderByStatus("待发货");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList !=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	 * 商家查看所有待收货订单列表
	 */
	@Override
	public ArrayList<HashMap> waitReceiveOrders() {
		List<Orders> oList=odao.selectOrderByStatus("待收货");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	 * 查看所有交易成功的订单列表
	 */
	/*
	@Override
	public ArrayList<HashMap> finishedOrder() {
		List<Orders> oList=odao.selectOrderByStatus("交易成功");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderId());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("ordestatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	 * 商家查看已经收货的订单
	 * @return
	 */
	public ArrayList<HashMap> ReceivedOrder() {
		List<Orders> oList=odao.selectOrderByStatus("已收货");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("ordedealDate",oList.get(i).getDealDate());//订单处理日期
				map.put("payBank", oList.get(i).getPayBank());//付款银行
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	
	public int countOrders(){
		return odao.countAll();
	}
	/**
	 * 商家查询所有待付款的订单
	 * @return
	 */
	public int countWaitPayOrder(){
		if(odao.selectOrderByStatus("待付款")!=null){
			return odao.selectOrderByStatus("待付款").size();
		}else{
			return 0;
		}	
	}
	/**
	 * 根据订单编号态查询订单
	 * @param orderNumber:订单编号
	 * @return 返回查询到的这条订单记录
	 */
	public ArrayList<HashMap> selectOrderByNum(String orderNumber){
		List<Orders> oList=odao.findByOrderNumber(orderNumber);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("dealDate", oList.get(i).getDealDate());//订单处理日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	 * 根据订单编号和状态查询订单
	 * @param orderNumber:订单编号
	 * @param orderStatus:订单状态
	 * @return 返回查询到的这条订单记录
	 */
	public ArrayList<HashMap> selectOrderByNumAndStatus(String orderNumber,String orderStatus){
		List<Orders> oList=odao.selectOrderByNumAndStatus(orderNumber, orderStatus);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//付款银行
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId", String.valueOf(oList.get(i).getOrderId()));//商品Id
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
	 * 商家查看订单详情
	 */
	public HashMap<String, String> OrderDetail(int orderId) {
		Orders order=odao.findById(orderId);
		String userName=udao.findById(order.getUserId()).getUname();
		Orderitem orderitem=oidao.selectByOrderId(order.getOrderNumber());
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("orderId", String.valueOf(order.getOrderId()));//订单主键
		map.put("orderNum",order.getOrderNumber());//订单编号
		map.put("userName",userName);//买家
		map.put("orderAddress",orderitem.getAddress());//收货人信息	
		map.put("proId", String.valueOf(order.getProductId()));//商品Id
		map.put("proName", orderitem.getProductName());//商品名
		map.put("proImg", orderitem.getProductImage());//商品图片		
		map.put("color", orderitem.getProductColor());//商品颜色
		map.put("style", orderitem.getProductStyle());//商品款式
		map.put("count",String.valueOf(orderitem.getProductQuantity()));//购买数量
		map.put("orderStatus",order.getOrderStatus());//订单状态
		map.put("ordedate",order.getOrderDate());//订单日期
		map.put("ordedealdate",order.getDealDate());//订单处理日期
		map.put("payBank",order.getPayBank());//订单支付银行
		map.put("totalprice", String.valueOf(orderitem.getTotalPrice()));//付款总价
		return map;
	}
	
	

}
