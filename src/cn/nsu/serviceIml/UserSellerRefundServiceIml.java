package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.nsu.dao.CreditDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.entity.Credit;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.interfaces.RefundManage;
@Transactional
@Service("usrefuiml")
public class UserSellerRefundServiceIml implements RefundManage {
    @Resource(name="ordersdao")
    OrdersDAO odao;
    @Resource(name="orderitemdao")
    OrderitemDAO oidao;
    @Resource(name="creditdao")
	CreditDAO cdao;
    @Resource(name="productdao")
    ProductDAO pdao;
    
    /**
     * 用户申请退款
     */
	@Override
	public int applyRefund(String orderNumber) {
		odao.updateStatusByorderNum("退款申请中",orderNumber);
		return 1;
	}
    /**
     * 商家查看退款订单列表
     */
	@Override
	public ArrayList<HashMap> showWaitRefundlist() {
		List<Orders> oList=odao.selectOrderByStatus("退款申请中");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("dealDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
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
	 * 商家查看所有跟退款有关的订单
	 * @return
	 */
	public ArrayList<HashMap> selectOrderLikeStatus() {
		System.out.println("模糊查询有关退款的订单！");
		List<Orders> oList=odao.selectOrderLikeStatus("退款");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("dealDate", oList.get(i).getDealDate());//退款日期
				map.put("payBank", oList.get(i).getPayBank());//银行账号
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
    /**
     * 用户查看自己退款订单列表
     */
	@Override
	public ArrayList<HashMap> showUserRefundlist(int user_id) {
		List<Orders> oList=odao.selectOrderByUidandStatus(user_id, "退款申请中");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
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
     * 商家处理退款，把钱退回到用户的账户中,库存要修改，销量也要修改
     */
	@Override
	public int refund(int orderId, String payBank, String dealDate) {
		Orders order=odao.findById(orderId);	
		int proId=order.getProductId();
		Product p=pdao.findById(proId);	
		int saleCount=p.getSaleCount();
		int productStock=p.getProductStock();
		int buyCount=oidao.selectByOrderId(order.getOrderNumber()).getProductQuantity();
		productStock=productStock+buyCount;	
		saleCount=saleCount-buyCount;
		Double price=order.getTotalPrice();
		System.out.println("用户ID:"+order.getUserId());
		Credit credit=cdao.selectByCreNumAndUid(payBank, order.getUserId());
		float balance=credit.getBalance();
		balance=(float) (balance+price);
		try {
			cdao.updateBalanceBycreNum(balance, payBank);//退钱到用户卡中
			odao.updateStatusByorderId("已退款", dealDate, orderId);//修改订单为“已退款”
			System.out.println("同时 修改商品库存和销量");
			pdao.updateSaleAndStock(proId, productStock, saleCount);//同时 修改商品库存和销量		
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("退款失败！！！");
			return 0;
		}
		
	}
    /**
     * 商家查看成功退款的订单列表
     */
	@Override
	public ArrayList<HashMap> showSuccRefundlist() {
		List<Orders> oList=odao.selectOrderByStatus("已退款");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("dealDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
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
     * 用户查单自己退款成功的订单
     */
	@Override
	public ArrayList<HashMap> showUserSuccRefundlist(int user_id) {
		List<Orders> oList=odao.selectOrderByUidandStatus(user_id, "已退款");
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,String> map=new HashMap<String,String>();
				map.put("orderId", String.valueOf(oList.get(i).getOrderId()));//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
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
			return aList;
		}
		
	}
	public int CountAllRefund(int user_id){
		return odao.selectLikeUidAndStatus("退款", user_id).size();
	}
	public ArrayList<HashMap> showAllRefund(int user_id,int pageIndex) {
		List<Orders> oList=odao.selectLikeUidAndStatus("退款", user_id,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(oList!=null){
			for(int i=0;i<oList.size();i++){
				Orderitem orderitem=oidao.selectByOrderId(oList.get(i).getOrderNumber());
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("orderId", oList.get(i).getOrderId());//订单主键
				map.put("orderDate", oList.get(i).getOrderDate());//订单日期
				map.put("dealDate", oList.get(i).getDealDate());//订单日期
				map.put("orderNum", oList.get(i).getOrderNumber());//订单编号
				map.put("payBank", oList.get(i).getPayBank());//银行账号
				map.put("orderStatus",oList.get(i).getOrderStatus());//订单状态
				map.put("proId",oList.get(i).getProductId());//商品名
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
			return aList;
		}
		
	}
	
	public int deleteOrder(int orderId){
		Orders o=odao.findById(orderId);
		odao.deleteById(orderId);
		System.out.println("0000000000000000000000:"+o.getOrderNumber());
		oidao.deleteByorderId(o.getOrderNumber());
		return 1;
	}

	

}
