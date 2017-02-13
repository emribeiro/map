package br.com.map.data.dao;

import java.util.List;

import javax.persistence.Query;

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
	
	public long count() throws Exception{
		conn.beginTransaction();
		Query q = conn.getEntityManager().createQuery("select count(c) from Componente c");
		
		return (Long)q.getSingleResult();
	}
	
	public long countByTipo(int tipo) throws Exception{
		conn.beginTransaction();
		Query q = conn.getEntityManager().createQuery("select count(c) from Componente c where c.tipo = :tipo");
		q.setParameter("tipo", tipo);
		
		return (Long)q.getSingleResult();
	}

	public List<Componente> listar() throws Exception {
		conn.beginTransaction();
		Query q = conn.getEntityManager().createQuery("select c from Componente c", Componente.class);
		
		return q.getResultList();
	}

	public Componente pesquisarPorId(long id) throws Exception {
		conn.beginTransaction();
		
		return conn.getEntityManager().find(Componente.class,id);
	}

	public List<Componente> pesquisarPorNome(String nome) throws Exception {
		conn.beginTransaction();
		Query q = conn.getEntityManager().createQuery("select c from Componente c where nome like :nome", Componente.class);
		q.setParameter("nome", "%" + nome + "%");
		
		return q.getResultList();
	}

	public List<Componente> pesquisarPorTipo(int tipo) throws Exception {
		conn.beginTransaction();
		
		Query q = conn.getEntityManager().createQuery("select c from Componente c where tipo = :tipo", Componente.class);
		q.setParameter("tipo", tipo);
		return q.getResultList();
	}

	public Componente pesquisarPorNomeETipo(String nome, int tipo) throws Exception {
		conn.beginTransaction();
		
		Query q = conn.getEntityManager().createQuery("select c from Componente c where nome = :nome and tipo = :tipo", Componente.class);
		q.setParameter("nome", nome);
		q.setParameter("tipo", tipo);
		return (Componente)q.getSingleResult();
	}

	public void alterar(Componente componente) throws Exception{
		conn.beginTransaction();
		
		try{
			conn.getEntityManager().merge(componente);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}
		
	}
}
