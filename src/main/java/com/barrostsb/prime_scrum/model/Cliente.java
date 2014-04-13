package com.barrostsb.prime_scrum.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id_cliente", referencedColumnName="id_pessoa")
public class Cliente extends Pessoa{

	private static final long serialVersionUID = 1L;
	

}
