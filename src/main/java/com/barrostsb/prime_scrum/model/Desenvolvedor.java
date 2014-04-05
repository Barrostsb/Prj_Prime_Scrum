package com.barrostsb.prime_scrum.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "desenvolvedor")
//@PrimaryKeyJoinColumn(name = "id_desenvolvedor", referencedColumnName="id_pessoa")
public class Desenvolvedor extends Pessoa{
		
	private static final long serialVersionUID = 1L;
//	private List<Tarefa> listaTarefas;
	private List<Projeto> listaProjetos;
	

	
//	@ManyToMany
//	@JoinTable(name = "desenvolvedor_tarefa_valores", joinColumns = @JoinColumn(name = "id_desenvolvedor"), inverseJoinColumns = @JoinColumn(name = "id_tarefa"))
//	public List<Tarefa> getListaTarefas() {
//		return listaTarefas;
//	}
//	
//	public void setListaTarefas(List<Tarefa> listaTarefas) {
//		this.listaTarefas = listaTarefas;
//	}
	
	
//	@OneToMany(mappedBy="devId")
//	private Set<Desenvolvedor_tarefa_valores> tarefa_valores;
	
	
	
	@ManyToMany
	@JoinTable(name = "desenvolvedor_projeto", joinColumns = @JoinColumn(name = "id_desenvolvedor"), inverseJoinColumns = @JoinColumn(name = "id_projeto"))
	public List<Projeto> getListaProjetos() {
		return listaProjetos;
	}
	
	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
}
