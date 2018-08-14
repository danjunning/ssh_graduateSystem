package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import cn.nsu.entity.Shopcart;

public interface ShopCart {
	//加入购物车
	public int addShopCart(Shopcart shopcart);
	
	//修改购物车中商品的数量
	public int updateCartCount(int shopcartId,int proAmount);
	
	//删除购物中商品
	public int delelteById(int shopcartId);
	
	//获取用户的购物车清单
	public ArrayList<HashMap> showShopCartlist(int user_id);
	
	

}
