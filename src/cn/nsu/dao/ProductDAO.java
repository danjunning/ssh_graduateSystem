package cn.nsu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.nsu.entity.Product;


/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Product
 * @author MyEclipse Persistence Tools
 */

@Repository("productdao")
@Transactional
public class ProductDAO {
	private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_DETAIL = "productDetail";
	public static final String PRODUCT_STOCK = "productStock";
	public static final String SALE_COUNT = "saleCount";
	public static final String COMMENT_COUNT = "commentCount";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String PRODUCT_REMARK = "productRemark";
	public static final String PRODUCT_DATE = "productDate";
	public static final String SUBTYPE_ID = "subtypeId";
	public static final String TYPE_ID = "typeId";
    @Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Product transientInstance) {
		log.debug("saving Product instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public int insert(Product pro){
		String sql="insert product(productName,productDetail,productStock,saleCount,commentCount,productPrice,productDate,subtype_id,type_id) value(?,?,?,?,?,?,?,?,?)";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, pro.getProductName());
		query.setParameter(1, pro.getProductDetail());
		query.setParameter(2, pro.getProductStock());
		query.setParameter(3, 0);
		query.setParameter(4, 0);
		query.setParameter(5,pro.getProductPrice());
		query.setParameter(6, pro.getProductDate());
		query.setParameter(7, pro.getSubtypeId());
		query.setParameter(8, pro.getTypeId());
		int result=query.executeUpdate();
		return result;
	}

	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	//删除一个二级分类下的所有商品
	public int deleteBysubtypId(int subtype_id){
		String sql="delete from product where product.subtype_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, subtype_id);
		int result=query.executeUpdate();
		return result;
	}
	//删除指定id的商品
	public int deleteById(int productId){
		String sql="delete from product where product.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, productId);
		int result=query.executeUpdate();
		return result;
	}
	//模糊查询商品
	public List<Product> selectByParm(String param){
		String sql="select * from product p where p.productName like '%"+param+"%'";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		List<Product> list=query.list();
		return list;
	}
	public List<Product> selectByParm(String param,int pageIndex){
		String sql="select * from product p where p.productName like '%"+param+"%'";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		List<Product> list=query.list();
		return list;
	}
	
	
	//根据评论数来排序,要根据该二级分类
	public List<Product> orderBycomment(int subtype_id){
		String sql="select * from product p where p.subtype_id=? order by p.commentCount desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		return query.list();
	}
	
	//根据销量排序
	public List<Product> orderBysaleCountAndParm(String param,int pageIndex){
		String sql="select * from product p where p.productName like '%"+param+"%' order by p.saleCount desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		List<Product> list=query.list();
		return list;
	}
	public List<Product> orderBysaleCount(int subtype_id,int pageIndex){
		String sql="select * from product p where p.subtype_id=? order by p.saleCount desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();
	}
	public List<Product> orderBysaleCountAndType(int type_id,int pageIndex){
		String sql="select * from product p where p.type_id=? order by p.saleCount desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, type_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();
	}
	//根据价格升序
	public List<Product> orderBypriceDeAndParam(String param,int pageIndex){
		String sql="select * from product p where p.productName like '%"+param+"%' order by p.productPrice desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		List<Product> list=query.list();
		return list;
	}
	public List<Product> orderBypriceDe(int subtype_id,int pageIndex){
		String sql="select * from product p where p.subtype_id=? order by p.productPrice desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();			
	}
	public List<Product> orderBypriceDeAndType(int type_id,int pageIndex){
		String sql="select * from product p where p.type_id=? order by p.productPrice desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, type_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();			
	}
	//根据价格降序
	public List<Product> orderBypriceAscAndParm(String param,int pageIndex){
		String sql="select * from product p where p.productName like '%"+param+"%' order by p.productPrice asc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		List<Product> list=query.list();
		return list;
	}
	public List<Product> orderBypriceAsc(int subtype_id,int pageIndex){
		String sql="select * from product p where p.subtype_id=? order by p.productPrice asc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();			
	}
	public List<Product> orderBypriceAscAndType(int type_id,int pageIndex){
		String sql="select * from product p where p.type_id=? order by p.productPrice asc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, type_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();			
	}
	//根据商品添加时间先后排序,也是按二级类别分类
	public List<Product> orderBydate(int subtype_id){
		String sql="select * from product p where p.subtype_id=? order by p.productDate desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		return query.list();			
	}
	//统计全部商品数量
	public int countAll(){
		String sql="select * from product";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		return query.list().size();
	}
	//统计某类商品的数量
	public int countBysubtype(int subtype_id){
		String sql="select * from product p where p.subtype_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, subtype_id);
		return query.list().size();
	}
    //修改商品的名称、详情、价格等属性
	public int update(int productId,Product pro){
		String sql="update product p set p.productName=?,p.productDetail=?,p.productPrice=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, pro.getProductName());
		query.setParameter(1, pro.getProductDetail());
		query.setParameter(2, pro.getProductPrice());
		query.setParameter(3, productId);
		int result=query.executeUpdate();
		return result;
	}
	//修改库存量
	public int updateStock(int productId,int productStock){
		String sql="update product p set p.productStock=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, productStock);
		query.setParameter(1, productId);
		int result=query.executeUpdate();
		return result;	
	}
	public int updatePrice(int productId,Double productPrice){
		String sql="update product p set p.productPrice=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, productPrice);
		query.setParameter(1, productId);
		int result=query.executeUpdate();
		return result;	
	}
	public int updateStock(String  productName,int productStock){
		String sql="update product p set p.productStock=? where p.productName=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, productStock);
		query.setParameter(1, productName);
		int result=query.executeUpdate();
		return result;	
	}
	//更新销售量
	public int updateSaleCount(int productId,int saleCount){
		String sql="update product p set p.saleCount=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, saleCount);
		query.setParameter(1, productId);
		int result=query.executeUpdate();
		return result;
	}
	//同时更新库存和销量
	public int updateSaleAndStock(int productId,int productStock,int saleCount){
		String sql="update product p set p.saleCount=?,p.productStock=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, saleCount);
		query.setParameter(1, productStock);
		query.setParameter(2, productId);
		int result=query.executeUpdate();
		return result;
	}
	//更新评论数
	public int updateCommentCount(int productId,int commentCount){
		String sql="update product p set p.commentCount=? where p.productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, commentCount);
		query.setParameter(1, productId);
		int result=query.executeUpdate();
		return result;
	}
	public List<Product> findTopeight(int typeId){
		String sql="select * from product where type_id=? order by saleCount desc limit 8";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, typeId);
		return query.list();
	}
	public List<Product> LateTopeight(int typeId){
		String sql="select * from product where type_id=? order by productDate desc limit 8";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, typeId);
		return query.list();
	}
	public List<Product> LateTopeightBysub(int subtypeId){
		String sql="select * from product where subtype_id=? order by productDate desc limit 8";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtypeId);
		return query.list();
	}
	public List<Product> LateTopeightByParam(String param){
		String sql="select * from product where productName like '%"+param+"%' order by productDate desc limit 8";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		return query.list();
	}
	

	public Product findById(java.lang.Integer id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getCurrentSession().get("cn.nsu.entity.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Product instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Product as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Product> findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List<Product> findByProductDetail(Object productDetail) {
		return findByProperty(PRODUCT_DETAIL, productDetail);
	}

	public List<Product> findByProductStock(Object productStock) {
		return findByProperty(PRODUCT_STOCK, productStock);
	}

	public List<Product> findBySaleCount(Object saleCount) {
		return findByProperty(SALE_COUNT, saleCount);
	}

	public List<Product> findByCommentCount(Object commentCount) {
		return findByProperty(COMMENT_COUNT, commentCount);
	}

	public List<Product> findByProductPrice(Object productPrice) {
		return findByProperty(PRODUCT_PRICE, productPrice);
	}

	public List<Product> findByProductRemark(Object productRemark) {
		return findByProperty(PRODUCT_REMARK, productRemark);
	}

	public List<Product> findByProductDate(Object productDate) {
		return findByProperty(PRODUCT_DATE, productDate);
	}

	public List<Product> findBySubtypeId(Object subtypeId) {
		return findByProperty(SUBTYPE_ID, subtypeId);
	}
	public List<Product> findBySubtypeId(int subtype_id,int pageIndex){
		String sql="select * from product p where p.subtype_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, subtype_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();
	}

	public List<Product> findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
	}
	public List<Product> findByTypeId(int type_id,int pageIndex){
		String sql="select * from product p where p.type_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Product.class);
		query.setParameter(0, type_id);
		query.setFirstResult((pageIndex-1)*32);
		query.setMaxResults(32);
		return query.list();
	}

	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDAO) ctx.getBean("ProductDAO");
	}
}