package br.com.map.data.test.persistencia;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.map.data.dao.impl.ComponenteDAO;
import br.com.map.data.entity.Componente;

public class ConsultarComponenteTest {
	
	private ComponenteDAO cDAO;
	private boolean isNotSetUp = true;
	private Set<Componente> chavesDelete;
	private Componente componente;
	private long qtdeAntes;
	
	@Before
	public void setUp() throws Exception{
		if(isNotSetUp){
			cDAO = new ComponenteDAO();
			isNotSetUp = false;
			qtdeAntes = cDAO.count();
		}
		
		chavesDelete = new HashSet<Componente>();
		
	}
	
	@Test
	public void deveListarTudo() throws Exception{
		componente = new Componente();
		componente.setNome("TesteListaTudo1");
		componente.setDescricao("Teste lista tudo 1 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		componente = new Componente();
		componente.setNome("TesteListaTudo2");
		componente.setDescricao("Teste lista tudo 2 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		componente = new Componente();
		componente.setNome("TesteListaTudo3");
		componente.setDescricao("Teste lista tudo 3 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		List<Componente> componentes = cDAO.listar();
		
		assertEquals(qtdeAntes + 3, componentes.size());
	}
	
	@Test
	public void devePesquisarPorId() throws Exception{
		componente = new Componente();
		componente.setNome("TestePesquisaID1");
		componente.setDescricao("Teste pesquisa por Id 1 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		Componente c = cDAO.pesquisarPorId(componente.getId());
		
		assertTrue(componente.equals(c));
	}
	
	@Test
	public void devePesquisarPorNome() throws Exception{
		componente = new Componente();
		componente.setNome("TestePesquisaNome1");
		componente.setDescricao("Teste pesquisa por nome 1 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		List<Componente> componentes =  cDAO.pesquisarPorNome(componente.getNome()); 
		
		assertTrue(componente.equals(componentes.get(0)));
	}
	
	@Test
	public void devePesquisarPorTipo() throws Exception{
		long qtdeTipo = cDAO.countByTipo(1);
		
		List<Componente> componentes = cDAO.pesquisarPorTipo(1);
		
		assertEquals(qtdeTipo, componentes.size());
	}
	
	@Test
	public void devePesquisarPorNomeETipo() throws Exception{
		componente = new Componente();
		componente.setNome("TestePesquisaNomeETipo1");
		componente.setDescricao("Teste pesquisa por nome e Tipo 1 ");
		componente.setTipo(1);
		componente.setInclusao(new Date());
		cDAO.incluir(componente);
		chavesDelete.add(componente);
		
		Componente c = cDAO.pesquisarPorNomeETipo(componente.getNome(), componente.getTipo());
		
		assertTrue(componente.equals(c));
	}
	
	@After
	public void tearDown() throws Exception{
		for(Componente chave : chavesDelete ){
			cDAO.excluir(chave);
		}
	}
	
	

}
