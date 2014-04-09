package com.barrostsb.prime_scrum.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.barrostsb.prime_scrum.EntitiesPKs.DevTaskVal_PK;

/**
 * http://depressedprogrammer.wordpress.com/2007/02/23/additional-columns-in-a-manytomany-mapping-using-java-persistence-api-toplink/
 */

//@Entity(name = "Desenvolvedor_tarefa_valor")
//@IdClass(DevTaskVal_PK.class)
public class Desenvolvedor_tarefa_valores {
	  @Id
	  @Column(name="devId",insertable=false, updatable=false)
	  private int devId;

	  @Id
	  @Column(name="tarefaId",insertable=false,  updatable=false)
	  private int tarefaId;
	  
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name="devId")
	  private Desenvolvedor desenvolvedor;

	  @ManyToOne
	  @JoinColumn(name="tarefaId")
	  private Tarefa tarefa;

	  @Column(name="ENABLED")
	  private boolean enabled;
	  
	  @Column(name = "prioridade")
	  private int prioridade;
	  @Column(name = "tempo_execucao")
	  private float tempo_execucao;
	  @Column(name = "dificuldade")
	  private int dificuldade;

}
