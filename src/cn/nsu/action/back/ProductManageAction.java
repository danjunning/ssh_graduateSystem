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
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.nsu.entity.Product;
import cn.nsu.serviceIml.CategoryManageIml;
import cn.nsu.serviceIml.ProductMangeIml;
import cn.nsu.tools.GetCurrentDate;
import cn.nsu.tools.StringToList;
import net.sf.json.JSONArray;
@Controller("productManage")
public class ProductManageAction implements SessionAware,ServletRequestAware{
	@Resource(name="stl")
	StringToList stl;
	@Resource(name="getTime")
	GetCurrentDate gettime;
	@Resource(name="pmiml")
	ProductMangeIml pmiml;
	@Resource(name="cgiml")
	CategoryManageIml cgiml;
	private Integer typeId;
	private Integer subtypeId;
	private Integer productId;
	private String productName;
	private String productDetail;
	private Integer productStock;
	private Double productPrice;
	private String color;
	private String style;

	private List<File> file;
	private String savePath;
    private List<String> fileFileName;
    private List<String> fileContentType;
    private ArrayList<String> dataUrl;
	
	public String getFType(){
		session.put("fisrttypelist", cgiml.firstTypeList());
		return "ok";
	}
	public String getSubypeByTid(){	
		System.out.println("ajax触发"+typeId);
		session.put("secondtypelistByFir", cgiml.findSecondTypeList(typeId));
		
		//转成JSONArray
        //JSONArray jsonArray = JSONArray.fromObject(cgiml.findSecondTypeList(typeId));
        //转成String类型，这里要解释，虽然后面的param的type是json，但是并不影响实际参数是字符串
        //result = jsonArray.toString();
		return "ok";
	}
	public String getAllPro(){
		session.put("secondtypelist", cgiml.secondTypeList());
		List<Product> prolistList=pmiml.showAllProducts();
		session.put("productlist", prolistList);
		return "ok";
	}
	public String getProByName(){
		List<Product> prolistList=pmiml.showProductByName(productName);
		session.put("productlist", prolistList);
		return "ok";
	}
	public String deletePro(){
		pmiml.deleteProduct(productId);
		session.put("productlist", pmiml.showAllProducts());
		return "ok";
	}
	public String addPro() throws IOException {
		System.out.println(subtypeId+"-"+productName+"+"+productPrice+"+"+productStock+"+"+color+"+"+style);
		Product product=new Product();
		product.setTypeId(typeId);
		product.setSubtypeId(subtypeId);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductStock(productStock);
		product.setProductDetail(productDetail);
		product.setProductDate(gettime.getCurrentDate());
	
        dataUrl = new ArrayList<String>();
		for (int i = 0; i < file.size(); ++i) {
			InputStream is = new FileInputStream(file.get(i));
			dataUrl.add("upload/"+this.getFileFileName().get(i));
			File destFile = new File(getSavePath(), this.getFileFileName().get(i));
		    OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			try {
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}			
			is.close();
			os.close();
		 }
		pmiml.addProduct(product, stl.getList(color), stl.getList(style), dataUrl);
		return "ok";
	}
	
	public String getPro(){
		Product product=pmiml.selectById(productId);
		request.setAttribute("behindsubtype", cgiml.getSubType(product.getSubtypeId()).getSubtypeName());
		request.setAttribute("behindtype", cgiml.getType(product.getTypeId()).getTypeName());
		request.setAttribute("behindproduct",product);
		request.setAttribute("behindimglist", pmiml.selectImgByPId(productId));
		request.setAttribute("behindcolorlist", pmiml.selectColorByPId(productId));
		request.setAttribute("behindstylelist", pmiml.selectStyleByPId(productId));
		return "ok";		
	}
	public void updatePrice(){
		pmiml.updatePrice(productId, productPrice);
	}
	public void updateStock(){
		pmiml.updateStock(productId, productStock);
	}
	
	
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	
	
	
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public List<File> getFile() {
		return file;
	}
	public void setFile(List<File> file) {
		this.file = file;
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
	public String getSavePath() {
		String str=ServletActionContext.getServletContext().getRealPath(savePath);
		return str;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	

}
