package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.ShopcartDAO;
import cn.nsu.entity.Image;
import cn.nsu.entity.Product;
import cn.nsu.entity.Shopcart;
import cn.nsu.interfaces.ShopCart;
import cn.nsu.tools.GetOrderNum;
@Transactional
@Service("sciml")
public class ShopCartServiceIml implements ShopCart {
    @Resource(name="shopcartdao")
    ShopcartDAO scdao;
    @Resource(name="productdao")
    ProductDAO pdao;
    @Resource(name="imagedao")
    ImageDAO idao;
    /**
     * 用户向购物车中加入商品记录
     */
	@Override
	public int addShopCart(Shopcart shopcart) {
		scdao.save(shopcart);
		return 1;
	}
    /**
     * 用户修改购物车中商品的数量
     */
	@Override
	public int updateCartCount(int shopcartId,int proAmount) {
		scdao.update(shopcartId, proAmount);
		return 1;
	}
    /**
     * 用户删除购物车中的商品
     */
	@Override
	public int delelteById(int shopcartId) {
		scdao.deleteByshopcartId(shopcartId);
		return 1;
	}
    /**
     * 获取用户的购物车
     */
	@Override
	public ArrayList<HashMap> showShopCartlist(int user_id) {
		List<Shopcart> sclist=scdao.findByUserId(user_id);
		ArrayList<HashMap> arrayList=new ArrayList<HashMap>();
		if(sclist!=null){
			for(int i=0;i<sclist.size();i++){
				try {
					Product product=pdao.findById(sclist.get(i).getProductId());
					List<Image> imagelist=idao.findByProductId(product.getProductId());
					if(product!=null){
						String img=imagelist.get(0).getImage();//取出第一张图片
						HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("shopcartId",String.valueOf(sclist.get(i).getShopcartId()));
						map.put("productId", product.getProductId());
						map.put("proImg", img);
						map.put("proName", product.getProductName());
						map.put("proColor", sclist.get(i).getProColor());
						map.put("proStyle", sclist.get(i).getProStyle());
						map.put("proPrice", product.getProductPrice());
						map.put("proAmount",String.valueOf(sclist.get(i).getProAmount()));
						arrayList.add(map);
					}
					
				} catch (Exception e) {
					
				}
								
			}
			return arrayList;
		}else{
			return null;
		}	
	}
	
	public Shopcart selectById(int shopcartId){
		return scdao.findById(shopcartId);
	}
	
	public ArrayList<HashMap> preorderlist(List<Integer> shopcartIds){
		ArrayList<HashMap> arrayList=new ArrayList<HashMap>();
		for(int i=0;i<shopcartIds.size();i++){
			Shopcart cart=scdao.findById(shopcartIds.get(i));
			Product p=pdao.findById(cart.getProductId());
			List<Image> iList=idao.findByProductId(cart.getProductId());
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("shopcartId", cart.getShopcartId());
			map.put("productId", cart.getProductId());
			map.put("productName", p.getProductName());
			map.put("productImage", iList.get(0).getImage());
			map.put("productPrice", p.getProductPrice());
			map.put("productQuantity", cart.getProAmount());
			//Double totalPrice=cart.getProAmount()*p.getProductPrice();
			map.put("totalPrice", cart.getProAmount()*p.getProductPrice());
			map.put("productColor", cart.getProColor());
			map.put("productStyle", cart.getProStyle());
			map.put("orderNumber", GetOrderNum.getOrderNo());
			arrayList.add(map);
		}
		return arrayList;
		
	}

}
