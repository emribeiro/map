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
		conn.getEntityManager().persist(componente);
		conn.commit();
		
		return componente.getId();
	}

	public void excluir(Componente componente) throws Exception{
		conn.beginTransaction();
		conn.getEntityManager().remove(componente);
		conn.commit();
	}
}
