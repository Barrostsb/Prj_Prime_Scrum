package com.barrostsb.prime_scrum.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "projeto")
public class Projeto {
	private static final String NAO_INCICADO = "Não Iniciado";
	
	private int id_projeto;	
	private ScrumMaster scrumMaster;
	private Cliente cliente;
	private List<Desenvolvedor> listaDesenvolvedores;

	private String nome;
//	private List<Sprint> listaSprint;
//	private List<Historia> listaHistoria;
	private Date dataInicio;
	private String status = NAO_INCICADO;

	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
//	
	
	
	
//	@ManyToMany(mappedBy="listaProjetos", fetch=FetchType.LAZY)
	
	@ManyToMany(cascade={ CascadeType.ALL, CascadeType.MERGE })  
    @JoinTable(name="desenvolvedor_projeto",   
    		joinColumns = @JoinColumn(name="projeto_id" , referencedColumnName="id_projeto"),
    		inverseJoinColumns = @JoinColumn(name="desenvolvedor_id" , referencedColumnName="id_desenvolvedor"))  
	public List<Desenvolvedor> getListaDesenvolvedores() {
		return listaDesenvolvedores;
	}
	public void setListaDesenvolvedores(List<Desenvolvedor> listaDesenvolvedores) {
		this.listaDesenvolvedores = listaDesenvolvedores;
	}
	
	@ManyToOne
	@JoinColumn(name = "scrumMaster", referencedColumnName="id_scrumMaster")
	public ScrumMaster getScrumMaster() {
		return scrumMaster;
	}
	
	public void setScrumMaster(ScrumMaster scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
	
	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName="id_cliente")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Id
	@GeneratedValue
	public int getId_projeto() {
		return id_projeto;
	}
	public void setId_projeto(int id_projeto) {
		this.id_projeto = id_projeto;
	}
	
	@Column
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
//	@OneToMany(mappedBy = "projeto", fetch=FetchType.LAZY)
//	public List<Sprint> getListaSprint() {
//		return listaSprint;
//	}
//	
//	public void setListaSprint(List<Sprint> listaSprint) {
//		this.listaSprint = listaSprint;
//	}
	
//	@OneToMany(mappedBy = "projeto", fetch=FetchType.LAZY)
//	public List<Historia> getListaHistoria() {
//		return listaHistoria;
//	}
//	public void setListaHistoria(List<Historia> listaHistoria) {
//		this.listaHistoria = listaHistoria;
//	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_projeto;
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
		Projeto other = (Projeto) obj;
		if (id_projeto != other.id_projeto)
			return false;
		return true;
	}
	
}
