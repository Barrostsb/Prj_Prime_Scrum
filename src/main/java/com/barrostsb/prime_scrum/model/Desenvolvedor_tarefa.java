package com.barrostsb.prime_scrum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.barrostsb.prime_scrum.EntitiesPKs.DevTaskVal_PK;


@Entity(name = "desenvolvedor_tarefa")
@IdClass(DevTaskVal_PK.class)
public class Desenvolvedor_tarefa{
	
	@Id
	@Column(name="id_desenvolvedor",insertable=false, updatable=false)
	private int devId;
	@Id
	@Column(name="id_tarefa",insertable=false,  updatable=false)
	private int tarefaId;
	@Column(name = "tempo_execucao")
	private float tempo_execucao;
	@Column(name = "dificuldade")
	private int dificuldade;
	@Column(name = "prioridade")
	private int prioridade;
	
	private String TarefaNome;
	private String DevNome;


	public String getTarefaNome() {
		return TarefaNome;
	}

	public void setTarefaNome(String tarefaNome) {
		TarefaNome = tarefaNome;
	}

	public String getDevNome() {
		return DevNome;
	}

	public void setDevNome(String devNome) {
		DevNome = devNome;
	}

	public float getTempo_execucao() {
		return tempo_execucao;
	}

	public void setTempo_execucao(float tempo_execucao) {
		this.tempo_execucao = tempo_execucao;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getDevId() {
		return devId;
	}

	public void setDevId(int devId) {
		this.devId = devId;
	}

	public int getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(int tarefaId) {
		this.tarefaId = tarefaId;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
}
