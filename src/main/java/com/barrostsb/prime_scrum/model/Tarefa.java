package com.barrostsb.prime_scrum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.barrostsb.prime_scrum.EntitiesPKs.TarefaPK;

@Entity
@Table (name = "tarefa")
@IdClass(value=TarefaPK.class)
public class Tarefa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id_tarefa;
	private Projeto projeto;
	
	private int sprint;
//	private int historia;
	
	private Desenvolvedor desenvolvedor;
	private String nome;
	private int prioridade;
	private float tempo_execucao;
	private int dificuldade;
	
	public Tarefa() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	public int getId_tarefa() {
		return id_tarefa;
	}
	
	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_projeto", referencedColumnName="id_projeto")
	@ForeignKey(name="FK_ProjetoTarefa")
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

//	@ManyToOne
//	@JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
//	@ForeignKey(name = "FK_SprintTarefa")
//	public int getSprint() {
//		return sprint;
//	}
//	
//	public void setSprint(int sprint) {
//		this.sprint = sprint;
//	}
	
//	@ManyToOne
//	@JoinColumn(name="id_historia", referencedColumnName="id_historia")
//	public int getHistoria() {
//		return historia;
//	}
//	
//	public void setHistoria(int historia) {
//		this.historia = historia;
//	}
	
	@ManyToOne
	@JoinColumn( name = "id_desenvolvedor", 
				 referencedColumnName="id_desenvolvedor")
	@ForeignKey(name = "FK_DesenvolvedorTarefa")
	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}
	
	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
	
	@Column
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	 @Column
	public int getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
	@Column
	public float getTempo_execucao() {
		return tempo_execucao;
	}
	
	public void setTempo_execucao(float tempo_execucao) {
		this.tempo_execucao = tempo_execucao;
	}
	
	@Column
	public int getDificuldade() {
		return dificuldade;
	}
	
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}
}
