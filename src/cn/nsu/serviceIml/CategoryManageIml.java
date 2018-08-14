package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.ColorDAO;
import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.ProductDAO;
import cn.nsu.dao.StyleDAO;
import cn.nsu.dao.SubtypeDAO;
import cn.nsu.dao.TypeDAO;
import cn.nsu.entity.Product;
import cn.nsu.entity.Subtype;
import cn.nsu.entity.Type;
import cn.nsu.interfaces.CategoryManage;
@Transactional
@Service("cgiml")
public class CategoryManageIml implements CategoryManage {
	@Resource(name="typedao")
	TypeDAO tdao;
	@Resource(name="subtypedao")
	SubtypeDAO stdao;
	@Resource(name="productdao")
	ProductDAO pdao;
	@Resource(name="imagedao")
	ImageDAO idao;
	@Resource(name="colordao")
	ColorDAO cdao;
	@Resource(name="styledao")
	StyleDAO sdao;
	
	/**
	 * 添加一个一级分类，type:一级分类实例
	 */
	@Override
	public int addFirstType(Type type) {
		if(1==tdao.save(type)){
			System.out.println("添加一级分类成功");
			return 1;
		}else{
			return 0;
		}
	}
    /**
     * 添加一个二级分类，subtype:一个二级分类实体
     */
	@Override
	public int addSecondtype(Subtype subtype) {
		if(1==stdao.save(subtype)){
			System.out.println("添加二级分类成功。");
			return 1;
		}else{
			return 0;
		}
		
	}

	/**
	 * 删除一个一级分类，如果一级分类下有二级分类，先删除下面的二级分类，如果二级分类下有商品，
	 * 先删除二级分类里面的所有商品,要删除商品的颜色、图片、款式
	 * typeId：该一级分类的标识
	 */
	@Override
	public int deleteFirstTypeById(int typeId) {
		//1.找到所有二级分类
		List<Subtype> stlist=stdao.selectBytypeId(typeId);
		if(stlist.size()>0){
			for(int i=0;i<stlist.size();i++){
				//2.循环找到每个二级分类下的全部商品
				List<Product> proList=pdao.findBySubtypeId(stlist.get(i).getSubtypeId());
				if(proList.size()>0){
					for(int j=0;j<proList.size();j++){
						//int proId=proList.get(j).getProductId();
						idao.deleteByPid(proList.get(j).getProductId());
						cdao.deleteByprId(proList.get(j).getProductId());
						sdao.deleteById_prd(proList.get(j).getProductId());
					}
					//3.根据二级分类id删除其所有商品
					System.out.println("准备删除:"+stlist.get(i).getSubtypeName());
					pdao.deleteBysubtypId(stlist.get(i).getSubtypeId());					
					System.out.println("删除一个二级分类商品:<"+stlist.get(i).getSubtypeName()+">");
				}
				//4.删除二级分类
				stdao.deleteBytypeId(typeId);
				System.out.println("删除旗下的所有二级分类：");
			}
		}
		//删除一级分类
		tdao.deleteById(typeId);
		System.out.println("删除该一级分类：");
		return 1;
	}
    /**
     * 删除二级分类，先检查二级分类下是否有商品，若有，先删除其中所属的全部商品
     * subtypeId:二级分类标识
     */
	@Override
	public int deleteSecondtypeById(int subtypeId) {
		//1.首先获取该二级类别下的所有商品
		List<Product> PList=pdao.findBySubtypeId(subtypeId);
		if(PList.size()>0){
			for(int j=0;j<PList.size();j++){
				//int proId=proList.get(j).getProductId();
				idao.deleteByPid(PList.get(j).getProductId());
				cdao.deleteByprId(PList.get(j).getProductId());
				sdao.deleteById_prd(PList.get(j).getProductId());
			}
			//2.删除所有商品
			pdao.deleteBysubtypId(subtypeId);
			System.out.println("删除该二级分类里面的所有商品");
		}
		//3.删除这个二级类别
		stdao.deleteById(subtypeId);
		System.out.println("删除这个二级分类。");
		return 0;
	}

	/**
	 * 获取所有的一级分类,返回的是list集合，集合里面装的是所有一级类型名称
	 */
	@Override
	public List<String> showFirstType() {	
		List<Type> tlist=tdao.findAll();
		if(tlist.size()>0){
			List<String> list=new ArrayList<String>();
			for(int i=0;i<tlist.size();i++){
				list.add(tlist.get(i).getTypeName());
			}
			return list;
		}else{
			System.out.println("对不起，没有一个一级类别");
			return null;
		}	
	}
	public List<Type> AllFirstType() {	
		return tdao.findAll();
	}
	public List<Type> firstTypeList() {	
		return tdao.findAll();

	}
	
	/**
	 * 获取所有二级类别，返回一个list集合，里面装的是所有二级类型名称
	 */
	@Override
	public List<String> showSecondType(int type_id) {
		List<Subtype> sblist=stdao.selectBytypeId(type_id);
		if(sblist.size()>0){
			List<String> list=new ArrayList<String>();
			for(int i=0;i<sblist.size();i++){
				list.add(sblist.get(i).getSubtypeName());
			}
			return list;
		}else {
			System.out.println("对不起，这个一级类别下没有二级类别");
			return null;
		}
	}
	public List<Subtype> secondTypeList() {	
		return stdao.findAll();

	}
	/**
	 * 根据一级分类id来查询旗下所有的二级分类
	 * @param type_id:二级分类所属的一级分类
	 * @return
	 */
	public List<Subtype> findSecondTypeList(int type_id) {
		return stdao.selectBytypeId(type_id);
		
	}
    /**
     * 获取某一二级类别下面的所有商品，subtype_id:一个二级类别的唯一标识
     */
	@Override
	public List<Product> showProductsStype(int subtype_id){
		return pdao.findBySubtypeId(subtype_id);
	}
	
	public Subtype getSubType(int subtype_id){
		return stdao.findById(subtype_id);
	}
	public Type getType(int type_id){
		return tdao.findById(type_id);
	}
}
