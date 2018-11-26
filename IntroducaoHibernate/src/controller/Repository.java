package controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Paciente;

/**
 * 
 * @author cleefsouza
 *
 */
public class Repository {
	EntityManagerFactory fabrica;
	EntityManager gerente;

	public Repository() {
		fabrica = Persistence.createEntityManagerFactory("dbclinica");
		gerente = fabrica.createEntityManager();
	}

	public EntityManager getGerente() {
		return gerente;
	}

	// salvar no banco de dados
	public void create(Paciente p) {
		gerente.getTransaction().begin();
		gerente.persist(p);
		gerente.getTransaction().commit();
	}

	// buscar no banco de dados
	public Paciente findForId(int id) {
		gerente.getTransaction().begin();
		Paciente p = gerente.find(Paciente.class, id);
		gerente.getTransaction().commit();
		return p;
	}

	// listar todos do banco de dados
	public ArrayList<Paciente> listAll() {
		gerente.getTransaction().begin();
		Query consulta = gerente.createQuery("select paciente from Paciente paciente");
		ArrayList<Paciente> pacientes;
		pacientes = (ArrayList<Paciente>) consulta.getResultList();
		gerente.getTransaction().commit();
		return pacientes;
	}

	// atualizar no banco de dados
	public void update(Paciente p) {
		gerente.getTransaction().begin();
		gerente.merge(p);
		gerente.getTransaction().commit();
	}
	
	// remover do banco de dados
	public void remove(Paciente p) {
		gerente.getTransaction().begin();
		gerente.remove(p);
		gerente.getTransaction().commit();
	}
}
