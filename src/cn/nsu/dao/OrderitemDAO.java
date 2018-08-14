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

import cn.nsu.entity.Orderitem;

/**
 * A data access object (DAO) providing persistence and search support for
 * Orderitem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Orderitem
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("orderitemdao")
public class OrderitemDAO {
	private static final Logger log = LoggerFactory.getLogger(OrderitemDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_IMAGE = "productImage";
	public static final String PRODUCT_COLOR = "productColor";
	public static final String PRODUCT_STYLE = "productStyle";
	public static final String PRODUCT_QUANTITY = "productQuantity";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String ADDRESS = "address";
	public static final String ORDER_ID = "orderId";
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
	
	public Orderitem selectByOrderId(String order_id){
		Orderitem orderitem=null;
		String sql="select * from orderitem where order_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Orderitem.class);
		query.setParameter(0, order_id);
		List<Orderitem> oilist=query.list();
		for(Orderitem oi:oilist){
			orderitem=oi;
			System.out.println("找到这个订单项");
		}
		return orderitem;
	}
	public void save(Orderitem transientInstance) {
		log.debug("saving Orderitem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//根据所属订单后进行删除该条订单明细
	public int deleteByorderId(String order_id){
		String sql="delete from orderitem where order_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, order_id);
		int result=query.executeUpdate();
		return result;
	}

	public void delete(Orderitem persistentInstance) {
		log.debug("deleting Orderitem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Orderitem findById(java.lang.Integer id) {
		log.debug("getting Orderitem instance with id: " + id);
		try {
			Orderitem instance = (Orderitem) getCurrentSession().get("cn.nsu.entity.Orderitem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Orderitem> findByExample(Orderitem instance) {
		log.debug("finding Orderitem instance by example");
		try {
			List<Orderitem> results = (List<Orderitem>) getCurrentSession().createCriteria("cn.nsu.entity.Orderitem")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Orderitem instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Orderitem as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Orderitem> findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List<Orderitem> findByProductImage(Object productImage) {
		return findByProperty(PRODUCT_IMAGE, productImage);
	}

	public List<Orderitem> findByProductColor(Object productColor) {
		return findByProperty(PRODUCT_COLOR, productColor);
	}

	public List<Orderitem> findByProductStyle(Object productStyle) {
		return findByProperty(PRODUCT_STYLE, productStyle);
	}

	public List<Orderitem> findByProductQuantity(Object productQuantity) {
		return findByProperty(PRODUCT_QUANTITY, productQuantity);
	}

	public List<Orderitem> findByTotalPrice(Object totalPrice) {
		return findByProperty(TOTAL_PRICE, totalPrice);
	}

	public List<Orderitem> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Orderitem> findByOrderId(Object orderId) {
		return findByProperty(ORDER_ID, orderId);
	}

	public List findAll() {
		log.debug("finding all Orderitem instances");
		try {
			String queryString = "from Orderitem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orderitem merge(Orderitem detachedInstance) {
		log.debug("merging Orderitem instance");
		try {
			Orderitem result = (Orderitem) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orderitem instance) {
		log.debug("attaching dirty Orderitem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orderitem instance) {
		log.debug("attaching clean Orderitem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderitemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrderitemDAO) ctx.getBean("OrderitemDAO");
	}
}