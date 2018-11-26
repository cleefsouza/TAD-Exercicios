package view;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.Repository;
import model.Paciente;

/**
 * 
 * @author cleefsouza
 *
 */
public class Aplicacao {
	public static void main(String[] args) {
		Repository rp = new Repository();
		menu(rp);
	}

	/*
	 * métodos
	 */
	private static void menu(Repository rp) {
		System.out.println("__________________________\n");
		System.out.println(" CRUD - Paciente");
		System.out.println("__________________________");
		System.out.println("\nMenu\n");
		System.out.println("1 - Inserir Paciente");
		System.out.println("2 - Listagem de Pacientes");
		System.out.println("3 - Atualizar Paciente");
		System.out.println("4 - Remover Paciente");
		System.out.println("5 - Sair");
		System.out.print(">>> ");

		Scanner scn = new Scanner(System.in);
		String opc = scn.next();

		switch (opc) {
		case "1":
			criar(rp);
			break;
		case "2":
			listar(rp);
			break;
		case "3":
			atualizar(rp);
			break;
		case "4":
			remover(rp);
			break;
		case "5":
			sair(rp);
			break;
		default:
			System.err.println("Opção inválida!");
			menu(rp);
		}
	}

	private static void sair(Repository rp) {
		System.err.println("Encerrando!");
		rp.getGerente().close();
		System.exit(0);
	}

	/*
	 * crud
	 */
	private static void criar(Repository rp) {
		Paciente paciente = new Paciente();
		Scanner scn = new Scanner(System.in);

		System.out.print("Nome: ");
		paciente.setNome(scn.nextLine());

		System.out.print("Idade: ");
		paciente.setIdade(scn.nextInt());

		rp.create(paciente);
		System.out.println("Paciente salvo!");
		menu(rp);
	}

	private static void listar(Repository rp) {
		ArrayList<Paciente> pacientes = rp.listAll();

		System.out.println("__________________________\n");
		for (Paciente p : pacientes) {
			System.out.println("Id: " + p.getId());
			System.out.println("Nome: " + p.getNome());
			System.out.println("Idade: " + p.getIdade());
			System.out.println("__________________________\n");
		}

		System.out.println("Listagem completa!");
		menu(rp);
	}

	private static void atualizar(Repository rp) {
		Scanner scn = new Scanner(System.in);

		System.out.print("Digite o id do paciente que deseja atualizar: ");
		int id = scn.nextInt();
		scn.nextLine();

		Paciente paciente = rp.findForId(id);

		System.out.print("Nome: ");
		paciente.setNome(scn.nextLine());

		System.out.print("Idade: ");
		paciente.setIdade(scn.nextInt());

		rp.update(paciente);
		System.out.println("Paciente atualizado!");
		menu(rp);
	}

	private static void remover(Repository rp) {
		System.out.print("Digite o id do paciente que deseja remover: ");
		Scanner scn = new Scanner(System.in);

		int id = scn.nextInt();
		Paciente paciente = rp.findForId(id);

		rp.remove(paciente);
		System.out.println("Paciente removido!");
		menu(rp);
	}
}
