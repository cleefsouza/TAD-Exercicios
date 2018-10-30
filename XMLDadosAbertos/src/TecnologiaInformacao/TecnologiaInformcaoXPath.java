package TecnologiaInformacao;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.xpath.*;

/**
 * 
 * @author cleefsouza
 *
 */

public class TecnologiaInformcaoXPath {

	static Scanner ent;

	// Construtor
	public TecnologiaInformcaoXPath() {

	}

	// Métodos
	public static void fazerConsulta() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse("c:\\xml\\tecnologiainformacao.xml");

			XPath xp = XPathFactory.newInstance().newXPath();

			while (true) {
				ent = new Scanner(System.in);
				System.out.println(
						"------------------------------------\nServiços de Tecnologia da Informação\n------------------------------------");
				System.out.print(
						"1 - Valores Gerais\n2 - Consultar por Código do Serviço\n3 - Unidade < 500\n4 - Sair\n>>> ");
				String opc = ent.next();

				switch (opc) {
				case "1":
					NodeList n1 = (NodeList) xp.compile("//ValorDescritoPorSuasDimensoes").evaluate(doc,
							XPathConstants.NODESET);

					if (n1.getLength() == 0) {
						System.err.println("Nenhum Valor Encontrado!");
					} else {
						System.out.println("Lista de Valores");
						for (int i = 0; i < n1.getLength(); i++) {
							System.out.println("------------------------------------");
							System.out.println("Código Brasil: " + xp.compile("./D1C").evaluate(n1.item(i)) + "\n"
									+ "Brasil: " + xp.compile("./DN1").evaluate(n1.item(i)) + "\n" + "Código Variável: "
									+ xp.compile("./D2C").evaluate(n1.item(i)) + "\n" + "Varíavel: "
									+ xp.compile("./D2N").evaluate(n1.item(i)) + "\n" + "Código Ano: "
									+ xp.compile("./D3C").evaluate(n1.item(i)) + "\n" + "Ano: "
									+ xp.compile("./D3N").evaluate(n1.item(i)) + "\n" + "Código Produto ou Serviço: "
									+ xp.compile("./D4C").evaluate(n1.item(i)) + "\n" + "Produto ou Serviço: "
									+ xp.compile("./D4N").evaluate(n1.item(i)) + "\n" + "Código Unidade de Medida: "
									+ xp.compile("./MC").evaluate(n1.item(i)) + "\n" + "Unidade de Medida: "
									+ xp.compile("./MN").evaluate(n1.item(i)) + "\n" + "Valor: "
									+ xp.compile("./V").evaluate(n1.item(i)));
						}
					}
					break;

				case "2":
					System.out.print("2.1 - Código do Produto ou Serviço >>> ");
					String cod = ent.next();
					NodeList n2 = (NodeList) xp.compile("//ValorDescritoPorSuasDimensoes[D4C = " + cod + "]")
							.evaluate(doc, XPathConstants.NODESET);

					if (n2.getLength() == 0) {
						System.err.println("Nenhum Valor Encontrado!");
					} else {
						System.out.println("Lista de Valores");
						for (int i = 0; i < n2.getLength(); i++) {
							System.out.println("------------------------------------");
							System.out.println("Código Produto ou Serviço: " + xp.compile("./D4C").evaluate(n2.item(i))
									+ "\n" + "Produto ou Serviço: " + xp.compile("./D4N").evaluate(n2.item(i)) + "\n"
									+ "Código Unidade de Medida: " + xp.compile("./MC").evaluate(n2.item(i)) + "\n"
									+ "Unidade de Medida: " + xp.compile("./MN").evaluate(n2.item(i)) + "\n" + "Valor: "
									+ xp.compile("./V").evaluate(n2.item(i)));
						}
					}
					break;
				case "3":
					NodeList n3 = (NodeList) xp.compile("//ValorDescritoPorSuasDimensoes[V < 500]").evaluate(doc,
							XPathConstants.NODESET);

					if (n3.getLength() == 0) {
						System.err.println("Nenhum Valor Encontrado!");
					} else {
						System.out.println("Lista de Valores");
						for (int i = 0; i < n3.getLength(); i++) {
							System.out.println("------------------------------------");
							System.out.println("Código Produto ou Serviço: " + xp.compile("./D4C").evaluate(n3.item(i))
									+ "\n" + "Produto ou Serviço: " + xp.compile("./D4N").evaluate(n3.item(i)) + "\n"
									+ "Código Unidade de Medida: " + xp.compile("./MC").evaluate(n3.item(i)) + "\n"
									+ "Unidade de Medida: " + xp.compile("./MN").evaluate(n3.item(i)) + "\n" + "Valor: "
									+ xp.compile("./V").evaluate(n3.item(i)));
						}
					}
					break;

				case "4":
					System.err.println("Encerrando!");
					return;
				default:
					System.err.println("Opção Inválida!");
					break;
				}
			}

		} catch (ParserConfigurationException e) {
			System.err.println("Erro: " + e.getMessage());
		} catch (SAXException | IOException e) {
			System.err.println("Erro: " + e.getMessage());
		} catch (XPathExpressionException e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}
}