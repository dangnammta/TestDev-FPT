package crdhn.usermanagement.model.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import crdhn.usermanagement.exception.DataException;
import crdhn.usermanagement.model.Model;
import crdhn.usermanagement.model.ModelService;
import crdhn.usermanagement.utils.HibernateFactory;
import firo.utils.ClassFinder;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author hoaronal
 */
public class HibernateModelService extends ModelService {

	public final static Logger logger = Logger.getLogger(HibernateModelService.class);
	private final static String _package = "crdhn.usermanagement.entity";

	public HibernateModelService() {
		HibernateFactory.buildIfNeeded();
	}

	public Class getEntityClassByName(String entityName) {
		Class clazz;
		List<Class<?>> classes = ClassFinder.getAllClassesInPackage(_package);
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getName().equals(_package + "." + entityName)) {
				clazz = classes.get(i);
				return clazz;
			}
		}
		return null;
	}

	public Integer create(Object obj, Class clazz) {
		Integer result = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Object claz = Class.forName(clazz.getName()).newInstance();
			Serialization.toHibernate(obj, claz);
			result = (Integer) session.save(claz);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("save : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			HibernateFactory.close(session);
		}
		return result;
	}

	public boolean update(Object obj, Class clazz) {
		boolean check = true;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Object claz = Class.forName(clazz.getName()).newInstance();
			Serialization.toHibernate(obj, claz);
			session.update(claz);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("update : " + e.toString());
			check = false;
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			HibernateFactory.close(session);
		}
		return check;
	}

	public boolean delete(Object obj, Class clazz) {
		boolean check = true;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Object claz = Class.forName(clazz.getName()).newInstance();
			Serialization.toHibernate(obj, claz);
			session.delete(claz);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("delete : " + e.toString());
			check = false;
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			HibernateFactory.close(session);
		}
		return check;
	}

	public List list(Class clazz, Class modelClass) {
		List objects = null;
		List listmodel = new ArrayList();
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			tx.commit();
			for (Object obj : objects) {
				Object model = Serialization.fromHibernate(obj, modelClass);
				listmodel.add(model);
			}
		}
		catch (HibernateException e) {
			logger.error("findAll : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			HibernateFactory.close(session);
		}
		return listmodel;
	}

	public List lists(Class clazz, Class modelClass) {
		List objects = null;
		List listmodel = new ArrayList();
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("findAll : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		catch (Exception ex) {
			java.util.logging.Logger.getLogger(HibernateModelService.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public boolean saveOrUpdate(Object obj) {
		boolean check = true;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(obj);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("saveOrUpdate : " + e.toString());
			check = false;
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return check;
	}

	public Object merge(Object obj) {
		Object result = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			result = session.merge(obj);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("merge : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return result;
	}

	public Object loadById(Class clazz, Serializable id) {
		Object obj = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			obj = session.load(clazz, id);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("loadById : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return obj;
	}

	@Override
	public Object get(Class clazz, Serializable id, Class modelClass) throws Exception {
		Object obj = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			obj = session.get(clazz, id);
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("getById : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return Serialization.fromHibernate(obj, modelClass);
	}

	public List findByProperty(Class clazz, String propertyName, Object value) {
		List objects = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from " + clazz.getName() + " as model where model." + propertyName + "= ?");
			query.setParameter(0, value);
			objects = query.list();
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("findByProperty : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public List findLikeProperty(Class clazz, String propertyName, Object value) {
		List objects = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(
					"from " + clazz.getName() + " as model where model." + propertyName + " like :keySearch");
			query.setParameter("keySearch", "%" + value + "%");
			objects = query.list();
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("findByProperty : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public int countAllPaging(Class clazz) {
		int count = 0;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from " + clazz.getName());
			List objects = query.list();
			if (objects != null) {
				count = objects.size();
			}
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("countAllPaging : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return count;
	}

	public List findAllPaging(Class clazz, int firstRs, int maxRs) {
		List objects = null;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("from " + clazz.getName());
			query.setFirstResult(firstRs);
			query.setMaxResults(maxRs);
			objects = query.list();
			tx.commit();
		}
		catch (HibernateException e) {
			logger.error("findAllPaging : " + e.toString());
			HibernateFactory.rollback(tx);
			handleException(e);
		}
		finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public List findRange(Integer maxResult, Integer page, String entity) {
		List listObject = null;
		Integer firstResult = (page - 1) * maxResult;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StringBuilder sql = new StringBuilder("FROM " + entity + " ");
			Query queryObject = session.createQuery(sql.toString());

			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
			listObject = queryObject.list();
			tx.commit();
		}
		catch (Exception ex) {
			HibernateFactory.rollback(tx);
			throw ex;
		}
		finally {
			HibernateFactory.close(session);
		}
		return listObject;
	}

	public List findRange(Integer maxResult, Integer page, String entity, String condition) {
		List listObj = null;
		Integer firstResult = (page - 1) * maxResult;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StringBuilder sql = new StringBuilder("FROM " + entity);
			sql.append(" WHERE username LIKE :condition");
			Query queryObject = session.createQuery(sql.toString());
			queryObject.setParameter("condition", "%" + condition + "%");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
			listObj = queryObject.list();
			tx.commit();
		}
		catch (Exception ex) {
			HibernateFactory.rollback(tx);
			throw ex;
		}
		finally {
			HibernateFactory.close(session);
		}
		return listObj;
	}

	public List findRange(String field, Integer maxResult, String operator, Integer page, String entity, String condition) {
		List listObj = null;
		Integer firstResult = (page - 1) * maxResult;
		Session session = HibernateFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StringBuilder sql = new StringBuilder("FROM " + entity);
			sql.append(" WHERE " + field + " " + operator + " :condition");
			Query queryObject = session.createQuery(sql.toString());
			queryObject.setParameter("condition", "%" + condition + "%");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
			listObj = queryObject.list();
			tx.commit();
		}
		catch (Exception ex) {
			HibernateFactory.rollback(tx);
			throw ex;
		}
		finally {
			HibernateFactory.close(session);
		}
		return listObj;
	}

	public static void handleException(HibernateException e) throws DataException {
		throw new DataException(e);
	}

	public String trimspace(String str) {
		str = str.replaceAll("\\s+", " ");
		str = str.replaceAll("(^\\s+|\\s+$)", "");
		return str;
	}

}
