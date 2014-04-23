package com.barrostsb.prime_scrum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.barrostsb.prime_scrum.EntitiesPKs.DevTaskVal_PK;
import com.sun.faces.facelets.util.DevTools;


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

//	@ManyToOne
//	@JoinColumn(name="devId")
//	private Desenvolvedor desenvolvedor;
//
//	@ManyToOne
//	@JoinColumn(name="tarefaId")
//	private Tarefa tarefa;
	//		  
	//
	//
	//
	//
//	public Desenvolvedor getDesenvolvedor() {
//		return desenvolvedor;
//	}
	//	
	//		public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
	//			this.desenvolvedor = desenvolvedor;
	//		}
//
//	public Tarefa getTarefa() {
//		return tarefa;
//	}
//	public void setTarefa(Tarefa tarefa) {
//		this.tarefa = tarefa;
//	}

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









///**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	//	@Id
////	@Column(name="devId",insertable=false, updatable=false)
////	private int devId;
////	@Id
////	@Column(name="tarefaId",insertable=false,  updatable=false)
////	private int tarefaId;
//	@Column(name = "tempo_execucao")
//	private float tempo_execucao;
//	@Column(name = "dificuldade")
//	private int dificuldade;
//	@Column(name = "prioridade")
//	private int prioridade;
//	
//	@Id
//	@ManyToOne (fetch = FetchType.LAZY)
////	@JoinColumn(name="devId")
//	@JoinColumn(name = "devId", referencedColumnName="id_desenvolvedor")
//	private Desenvolvedor desenvolvedor;
//	
//	@Id
//	@ManyToOne (fetch = FetchType.LAZY)
////	@JoinColumn(name="tarefaId")
//	@JoinColumn(name = "tarefaId", referencedColumnName="id_tarefa")
//	private Tarefa tarefa;
//	public Desenvolvedor getDesenvolvedor() {
//		return desenvolvedor;
//	}
//	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
//		this.desenvolvedor = desenvolvedor;
//	}
//	public Tarefa getTarefa() {
//		return tarefa;
//	}
//	public void setTarefa(Tarefa tarefa) {
//		this.tarefa = tarefa;
//	}
//	public float getTempo_execucao() {
//		return tempo_execucao;
//	}
//
//	public void setTempo_execucao(float tempo_execucao) {
//		this.tempo_execucao = tempo_execucao;
//	}
//
//	public int getDificuldade() {
//		return dificuldade;
//	}
//
//	public void setDificuldade(int dificuldade) {
//		this.dificuldade = dificuldade;
//	}
//
////	public int getDevId() {
////		return devId;
////	}
////
////	public void setDevId(int devId) {
////		this.devId = devId;
////	}
////
////	public int getTarefaId() {
////		return tarefaId;
////	}
////
////	public void setTarefaId(int tarefaId) {
////		this.tarefaId = tarefaId;
////	}
//
//	public int getPrioridade() {
//		return prioridade;
//	}
//
//	public void setPrioridade(int prioridade) {
//		this.prioridade = prioridade;
//	}

}
