package com.barrostsb.prime_scrum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table (name = "tarefa")
public class Tarefa {
	private Projeto projeto;
	private int id_tarefa;
	private Desenvolvedor desenvolvedor;
	private String nome;
	private String tskBrdDesc;
	private Date dataTermino;
	private int prioridade;
	private float tempo_execucao;
	private int dificuldade;
	
	@ManyToOne
	@JoinColumn(name = "id_desenvolvedor", referencedColumnName="id_desenvolvedor", nullable= true)
	@ForeignKey(name="FK_DesenvolvedorTarefa")
	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}
	
	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}
	
	@Id
	@GeneratedValue
	public int getId_tarefa() {
		return id_tarefa;
	}
	
	public void setId_tarefa(int id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	
	@ManyToOne
	@JoinColumn(name="id_projeto")
	@ForeignKey(name="FK_Projeto_tarefa")
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
	
	@Column	
	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Tarefa(){
		projeto = new Projeto();
		tskBrdDesc = "todo";
	}
	
	@Column	
	public String getTskBrdDesc() {
		return tskBrdDesc;
	}

	public void setTskBrdDesc(String tskBrdDesc) {
		this.tskBrdDesc = tskBrdDesc;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_tarefa;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id_tarefa != other.id_tarefa)
			return false;
		return true;
	}
}
