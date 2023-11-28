package Application;

import java.util.List;



import model.Noticia;
import service.Fachada;
import util.Util;
import model.Assunto;

public class Consultar {
	/*
	 * quais as noticias publicados na data X
	 * quais as noticias do assunto de nome X
	 * quais os assuntos que tem mais de N noticias
	 */
	
	public Consultar() {
		Fachada.inicializar();
		
		System.out.println("==================");
		System.out.println("Exibindo notícias da data 2023-07-20");
		System.out.println("==================");
		/* quais as noticias publicados na data X */
		List<Noticia> q1Resultado = Fachada.pesqNoticiaByData("2023-07-20");
		for (Noticia n : q1Resultado) {
			System.out.println(n);
		}

		System.out.println("==================");
		System.out.println("Exibindo notícias do assunto trabalho");
		System.out.println("==================");

		/* quais as noticias do assunto de nome X */
		List<Noticia> q2Resultado = Fachada.pesqNoticiaByAssunto("trabalho");
		for (Noticia n : q2Resultado) {
			System.out.println(n);
		}

		System.out.println("==================");
		System.out.println("Exibindo os assuntos que tem mais de 1 noticias");
		System.out.println("==================");

		/* quais os assuntos que tem mais de 1 noticias */

		List<Assunto> q3Resultado =Fachada.pesqNoticiaByQuantNot(1);

		for (Assunto a : q3Resultado) {
			System.out.println(a);
		}
		System.out.println("Fim das consultas");

		Fachada.finalizar();
	}

	/* class Filtro implements Evaluation {
		private static final long serialVersionUID = 1L;

		public void evaluate(Candidate candidate) {
			Assunto a = (Assunto) candidate.getObject();
			if (a.getListaNoticia().size() > 1) {
				candidate.include(true);
			} else
				candidate.include(false);
		}

	}
 */
	public static void main(String[] args) {
		new Consultar();
	}
}
