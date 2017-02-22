package br.com.map.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB001_COMPONENTE")
@NamedQueries({
	@NamedQuery(name="componente.count", query="select count(c) from Componente c"),
	@NamedQuery(name="componente.countByTipo", query="select count(c) from Componente c where c.tipo = :tipo"),
	@NamedQuery(name="componente.listar", query="select c from Componente c"),
	@NamedQuery(name="componente.pesquisarPorNome", query="select c from Componente c where c.nome like :nome"),
	@NamedQuery(name="componente.pesquisarPorTipo", query="select c from Componente c where c.tipo = :tipo"),
	@NamedQuery(name="componente.pesquisarPorNomeETipo", query="select c from Componente c where c.nome = :nome and c.tipo = :tipo")
})
public class Componente extends BaseEntity{

	private static final long serialVersionUID = 5142478605260262836L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMPONENTE")
	private long id;
	
	@Column(name="NO_COMPONENTE")
	private String nome;
	
	@Column(name="DE_COMPONENTE")
	private String descricao;
	
	@Column(name="TP_COMPONENTE")
	private int tipo;
	
	@Column(name="TS_INCLUSAO")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date inclusao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getInclusao() {
		return inclusao;
	}
	public void setInclusao(Date inclusao) {
		this.inclusao = inclusao;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public boolean equals(Componente componente){
		return this.getId() == componente.getId() &&
			   this.getNome().equals(componente.getNome()) && 
			   this.getDescricao().equals(componente.getDescricao()) &&
			   this.getTipo() == componente.getTipo();
	}
}
