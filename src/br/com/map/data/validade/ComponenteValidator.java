package br.com.map.data.validade;

import br.com.map.data.dao.impl.ComponenteDAO;
import br.com.map.data.entity.Componente;
import br.com.map.data.model.mensagem.Mensagem;

public class ComponenteValidator extends AbstractValidator<Componente>{

	@Override
	protected void validarObrigatoriedade(Componente t) throws Exception {
		if(validarObjeto(t)){
			throw new Exception(Mensagem.COMPONENTE_NULO);
		}
		
		if(validarString(t.getNome())){
			throw new Exception(Mensagem.COMPONENTE_SEM_NOME);
		}
		
		if(validarNumero(t.getTipo())){
			throw new Exception(Mensagem.COMPONENTE_SEM_TIPO);
		}
	}

	@Override
	protected void validarFormato(Componente t) throws Exception {
		
	}

	@Override
	protected void validarDominio(Componente t) throws Exception {
		if(t.getTipo() != 1 && t.getTipo() != 2){
			throw new Exception(Mensagem.COMPONENTE_TIPO_INEXISTENTE);
		}
	}

	@Override
	protected void validarReferencia(Componente t) throws Exception {
		
	}

	@Override
	public void validarDuplicidade(Componente t) throws Exception {
		ComponenteDAO cDAO = new ComponenteDAO();
		Componente c;
		try{
			c = cDAO.pesquisarPorNomeETipo(t.getNome(), t.getTipo());
		}catch(Exception e){
			c = null;
		}
		
		if(c != null){
			throw new Exception(Mensagem.COMPONENTE_EXISTENTE);
		}
	}
	
	public void validarExistencia(Componente t) throws Exception{
		ComponenteDAO cDAO = new ComponenteDAO();
		Componente c;
		
		try {
			c = cDAO.pesquisarPorId(t.getId());
		} catch (Exception e) {
			c = null;
		}
		
		if(c == null){
			throw new Exception(Mensagem.COMPONENTE_INEXISTENTE);
		}
	}
	
	

}
