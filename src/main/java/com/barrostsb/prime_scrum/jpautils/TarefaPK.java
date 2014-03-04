package com.barrostsb.prime_scrum.jpautils;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TarefaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int projeto;
	private int id_tarefa;
	
	
	@ManyToOne
	@JoinColumn(name="id_projeto")
	public int getProjeto() {
		return projeto;
	}
	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}
	
	public int getId_tarefa() {
		return id_tarefa;
	}
	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	

	

}
