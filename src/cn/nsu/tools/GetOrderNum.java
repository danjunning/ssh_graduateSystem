package cn.nsu.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;
@Repository("getOrderNum")
public class GetOrderNum {
	/**  
     * 生成订单编号  
     * @return  
     */ 
	private static long orderNum = 0l;    
    private static String date ; 
    public static synchronized String getOrderNo() {    
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());    
        if(date==null||!date.equals(str)){    
            date = str;    
            orderNum  = 0l;    
        }    
        orderNum ++;    
        long orderNo = Long.parseLong((date)) * 10000;    
        orderNo += orderNum;;    
        return orderNo+"";    
    } 
}
