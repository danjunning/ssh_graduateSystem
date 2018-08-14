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

import cn.nsu.entity.Orders;

/**
 * A data access object (DAO) providing persistence and search support for
 * Orders entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.nsu.entity.Orders
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Repository("ordersdao")
public class OrdersDAO {
	private static final Logger log = LoggerFactory.getLogger(OrdersDAO.class);
	// property constants
	public static final String ORDER_NUMBER = "orderNumber";
	public static final String PAY_BANK = "payBank";
	public static final String ORDER_DATE = "orderDate";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String TOTAL_PRICE = "totalPrice";
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

	public void save(Orders transientInstance) {
		log.debug("saving Orders instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//生成订单
	//public int insertOrder(){return 1;};
    //删除订单
	public void delete(Orders persistentInstance) {
		log.debug("deleting Orders instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
   //根据主键来删除订单
	public int deleteById(int orderId){
		String sql="delete from orders where orderId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, orderId);
		int result=query.executeUpdate();
		return result;
	}
	
	//修改订单状态
	public int updateStatusByorderId(String status,String dealDate,int orderId){
		String sql="update orders o set o.orderStatus=?,o.dealDate=? where o.orderId=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, status);
		query.setParameter(1, dealDate);
		query.setParameter(2, orderId);
		int result=query.executeUpdate();
		return result;
	}
	//修改订单状态
	public int updateStatusByorderNum(String status,String dealDate,String orderNumber){
		String sql="update orders o set o.orderStatus=?,o.dealDate=? where o.orderNumber=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, status);
		query.setParameter(1, dealDate);
		query.setParameter(2, orderNumber);
		int result=query.executeUpdate();
		return result;
	}
	//修改订单状态
	public int updateStatusByorderNum(String status,String orderNumber){
		String sql="update orders o set o.orderStatus=? where o.orderNumber=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, status);
		query.setParameter(1, orderNumber);
		int result=query.executeUpdate();
		return result;
	}
	//修改订单状态,用户订单付款
	public int updateStatusByorderNumAndUId(String status,String dealDate,String orderNumber,String payBank,int user_id){
		String sql="update orders o set o.orderStatus=?,o.dealDate=?,o.payBank=? where o.orderNumber=? and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, status);
		query.setParameter(1, dealDate);
		query.setParameter(2, payBank);
		query.setParameter(3, orderNumber);
		query.setParameter(4, user_id);
		int result=query.executeUpdate();
		return result;
	}
	public int updateStatusByorderNumAndUId(String status,String dealDate,String orderNumber,int user_id){
		String sql="update orders o set o.orderStatus=?,o.dealDate=? where o.orderNumber=? and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, status);
		query.setParameter(1, dealDate);
		query.setParameter(2, orderNumber);
		query.setParameter(3, user_id);
		int result=query.executeUpdate();
		return result;
	}
	//根据订单编号查询
	public Orders selectByorderNum(String orderNumber){
		Orders order=null;
		String sqll="select * from orders where orderNumber=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderNumber);
		List<Orders> oList=query.list();
		for(Orders o:oList){
			order=o;
		}
		return order;	
	}
	//根据订单编号查询
	public Orders selectByorderNum(String orderNumber,int user_id){
		Orders order=null;
		String sqll="select * from orders where orderNumber=? and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderNumber);
		query.setParameter(1, user_id);
		List<Orders> oList=query.list();
		for(Orders o:oList){
			order=o;
			System.out.println("根据用户的订单号查到这个订单");
		}
		return order;	
	}
	//统计某个用户的所有订单
	public List<Orders> selectOrderByUid(int user_id){
		String sqll="select * from orders where user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, user_id);
		return query.list();
	}
	//查询某个用户的所有订单
	public List<Orders> selectOrderByUid(int user_id,int pageIndex){
		String sqll="select * from orders where user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, user_id);
		query.setFirstResult((pageIndex-1)*10);
		query.setMaxResults(10);
		return query.list();
	}
	//根据用户id和状态来查询不同用户不同状态的订单
	public List<Orders> selectOrderByUidandStatus(Integer user_id,String orderStatus){
		String sqll="select * from orders where user_id=? and orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, user_id);
		query.setParameter(1, orderStatus);
		return query.list();
	}
	//根据状态来查询所有用户不同状态的订单
	public List<Orders> selectOrderByStatus(String orderStatus){
		String sqll="select * from orders where orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderStatus);
		return query.list();
	}
	public List<Orders> selectOrderLikeStatus(String orderStatus){
		String sqll="select * from orders where orderStatus like '%"+orderStatus+"%'";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		return query.list();
	}
	public List<Orders> selectLikeUidAndStatus(String orderStatus,int userId){
		String sqll="select * from orders where orderStatus like '%"+orderStatus+"%' and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, userId);
		return query.list();
	}
	public List<Orders> selectLikeUidAndStatus(String orderStatus,int userId,int pageIndex){
		String sqll="select * from orders where orderStatus like '%"+orderStatus+"%' and user_id=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, userId);
		query.setFirstResult((pageIndex-1)*10);
		query.setMaxResults(10);
		return query.list();
	}
	//根据状态来统计不同用户不同状态的订单
	public List<Orders> selectOrderByUserIdAndStatus(int user_id,String orderStatus){
		String sqll="select * from orders where user_id=? and orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, user_id);
		query.setParameter(1, orderStatus);
		return query.list();
	}
	//根据状态来查询不同用户不同状态的订单
	public List<Orders> selectOrderByUserIdAndStatus(int user_id,String orderStatus,int pageIndex){
		String sqll="select * from orders where user_id=? and orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, user_id);
		query.setParameter(1, orderStatus);
		query.setFirstResult((pageIndex-1)*10);
		query.setMaxResults(10);
		return query.list();
	}
	//根据状态来查询所有用户各种状态的订单
	public List<Orders> selectOrderByNumAndStatus(String  orderNumber,String orderStatus){
		String sqll="select * from orders where orderNumber=? and orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderNumber);
		query.setParameter(1, orderStatus);
		return query.list();
	}
	
	//根据时间来查询订单
	public List<Orders> selectOrderByTime(String orderDate){
		String sqll="select * from orders where orderDate=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderDate);
		return query.list();
	}
	//统计所有订单数量
	public int countAll(){
		String sqll="select * from orders";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		if(query.list()!=null){
			return query.list().size();
		}else{
			return 0;
		}
		
	}
	//统计不同状态的订单数量
	public int countByStatus(String orderStatus){
		String sqll="select * from orders where orderStatus=?";
		SQLQuery query=getCurrentSession().createSQLQuery(sqll).addEntity(Orders.class);
		query.setParameter(0, orderStatus);
		if(query.list()!=null){
			return query.list().size();
		}else{
			return 0;
		}
	}

	public Orders findById(java.lang.Integer id) {
		log.debug("getting Orders instance with id: " + id);
		try {
			Orders instance = (Orders) getCurrentSession().get("cn.nsu.entity.Orders", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Orders> findByExample(Orders instance) {
		log.debug("finding Orders instance by example");
		try {
			List<Orders> results = (List<Orders>) getCurrentSession().createCriteria("cn.nsu.entity.Orders")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Orders instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Orders as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Orders> findByOrderNumber(Object orderNumber) {
		return findByProperty(ORDER_NUMBER, orderNumber);
	}

	public List<Orders> findByPayBank(Object payBank) {
		return findByProperty(PAY_BANK, payBank);
	}

	public List<Orders> findByOrderDate(Object orderDate) {
		return findByProperty(ORDER_DATE, orderDate);
	}

	public List<Orders> findByOrderStatus(Object orderStatus) {
		return findByProperty(ORDER_STATUS, orderStatus);
	}

	public List<Orders> findByTotalPrice(Object totalPrice) {
		return findByProperty(TOTAL_PRICE, totalPrice);
	}

	public List<Orders> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Orders instances");
		try {
			String queryString = "from Orders";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orders merge(Orders detachedInstance) {
		log.debug("merging Orders instance");
		try {
			Orders result = (Orders) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orders instance) {
		log.debug("attaching dirty Orders instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orders instance) {
		log.debug("attaching clean Orders instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrdersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrdersDAO) ctx.getBean("OrdersDAO");
	}
}