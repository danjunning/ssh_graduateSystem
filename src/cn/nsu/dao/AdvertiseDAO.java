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

import cn.nsu.entity.Advertise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Advertise entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Advertise
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("advertisedao")
public class AdvertiseDAO {
	private static final Logger log = LoggerFactory.getLogger(AdvertiseDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String SUBTYPE_ID = "subtypeId";
	public static final String PRODUCT_DETAIL = "productDetail";
	public static final String SALE_COUNT = "saleCount";
	public static final String COMMENT_COUNT = "commentCount";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String PRODUCT_STOCK = "productStock";
	public static final String PRODUCT_REMARK = "productRemark";
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

	public void save(Advertise transientInstance) {
		log.debug("saving Advertise instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
    public int deleteById(int adId){
    	String sqlString="delete from advertise where adId=?";
    	SQLQuery query=getCurrentSession().createSQLQuery(sqlString);
    	query.setParameter(0, adId);
    	int result=query.executeUpdate();
    	return result;
    }
	public void delete(Advertise persistentInstance) {
		log.debug("deleting Advertise instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
    
	public List<Advertise> findByProId(int productId){
		String sql="select * from advertise where productId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Advertise.class);
		query.setParameter(0, productId);
		return query.list();
		
	}
	public Advertise findById(java.lang.Integer id) {
		log.debug("getting Advertise instance with id: " + id);
		try {
			Advertise instance = (Advertise) getCurrentSession().get("cn.nsu.entity.Advertise", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Advertise> findByExample(Advertise instance) {
		log.debug("finding Advertise instance by example");
		try {
			List<Advertise> results = (List<Advertise>) getCurrentSession().createCriteria("cn.nsu.entity.Advertise")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Advertise instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Advertise as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Advertise> findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List<Advertise> findBySubtypeId(Object subtypeId) {
		return findByProperty(SUBTYPE_ID, subtypeId);
	}

	public List<Advertise> findByProductDetail(Object productDetail) {
		return findByProperty(PRODUCT_DETAIL, productDetail);
	}

	public List<Advertise> findBySaleCount(Object saleCount) {
		return findByProperty(SALE_COUNT, saleCount);
	}

	public List<Advertise> findByCommentCount(Object commentCount) {
		return findByProperty(COMMENT_COUNT, commentCount);
	}

	public List<Advertise> findByProductPrice(Object productPrice) {
		return findByProperty(PRODUCT_PRICE, productPrice);
	}

	public List<Advertise> findByProductStock(Object productStock) {
		return findByProperty(PRODUCT_STOCK, productStock);
	}

	public List<Advertise> findByProductRemark(Object productRemark) {
		return findByProperty(PRODUCT_REMARK, productRemark);
	}

	public List findAll() {
		log.debug("finding all Advertise instances");
		try {
			String queryString = "from Advertise";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Advertise merge(Advertise detachedInstance) {
		log.debug("merging Advertise instance");
		try {
			Advertise result = (Advertise) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Advertise instance) {
		log.debug("attaching dirty Advertise instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Advertise instance) {
		log.debug("attaching clean Advertise instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdvertiseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdvertiseDAO) ctx.getBean("AdvertiseDAO");
	}
}