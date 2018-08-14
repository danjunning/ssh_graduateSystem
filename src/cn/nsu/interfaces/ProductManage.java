package cn.nsu.interfaces;

import java.util.ArrayList;
import java.util.List;

import cn.nsu.entity.Product;


public interface ProductManage {
	//添加商品
	public int addProduct(Product product,ArrayList<String> colorlist,
			ArrayList<String> stylelist,ArrayList<String> imglist);
	
	//删除商品
	public int deleteProduct(int productId);
	
	//修改商品
	public int updateProduct(Product product);
	
	//添加图片
	
	
	//添加款式
	
	
	//添加颜色
	
	
	//展示所有商品
	public List<Product> showAllProducts();
}
