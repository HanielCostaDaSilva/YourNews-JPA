package util;

import com.db4o.ObjectContainer;
import java.util.ArrayList;
import model.Assunto;
import model.Noticia;

class Cadastrar {
	protected ObjectContainer manager;
  
  public Cadastrar() {
		
    try {
			manager = Util.conectarBanco();
      ArrayList<Assunto> assuntos= new ArrayList<>();
      ArrayList<Noticia> noticias= new ArrayList<>();

      assuntos.add(new Assunto(1,"esportes"));
      assuntos.add(new Assunto(2,"economia"));
      assuntos.add(new Assunto(3,"política"));
      assuntos.add(new Assunto(4,"variedades"));
      assuntos.add(new Assunto(5,"tecnologia"));
      assuntos.add(new Assunto(6,"celebridades"));

      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());
      noticias.add(new Noticia());

    }
    catch(Exception e){
      System.out.println(e);
    }    
  }
}