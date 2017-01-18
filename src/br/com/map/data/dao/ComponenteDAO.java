package br.com.map.data.dao;

import br.com.map.data.dao.util.ConnUtil;
import br.com.map.data.entity.Componente;

public class ComponenteDAO {
	
	private ConnUtil conn;
	
	public ComponenteDAO(){
		conn = ConnUtil.getInstance();
	}
	
	public long incluir(Componente componente) throws Exception{
		
		conn.beginTransaction();
		
		try{
			conn.getEntityManager().persist(componente);
			conn.commit();			
		}catch(Exception e){
			conn.rollback();
			throw e;
		}
		
		return componente.getId();
	}
	
	public void excluir(Componente componente) throws Exception{
		conn.beginTransaction();
		Componente cDel = conn.getEntityManager().find(Componente.class, componente.getId());
		
		if(cDel != null){
			try{
				conn.getEntityManager().remove(cDel);
				conn.commit();
			}catch(Exception e){
				conn.rollback();
				throw e;
			}
		}
		
	}
}
