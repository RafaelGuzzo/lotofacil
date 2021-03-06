package br.loto.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.loto.domain.Concurso;
import br.loto.hibernate.HibernateUtil;
import br.loto.util.LotoCrudUtil;

public class LotoPrincipalH2 {

	private static Document site;
	private static Elements numeros;
	private static String path = ".//src//main//resources//jogoslotofacil.txt";

	public static void main(String[] args) throws TimeoutException {

		LotoCrudUtil util = new LotoCrudUtil();
		int ulConBD = util.ultimoConcurso(); // ultimo concurso do banco
		int numConc = retornaUltimoConcurso(); // ultimo concurso localizado no site

		Session session = HibernateUtil.getSessionFactory().openSession();
		if (ulConBD == 1) {
			numConc = retornaUltimoConcurso();
		} else {
			ulConBD += 1;
		}

		System.out.println(
				"Ultimo concurso do banco = " + (ulConBD - 1) + " ultimo concurso encontrado no site = " + numConc);

		for (int i = ulConBD; i <= numConc; i++) {

			try {

				repete(i, session);

			} catch (IOException e) {
				e.printStackTrace();

			}
			System.out.println("i = " + i);
		}

		

		/*
		 * List<Concurso> todosConc = util.todosConcursos(); for (Concurso concurso :
		 * todosConc) { System.out.println(concurso); }
		 */
		System.out.println("finalizado");
		session.close();
		HibernateUtil.finalizar();

	}

	private static void repete(int numConc, Session session) throws IOException {

		site = Jsoup.connect("https://www.netsorte.com.br/lotofacil/" + numConc + "#APLICATIVO").timeout(30 * 1000)
				.get();

		numeros = site.select("div[id=resultado1]");

		Elements a = null;

		for (Element element : numeros) {
			a = element.select("td[class=col_dezena]");
		}

		Concurso concurso = new Concurso();
		concurso.setNumConcurso(numConc);
		concurso.setId(numConc);

		String dezenas = "";
		int contador = 0;

		for (Element el : a) {
			contador++;

			if (Integer.parseInt(el.text().trim()) <= 9 && contador < 15) {
				dezenas = dezenas + ("0" + el.text().trim() + ",");
			} else if (contador < 15) {
				dezenas = dezenas + (el.text().trim() + ",");
			} else {
				dezenas = dezenas + (el.text().trim());
			}

		}

		concurso.setDezenas(dezenas);
		//System.out.println("String de dezenas " + dezenas);
		session.beginTransaction();

		String gravar = ("Inserido id " + concurso.getId() + " numCon " + concurso.getNumConcurso() + " dez "
				+ concurso.getDezenas());

		System.out.println(gravar);

		escrever(path, gravar);

		session.save(concurso);
		session.getTransaction().commit();
		// session.close();
	}

	private static int retornaUltimoConcurso() {
		Document doc;
		String numConcurso = "";

		try {
			doc = Jsoup.connect("https://www.netsorte.com.br/lotofacil").timeout(30 * 1000).get();
			Elements a = doc.select("div[id=labelNumeroConcurso]");

			numConcurso = a.text().trim();

		} catch (IOException e) {
			e.printStackTrace();
		}
		int numConc = Integer.parseInt(numConcurso);
		return numConc;
	}

	public static void escrever(String path, String texto) {
		try {
			// O parametro é que indica se deve sobrescrever ou continua no
			// arquivo.
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(texto);
			conexao.newLine();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
