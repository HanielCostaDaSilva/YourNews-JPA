package Application;

import model.Assunto;
import model.Noticia;
import service.Fachada;

public class Alterar {

	public static void main(String[] args) {
		try {
			Fachada.inicializar();

			System.out.println("Alterando...");

			// Atualizando nóticia com id 6
			// colocando título "Edu Camaargo ensina a programar em PhP"

			Noticia not = Fachada.localizarNoticia(6);

			System.out.println("===================================================");
			System.out.println("Atualizando Noticia: \n" + not.getTitulo() );
			System.out.println("===================================================");

			Fachada.atualizarNoticia(6, "Edu Camargo ensina a programar em PHP", "", "");

			System.out.println("Noticia: \n" + not.getTitulo() + "\natualizada");

			// Associando Noticia com id 5 ao assunto com id 6
			System.out.println("===================================================");

			not = Fachada.localizarNoticia(2);
			Assunto ass = Fachada.localizarAssunto(1);

			System.out.println(
					"Associando Noticia: " + not.getTitulo() + "\nAo Assunto: " + ass.getNome());

			System.out.println("===================================================");

			Fachada.associarAssuntoNoticia(2, 1);

			// Desassociar Noticia com id 5 do assunto com id 6
			System.out.println("===================================================");

			not = Fachada.localizarNoticia(6);
			ass = Fachada.localizarAssunto(5);

			System.out.println(
					"Desassociando Noticia: " + not.getTitulo() + "\ndo Assunto: " + ass.getNome());

			Fachada.desassociarNoticiaAssunto(6, 5);

			System.out.println("===================================================");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("Fim do programa.");
	}
}
