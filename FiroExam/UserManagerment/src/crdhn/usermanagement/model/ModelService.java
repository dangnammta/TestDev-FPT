/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crdhn.usermanagement.model;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *
 * @author hoaronal
 */
public abstract class ModelService {

	public ModelService() {

	}
	public Class getEntityClassByName(String entityName){
		throw new UnsupportedOperationException("Unsupported");
	};
	
	public abstract List list(Class clazz, Class modelClass);
	
	public abstract List lists(Class clazz, Class modelClass);
	
	public abstract List findRange(Integer maxResult ,  Integer page, String entity);
	public abstract List findRange(Integer maxResult ,  Integer page, String entity, String condition);
	
	public abstract Object get(Class clazz, Serializable id,Class modelClass) throws Exception ;

	public abstract boolean delete(Object obj, Class clazz);

	public abstract boolean update(Object obj, Class clazz);
	public abstract Integer create(Object obj, Class clazz);
}
