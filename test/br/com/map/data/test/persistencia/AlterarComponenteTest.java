package br.com.map.data.test.persistencia;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.map.data.dao.impl.ComponenteDAO;
import br.com.map.data.entity.Componente;

public class AlterarComponenteTest {

	private ComponenteDAO cDAO;
	
	@Before
	public void setUp() throws Exception{
		cDAO = new ComponenteDAO();
	}
	
	@Test
	public void deveAlterarComponente() throws Exception{
		Componente antigo = new Componente();
		antigo.setNome("Teste Alteração Nova");
		antigo.setDescricao("Teste Alteração de componente");
		antigo.setTipo(1);
		antigo.setInclusao(new Date());
		
		cDAO.incluir(antigo);
		
		antigo.setNome("Nome alterado Teste");
		antigo.setInclusao(new Date());
		cDAO.alterar(antigo);
		
		Componente novo = cDAO.pesquisarPorId(antigo.getId());
		
		assertEquals(antigo, novo);
		
		cDAO.excluir(antigo);
	
	}
	
}
