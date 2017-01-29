package br.com.map.data.test.model;

import org.junit.Before;
import org.junit.Test;

import br.com.map.data.model.ComponenteModel;

public class ConsultarComponenteModelTest {
	
	private ComponenteModel cModel;
	private boolean isNotSetUp = true;
	
	
	@Before
	public void setUp(){
		if(isNotSetUp){
			cModel = new ComponenteModel();
			isNotSetUp = false;
		}
	}
	
	@Test
	public void deveListarTudo() throws Exception{
		
	}
	
	
	@Test
	public void deveConsultarComponentePorId() throws Exception{
		
	}
	
	@Test 
	public void deveConsultarComponentePorNome() throws Exception{
		
	}
	
	@Test
	public void deveConsultarPorTipo() throws Exception{
		
	}
	
	@Test
	public void deveConsultarPorNomeETipo() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarPorIdInexistente() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarPorNomeInexistente() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarPorTipoInexistente() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarPorNomeETipoInexistente() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarTipoSemTipoInformado() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarNomeSemNomeInformado() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarIdSemIdInformado() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarNomeETipoSemNomeInformado() throws Exception{
		
	}
	
	@Test
	public void naoDeveConsultarNomeETipoSemTipoInformado() throws Exception{
		
	}

}
