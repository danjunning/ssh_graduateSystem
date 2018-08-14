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

import cn.nsu.entity.Shopcart;

/**
 * A data access object (DAO) providing persistence and search support for
 * Shopcart entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Shopcart
 * @author MyEclipse Persistence Tools
 */

@Repository("shopcartdao")
public class ShopcartDAO {
	private static final Logger log = LoggerFactory.getLogger(ShopcartDAO.class);
	// property constants
	public static final String PRO_COLOR = "proColor";
	public static final String PRO_STYLE = "proStyle";
	public static final String PRO_AMOUNT = "proAmount";
	public static final String PRODUCT_ID = "productId";
	public static final String USER_ID = "userId";
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

	public void save(Shopcart transientInstance) {
		log.debug("saving Shopcart instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	//修改购物车商品的数量
	public int update(int shopcartId,int proAmount){
		String sql="update shopcart s set s.proAmount=? where s.shopcartId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, proAmount);
		query.setParameter(1, shopcartId);
		int result=query.executeUpdate();
		return result;
	}
    //删除某一个购物车的商品，根据id来删除
	public int deleteByshopcartId(int shopcartId) {
		String sql="delete from shopcart where shopcart.shopcartId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, shopcartId);
		int result= query.executeUpdate();
		return result;
	}
	//删除该用户的购物车里所有商品
	public int deleteByuserid(int user_id) {
		String sql="delete from shopcart where shopcart.user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, user_id);
		int result= query.executeUpdate();
		return result;
	}

	public Shopcart findById(java.lang.Integer id) {
		log.debug("getting Shopcart instance with id: " + id);
		try {
			Shopcart instance = (Shopcart) getCurrentSession().get("cn.nsu.entity.Shopcart", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Shopcart> findByExample(Shopcart instance) {
		log.debug("finding Shopcart instance by example");
		try {
			List<Shopcart> results = (List<Shopcart>) getCurrentSession().createCriteria("cn.nsu.entity.Shopcart")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Shopcart instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Shopcart as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Shopcart> findByProColor(Object proColor) {
		return findByProperty(PRO_COLOR, proColor);
	}

	public List<Shopcart> findByProStyle(Object proStyle) {
		return findByProperty(PRO_STYLE, proStyle);
	}

	public List<Shopcart> findByProAmount(Object proAmount) {
		return findByProperty(PRO_AMOUNT, proAmount);
	}

	public List<Shopcart> findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List<Shopcart> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Shopcart instances");
		try {
			String queryString = "from Shopcart";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Shopcart merge(Shopcart detachedInstance) {
		log.debug("merging Shopcart instance");
		try {
			Shopcart result = (Shopcart) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Shopcart instance) {
		log.debug("attaching dirty Shopcart instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Shopcart instance) {
		log.debug("attaching clean Shopcart instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ShopcartDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ShopcartDAO) ctx.getBean("ShopcartDAO");
	}
}