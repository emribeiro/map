package br.com.map.data.model;

import java.util.List;

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
	
	public long getQuantidadeComponentes() throws Exception{
		return cDAO.count();
	}
	
	public long getQuantidadePorTipo(int tipo) throws Exception{
		return cDAO.countByTipo(tipo);
	}
	
	public List<Componente> listarTodos() throws Exception{
		List<Componente> retorno = null;

		retorno = cDAO.listar();
		
		if(retorno.size() == 0 || retorno == null){
			throw new Exception(Mensagem.NENHUM_REGISTRO_ENCONTRADO);
		}
		
		return retorno;
	}
	
	public Componente pesquisarPorId(long id) throws Exception{
		if(id == 0){
			throw new Exception(Mensagem.PESQUISA_COMPONENTE_ID_NAO_INFORMADO);
		}
		
		Componente retorno = cDAO.pesquisarPorId(id);
		
		if(retorno == null){
			throw new Exception(Mensagem.COMPONENTE_NAO_ENCONTRADO);
		}
		
		return retorno;
	}
	
	public List<Componente> pesquisarPorNome(String nome) throws Exception{
		if(nome == null || nome.trim().length() == 0){
			throw new Exception(Mensagem.PESQUISA_COMPONENTE_NOME_NAO_INFORMADO);
		}
		
		List<Componente> retorno = cDAO.pesquisarPorNome(nome);
		
		if(retorno == null || retorno.size() == 0){
			throw new Exception(Mensagem.COMPONENTE_NAO_ENCONTRADO);
		}
		
		return retorno;
	}
	
	public List<Componente> pesquisarPorTipo(int tipo) throws Exception{
		if(tipo == 0){
			throw new Exception(Mensagem.PESQUISA_COMPONENTE_TIPO_NAO_INFORMADO);
		}
		
		List<Componente> retorno = cDAO.pesquisarPorTipo(tipo);
		
		if(retorno == null || retorno.size() == 0){
			throw new Exception(Mensagem.COMPONENTE_NAO_ENCONTRADO);
		}
		
		return retorno;
	}
	
	public Componente pesquisarPorNomeETipo(String nome, int tipo) throws Exception{
		if(nome == null || nome.trim().length() == 0){
			throw new Exception(Mensagem.PESQUISA_COMPONENTE_NOME_NAO_INFORMADO);
		}
		
		if(tipo == 0){
			throw new Exception(Mensagem.PESQUISA_COMPONENTE_TIPO_NAO_INFORMADO);
		}
		
		Componente retorno = cDAO.pesquisarPorNomeETipo(nome, tipo);
		
		if(retorno == null){
			throw new Exception(Mensagem.COMPONENTE_NAO_ENCONTRADO);
		}
		
		return retorno;
	}
	
	public void alterar(Componente componente) throws Exception{
		
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
		
		try{
			pesquisarPorId(componente.getId());
		}catch(Exception e){
			throw new Exception(Mensagem.COMPONENTE_INEXISTENTE);
		}
		
		cDAO.alterar(componente);
		
	}
}
