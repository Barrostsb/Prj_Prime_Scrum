package com.barrostsb.prime_scrum.EntitiesPKs;

import java.io.Serializable;



public class SprintPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int projeto;
	private int id_sprint;
	
	public SprintPK(){
		
	}
	public int getProjeto() {
		return projeto;
	}
	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}
	
	public int getId_sprint() {
		return id_sprint;
	}
	public void setId_sprint(int sprint) {
		this.id_sprint = sprint;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_sprint;
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
		SprintPK other = (SprintPK) obj;
		if (id_sprint != other.id_sprint)
			return false;
		if (projeto != other.projeto)
			return false;
		return true;
	}
}
