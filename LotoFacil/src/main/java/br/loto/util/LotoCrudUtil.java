package br.loto.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.loto.domain.Concurso;
import br.loto.hibernate.HibernateUtil;

public class LotoCrudUtil {

	public int ultimoConcurso() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List ultimo = session.createQuery("select max(numConcurso) from Concurso").list();

		session.close();

		// HibernateUtil.finalizar();
		if (ultimo.get(0) == null) {
			return 1;
		} else {
			return (Integer) ultimo.get(0);
		}

	}

	public List<Concurso> todosConcursos() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Concurso> lista = session.createQuery("from Concurso").list();

		session.close();

		// HibernateUtil.finalizar();

		return lista;

	}

	public List<Concurso> existeConcurso(String jogo) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Concurso> lista = session.createQuery("from Concurso where dezenas = '" + jogo + "'").list();

		session.close();
		return lista;

		// HibernateUtil.finalizar();

	}
}
