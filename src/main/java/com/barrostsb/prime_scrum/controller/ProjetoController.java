package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroProjetos;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Cliente;
import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.ScrumMaster;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.Projetos;
import com.barrotsb.prime_scrum.facesUtils.FacesUtil;

@ManagedBean(name = "projetoController")
@ApplicationScoped
public class ProjetoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Projeto projeto = new Projeto();
	private List<Projeto> projetosBuscados = null;
	private List<Desenvolvedor> desenvolvedores;
	private List<Cliente> clientes;

	private Projeto projetoSelecionado;

	EntityManager manager = JpaUtils.getEntityManager();

	public void salvar() {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroProjetos cadastro = new CadastroProjetos(new Projetos(manager));
			
			Pessoa pessoa = getUsuarioLogado();
			projeto.setScrumMaster((ScrumMaster) pessoa);
			
			cadastro.salvar(this.projeto);
			this.projeto = new Projeto();			
			context.addMessage(null, new FacesMessage("Projeto salvo com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}

	private Pessoa getUsuarioLogado() {
		Pessoa pessoa;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		System.out.println("USUARIO LOGADO:  "+pessoa.getLogin());
		return pessoa;
	}
	
	public void alterar() {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			CadastroProjetos cadastro = new CadastroProjetos(new Projetos(manager));
			cadastro.alterar(projetoSelecionado);
			//this.projeto = new Projeto();			
			context.addMessage(null, new FacesMessage("Projeto alterado com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}
	public void deletar(ActionEvent event) {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		Projeto projetoSelecionado = (Projeto) FacesUtil.getActionAttribute(event, "projetoSelecionado");
		
		try {
			trx.begin();
			CadastroProjetos cadastro = new CadastroProjetos(new Projetos(manager));
			cadastro.deletar(projetoSelecionado);
			context.addMessage(null, new FacesMessage("Projeto excluído com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}

	public void buscarTodosProjetos() {
		Projetos prjs = new Projetos(manager);
		prjs.todos();

		//		EntityManager manager = JpaUtils.getEntityManager();
		//		TypedQuery<Projeto> query = manager.createQuery("from Projeto", Projeto.class);
		//		this.projetosBuscados = query.getResultList();
		//		manager.close();

		//		EntityManager maneger = JpaUtils.getEntityManager();
		//		try {
		//			projetosBuscados = maneger.createQuery("FROM Projeto").getResultList();
		//		} finally {
		//			maneger.close();
		//		}
	}
	
	public List<Projeto> getProjetosPorSM(){
		Projetos prjs = new Projetos(manager);
		projetosBuscados = prjs.projetosPorSM(getUsuarioLogado().getId_pessoa());
		return projetosBuscados;
	}
	
	public List<Projeto> getProjetosPorCliente(){
		Projetos prjs = new Projetos(manager);
		projetosBuscados = prjs.projetosPorCliente(getUsuarioLogado().getId_pessoa());
		return projetosBuscados;
	}
	

	public String clear(){
		projeto = new Projeto();
		return "/restrict/CadastrarProjeto.jsf"; 
	}


	public List<Projeto> getProjetosBuscados() {
//		EntityManager maneger = JpaUtils.getEntityManager();
//		try {
			projetosBuscados = manager.createQuery("FROM Projeto", Projeto.class).getResultList();
//			TypedQuery<Projeto> query = maneger.createQuery("from Projeto", Projeto.class);
//			projetosBuscados = query.getResultList();
////			manager.close();
//		} finally {
//			maneger.close();
//		}
		return projetosBuscados;
	}

	public void setProjetosBuscados(List<Projeto> projetosBuscados) {
		this.projetosBuscados = projetosBuscados;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("projetoSelecionado", projetoSelecionado);
	}
	
	public List<Desenvolvedor> getTodosDesenvolvedores() {
		EntityManager maneger = JpaUtils.getEntityManager();
		desenvolvedores = maneger.createQuery("FROM Desenvolvedor", Desenvolvedor.class).getResultList();
		return desenvolvedores;
	}
	
	public List<Cliente> getTodosClientes() {
		EntityManager maneger = JpaUtils.getEntityManager();
		clientes = maneger.createQuery("FROM Cliente", Cliente.class).getResultList();
		return clientes;
	}
	
	public List<Desenvolvedor> getDesenvolvedorPorProjeto() {
		EntityManager maneger = JpaUtils.getEntityManager();
		desenvolvedores = maneger.createQuery("FROM Desenvolvedor where id_projeto = :id_proj ", Desenvolvedor.class)
				.setParameter("id_proj", getProjetoSelecionado().getId_projeto())
				.getResultList();
		return desenvolvedores;
	}

}

