package com.barrostsb.prime_scrum.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.repository.Pessoas;

@ManagedBean (name = "loginController")
public class LoginController{
  
    private String username;        
    private String password;
    private Pessoa usuarioLogado;
      
    public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public void login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
          
//        if(username != null  && username.equals("barrostsb") && password != null  && password.equals("barrostsb")) {  
//            loggedIn = true;  
//            try {
//				FacesContext.getCurrentInstance().getExternalContext().redirect("restrict/home.jsf");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);  
//        } else {  
//            loggedIn = false;  
//            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");  
//        }  
        EntityManager manager = JpaUtils.getEntityManager();
        Pessoas pessoas = new Pessoas(manager);
        if(username != null  && password != null ) {
        	if(pessoas.todos().contains(pessoas.PessoaPorLogin(username))){
        		setUsuarioLogado(pessoas.PessoaPorLogin(username));
        		if (password.equals(usuarioLogado.getSenha())){
        			
        			switch (usuarioLogado.getPermissao()) {
					case "ROLE_ADM":
						try {
	                		FacesContext.getCurrentInstance().getExternalContext().redirect("restrict/home.jsf");
	                	} catch (IOException e) {
	                		e.printStackTrace();
	                	}
						break;
					case "ROLE_DEV":
						try {
							FacesContext.getCurrentInstance().getExternalContext().redirect("restrict/desenvolvedor/home.jsf");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case "ROLE_CLIENTE":
						try {
							FacesContext.getCurrentInstance().getExternalContext().redirect("restrict/cliente/home.jsf");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;

					default:
						break;
					}
        		
        		}
        	} 
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);  
        } else {  
        	loggedIn = false;  
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"); 
        }
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);  
    }  
}