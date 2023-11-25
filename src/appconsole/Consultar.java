package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Noticia;
import DAO_JPA.Util;
import modelo.Assunto;

public class Consultar {
	/*
	 * quais as noticias publicados na data X
	 * quais as noticias do assunto de nome X
	 * quais os assuntos que tem mais de N noticias
	 */
	protected ObjectContainer manager;

	public Consultar() {
		System.out.println("==================");
		System.out.println("Exibindo notícias da data 2023-07-20");
		System.out.println("==================");
		/* quais as noticias publicados na data X */
		manager = (ObjectContainer) Util.conectarBanco();
		Query q1 = manager.query();

		q1.constrain(Noticia.class);
		q1.descend("dataPublicacao").constrain("2023-07-20");
		List<Noticia> q1Resultado = q1.execute();
		for (Noticia n : q1Resultado) {
			System.out.println(n);
		}

		System.out.println("==================");
		System.out.println("Exibindo notícias do assunto trabalho");
		System.out.println("==================");
		/* quais as noticias do assunto de nome X */
		Query q2 = manager.query();
		q2.constrain(Assunto.class);
		q2.descend("nome").constrain("trabalho");
		Assunto q2Resultado = (Assunto) q2.execute().get(0);
		for (Noticia n : q2Resultado.getListaNoticia()) {
			System.out.println(n);
		}

		System.out.println("==================");
		System.out.println("Exibindo os assuntos que tem mais de 1 noticias");
		System.out.println("==================");

		/* quais os assuntos que tem mais de 1 noticias */
		Query q3 = manager.query();
		q3.constrain(Assunto.class);
		q3.constrain(new Filtro());
		List<Assunto> q3Resultado = q3.execute();

		for (Assunto a : q3Resultado) {
			System.out.println(a);
		}
		System.out.println("Fim das consultas");

		Util.fecharBanco();
	}

	class Filtro implements Evaluation {
		private static final long serialVersionUID = 1L;

		public void evaluate(Candidate candidate) {
			Assunto a = (Assunto) candidate.getObject();
			if (a.getListaNoticia().size() > 1) {
				candidate.include(true);
			} else
				candidate.include(false);
		}

	}

	public static void main(String[] args) {
		new Consultar();
	}
}
