package Application;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import model.Assunto;
import model.Noticia;
import util.Util;

public class Listar {
	protected ObjectContainer manager;
	
	public Listar() {
		try {
			manager = Util.conectarBanco();
			
			System.out.println("Listagem de Assuntos:");
			Query q = manager.query();
			q.constrain(Assunto.class);
			List<Assunto> resultados1 = q.execute();
			for(Assunto a: resultados1) {
				System.out.println(a);
			}
			
			System.out.println("Listagem de Notícias:");
			q = manager.query();
			q.constrain(Noticia.class);
			List<Noticia> resultados2 = q.execute();
			for(Noticia n: resultados2) {
				System.out.println(n);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Util.desconectar();
		System.out.println("Fim do programa");
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}
