package br.com.map.data.validade;

import java.util.Date;

import br.com.map.data.entity.BaseEntity;

public abstract class AbstractValidator<T extends BaseEntity> {

	public void validate(T t) throws Exception{
		validarObrigatoriedade(t);
		validarFormato(t);
		validarDominio(t);
		validarReferencia(t);
	}
	
	protected abstract void validarObrigatoriedade(T t) throws Exception;
	protected abstract void validarFormato(T t) throws Exception;
	protected abstract void validarDominio(T t) throws Exception;
	protected abstract void validarReferencia(T t) throws Exception;
	protected abstract void validarDuplicidade(T t) throws Exception;
	
	
	protected boolean validarString(String s){
		return (s == null || s.trim().length() == 0);
	}
	
	protected boolean validarNumero(int l){
		return (l ==0);
	}
	
	protected boolean validarData(Date d){
		return (d == null);
	}
	
	protected boolean validarObjeto(Object o){
		return (o == null);
	}
	
}
