package br.com.map.data.test.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.map.data.entity.Componente;
import br.com.map.data.model.ComponenteModel;
import br.com.map.data.model.mensagem.Mensagem;

public class ConsultarComponenteModelTest {
	
	private ComponenteModel cModel;
	private boolean isNotSetUp = true;
	private Componente componente;
	private long qtdeComponentes = 0;
	private long qtdeTipo = 0;
	
	
	@Before
	public void setUp() throws Exception{
		if(isNotSetUp){
			cModel = new ComponenteModel();
			isNotSetUp = false;
			
			componente = new Componente();
			componente.setNome("Componente Teste Consulta");
			componente.setDescricao("Componente para teste de consulta!!");
			componente.setTipo(1);
			componente.setInclusao(new Date());
			
			cModel.incluir(componente);
			
			qtdeComponentes = cModel.getQuantidadeComponentes();
			qtdeTipo = cModel.getQuantidadePorTipo(1);
		}
	}
	
	@Test
	public void deveListarTudo() throws Exception{
		List<Componente> componentes = cModel.listarTodos();
		
		assertEquals(qtdeComponentes, componentes.size());
	}
	
	
	@Test
	public void deveConsultarComponentePorId() throws Exception{
		Componente c = cModel.pesquisarPorId(componente.getId());
		
		assertTrue(c.equals(componente));
	}
	
	@Test 
	public void deveConsultarComponentePorNome() throws Exception{
		List<Componente> resultado = cModel.pesquisarPorNome(componente.getNome());
		
		assertEquals(1, resultado.size());
		assertTrue(componente.equals(resultado.get(0)));
	}
	
	@Test
	public void deveConsultarPorTipo() throws Exception{
		List<Componente> resultado = cModel.pesquisarPorTipo(1);
		
		assertEquals(qtdeTipo, resultado.size());
	}
	
	@Test
	public void deveConsultarPorNomeETipo() throws Exception{
		Componente c = cModel.pesquisarPorNomeETipo(componente.getNome(), componente.getTipo());
		
		assertTrue(componente.equals(c));
	}
	
	@Test
	public void naoDeveConsultarPorIdInexistente() throws Exception{
		try{
			cModel.pesquisarPorId(9999999l);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarPorNomeInexistente() throws Exception{
		try{
			cModel.pesquisarPorNome("Jose das Couves");
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarPorTipoInexistente() throws Exception{
		try{
			cModel.pesquisarPorTipo(3);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarPorNomeETipoInexistente() throws Exception{
		try{
			cModel.pesquisarPorTipo(3);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarTipoSemTipoInformado() throws Exception{
		try{
			cModel.pesquisarPorTipo(0);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.PESQUISA_COMPONENTE_TIPO_NAO_INFORMADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarNomeSemNomeInformado() throws Exception{
		try{
			cModel.pesquisarPorNome("");
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.PESQUISA_COMPONENTE_NOME_NAO_INFORMADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarIdSemIdInformado() throws Exception{
		try{
			cModel.pesquisarPorId(0);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.PESQUISA_COMPONENTE_ID_NAO_INFORMADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarNomeETipoSemNomeInformado() throws Exception{
		try{
			cModel.pesquisarPorNomeETipo(null, 1);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.PESQUISA_COMPONENTE_NOME_NAO_INFORMADO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveConsultarNomeETipoSemTipoInformado() throws Exception{
		try{
			cModel.pesquisarPorNomeETipo("Teste", 0);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.PESQUISA_COMPONENTE_TIPO_NAO_INFORMADO, e.getMessage());
		}
	}
	
	@After
	public void tearDown() throws Exception{
		cModel.excluir(componente);
	}

}
