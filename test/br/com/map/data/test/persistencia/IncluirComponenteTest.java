package br.com.map.data.test.persistencia;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class IncluirComponenteTest {
	
	private ComponenteDAO cDAO;
	
	@Test
	public void deveIncluirComponente() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste Inclusão");
		c.setTipo(1);
		c.setInclusao(new Date());
		
		long retorno = cDAO.incluir(c);
		
		assertTrue(retorno > 0);
	}
	
	@Test
	public void naoDeveIncluirSemNome() throws Exception{
		Componente c = new Componente();
		c.setTipo(1);
		c.setInclusao(new Date());
		
		try {
			cDAO.incluir(c);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void naoDeveIncluirSemTipo() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste sem Tipo");
		c.setInclusao(new Date());
		
		try {
			cDAO.incluir(c);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void naoDeveIncluirComTipoInexistente() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste sem Tipo");
		c.setTipo(9);
		c.setInclusao(new Date());
		
		try {
			cDAO.incluir(c);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void naoDeveIncluirDuplicado() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste Duplicidade");
		c.setTipo(1);
		c.setInclusao(new Date());
		
		try {
			cDAO.incluir(c);
			cDAO.incluir(c);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}

}
