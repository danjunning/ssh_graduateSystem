package cn.nsu.serviceIml;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.OrderitemDAO;
import cn.nsu.dao.OrdersDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.ShopcartDAO;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Orders;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.interfaces.BuyGoodService;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.GetOrderNum;
@Transactional
@Service("bgiml")
public class BuyGoodsServiceIml implements BuyGoodService {
    @Resource(name="ordersdao")
	OrdersDAO odao;
    @Resource(name="orderitemdao")
	OrderitemDAO oidao;
    @Resource(name="shopcartdao")
    ShopcartDAO scdao;
    @Resource(name="productdao")
	ProductDAO pdao;
    @Resource(name="imagedao")
	ImageDAO idao;
    @Resource(name="getTime")
	GetCurrentDate gcd;
    
    /**
     * 在购物车中结算，在提交订单后，就要相应地删除购物车中的记录
     */
	@Override
	public int placeOrderFromCart(int shopcartId,Orders order, Orderitem orderitem) {
		scdao.deleteByshopcartId(shopcartId);
		odao.save(order);
		oidao.save(orderitem);
		return 1;
	}
	/**
     * 在购物车中结算，一次提交多个订单，在提交订单后，就要相应地删除购物车中的记录
     */
	public int placeOrders(List<Integer> cartidlist,int userId, String address) {
		for(int i=0;i<cartidlist.size();i++){
			System.out.println(cartidlist.get(i));
			Shopcart cart=scdao.findById(cartidlist.get(i));
			Product p=pdao.findById(cart.getProductId());
			String productImage=idao.findByProductId(p.getProductId()).get(0).getImage();
			Orders orders=new Orders();
			Orderitem oi=new Orderitem();
			String orderNumber=GetOrderNum.getOrderNo();
			orders.setOrderDate(gcd.getCurrentDate());
			orders.setOrderNumber(orderNumber);
			orders.setOrderStatus("待付款");
			orders.setUserId(userId);
			orders.setProductId(cart.getProductId());
			orders.setTotalPrice(p.getProductPrice()*cart.getProAmount());
			oi.setTotalPrice(p.getProductPrice()*cart.getProAmount());
			oi.setOrderId(orderNumber);
			oi.setAddress(address);
			oi.setProductName(p.getProductName());
			oi.setProductStyle(cart.getProStyle());
			oi.setProductColor(cart.getProColor());
			oi.setProductImage(productImage);
			oi.setProductQuantity(cart.getProAmount());
			odao.save(orders);
			oidao.save(oi);
			scdao.deleteByshopcartId(cartidlist.get(i));
		}
		return 1;
	}
    /**
     * 查看商品详情后直接下订单
     */
	@Override
	public int placeOrder(Orders order, Orderitem orderitem) {
		odao.save(order);
		oidao.save(orderitem);
		return 1;
	}

}
