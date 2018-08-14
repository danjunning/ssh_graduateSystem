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

import cn.nsu.entity.Address;

/**
 * A data access object (DAO) providing persistence and search support for
 * Address entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Address
 * @author MyEclipse Persistence Tools
 */

@Repository("addressdao")
public class AddressDAO {
	private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);
	// property constants
	public static final String PROVINCE = "province";
	public static final String ADDRESS = "address";
	public static final String ZIPCODE = "zipcode";
	public static final String RECEIVER = "receiver";
	public static final String RECEIVE_TEL = "receiveTel";
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

	public void save(Address transientInstance) {
		log.debug("saving Address instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public int updateAddress(int addressId,Address address){
		String sql="update address set province=?,address=?,zipcode=?,receiver=?,receiveTel=? where addressId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0,address.getProvince() );
		query.setParameter(1,address.getAddress() );
		query.setParameter(2,address.getZipcode());
		query.setParameter(3,address.getReceiver());
		query.setParameter(4,address.getReceiveTel());
		query.setParameter(5, addressId);
		int result=query.executeUpdate();
		return result;
	}
	public int deleteById(int addressId){
		String sql="delete from address where addressId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, addressId);
		int result=query.executeUpdate();
		return result;
	}
	
	public int count(int user_id){
		String sql="select * from address where user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, user_id);
		int result=query.list().size();
		return result;
		
	}
	

	public void delete(Address persistentInstance) {
		log.debug("deleting Address instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Address findById(java.lang.Integer id) {
		log.debug("getting Address instance with id: " + id);
		try {
			Address instance = (Address) getCurrentSession().get("cn.nsu.entity.Address", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Address> findByExample(Address instance) {
		log.debug("finding Address instance by example");
		try {
			List<Address> results = (List<Address>) getCurrentSession().createCriteria("cn.nsu.entity.Address")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Address instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Address as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Address> findByProvince(Object province) {
		return findByProperty(PROVINCE, province);
	}

	public List<Address> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Address> findByZipcode(Object zipcode) {
		return findByProperty(ZIPCODE, zipcode);
	}

	public List<Address> findByReceiver(Object receiver) {
		return findByProperty(RECEIVER, receiver);
	}

	public List<Address> findByReceiveTel(Object receiveTel) {
		return findByProperty(RECEIVE_TEL, receiveTel);
	}

	public List<Address> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Address instances");
		try {
			String queryString = "from Address";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Address merge(Address detachedInstance) {
		log.debug("merging Address instance");
		try {
			Address result = (Address) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Address instance) {
		log.debug("attaching dirty Address instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Address instance) {
		log.debug("attaching clean Address instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AddressDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AddressDAO) ctx.getBean("AddressDAO");
	}
}