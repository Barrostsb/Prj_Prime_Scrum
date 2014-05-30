package com.barrostsb.prime_scrum.EntitiesPKs;

import java.io.Serializable;


public class DevTaskVal_PK implements Serializable {
	private static final long serialVersionUID = 1L;
	private int devId;
	private int tarefaId;
	
	public int getDevId() {
		return devId;
	}
	public void setDevId(int devId) {
		this.devId = devId;
	}
	public int getTarefaId() {
		return tarefaId;
	}
	public void setTarefaId(int tarefaId) {
		this.tarefaId = tarefaId;
	}
		
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + devId;
		result = prime * result + tarefaId;
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
		DevTaskVal_PK other = (DevTaskVal_PK) obj;
		if (devId != other.devId)
			return false;
		if (tarefaId != other.tarefaId)
			return false;
		return true;
	}

}
