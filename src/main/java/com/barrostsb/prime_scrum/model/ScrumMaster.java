package com.barrostsb.prime_scrum.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "scrumMaster")
@PrimaryKeyJoinColumn(name = "id_scrumMaster", referencedColumnName="id_pessoa")
public class ScrumMaster extends Pessoa{
	private static final long serialVersionUID = 1L;
//	private List<Projeto> listaProjetos;

//	@OneToMany(mappedBy= "scrumMaster",  fetch=FetchType.LAZY)
//	@OneToMany(mappedBy= "scrumMaster",  fetch=FetchType.LAZY)
//	public List<Projeto> getListaProjetos() {
//		return listaProjetos;
//	}
	
//	public void setListaProjetos(List<Projeto> listaProjetos) {
//		this.listaProjetos = listaProjetos;
//	}
}
