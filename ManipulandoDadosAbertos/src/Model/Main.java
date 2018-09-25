package Model;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/*
 * @author cleefsouza 
 */

public class Main {
	public static void main(String[] args) {
		// Lendo o arquivo xml
		File arq = new File("/home/cleefsouza/Downloads/CONDEPEFIDEN_ParticipacaoPIBMunicipiosPE2009_metadado.xml");

		// Classe para processar o xml
		SAXBuilder sax_b = new SAXBuilder();

		// Criando documento que possui toda a estrutura do arquivo
		Document doc = null;
		try {
			doc = sax_b.build(arq);
		} catch (JDOMException jodm_ex) {
			System.err.println("Erro: " + jodm_ex);
		} catch (IOException io_ex) {
			System.err.println("Erro: " + io_ex);
		}

		// Recuperando o elemento raiz
		Element raiz = doc.getRootElement();

		// Recuperando os filhos da raiz e inserindo num objeto List
		List elementos = raiz.getChildren();

		// Elementos na posição 1 da lista [Acessando o corpo]
		Element elemento = (Element) elementos.get(1);

		// Filhos do objeto 'elementos' sendo guardados no objeto elemento
		elementos = elemento.getChildren();

		// Guardando os elementos da lista 'elementos' da posição 1 'elementos'
		elemento = (Element) elementos.get(1);

		// Atribuindo os filhos de elemento na lista 'elementos'
		elementos = elemento.getChildren();

		// Salvando iterações de 'elementos'
		Iterator ite = elementos.iterator();

		// Exibindo informações
		while (ite.hasNext()) {
			elemento = (Element) ite.next();
			System.out.println(elemento.getAttributeValue("campo1")+""
					+ " - "+elemento.getAttributeValue("campo2"));
		}

	}
}
