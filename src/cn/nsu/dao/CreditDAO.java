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

import cn.nsu.entity.Credit;

/**
 * A data access object (DAO) providing persistence and search support for
 * Credit entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Credit
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("creditdao")
public class CreditDAO {
	private static final Logger log = LoggerFactory.getLogger(CreditDAO.class);
	// property constants
	public static final String CREDIT_NUMBER = "creditNumber";
	public static final String CREDIT_TYPE = "creditType";
	public static final String PAY_PASSWORD = "payPassword";
	public static final String BALANCE = "balance";
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

	public void save(Credit transientInstance) {
		log.debug("saving Credit instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//修改金额，根据卡号
	public int updateBalanceBycreNum(Float balance,String creditNumber){
		String sql="update credit set balance=? where creditNumber=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, balance);
		query.setParameter(1, creditNumber);
		int result=query.executeUpdate();
		return result;
	}
	//根据卡号查询
	public Credit selectByCreNumAndUid(String creditNumber,int user_id){
		Credit credit=null;
		String sql="select * from credit where creditNumber=? and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Credit.class);
		query.setParameter(0, creditNumber);
		query.setParameter(1, user_id);
		List<Credit> cList=query.list();
		for(Credit c:cList){
			credit=c;
			System.out.println("根据卡号查到这张卡");
		}
		return credit;
	}
	//根据卡号查询
	public Credit selectByCreNum(String creditNumber){
		Credit credit=null;
		String sql="select * from credit where creditNumber=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Credit.class);
		query.setParameter(0, creditNumber);
		List<Credit> cList=query.list();
		for(Credit c:cList){
			credit=c;
		}
		return credit;
	}
	//根据卡号查询
	public List<Credit> selectById(int user_id){
		String sql="select * from credit where user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Credit.class);
		query.setParameter(0, user_id);
		return query.list();
	}
	
	//查询该卡是否存在
	public Credit isExistByNum(String creditNumber){
		Credit credit=null;
		if(findByCreditNumber(creditNumber).size()==1){
			System.out.println("该卡存在");
			for(Credit c:findByCreditNumber(creditNumber)){
				credit=c;
			}
			return credit;
		}else{
			return null;
		}
	}

	public void delete(Credit persistentInstance) {
		log.debug("deleting Credit instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Credit findById(java.lang.Integer id) {
		log.debug("getting Credit instance with id: " + id);
		try {
			Credit instance = (Credit) getCurrentSession().get("cn.nsu.entity.Credit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Credit> findByExample(Credit instance) {
		log.debug("finding Credit instance by example");
		try {
			List<Credit> results = (List<Credit>) getCurrentSession().createCriteria("cn.nsu.entity.Credit")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Credit instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Credit as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Credit> findByCreditNumber(Object creditNumber) {
		return findByProperty(CREDIT_NUMBER, creditNumber);
	}

	public List<Credit> findByCreditType(Object creditType) {
		return findByProperty(CREDIT_TYPE, creditType);
	}

	public List<Credit> findByPayPassword(Object payPassword) {
		return findByProperty(PAY_PASSWORD, payPassword);
	}

	public List<Credit> findByBalance(Object balance) {
		return findByProperty(BALANCE, balance);
	}

	public List<Credit> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Credit instances");
		try {
			String queryString = "from Credit";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Credit merge(Credit detachedInstance) {
		log.debug("merging Credit instance");
		try {
			Credit result = (Credit) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Credit instance) {
		log.debug("attaching dirty Credit instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Credit instance) {
		log.debug("attaching clean Credit instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CreditDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CreditDAO) ctx.getBean("CreditDAO");
	}
}