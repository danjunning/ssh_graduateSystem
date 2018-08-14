package cn.nsu.interfaces;

import java.util.List;

import cn.nsu.entity.Advertise;

public interface AdvertiseManage {
	//添加广告
	public int addAdvetise(Advertise advertise);
	
	//删除广告
	public int deleteByAid(int adId);
	
	//显示所有广告
	public List<Advertise> showAdvertise();

}
