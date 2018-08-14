package cn.nsu.tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.nsu.dao.AdvertiseDAO;
import cn.nsu.entity.Advertise;
@Repository("getAdverNum")
public class GetAdvertiseNum {
	@Resource(name="advertisedao")
	AdvertiseDAO aDao;

	   public int getAdvNumId(){
	       int productId=getCard();//调用生成随机数的方法：这里以6位为例
	       List<Advertise> advertise=aDao.findByProId(productId);//生成的随机数进入数据库中查询一下，看时候有相同的。
	       if(advertise.size()>0){//如果有相同的数据
	          return getAdvNumId();//再次调用方法，生成一个随机数
	       }else{//否则
	           return productId;//这个数据我就要
	       }
	   }
	   //生成随机数
	   public static int getCard(){
	       Random rand=new Random();//生成随机数
	        String cardNnumer="";
	        for(int a=0;a<6;a++){
	        cardNnumer+=rand.nextInt(10);//生成6位数字
	        }
	        System.out.println(cardNnumer);
	       return  Integer.parseInt(cardNnumer);


	  }

}
