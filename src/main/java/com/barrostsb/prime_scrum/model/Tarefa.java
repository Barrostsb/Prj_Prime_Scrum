package com.barrostsb.prime_scrum.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.barrostsb.prime_scrum.jpautils.TarefaPK;

@Entity
@Table (name = "tarefa")
public class Tarefa {
	private Projeto projeto;
	private Sprint sprint;
	private Historia historia;
	private int id_tarefa;
	private Desenvolvedor desenvolvedor;
	private String nome;
	private int prioridade;
	private float tempo_execucao;
	private int dificuldade;
	private TarefaPK tarefapk;
	
	@ManyToOne
	@JoinColumn(name="id_projeto")
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@Id
	@EmbeddedId
	public TarefaPK getTarefapk() {
		return tarefapk;
	}

	public void setTarefapk(TarefaPK tarefapk) {
		this.tarefapk = tarefapk;
	}

	@Column
	public Sprint getSprint() {
		return sprint;
	}
	
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	@ManyToOne
	@JoinColumn(name="id_historia")
	public Historia getHistoria() {
		return historia;
	}
	
	public void setHistoria(Historia historia) {
		this.historia = historia;
	}
	
	public int getId_tarefa() {
		return id_tarefa;
	}
	
	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_desenvolvedor", referencedColumnName="id_pessoa")
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
