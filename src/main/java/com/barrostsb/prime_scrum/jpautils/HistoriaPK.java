package com.barrostsb.prime_scrum.jpautils;

import java.io.Serializable;


public class HistoriaPK implements Serializable {
	private static final long serialVersionUID = 1L;
//	private int projeto;
	private int sprint;
	private int id_historia;
	
	public HistoriaPK() {
		// TODO Auto-generated constructor stub
	}
	
//	public int getProjeto() {
//		return projeto;
//	}
//	public void setProjeto(int projeto) {
//		this.projeto = projeto;
//	}
	
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
		if (sprint != other.sprint)
			return false;
		return true;
	}

	
}
