package com.barrostsb.prime_scrum.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.model.Cliente;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.repository.Pessoas;

@FacesConverter(value="clienteConverter" , forClass = Pessoa.class)
public class ClienteConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context,	UIComponent component, String value) {
		Pessoa retorno = null;
		EntityManager manager = JpaUtils.getEntityManager();
		try {
			if (value != null) {
				Pessoas pessoas = new Pessoas(manager);
				retorno = (Cliente)pessoas.PessoaPorLogin(value);
			}
			return retorno;
		} finally {
			manager.close();
		}
	}
	@Override
	public String getAsString(FacesContext context,	UIComponent component, Object value) {
		if (value != null) {
			return ((Pessoa) value).getLogin().toString();
		}
		return null;
	}
}
