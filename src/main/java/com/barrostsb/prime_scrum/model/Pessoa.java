package com.barrostsb.prime_scrum.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table (name = "pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id_pessoa;
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pessoa", nullable = false)
	
	public int getId_pessoa() {
		return id_pessoa;
	}
	
	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	
	@Column
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_pessoa ^ (id_pessoa >>> 32));
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
		Pessoa other = (Pessoa) obj;
		if (id_pessoa != other.id_pessoa)
			return false;
		return true;
	}
}
