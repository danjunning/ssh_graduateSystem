package cn.nsu.action.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Advertise;
import cn.nsu.entity.Product;
import cn.nsu.serviceIml.AdvertiseManageIml;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.tools.GetAdvertiseNum;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.StringToList;

@Controller("AdvertiseManage")
public class AdvertiseManageAction implements SessionAware{
	@Resource(name="getAdverNum")
	GetAdvertiseNum getanum;
	@Resource(name="stl")
	StringToList stl;
	@Resource(name="getTime")
	GetCurrentDate gettime;
    @Resource(name="adveriml")
	AdvertiseManageIml amiml;
    @Resource(name="cgiml")
	CategoryManageIml cgiml;
	
	private Integer adId;
	
	private Integer typeId;
	private Integer subtypeId;
	private Integer productId;
	private String productName;
	private String productDetail;
	private Integer productStock;
	private Double productPrice;
	private String color;
	private String style;

	private String result;
	private List<File> file;
	private String savePath;
    private List<String> fileFileName;
    private List<String> fileContentType;
    private ArrayList<String> dataUrl;
	public String getAllAdver(){
		session.put("advertiselist", amiml.showAdvertise());
		return "ok";
	}
	public String addAdver() throws IOException{
		System.out.println(subtypeId+"-"+productName+"+"+productPrice+"+"+productStock+"+"+color+"+"+style);
		Advertise adver=new Advertise();
		adver.setSubtypeId(subtypeId);
		adver.setProductId(getanum.getAdvNumId());
		adver.setProductName(productName);
		adver.setProductPrice(productPrice);
		adver.setProductStock(productStock);
		adver.setSaleCount(0);
		adver.setCommentCount(0);
		adver.setProductDetail(productDetail);
		adver.setProductDate(gettime.getCurrentDate());
	
        dataUrl = new ArrayList<String>();
    	//ArrayList<String> imagePath=new ArrayList<String>();
		String imgpath = "upload/";
		for (int i = 0; i < file.size(); ++i) {
			InputStream is = new FileInputStream(file.get(i));
			dataUrl.add("upload/"+this.getFileFileName().get(i));
		    //File destFile = new File(path+imgpath, this.getFileFileName().get(i));
			File destFile = new File(getSavePath(), this.getFileFileName().get(i));
		    OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			try {
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			is.close();
			os.close();
		 }
		//amiml.addProduct(product, stl.getList(color), stl.getList(style), dataUrl);
		amiml.addAdvetise(adver,stl.getList(color), stl.getList(style), dataUrl);
		
		return "ok";
		
	}
	
	public String deleteAdver(){
		if(1==amiml.deleteByAid(adId)){
			session.put("advertiselist", amiml.showAdvertise());
			return "ok";
		}else{
			return "fail";
		}
		
	}
	
	
	
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getSubtypeId() {
		return subtypeId;
	}
	public void setSubtypeId(Integer subtypeId) {
		this.subtypeId = subtypeId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
	}
	public String getSavePath() {
		String str=ServletActionContext.getServletContext().getRealPath(savePath);
		return str;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public List<String> getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<String> getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	public ArrayList<String> getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(ArrayList<String> dataUrl) {
		this.dataUrl = dataUrl;
	}


	Map<String , Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	

}
