package Application;

import java.util.List;

import model.Assunto;
import model.Noticia;
import service.Fachada;

public class Listar {

	public Listar() {
		try {

			System.out.println("Listagem de Assuntos:");
			Fachada.inicializar();

			List<Assunto> resultados1 = Fachada.listarAssuntos();
			for (Assunto a : resultados1) {
				System.out.println(a);
			}
			System.out.println("==================");
			System.out.println("Listagem de Notícias:");

			;
			List<Noticia> resultados2 = Fachada.listarNoticias();
			System.out.println("==================");
			for (Noticia n : resultados2) {
				System.out.println(n);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("Fim do programa");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
