package com.barrostsb.prime_scrum.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_cliente", referencedColumnName="id_pessoa")
public class Cliente extends Pessoa{

	private static final long serialVersionUID = 1L;
	private int id_scrumMaster;
	
	public int getId_scrumMaster() {
		return id_scrumMaster;
	}
	public void setId_scrumMaster(int id_scrumMaster) {
		this.id_scrumMaster = id_scrumMaster;
	}
}
