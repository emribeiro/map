package br.com.map.data.entity.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.map.data.entity.BaseEntity;

@Entity
@Table(name="TBD01_TIPO_COMPONENTE")
@NamedQueries({
	@NamedQuery(name="tipoComponente.listar", query="Select tc from TipoComponente tc"),
	@NamedQuery(name="tipoComponente.pesquisarPorNome", query="Select tc from TipoComponente tc where tc.nome = :nome")
})
public class TipoComponente extends BaseEntity{
	private static final long serialVersionUID = 4988616009919496930L;
	@Id
	@Column(name="TP_COMPONENTE")
	private int tipo;
	@Column(name="NO_TP_COMPONENTE")
	private String nome;
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
