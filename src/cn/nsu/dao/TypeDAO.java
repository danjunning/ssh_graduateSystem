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

import cn.nsu.entity.Type;
import freemarker.core.ReturnInstruction.Return;

/**
 * A data access object (DAO) providing persistence and search support for Type
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.nsu.entity.Type
 * @author MyEclipse Persistence Tools
 */

@Repository("typedao")
public class TypeDAO {
	private static final Logger log = LoggerFactory.getLogger(TypeDAO.class);
	// property constants
	public static final String TYPE_NAME = "typeName";
	public static final String TYPE_REMARK = "typeRemark";
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

	public int save(Type transientInstance) {
		log.debug("saving Type instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return 1;
	}

	public void delete(Type persistentInstance) {
		log.debug("deleting Type instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public int deleteById(int typeId){
		String sql="delete from type where type.typeId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, typeId);
		query.executeUpdate();
		System.out.println("调用dao删除一级分类");
		return 1;
	}
	public int deleteByName(String typeName){
		String sql="delete from type where type.typeName=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, typeName);
		query.executeUpdate();
		return 1;
	}
	public int deleteAll(int typeId){
		String sql="delete from type";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
		return 1;
	}
	public int updateType(int typeId,String typeName){
		String sql="update type t set t.typeName=? where t.typeId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, typeName);
		query.setParameter(1, typeId);
		query.executeUpdate();
		return 1; 
	}

	public Type findById(java.lang.Integer id) {
		log.debug("getting Type instance with id: " + id);
		try {
			Type instance = (Type) getCurrentSession().get("cn.nsu.entity.Type", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}




	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Type instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Type as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Type> findByTypeName(Object typeName) {
		return findByProperty(TYPE_NAME, typeName);
	}

	public List<Type> findByTypeRemark(Object typeRemark) {
		return findByProperty(TYPE_REMARK, typeRemark);
	}

	public List<Type> findAll() {
		log.debug("finding all Type instances");
		try {
			String queryString = "from Type";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Type merge(Type detachedInstance) {
		log.debug("merging Type instance");
		try {
			Type result = (Type) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Type instance) {
		log.debug("attaching dirty Type instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Type instance) {
		log.debug("attaching clean Type instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TypeDAO) ctx.getBean("TypeDAO");
	}
}