package cn.nsu.serviceIml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.dao.AdvertiseDAO;
import cn.nsu.dao.ColorDAO;
import cn.nsu.dao.ImageDAO;
import cn.nsu.dao.StyleDAO;
import cn.nsu.entity.Advertise;
import cn.nsu.entity.Image;
import cn.nsu.entity.Product;
import cn.nsu.interfaces.AdvertiseManage;
@Transactional
@Service("adveriml")
public class AdvertiseManageIml implements AdvertiseManage {
   @Resource(name="advertisedao")
	AdvertiseDAO addao;
   
   @Resource(name="imagedao")
	ImageDAO idao;
	@Resource(name="styledao")
	StyleDAO sdao;
	@Resource(name="colordao")
	ColorDAO cdao;
	
   /**
    * 添加一条广告
    */
	@Override
	public int addAdvetise(Advertise advertise) {
		addao.save(advertise);	
		return 1;
	}
	public int addAdvetise(Advertise advertise,ArrayList<String> colorlist,
            ArrayList<String> stylelist,ArrayList<String> imglist){
		addao.save(advertise);
		for(int i=0;i<colorlist.size();i++){//添加颜色
			cdao.insertBycolorId(advertise.getProductId(), colorlist.get(i));
		}
		for(int i=0;i<stylelist.size();i++){//添加款式
			sdao.insertById_prd(advertise.getProductId(), stylelist.get(i));
		}
		for(int i=0;i<imglist.size();i++){//添加
			idao.insertByPid(advertise.getProductId(), imglist.get(i));
		}
		return 1;
	}
	

	@Override
	public int deleteByAid(int adId) {
		addao.deleteById(adId);
		return 1;
	}

	@Override
	public List<Advertise> showAdvertise() {
		List<Advertise> adlist=addao.findAll();
		return adlist;
	}
	public ArrayList<HashMap> Adverlist(){
		List<Advertise> pList=addao.findAll();
		ArrayList<HashMap> aList=new ArrayList<HashMap>();
		if(pList !=null){
			for(int i=0;i<pList.size();i++){
				List<Image> imglist=idao.findByProductId(pList.get(i).getProductId());
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				map.put("productId", pList.get(i).getProductId());
				map.put("productImg", imglist.get(0).getImage());
				aList.add(map);
			}
			return aList;
		}else{
			return null;
		}	
	} 

}
