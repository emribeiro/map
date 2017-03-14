package br.com.map.data.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.map.data.dao.util.ConnUtil;
import br.com.map.data.entity.BaseEntity;

public class GenericDAO<E extends BaseEntity> {
	
	private ConnUtil conn;
	private Class<E> classePersistida;
	
	public GenericDAO(Class<E> classePersistida){
		conn = ConnUtil.getInstance();
		this.classePersistida = classePersistida;
	}
	
	public E save(E e) throws Exception{

		conn.beginTransaction();
		
		try{
			conn.getEntityManager().persist(e);
			conn.commit();			
		}catch(Exception ex){
			conn.rollback();
			throw ex;
		}
		
		return e;
	}
	
	public void update(E e) throws Exception{
		conn.beginTransaction();
		
		try{
			conn.getEntityManager().merge(e);
			conn.commit();
		}catch(Exception ex){
			conn.rollback();
			throw ex;
		}
	}
	
	public void delete(E e) throws Exception{
		conn.beginTransaction();
		
		try{
			conn.getEntityManager().remove(e);
			conn.commit();
		}catch(Exception ex){
			conn.rollback();
			throw ex;
		}
	}
	
	public E find(long key) throws Exception{
		conn.beginTransaction();
		return conn.getEntityManager().find(classePersistida, key);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeListQuery(String queryName, Map<String, Object> parameters) throws Exception{
		List<E> data = null;
		
		Query query = conn.getEntityManager().createNamedQuery(queryName);
		
		if(parameters != null){
			for(Map.Entry<String, Object> param : parameters.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		
		data = query.getResultList();
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public E executeSingleQuery(String queryName, Map<String, Object> parameters){
		E data = null;
		
		Query query = conn.getEntityManager().createNamedQuery(queryName);
		
		if(parameters != null){
			for(Map.Entry<String, Object> param : parameters.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		
		data = (E) query.getSingleResult();
		
		
		return data;
	}
	
	public long executeScalarQuery(String queryName, Map<String, Object> parameters){
		
		Query query = conn.getEntityManager().createNamedQuery(queryName);
		
		if(parameters != null){
			for(Map.Entry<String, Object> param : parameters.entrySet()){
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		
		return (Long)query.getSingleResult();
	}
}
