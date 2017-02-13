package br.com.map.data.test.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.map.data.entity.Componente;
import br.com.map.data.model.ComponenteModel;
import br.com.map.data.model.mensagem.Mensagem;

public class AlterarComponenteModelTest {
	private ComponenteModel cModel;
	
	@Before
	public void setUp() throws Exception{
		cModel = new ComponenteModel();
	}
	
	@Test
	public void deveAlterarComponente() throws Exception{
		Componente antigo = new Componente();
		antigo.setNome("alteração nome teste positivo");
		antigo.setDescricao("Teste de Alteração Model");
		antigo.setTipo(1);
		antigo.setInclusao(new Date());
		
		antigo.setId(cModel.incluir(antigo));
		
		antigo.setNome("alteração nome novo");
		antigo.setInclusao(new Date());
		
		System.out.println(antigo.getId());
		cModel.alterar(antigo);
		
		Componente novo = cModel.pesquisarPorId(antigo.getId());
		assertEquals(antigo, novo);
		
		cModel.excluir(antigo);
	}
	
	@Test
	public void naoDeveAlterarComponenteNulo() throws Exception{
		try{
			cModel.alterar(null);
			fail();
		}catch(Exception e){
			assertEquals(Mensagem.COMPONENTE_NULO, e.getMessage());
		}
	}
	
	@Test
	public void naoDeveAlterarComponenteInexistente() throws Exception{
		Componente alteradoSoQueNao = new Componente();
		alteradoSoQueNao.setId(99999l);
		alteradoSoQueNao.setNome("Teste de não alteração");
		alteradoSoQueNao.setDescricao("Teste de alteração que não vai funcionar.");
		alteradoSoQueNao.setTipo(1);
		alteradoSoQueNao.setInclusao(new Date());
		
		
		try {
			cModel.alterar(alteradoSoQueNao);
			fail();
		} catch (Exception e) {
			assertEquals(Mensagem.COMPONENTE_INEXISTENTE, e.getMessage());
		}
		
	}
	
	@Test
	public void naoDeveAlterarNomeParaBrancos() throws Exception{
		Componente antigo = new Componente();
		antigo.setNome("alteração nome brancos");
		antigo.setDescricao("Teste de Alteração Model nomes brancos");
		antigo.setTipo(1);
		antigo.setInclusao(new Date());
		
		antigo.setId(cModel.incluir(antigo));
		antigo.setNome("");
		
		try {
			cModel.alterar(antigo);
			cModel.excluir(antigo);
			fail();
		} catch (Exception e) {
			assertEquals(Mensagem.COMPONENTE_SEM_NOME, e.getMessage());
			cModel.excluir(antigo);
		}
		
	}
	
	@Test
	public void naoDeveAlterarTipoParaZeros() throws Exception{
		Componente antigo = new Componente();
		antigo.setNome("alteração nome tipo zero");
		antigo.setDescricao("Teste de Alteração Model");
		antigo.setTipo(1);
		antigo.setInclusao(new Date());
		
		antigo.setId(cModel.incluir(antigo));
		antigo.setTipo(0);
		
		try {
			cModel.alterar(antigo);
			cModel.excluir(antigo);
			fail();
		} catch (Exception e) {
			assertEquals(Mensagem.COMPONENTE_SEM_TIPO, e.getMessage());
			cModel.excluir(antigo);
		}
		
		
	}
	
	
	@Test
	public void naoDeveAlterarTipoParaTipoInexistente() throws Exception{
		Componente antigo = new Componente();
		antigo.setNome("alteração nome tipo inexistente");
		antigo.setDescricao("Teste de Alteração Model");
		antigo.setTipo(1);
		antigo.setInclusao(new Date());
		
		antigo.setId(cModel.incluir(antigo));
		antigo.setTipo(999);
		
		try {
			cModel.alterar(antigo);
			cModel.excluir(antigo);
			fail();
		} catch (Exception e) {
			assertEquals(Mensagem.COMPONENTE_TIPO_INEXISTENTE, e.getMessage());
			cModel.excluir(antigo);
		}
	}
}
