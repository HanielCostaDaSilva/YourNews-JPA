package Application;


import model.Assunto;
import model.Noticia;
import service.Fachada;

public class Alterar {

	public Alterar() {
		try {
			Fachada.inicializar();

			System.out.println("Alterando...");

			// consultar notícia
			Query q1 = manager.query();
			q1.constrain(Noticia.class);

			q1.descend("id").constrain(2);

			List<Noticia> resultados1 = q1.execute();

			// consultar assunto
			Query q2 = manager.query();

			q2.constrain(Assunto.class);
			q2.descend("id").constrain(6);

			List<Assunto> resultados2 = q2.execute();

			if (resultados1.size() > 0 && resultados2.size() > 0) {
				Noticia noticia = resultados1.get(0);
				Assunto assunto = resultados2.get(0);

				noticia.adicionar(assunto);
				assunto.adicionar(noticia);
				manager.store(assunto);
				manager.commit();
			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("Fim do programa.");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
