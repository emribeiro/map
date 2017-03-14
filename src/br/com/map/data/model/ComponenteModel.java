package br.com.map.data.model;

import java.util.List;

import br.com.map.data.dao.impl.ComponenteDAO;
import br.com.map.data.entity.Componente;
import br.com.map.data.model.mensagem.Mensagem;
import br.com.map.data.validade.ComponenteValidator;

public class ComponenteModel {

	private ComponenteDAO cDAO;
	private ComponenteValidator cValidator;
	
	public ComponenteModel(){
		cDAO = new ComponenteDAO();
		cValidator = new ComponenteValidator();
	}
	
	public long incluir(Componente componente) throws Exception{
		
		cValidator.validate(componente);
		cValidator.validarDuplicidade(componente);
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
		cValidator.validate(componente);
		cValidator.validarExistencia(componente);
		cDAO.alterar(componente);
	}
}
