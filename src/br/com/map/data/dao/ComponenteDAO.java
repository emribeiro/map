
package br.com.map.data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.map.data.entity.Componente;

public class ComponenteDAO {

	private GenericDAO<Componente> gDAO;
	
	public ComponenteDAO(){
		gDAO = new GenericDAO<Componente>(Componente.class);
	}
	
	public long incluir(Componente componente) throws Exception{
		return gDAO.save(componente).getId();	
	}
	
	public void alterar(Componente componente) throws Exception{
		gDAO.update(componente);
	}
	
	public void excluir(Componente componente) throws Exception{
		gDAO.delete(gDAO.find(componente.getId()));
	}
	
	public long count() throws Exception{
		return gDAO.executeScalarQuery("componente.count", null);
	}
	
	public long countByTipo(int tipo) throws Exception{
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipo", tipo);

		return gDAO.executeScalarQuery("componente.countByTipo", parametros);
	}

	public List<Componente> listar() throws Exception {
		return gDAO.executeListQuery("componente.listar", null);
	}

	public Componente pesquisarPorId(long id) throws Exception {
		return gDAO.find(id);
	}

	public List<Componente> pesquisarPorNome(String nome) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nome", "%" + nome + "%");
		
		return gDAO.executeListQuery("componente.pesquisarPorNome", parametros);
	}

	public List<Componente> pesquisarPorTipo(int tipo) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipo", tipo);
		
		return gDAO.executeListQuery("componente.pesquisarPorTipo", parametros);
	}

	public Componente pesquisarPorNomeETipo(String nome, int tipo) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipo", tipo);
		parametros.put("nome", nome);
		
		return gDAO.executeSingleQuery("componente.pesquisarPorNomeETipo", parametros);
	}
}
