package App;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;

/**
 * 
 * @author cleefsouza
 *
 */

public class ProdutosXpath {
	public static void fazerConsulta() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("c:\\xml\\produtos.xml");
			XPath xp = XPathFactory.newInstance().newXPath();

			// O argumento de compile é o caminho do Xpath a ser procurado
			NodeList nl = (NodeList) xp.compile("//produto[preco > 3]").evaluate(doc, XPathConstants.NODESET);
			System.out.println("Número de Produtos: " + nl.getLength());
			System.out.println("Lista de Produtos");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.println("Id: " + xp.compile("./@id").evaluate(nl.item(i)));
				System.out.println("Nome: " + xp.compile("./nome").evaluate(nl.item(i)));
				System.out.println("Preço: " + xp.compile("./preco").evaluate(nl.item(i)));
				System.out.println("=========");
			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
