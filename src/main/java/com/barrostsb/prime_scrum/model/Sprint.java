package com.barrostsb.prime_scrum.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.barrostsb.prime_scrum.jpautils.SprintPK;


@Entity
@Table (name = "sprint")
public class Sprint {
	private Projeto projeto;
	private String nome;
	private int numero;
	private List<Historia> listaHitorias;
	private SprintPK sprintpk;
	

	@ManyToOne
	@JoinColumn(name="id_projeto")
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@Id	
	@EmbeddedId
	public SprintPK getSprintpk() {
		return sprintpk;
	}

	public void setSprintpk(SprintPK sprintpk) {
		this.sprintpk = sprintpk;
	}

	@Column
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	} 
	
	@Column
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@OneToMany(mappedBy = "sprint", fetch=FetchType.LAZY)
	public List<Historia> getListaHitorias() {
		return listaHitorias;
	}
	
	public void setListaHitorias(List<Historia> listaHitorias) {
		this.listaHitorias = listaHitorias;
	}
}
