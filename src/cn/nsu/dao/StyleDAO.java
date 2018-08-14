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

import cn.nsu.entity.Style;

/**
 * A data access object (DAO) providing persistence and search support for Style
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.nsu.entity.Style
 * @author MyEclipse Persistence Tools
 */
@Repository("styledao")
public class StyleDAO {
	private static final Logger log = LoggerFactory.getLogger(StyleDAO.class);
	// property constants
	public static final String STYLE = "style";
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

	public void save(Style transientInstance) {
		log.debug("saving Style instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public int insertById_prd(int product_id,String style){
		String sql="insert into style(style,product_id) value(?,?)";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, style);
		query.setParameter(1, product_id);
		int result=query.executeUpdate();
		return result;
	}

	public int deleteById_prd(int product_id){
		String sql="delete from style where style.product_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, product_id);
		int result=query.executeUpdate();
		return result;
	}

	public Style findById(java.lang.Integer id) {
		log.debug("getting Style instance with id: " + id);
		try {
			Style instance = (Style) getCurrentSession().get("cn.nsu.entity.Style", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Style> findByExample(Style instance) {
		log.debug("finding Style instance by example");
		try {
			List<Style> results = (List<Style>) getCurrentSession().createCriteria("cn.nsu.entity.Style")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Style instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Style as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Style> findByStyle(Object style) {
		return findByProperty(STYLE, style);
	}

	public List<Style> findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List findAll() {
		log.debug("finding all Style instances");
		try {
			String queryString = "from Style";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Style merge(Style detachedInstance) {
		log.debug("merging Style instance");
		try {
			Style result = (Style) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Style instance) {
		log.debug("attaching dirty Style instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Style instance) {
		log.debug("attaching clean Style instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StyleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StyleDAO) ctx.getBean("StyleDAO");
	}
}