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

import cn.nsu.entity.Evaluate;

/**
 * A data access object (DAO) providing persistence and search support for
 * Evaluate entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Evaluate
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("evaluatedao")
public class EvaluateDAO {
	private static final Logger log = LoggerFactory.getLogger(EvaluateDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String EVALUATE_DATE = "evaluateDate";
	public static final String USER_ID = "userId";
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

	public void save(Evaluate transientInstance) {
		log.debug("saving Evaluate instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Evaluate persistentInstance) {
		log.debug("deleting Evaluate instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	//统计该用户的评价数量
	public int countByuId(int user_id){
		String sql="select * from evaluate where user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, user_id);
		int result=query.list().size();
		return result;
	}
	//统计商品的评论数
	public int countByprId(int product_id){
		String sql="select * from evaluate where product_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, product_id);
		int result=query.list().size();
		return result;
	}
	//用户修改自己某条评论
	public int updateByevaId(int evaluateId,String content){
		String sql="update evaluate set content=? where evaluateId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, content);
		query.setParameter(1, evaluateId);
		int result=query.executeUpdate();
		return result;
	}
	//用户删除某条评论
	public int deleteByevaId(int evaluateId){
		String sql="delete from evaluate where evaluateId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, evaluateId);
		int result=query.executeUpdate();
		return result;
	}
	public List<Evaluate> selectByUidOrderByDate(int user_id){
		String sql="select * from evaluate e where e.user_id=? order by e.evaluateDate desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Evaluate.class);
		query.setParameter(0, user_id);
		return query.list();	
	}
	//根据用户Id,查看评论，并将评论按照时间排序
	public List<Evaluate> selectByUidOrderByDate(int user_id,int pageIndex){
		String sql="select * from evaluate e where e.user_id=? order by e.evaluateDate desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Evaluate.class);
		query.setParameter(0, user_id);
		query.setFirstResult((pageIndex-1)*10);
		query.setMaxResults(10);
		return query.list();	
	}
	//根据商品Id,查看评论，并将评论按照时间排序
	public List<Evaluate> selectByPidOrderByDate(int product_id){
		String sql="select * from evaluate e where e.product_id=? order by e.evaluateDate desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Evaluate.class);
		query.setParameter(0, product_id);
		return query.list();
		
	}
	//获取评论，并将评论根据商品分组按照时间排序
	public List<Evaluate> selectAll(){
		//SELECT * FROM (SELECT * FROM table ORDER BY test DESC) AS a GROUP BY a.test1 ORDER BY a.test DESC;
		//select * from (select * from storelog order by logintime desc) t group by logintime order by logintime desc;   
		String sql="select * from evaluate as e group by e.product_id,e.order_id order by e.evaluateDate desc";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Evaluate.class);
		return query.list();
		
	}
	public Evaluate findByUidAndOid(int userid,int order_id){
		String sql="select * from evaluate where user_id=? and order_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql).addEntity(Evaluate.class);
		query.setParameter(0, userid);
		query.setParameter(1, order_id);
		Evaluate evaluate=null;
		List<Evaluate> list=query.list();
		for(Evaluate e:list){
			evaluate=e;
		}
		return evaluate;

	}
	public boolean isExist(int userid,int order_id){
		String sql="select * from evaluate where user_id=? and order_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, userid);
		query.setParameter(1, order_id);
		if(query.list().size()>0){
			return true;
		}else{
			return false;
		}
		
	}

	public Evaluate findById(java.lang.Integer id) {
		log.debug("getting Evaluate instance with id: " + id);
		try {
			Evaluate instance = (Evaluate) getCurrentSession().get("cn.nsu.entity.Evaluate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Evaluate> findByExample(Evaluate instance) {
		log.debug("finding Evaluate instance by example");
		try {
			List<Evaluate> results = (List<Evaluate>) getCurrentSession().createCriteria("cn.nsu.entity.Evaluate")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Evaluate instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Evaluate as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Evaluate> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Evaluate> findByEvaluateDate(Object evaluateDate) {
		return findByProperty(EVALUATE_DATE, evaluateDate);
	}

	public List<Evaluate> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Evaluate> findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List findAll() {
		log.debug("finding all Evaluate instances");
		try {
			String queryString = "from Evaluate";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Evaluate merge(Evaluate detachedInstance) {
		log.debug("merging Evaluate instance");
		try {
			Evaluate result = (Evaluate) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluate instance) {
		log.debug("attaching dirty Evaluate instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluate instance) {
		log.debug("attaching clean Evaluate instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EvaluateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EvaluateDAO) ctx.getBean("EvaluateDAO");
	}
}