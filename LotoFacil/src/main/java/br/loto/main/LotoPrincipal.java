package br.loto.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.loto.domain.Concurso;

public class LotoPrincipal {

	private static final String path = "C:\\Users\\Administrador\\Downloads\\todosjogoslotofacil_novo.txt";
	private static String dezenas = "";

	private static Document site;
	private static Elements numeros;

	public static void main(String[] args) throws TimeoutException {

		File file = new File(path);

		int numConc = retornaUltimoConcurso();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			while (numConc > 0) {
				repete(numConc, writer);

				numConc--;
			}

			System.out.println("finalizado");
		} catch (IOException e) {

		}

	}

	private static void repete(int numConc, BufferedWriter writer) throws IOException {

		site = Jsoup.connect("https://www.netsorte.com.br/lotofacil/" + numConc + "#APLICATIVO").get();
		numeros = site.select("div[id=resultado1]");
		
		Elements a = null;
		
		for (Element element : numeros) {
			a = element.select("td[class=col_dezena]");
		}

		
		Concurso concurso = new Concurso();
		concurso.setNumConcurso(numConc);
		
		ArrayList<String> listDezenas = new ArrayList<String>();
		
		int contador = 0;
		for (Element el : a) {
			contador++;

			if (contador < 15) {
				dezenas = dezenas + el.text().trim() + ",";
			} else {
				dezenas = dezenas + el.text().trim();
			}
			
			listDezenas.add(el.text().trim());

		}
		contador = 0;
		dezenas = numConc + " - " + dezenas;
		writer.write(dezenas);
		writer.newLine();
		writer.flush();

		dezenas = "";
		
		
		
		
		
		
		
	}

	private static int retornaUltimoConcurso() {
		Document doc;
		String numConcurso = "";

		try {
			doc = Jsoup.connect("https://www.netsorte.com.br/lotofacil").get();
			Elements a = doc.select("div[id=labelNumeroConcurso]");

			numConcurso = a.text().trim();

		} catch (IOException e) {
			e.printStackTrace();
		}
		int numConc = Integer.parseInt(numConcurso);
		return numConc;
	}

}
