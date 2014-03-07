package com.barrostsb.prime_scrum.EntitiesPKs;

import java.io.Serializable;

public class TarefaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int projeto;
	private int id_tarefa;
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_tarefa;
		result = prime * result + projeto;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaPK other = (TarefaPK) obj;
		if (id_tarefa != other.id_tarefa)
			return false;
		if (projeto != other.projeto)
			return false;
		return true;
	}
}
