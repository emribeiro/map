package br.com.map.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB001_COMPONENTE")
public class Componente {
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
}
