package br.com.map.data.dao.impl;

import br.com.map.data.dao.GenericDAO;
import br.com.map.data.entity.dominio.TipoComponente;

public class TipoComponenteDAO {
	private GenericDAO<TipoComponente> gDAO;
	
	public TipoComponenteDAO(){
		gDAO = new GenericDAO<TipoComponente>(TipoComponente.class);
	}
	
	public int incluir(TipoComponente tipoComponente) throws Exception{
		return gDAO.save(tipoComponente).getTipo();
	}

	public void alterar(TipoComponente tipoComponente) throws Exception{
		gDAO.update(tipoComponente);
	}
	
	public TipoComponente pesquisarPorTipo(int tipo) throws Exception{
		return gDAO.find(tipo);
	}
	
	public void excluir(TipoComponente tipoComponente) throws Exception{
		gDAO.delete(this.pesquisarPorTipo(tipoComponente.getTipo()));
	}
}
