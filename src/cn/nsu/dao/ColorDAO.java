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

import cn.nsu.entity.Color;

/**
 * A data access object (DAO) providing persistence and search support for Color
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.nsu.entity.Color
 * @author MyEclipse Persistence Tools
 */
@Repository("colordao")
public class ColorDAO {
	private static final Logger log = LoggerFactory.getLogger(ColorDAO.class);
	// property constants
	public static final String COLOR = "color";
	public static final String PRODUCT_ID = "productId";
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

	public void save(Color transientInstance) {
		log.debug("saving Color instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Color persistentInstance) {
		log.debug("deleting Color instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	//根据商品id添加颜色
	public int insertBycolorId(int product_id,String color){
		String sql="insert into color(color,product_id) value(?,?)";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, color);
		query.setParameter(1, product_id);
		int result=query.executeUpdate();
		return result;
	}
	//根据商品id，删除所有颜色
	public int deleteByprId(int product_id){
		String sql="delete from color where product_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, product_id);
		int result=query.executeUpdate();
		return result;
	}
	//
	//

	public Color findById(java.lang.Integer id) {
		log.debug("getting Color instance with id: " + id);
		try {
			Color instance = (Color) getCurrentSession().get("cn.nsu.entity.Color", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Color> findByExample(Color instance) {
		log.debug("finding Color instance by example");
		try {
			List<Color> results = (List<Color>) getCurrentSession().createCriteria("cn.nsu.entity.Color")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Color instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Color as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Color> findByColor(Object color) {
		return findByProperty(COLOR, color);
	}

	public List<Color> findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List findAll() {
		log.debug("finding all Color instances");
		try {
			String queryString = "from Color";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Color merge(Color detachedInstance) {
		log.debug("merging Color instance");
		try {
			Color result = (Color) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Color instance) {
		log.debug("attaching dirty Color instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Color instance) {
		log.debug("attaching clean Color instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ColorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ColorDAO) ctx.getBean("ColorDAO");
	}
}