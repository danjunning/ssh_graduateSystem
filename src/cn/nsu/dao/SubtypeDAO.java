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

import cn.nsu.entity.Subtype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Subtype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Subtype
 * @author MyEclipse Persistence Tools
 */

@Repository("subtypedao")
public class SubtypeDAO {
	private static final Logger log = LoggerFactory.getLogger(SubtypeDAO.class);
	// property constants
	public static final String SUBTYPE_NAME = "subtypeName";
	public static final String SUBTYPE_REMARK = "subtypeRemark";
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

	public int save(Subtype transientInstance) {
		log.debug("saving Subtype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return 1;
	}

	public void delete(Subtype persistentInstance) {
		log.debug("deleting Subtype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	//根据唯一id来删除该条记录
	public int deleteById(int subtypeId){
		String sql="delete from subtype where subtype.subtypeId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, subtypeId);
		int result=query.executeUpdate();
		return result;
	}
	//根据二级分类名来删除该条记录
		public int deleteByName(String subtypeName){
			String sql="delete from subtype where subtype.subtypeName=?";
			SQLQuery query=getCurrentSession().createSQLQuery(sql);
			query.setParameter(0, subtypeName);
			int result=query.executeUpdate();
			return result;
		}
	//根据type_id来删除该一级分类的所有二级分类。
	public int deleteBytypeId(int type_id){
		String sql="delete from subtype where subtype.type_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, type_id);
		int result=query.executeUpdate();
		return result;
	}
    //根据type_id所表示的一级分类来查找其包含的所有二级分类。
	public List<Subtype> findBytypeid(int type_id){
		String sql="select * from subtype s where s.type_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Subtype.class);
		query.setParameter(0, type_id);
		List<Subtype> list =query.list();
		return list;	
	}
	public Subtype findById(java.lang.Integer id) {
		log.debug("getting Subtype instance with id: " + id);
		try {
			Subtype instance = (Subtype) getCurrentSession().get("cn.nsu.entity.Subtype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<Subtype> selectBytypeId(int typeid){
		String sql="select  * from subtype where subtype.type_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Subtype.class);
		query.setParameter(0, typeid);
		List<Subtype> list=query.list();
		return list;		
	}
	

	public List<Subtype> findByExample(Subtype instance) {
		log.debug("finding Subtype instance by example");
		try {
			List<Subtype> results = (List<Subtype>) getCurrentSession().createCriteria("cn.nsu.entity.Subtype")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Subtype instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Subtype as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Subtype> findBySubtypeName(Object subtypeName) {
		return findByProperty(SUBTYPE_NAME, subtypeName);
	}

	public List<Subtype> findBySubtypeRemark(Object subtypeRemark) {
		return findByProperty(SUBTYPE_REMARK, subtypeRemark);
	}

	public List<Subtype> findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
	}

	public List<Subtype> findAll() {
		log.debug("finding all Subtype instances");
		try {
			String queryString = "from Subtype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Subtype merge(Subtype detachedInstance) {
		log.debug("merging Subtype instance");
		try {
			Subtype result = (Subtype) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Subtype instance) {
		log.debug("attaching dirty Subtype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subtype instance) {
		log.debug("attaching clean Subtype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SubtypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SubtypeDAO) ctx.getBean("SubtypeDAO");
	}
}