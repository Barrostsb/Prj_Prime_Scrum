package com.barrostsb.prime_scrum.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "desenvolvedor")
@PrimaryKeyJoinColumn(name = "id_desenvolvedor", referencedColumnName="id_pessoa")
public class Desenvolvedor extends Pessoa{
		
	private static final long serialVersionUID = 1L;
	
	private int id_scrumMaster;
	private List<Projeto> listaProjetos;
	
	public int getId_scrumMaster() {
		return id_scrumMaster;
	}
	public void setId_scrumMaster(int id_scrumMaster) {
		this.id_scrumMaster = id_scrumMaster;
	}
	
	@ManyToMany(mappedBy="listaDesenvolvedores")  
	public List<Projeto> getListaProjetos() {
		return listaProjetos;
	}
	
	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
}
