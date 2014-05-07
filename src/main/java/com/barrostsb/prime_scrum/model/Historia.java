package com.barrostsb.prime_scrum.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;


//@Entity
//@Table(name = "historia")
//@IdClass(value=HistoriaPK.class)
public class Historia {
	private Sprint sprint;
//	private Projeto projeto;	
	private String nome;
	private int id_historia;
	private List<Tarefa> listaTarefas;

//	@Id
//	@ManyToOne
//	@JoinColumn(name="id_projeto")
//	@ForeignKey(name="FK_ProjetoHistoia")
//	public Projeto getProjeto() {
//		return projeto;
//	}
//	
//	public void setProjeto(Projeto projeto) {
//		this.projeto = projeto;
//	}
	
	@Id
	public int getId_historia() {
		return id_historia;
	}
	
	public void setId_historia(int id_historia) {
		this.id_historia = id_historia;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_sprint")
	@ForeignKey(name="FK_SprintHistoria")
	public Sprint getSprint() {
		return sprint;
	}
	
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
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
