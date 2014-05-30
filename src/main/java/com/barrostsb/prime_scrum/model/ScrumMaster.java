package com.barrostsb.prime_scrum.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "scrumMaster")
@PrimaryKeyJoinColumn(name = "id_scrumMaster", referencedColumnName="id_pessoa")
public class ScrumMaster extends Pessoa{
	private static final long serialVersionUID = 1L;
}
