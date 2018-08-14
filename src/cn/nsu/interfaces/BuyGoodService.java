package cn.nsu.interfaces;

import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;

public interface BuyGoodService {
	//在购物车里结算
	public int placeOrderFromCart(int shopcartId,Orders order,Orderitem orderitem);
	
	//直接结算
	public int placeOrder(Orders order,Orderitem orderitem);

}
