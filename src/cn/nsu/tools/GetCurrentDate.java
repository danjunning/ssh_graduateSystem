package cn.nsu.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("getTime")
public class GetCurrentDate {
	/**
	 * 获取当前时间
	 * @return
	 */
	public String getCurrentDate(){
		Date date=new Date();		
		//对date对象进行格式化
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		
		String date_style=dateFormat.format(date);//将日期对象转化成日期格式的【字符串】 
		return date_style;
	}

}
