package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.ColorDAO;
import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.StyleDAO;
import cn.nsu.entity.Color;
import cn.nsu.entity.Image;
import cn.nsu.entity.Orderitem;
import cn.nsu.entity.Product;
import cn.nsu.entity.Style;
import cn.nsu.interfaces.ProductManage;
import cn.nsu.tools.StringToList;
@Transactional
@Service("pmiml")
public class ProductMangeIml implements ProductManage {
	
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="imagedao")
	ImageDAO idao;
	@Resource(name="styledao")
	StyleDAO sdao;
	@Resource(name="colordao")
	ColorDAO cdao;
	/**
	 * 商品添加。相应的就要添加它的图片、颜色、款式等信息
	 * subtype_id:商品所属分类，product：一个商品实例
	 * ArrayList<String> color:颜色，存放多个颜色
	 * ArrayList<String> style：款式，存放多个款式
	 * ArrayList<String> img：存放多张图片
	 */
	@Override
	public int addProduct(Product product,ArrayList<String> colorlist,
			              ArrayList<String> stylelist,ArrayList<String> imglist)
	{
		pdao.save(product);
		for(int i=0;i<colorlist.size();i++){//添加颜色
			cdao.insertBycolorId(product.getProductId(), colorlist.get(i));
		}
		for(int i=0;i<stylelist.size();i++){//添加款式
			sdao.insertById_prd(product.getProductId(), stylelist.get(i));
		}
		for(int i=0;i<imglist.size();i++){//添加
			idao.insertByPid(product.getProductId(), imglist.get(i));
		}
		return 1;
	}
    /**
     * 删除商品，删除商品后，相应的要删除它的颜色、款式、图片
     * productId:商品唯一标识
     */
	@Override
	public int deleteProduct(int productId) {
		pdao.deleteById(productId);
		cdao.deleteByprId(productId);
		sdao.deleteById_prd(productId);
		idao.deleteByPid(productId);
		System.out.println("成功删除了该商品以及它的图片、颜色、款式");
		return 1;
	}
	/**
	 * 更新商品的记录，
	 */
	@Override
	public int updateProduct(Product product) {	
		return 1;
	}
	public int updatePrice(Integer productId,Double productPrice) {	
		return pdao.updatePrice(productId, productPrice);
	}
	public int updateStock(Integer productId,int productStock) {	
		return pdao.updateStock(productId, productStock);
	}
	/**
	 * 取出所有商品
	 */
	@Override
	public List<Product> showAllProducts() {	
		return pdao.findAll();
	}
	/**
	 * 根据商品名称来获取商品记录
	 * @param productName:商品名称
	 * @return
	 */
	public List<Product> showProductByName(String productName){
		return pdao.selectByParm(productName);
	}
	
	/**
	 * 手机前8条记录
	 */
	public ArrayList<HashMap> Phonelist(){
		List<Product> pList=pdao.findTopeight(8);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(1).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
		
	}
	/**
	 * 电脑前8条记录
	 */
	public ArrayList<HashMap> Computerlist(){
		List<Product> pList=pdao.findTopeight(9);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(1).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	/**
	 * 穿戴智能设备前8条记录
	 */
	public ArrayList<HashMap> Wearlist(){
		List<Product> pList=pdao.findTopeight(11);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
		
	}
	/**
	 * 摄影前8条记录
	 */
	public ArrayList<HashMap> Camarolist(){
		List<Product> pList=pdao.findTopeight(13);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(1).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}	
	}
	public int countByTyId(int typeId){
		return pdao.findByTypeId(typeId).size();
	}
	public ArrayList<HashMap> selectProListByTyId(int typeId,int pageIndex){
		List<Product> pList=pdao.findByTypeId(typeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据销量排序
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListBySale(int typeId,int pageIndex){
		List<Product> pList=pdao.orderBysaleCountAndType(typeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 价格降序
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListByupPrice(int typeId,int pageIndex){
		List<Product> pList=pdao.orderBypriceDeAndType(typeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据价格升序
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListByonPrice(int typeId,int pageIndex){
		List<Product> pList=pdao.orderBypriceAscAndType(typeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	public int countBySubId(int subtypeId){
		return pdao.findBySubtypeId(subtypeId).size();
	}
	public ArrayList<HashMap> selectProListBySubId(int subtypeId,int pageIndex){
		List<Product> pList=pdao.findBySubtypeId(subtypeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据销量排序
	 * @param subtypeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListBySaleAndSub(int subtypeId,int pageIndex){
		List<Product> pList=pdao.orderBysaleCount(subtypeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 按照交个降序
	 * @param subtypeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListByPriceUpAndSub(int subtypeId,int pageIndex){
		List<Product> pList=pdao.orderBypriceDe(subtypeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 按照价格升序
	 * @param subtypeId
	 * @return
	 */
	public ArrayList<HashMap> sortProListByPriceOnAndSub(int subtypeId,int pageIndex){
		List<Product> pList=pdao.orderBypriceAsc(subtypeId,pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	public int countByParam(String param){
		return pdao.selectByParm(param).size();
	}
	public ArrayList<HashMap> selectProListByParam(String param,int pageIndex){
		List<Product> pList=pdao.selectByParm(param, pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据销量排序
	 * @param key_param
	 * @param pageIndex
	 * @return
	 */
	public ArrayList<HashMap> sortProListBySaleAndPar(String key_param,int pageIndex){
		List<Product> pList=pdao.orderBysaleCountAndParm(key_param, pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据价格降序
	 * @param key_param
	 * @param pageIndex
	 * @return
	 */
	public ArrayList<HashMap> sortProListByPriceUpAndPar(String key_param,int pageIndex){
		List<Product> pList=pdao.orderBypriceDeAndParam(key_param, pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 根据价格升序
	 * @param key_param
	 * @param pageIndex
	 * @return
	 */
	public ArrayList<HashMap> sortProListByPriceOnAndPar(String key_param,int pageIndex){
		List<Product> pList=pdao.orderBypriceAscAndParm(key_param, pageIndex);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	
	public Product selectById(int productId){
		return pdao.findById(productId);
	}
	public List<Image> selectImgByPId(int productId){
		return idao.findByProductId(productId);
	}
	public List<Color> selectColorByPId(int productId){
		return cdao.findByProductId(productId);
	}
	public List<Style> selectStyleByPId(int productId){
		return sdao.findByProductId(productId);
	}
	/**
	 * 取出第一张图片
	 * @param productId
	 * @return
	 */
	public String image(int productId){
		return idao.findByProductId(productId).get(0).getImage();	
	}
	/**
	 * 
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> getTopeightByParam(String param){
		List<Product> pList=pdao.LateTopeightByParam(param);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 一级分类的最新8条产品
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> getTopeightByty(int typeId){
		List<Product> pList=pdao.LateTopeight(typeId);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	/**
	 * 二级分类下的最新8个产品
	 * @param typeId
	 * @return
	 */
	public ArrayList<HashMap> getTopeightBysub(int subtypeId){
		List<Product> pList=pdao.LateTopeightBysub(subtypeId);
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				map.put("productName", pList.get(i).getProductName());
				map.put("productSale", pList.get(i).getSaleCount());
				map.put("productPrice", pList.get(i).getProductPrice());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}
	}
	
}
