package com.barrostsb.prime_scrum.jpautils;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class HistoriaPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projeto;
	private int sprint;
	private int id_historia;
	
	@ManyToOne
	@JoinColumn(name="id_projeto")
	public int getProjeto() {
		return projeto;
	}
	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}
	
	@ManyToOne
	@JoinColumn(name="id_sprint")
	public int getSprint() {
		return sprint;
	}
	public void setSprint(int sprint) {
		this.sprint = sprint;
	}
	
	public int getId_historia() {
		return id_historia;
	}
	public void setId_historia(int id_historia) {
		this.id_historia = id_historia;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_historia;
		result = prime * result + projeto;
		result = prime * result + sprint;
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
		HistoriaPK other = (HistoriaPK) obj;
		if (id_historia != other.id_historia)
			return false;
		if (projeto != other.projeto)
			return false;
		if (sprint != other.sprint)
			return false;
		return true;
	}
}
