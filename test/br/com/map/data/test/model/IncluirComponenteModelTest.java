package br.com.map.data.test.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.map.data.entity.Componente;
import br.com.map.data.model.ComponenteModel;
import br.com.map.data.model.mensagem.Mensagem;

public class IncluirComponenteModelTest {

	private ComponenteModel cModel;
	private boolean isSetUp = false;
	private Set<Componente> incluidosTeste;
	
	@Before
	public void setUp(){
		if(!isSetUp){
			cModel = new ComponenteModel();
			isSetUp = true;
			incluidosTeste = new HashSet<Componente>();
		}
	}
	
	@Test
	public void deveInserirComponente() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste de inclusão Model Normal");
		c.setDescricao("Descrição de teste de inclusão em model");	
		c.setTipo(1);
		c.setInclusao(new Date());
		
		assertTrue(cModel.incluir(c) > 0);
		
		incluidosTeste.add(c);
		
	}
	
	@Test
	public void deveInserirComponenteSemDescricao() throws Exception{
		Componente c = new Componente();
		c.setNome("Teste de inclusão Model Sem Descrição");
		c.setTipo(1);
		c.setInclusao(new Date());
		
		assertTrue(cModel.incluir(c) > 0);
		
		incluidosTeste.add(c);
	}
	
	@Test
	public void naoDeveInserirComponenteNulo(){
		try{
			cModel.incluir(null);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NULO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveInserirComponenteSemNome(){
		Componente c = new Componente();
		c.setDescricao("Descrição de teste de inclusão em model - Componente sem nome");	
		c.setTipo(1);
		c.setInclusao(new Date());
		
		try{
			cModel.incluir(c);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_SEM_NOME, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveInserirComponenteSemTipo(){
		Componente c = new Componente();
		c.setNome("Teste de inclusão Model - sem tipo");
		c.setDescricao("Descrição de teste de inclusão em model componente sem tipo");	
		c.setInclusao(new Date());
		
		try{
			cModel.incluir(c);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_SEM_TIPO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveInserirComponenteComTipoForaDoDominio(){
		Componente c = new Componente();
		c.setNome("Teste de inclusão Model - fora do dominio");
		c.setDescricao("Descrição de teste de inclusão em model");	
		c.setTipo(99);
		c.setInclusao(new Date());
		
		try{
			cModel.incluir(c);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_TIPO_INEXISTENTE, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveInserirComponenteExistente(){
		Componente c = new Componente();
		c.setNome("Teste de inclusão Model Existente");
		c.setDescricao("Descrição de teste de inclusão em model");	
		c.setTipo(1);
		c.setInclusao(new Date());
		
		Componente c2 = new Componente();
		c2.setNome("Teste de inclusão Model Existente");
		c2.setDescricao("Descrição de teste de inclusão em model - Componente 2");	
		c2.setTipo(1);
		c2.setInclusao(new Date());
		
		try{
			cModel.incluir(c);
			incluidosTeste.add(c);
			cModel.incluir(c2);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_EXISTENTE, e.getMessage());
		}
	}
	
	@After
	public void tearDown() throws Exception{
		for(Componente c : incluidosTeste){
			cModel.excluir(c);
		}
	}
}
