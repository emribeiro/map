package br.com.map.data.test.persistencia;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.map.data.dao.impl.ComponenteDAO;
import br.com.map.data.entity.Componente;

public class IncluirComponenteTest {
	
	private ComponenteDAO cDAO;
	private boolean isSetUp = false;
	private Set<Componente> incluidosTeste;
	
	@Before
	public void setUp(){
		if(!isSetUp){
			cDAO = new ComponenteDAO();
			isSetUp = true;
			incluidosTeste = new HashSet<Componente>();
		}
	}
	
	@Test
	public void deveIncluirComponente() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste Inclusão");
		c.setTipo(1);
		c.setDescricao("Componente teste inclusão!!");
		c.setInclusao(new Date());
		
		long retorno = cDAO.incluir(c);
		
		incluidosTeste.add(c);
		
		assertTrue(retorno > 0);
	}
	
	@Test
	public void deveIncluirComponenteSemDescricao() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste Inclusão sem descricao");
		c.setTipo(1);
		c.setInclusao(new Date());
		long retorno = cDAO.incluir(c);
		
		incluidosTeste.add(c);
		
		assertTrue(retorno > 0);
	}
	
	@Test
	public void naoDeveIncluirSemNome() throws Exception{
		Componente c = new Componente();
		c.setTipo(1);
		c.setInclusao(new Date());
		c.setDescricao("Teste inclusão sem nome");
		
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
		c.setDescricao("Componente teste sem tipo!!");
		
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
		c.setDescricao("Componente teste inclusão tipo inexistente!!");
		
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
		c.setDescricao("Componente teste inclusão duplicada");
		
		Componente c2 = new Componente();
		c2.setNome("Teste Duplicidade");
		c2.setTipo(1);
		c2.setInclusao(new Date());
		c2.setDescricao("Componente teste inclusão duplicada");
		
		try {
			cDAO.incluir(c);
			incluidosTeste.add(c);
			cDAO.incluir(c2);
			incluidosTeste.add(c2);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@After
	public void tearDown() throws Exception{
		for(Componente c : incluidosTeste){
			cDAO.excluir(c);
		}
	}

}
