package cn.nsu.tools;

import java.util.ArrayList;



import org.springframework.stereotype.Repository;

@Repository("stl")
public class StringToList {
	
	
	public ArrayList<String> getList(String str){	
		String[] s = str.split("，"); 
        ArrayList<String> stylelist = new ArrayList<String>();
        for(int i=0;i<s.length;i++)
        {
        	stylelist.add(s[i]);
        }
		return stylelist;
		
	}

}
