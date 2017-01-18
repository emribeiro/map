package br.com.map.data.model;

import javax.persistence.Query;

import br.com.map.data.dao.ComponenteDAO;
import br.com.map.data.dao.util.ConnUtil;
import br.com.map.data.entity.Componente;
import br.com.map.data.model.mensagem.Mensagem;

public class ComponenteModel {

	private ComponenteDAO cDAO;
	
	public ComponenteModel(){
		cDAO = new ComponenteDAO();
	}
	
	public long incluir(Componente componente) throws Exception{
		
		if(componente == null){
			throw new Exception(Mensagem.COMPONENTE_NULO);
		}
		
		if(componente.getNome() == null || componente.getNome().trim().length() == 0){
			throw new Exception(Mensagem.COMPONENTE_SEM_NOME);
		}
		
		if(componente.getTipo() == 0){
			throw new Exception(Mensagem.COMPONENTE_SEM_TIPO);
		}
		
		if(componente.getTipo() != 1 && componente.getTipo() != 2){
			throw new Exception(Mensagem.COMPONENTE_TIPO_INEXISTENTE);
		}
		
		
		ConnUtil.getInstance().beginTransaction();
		Query q = ConnUtil.getInstance().getEntityManager().createQuery("select c from Componente c where c.nome = :nome and c.tipo = :tipo", Componente.class);
		q.setParameter("nome", componente.getNome());
		q.setParameter("tipo", componente.getTipo());
		Componente retorno;
		try{
			retorno = (Componente)q.getSingleResult();
		}catch(Exception e){
			retorno = null;
		}
		
		if(retorno != null){
			throw new Exception(Mensagem.COMPONENTE_EXISTENTE);
		}
		
		return cDAO.incluir(componente);
	}
	
	public void excluir(Componente componente) throws Exception{
		cDAO.excluir(componente);
	}
}
