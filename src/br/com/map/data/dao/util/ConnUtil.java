package br.com.map.data.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnUtil {

	private static ConnUtil instancia;
	private EntityManager manager;
	private EntityManagerFactory factory;
	
	private ConnUtil(){
		factory = Persistence.createEntityManagerFactory("mapData_PU");
		manager = factory.createEntityManager();
	}
	
	public static ConnUtil getInstance(){
		if(instancia == null){
			instancia = new ConnUtil();
		}
		
		return instancia;
		
	}
	
	public void beginTransaction() throws Exception{
		if(!manager.isOpen()){
			manager = factory.createEntityManager();
		}
		
		if(!manager.getTransaction().isActive()){
			manager.getTransaction().begin();
		}
	}
	
	public void closeConnection() throws Exception{
		if(manager.isOpen()){
			manager.close();
		}
	}
	
	public void commit(){
		manager.getTransaction().commit();
	}
	
	public EntityManager getEntityManager(){
		return manager;
	}
	
}
