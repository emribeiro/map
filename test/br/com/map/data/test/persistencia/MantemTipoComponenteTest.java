package br.com.map.data.test.persistencia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.map.data.dao.impl.TipoComponenteDAO;
import br.com.map.data.entity.dominio.TipoComponente;

public class MantemTipoComponenteTest {

	private TipoComponenteDAO tcDAO;
	
	@Before
	public void setUp(){
		tcDAO = new TipoComponenteDAO();
	}
	
	@Test
	public void deveInserirTipoComponente() throws Exception{
		TipoComponente tc = new TipoComponente();
		tc.setTipo(999);
		tc.setNome("Tipo de Teste inclusao");
		
		int tipo = tcDAO.incluir(tc);
		
		if(tipo == 0){
			fail();
		}
		tcDAO.excluir(tc);
		
		assertTrue(true);
	}
	
	@Test
	public void naoDeveInserirTipoComponenteDuplicado() throws Exception{
		TipoComponente tc = new TipoComponente();
		tc.setTipo(999);
		tc.setNome("Tipo de Teste inclusao duplicado");
		TipoComponente tc2 = new TipoComponente();
		tc2.setTipo(999);
		tc2.setNome("Tipo de Teste inclusao duplicado 2");
		
		try {
			tcDAO.incluir(tc);
			tcDAO.incluir(tc2);
			fail();
		} catch (Exception e) {
			tcDAO.excluir(tc);
			assertTrue(true);
		}
	}
	
	@Test
	public void deveAlterarTipoComponente() throws Exception{
		TipoComponente tc = new TipoComponente();
		tc.setTipo(999);
		tc.setNome("Tipo de Teste alteração");
		TipoComponente tc2 = null;
		
		tcDAO.incluir(tc);
		
		tc2 = tc;
		tc2.setNome("Alterado");
		tcDAO.alterar(tc2);
		
		TipoComponente tc3 = tcDAO.pesquisarPorTipo(tc2.getTipo());
		
		tcDAO.excluir(tc3);
		assertEquals(tc2.getNome(), tc3.getNome());
		
	}
	
	@Test
	public void deveExcluirTipoComponente() throws Exception{
		TipoComponente tc = new TipoComponente();
		tc.setTipo(999);
		tc.setNome("Tipo de Teste alteração");

		tcDAO.incluir(tc);
		
		tcDAO.excluir(tc);
		
		TipoComponente tc2 = tcDAO.pesquisarPorTipo(tc.getTipo());
			
		assertEquals(null, tc2);
	}
}
