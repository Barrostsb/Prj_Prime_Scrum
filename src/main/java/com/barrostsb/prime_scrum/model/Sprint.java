package com.barrostsb.prime_scrum.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.barrostsb.prime_scrum.EntitiesPKs.SprintPK;


@Entity
@Table (name = "sprint")
@IdClass(value=SprintPK.class)
public class Sprint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id_sprint;
	private Projeto projeto;
	private String nome;
	private int numero;
//	private List<Historia> listaHitorias;
	private List<Tarefa> listaTarefa;
	
//	@OneToMany(mappedBy = "sprint", fetch=FetchType.LAZY)
//	@ForeignKey(name = "FK_SprintTarefa")
//	public List<Tarefa> getListaTarefa() {
//		return listaTarefa;
//	}
	
	public void setListaTarefa(List<Tarefa> listaTarefa) {
		this.listaTarefa = listaTarefa;
	}
	public Sprint() {
		// TODO Auto-generated constructor stub
	}
	@Id
	public int getId_sprint() {
		return id_sprint;
	}

	public void setId_sprint(int id_sprint) {
		this.id_sprint = id_sprint;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_projeto", referencedColumnName="id_projeto")
	@ForeignKey(name = "FK_ProjetoSprint")
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
//	@OneToMany(mappedBy = "sprint", fetch=FetchType.LAZY)
//	@ForeignKey(name="FK_SprintHistoria")
//	public List<Historia> getListaHitorias() {
//		return listaHitorias;
//	}
//	
//	public void setListaHitorias(List<Historia> listaHitorias) {
//		this.listaHitorias = listaHitorias;
//	}
	
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

}
