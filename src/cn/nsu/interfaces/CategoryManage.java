package cn.nsu.interfaces;

import java.util.List;

import cn.nsu.entity.Product;
import cn.nsu.entity.Subtype;
import cn.nsu.entity.Type;

public interface CategoryManage {
	//添加一级分类
	public int addFirstType(Type type);
	
	//添加二级分类
	public int addSecondtype(Subtype subtype);
	
	//删除某一一级分类，并且删除其下属的二级分类，二级分类下面的商品
	public int deleteFirstTypeById(int typeId);
	
	//删除某个二级分类，并且删除相应的下面的所属商品
	public int deleteSecondtypeById(int subtypeId);
	
	//显示所有一级分类列表
	public List<String> showFirstType();
	
	//查询某个一级分类下属的二级分类列表
	public List<String> showSecondType(int type_id);
	
	//查某个二级分类下属的所有商品列表
	public List<Product> showProductsStype(int subtype_id);
}
