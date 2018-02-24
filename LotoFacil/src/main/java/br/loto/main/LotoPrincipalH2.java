package br.loto.main;

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

public class LotoPrincipalH2 {

	private static Document site;
	private static Elements numeros;
	//private static String SQL_MAIOR_CON = "SELECT MAX(numConcurso) FROM concurso";
	private static String SQL_MAIOR_CON = "SELECT numConcurso FROM Concurso";
	private static Object object;

	public static void main(String[] args) throws TimeoutException {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(" from Concurso order by numConcurso desc");
		
		List list = query.list();
		
		//System.out.println(() list.get(0));
		
		Concurso concurso_ = (Concurso) list.get(0);
		
		System.out.println(concurso_.getNumConcurso());
		
		//System.out.println("ultimo concurso encontrado no banco " + result);
		
//		int numConc = retornaUltimoConcurso();
//
//		while (numConc > 0) {
//
//			try {
//
//				repete(numConc, sessionFactory);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			numConc--;
//		}

		//Session session = sessionFactory.openSession();

//		List result2 = session.createQuery("from Concurso").list();
//
//		for (Concurso event : (List<Concurso>) result2) {
//			System.out.println(event);
//		}
//		System.out.println("finalizado");

	}

	private static void repete(int numConc, SessionFactory sessionFactory) throws IOException {

		site = Jsoup.connect("https://www.netsorte.com.br/lotofacil/" + numConc + "#APLICATIVO").get();

		numeros = site.select("div[id=resultado1]");

		Elements a = null;

		for (Element element : numeros) {
			a = element.select("td[class=col_dezena]");
		}

		Concurso concurso = new Concurso();
		concurso.setNumConcurso(numConc);

		ArrayList<String> dezenas = new ArrayList<String>();

		for (Element el : a) {
			dezenas.add(el.text().trim());
		}

		concurso.setDezenas(dezenas);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(concurso);
		session.getTransaction().commit();
		session.close();
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
