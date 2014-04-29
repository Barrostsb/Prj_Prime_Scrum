package com.barrostsb.prime_scrum.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import antlr.collections.impl.LList;


@Entity
@Table (name = "desenvolvedor")
@PrimaryKeyJoinColumn(name = "id_desenvolvedor", referencedColumnName="id_pessoa")
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
	
	
	
//	@ManyToMany
//	@JoinTable(name = "desenvolvedor_projeto", joinColumns = @JoinColumn(name = "id_desenvolvedor"), inverseJoinColumns = @JoinColumn(name = "id_projeto"))
	
	@ManyToMany(cascade={ CascadeType.ALL, CascadeType.MERGE })  
    @JoinTable(name="desenvolvedor_projeto",   
            joinColumns= @JoinColumn(name="desenvolvedor_id" , referencedColumnName="id_desenvolvedor"),   
            inverseJoinColumns = @JoinColumn(name="projeto_id" , referencedColumnName="id_projeto"))
	public List<Projeto> getListaProjetos() {
//		for(Projeto dev : listaProjetos){
//			System.out.println("lista de desenvolvedores" + dev.getNome());
//		}
		return listaProjetos;
	}
	
	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
}
