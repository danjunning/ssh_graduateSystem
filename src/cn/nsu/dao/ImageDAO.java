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

import cn.nsu.entity.Image;

/**
 * A data access object (DAO) providing persistence and search support for Image
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.nsu.entity.Image
 * @author MyEclipse Persistence Tools
 */
@Repository("imagedao")
public class ImageDAO {
	private static final Logger log = LoggerFactory.getLogger(ImageDAO.class);
	// property constants
	public static final String IMAGE = "image";
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

	public void save(Image transientInstance) {
		log.debug("saving Image instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	//根据商品id来添加图片
	public int insertByPid(int product_id,String image){
		String sql="insert into image(image,product_id) value(?,?)";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, image);
		query.setParameter(1, product_id);
		int result=query.executeUpdate();
		return result;
	}
	//根据商品id来删除图片
	public int deleteByPid(int product_id){
		String sql="delete from image where product_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, product_id);
		int result=query.executeUpdate();
		return result;
	}

	public void delete(Image persistentInstance) {
		log.debug("deleting Image instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Image findById(java.lang.Integer id) {
		log.debug("getting Image instance with id: " + id);
		try {
			Image instance = (Image) getCurrentSession().get("cn.nsu.entity.Image", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Image> findByExample(Image instance) {
		log.debug("finding Image instance by example");
		try {
			List<Image> results = (List<Image>) getCurrentSession().createCriteria("cn.nsu.entity.Image")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Image instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Image as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Image> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Image> findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List<Image> findAll() {
		log.debug("finding all Image instances");
		try {
			String queryString = "from Image";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Image merge(Image detachedInstance) {
		log.debug("merging Image instance");
		try {
			Image result = (Image) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Image instance) {
		log.debug("attaching dirty Image instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Image instance) {
		log.debug("attaching clean Image instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ImageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ImageDAO) ctx.getBean("ImageDAO");
	}
}