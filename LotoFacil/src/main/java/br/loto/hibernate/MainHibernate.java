package br.loto.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.loto.domain.Concurso;

public class MainHibernate {

	public static void main(String[] args) {
		Concurso user = new Concurso(1, 1, null);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from Concurso").list();
		for (Concurso event : (List<Concurso>) result) {
			System.out.println(event);
		}
		session.getTransaction().commit();
		session.close();
	}

}
