package com.barrostsb.prime_scrum.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;


@Entity
@Table (name = "desenvolvedor")
@PrimaryKeyJoinColumn(name = "id_desenvolvedor", referencedColumnName="id_pessoa")
@ForeignKey(name = "FK_PessoaDesenvolvedor")
public class Desenvolvedor extends Pessoa{
		
	private static final long serialVersionUID = 1L;

	private List<Tarefa> listaTarefas;
	private List<Projeto> listaProjetos;

	public Desenvolvedor() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(	mappedBy="desenvolvedor", 
				fetch=FetchType.LAZY)
	@ForeignKey(name = "FK_DesenvolvedorTarefa")
	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}
	
	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
	
	@ManyToMany
	@JoinTable(	name = "desenvolvedor_projeto", joinColumns = @JoinColumn(name = "id_desenvolvedor"), inverseJoinColumns = @JoinColumn(name = "id_projeto"))
	@ForeignKey(name = "FK_DesenvolvedorProjeto")
	public List<Projeto> getListaProjetos() {
		return listaProjetos;
	}
	
	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
}
