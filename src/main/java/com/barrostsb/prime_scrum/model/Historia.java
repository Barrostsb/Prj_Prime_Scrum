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

import com.barrostsb.prime_scrum.jpautils.HistoriaPK;


@Entity
@Table(name = "historia")
public class Historia {
	private Sprint sprint;
	private int id_historia;
	private String nome;
	private List<Tarefa> listaTarefas;
	private HistoriaPK historiaPK;
	
	@ManyToOne
	@JoinColumn(name="id_sprint")
	public Sprint getSprint() {
		return sprint;
	}
	
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	@Column
	public int getId_historia() {
		return id_historia;
	}
	
	public void setId_historia(int id_historia) {
		this.id_historia = id_historia;
	}
	
	@Id
	@EmbeddedId
	public HistoriaPK getHistoriaPK() {
		return historiaPK;
	}

	public void setHistoriaPK(HistoriaPK historiaPK) {
		this.historiaPK = historiaPK;
	}

	@Column
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@OneToMany(mappedBy = "historia", fetch=FetchType.LAZY)
	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}
	
	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
}
