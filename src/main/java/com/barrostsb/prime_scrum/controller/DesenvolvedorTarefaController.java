package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RateEvent;
import org.primefaces.event.TabChangeEvent;

import com.barrostsb.prime_scrum.JpaUtils.JpaUtils;
import com.barrostsb.prime_scrum.business.CadastroDevTarefa;
import com.barrostsb.prime_scrum.business.CadastroTarefas;
import com.barrostsb.prime_scrum.exception.BusinessException;
import com.barrostsb.prime_scrum.model.Desenvolvedor;
import com.barrostsb.prime_scrum.model.Desenvolvedor_tarefa;
import com.barrostsb.prime_scrum.model.Pessoa;
import com.barrostsb.prime_scrum.model.Projeto;
import com.barrostsb.prime_scrum.model.Tarefa;
import com.barrostsb.prime_scrum.repository.DevTarefas;
import com.barrostsb.prime_scrum.repository.Projetos;
import com.barrostsb.prime_scrum.repository.Tarefas;
import com.barrotsb.prime_scrum.facesUtils.FacesUtil;

@ManagedBean(name = "desenvolvedorTarefaController")
@ViewScoped
public class DesenvolvedorTarefaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Desenvolvedor_tarefa devTarefa = new Desenvolvedor_tarefa();
	private List<Tarefa> tarefasBuscadas = null;

	public DesenvolvedorTarefaController() {  
		tarefasBuscadas = new ArrayList<Tarefa>();
	} 

	private List<Projeto> todosProjetos = null;

	EntityManager manager = JpaUtils.getEntityManager();

	public List<Projeto> getTodosProjetos() {
		return todosProjetos;
	}

	public void setTodosProjetos(List<Projeto> todosProjetos) {
		this.todosProjetos = todosProjetos;
	}

	private Tarefa tarefaSelecionada;


	public void prepararCadastro() {
		EntityManager manager = JpaUtils.getEntityManager();
		try {
			Projetos projetos = new Projetos(manager);
			this.todosProjetos = projetos.todos();
		} finally {
			manager.close();
		}
	}

	public void salvar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			CadastroDevTarefa cadastro = new CadastroDevTarefa(new DevTarefas(manager));
			cadastro.salvar(this.devTarefa);
			this.devTarefa = new Desenvolvedor_tarefa();			
			trx.commit();
			context.addMessage(null, new FacesMessage("Tarefa salva com sucesso!"));
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}

	public void alterar() {
		EntityManager manager = JpaUtils.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroDevTarefa cadastro = new CadastroDevTarefa(new DevTarefas(manager));
			cadastro.alterar(this.devTarefa);
			trx.commit();
			this.devTarefa = new Desenvolvedor_tarefa();			
			context.addMessage(null, new FacesMessage("Tarefa alterada com sucesso!"));
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void salvarDados(ActionEvent event){
		Pessoa usuario;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		usuario = (Pessoa) session.getAttribute("usuarioLogado");
		
		Tarefa tarefaSel = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaSelecionada");
//		Desenvolvedor tarefaSelDev = (Desenvolvedor) FacesUtil.getActionAttribute(event, "usuarioLogado");
//		Desenvolvedor tarefaSelDev = (Desenvolvedor) FacesUtil.getActionAttribute(event, "tarefaSelecionadaDev");
//		devTarefa.setTarefa(tarefaSel);
//		devTarefa.setDesenvolvedor(tarefaSelDev);
		
//		devTarefa.setDesenvolvedor((Desenvolvedor) usuario);
		
		devTarefa.setTarefaId(tarefaSel.getId_tarefa());
		devTarefa.setTarefaNome(tarefaSel.getNome());
		devTarefa.setDevId(usuario.getId_pessoa());
		devTarefa.setDevNome(usuario.getLogin());
		System.out.println("DADOS: " );
		//System.out.println("dev: " + devTarefa.getDesenvolvedor().getNome());
		//System.out.println("tarefe: " +devTarefa.getTarefa().getNome());
		System.out.println("Dificuldade: " +devTarefa.getDificuldade());
		System.out.println("priori: "+devTarefa.getPrioridade() );
		System.out.println("tempo: " +devTarefa.getTempo_execucao());
		
		alterar();
	}

	public void deletar(ActionEvent event) {
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		Tarefa tarefaSelecionada = (Tarefa) FacesUtil.getActionAttribute(event, "tarefaSelecionada");

		try {
			trx.begin();
			CadastroTarefas cadastro = new CadastroTarefas(new Tarefas(manager));
			cadastro.deletar(tarefaSelecionada);
			context.addMessage(null, new FacesMessage("Tarefa excluído com sucesso!"));
			trx.commit();
		} catch (BusinessException e) {
			trx.rollback();
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		} 
	}

	public void buscarTodasTarefa() {

		Tarefas tarefas = new Tarefas(manager);
		tarefas.todos();
	}


	public String clear(){
		devTarefa = new Desenvolvedor_tarefa();
		return "/restrict/desenvolvedor/CadastrarProjeto.jsf"; 
	}


	public List<Tarefa> getTodasTarefas() {
		EntityManager maneger = JpaUtils.getEntityManager();
		tarefasBuscadas = maneger.createQuery("FROM Tarefa", Tarefa.class).getResultList();
		return tarefasBuscadas;
	}

	public List<Tarefa> getTarefaPorProjeto() {
		EntityManager maneger = JpaUtils.getEntityManager();
		tarefasBuscadas = maneger.createQuery("FROM Tarefa where id_projeto = :id_proj ", Tarefa.class)
				.setParameter("id_proj", getProjetoSelecionado().getId_projeto())
				.getResultList();
		return tarefasBuscadas;
	}

	private Projeto getProjetoSelecionado() {
		Projeto projeto;
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		projeto = (Projeto) session.getAttribute("projetoSelecionado");
		return projeto;
	}

	public Tarefa getTarefaSelecionada() {
//		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpSession session = (HttpSession) request.getSession();
//		tarefaSelecionada = (Tarefa) session.getAttribute("tarefaSelecionada");
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//		session.setAttribute("tarefaSelecionada", tarefaSelecionada);
		this.tarefaSelecionada = tarefaSelecionada;
	}

	public void setTodasTarefas(List<Tarefa> tarefaBuscadas) {
		this.tarefasBuscadas = tarefaBuscadas;
	}

	public Desenvolvedor_tarefa getTarefa() {

		return devTarefa;
	}

	public void setTarefa(Desenvolvedor_tarefa tarefa) {
		this.devTarefa = tarefa;
	}
}

