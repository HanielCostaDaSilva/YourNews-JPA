package Application;

import com.db4o.ObjectContainer;

import model.Assunto;
import model.Noticia;
import util.Util;

class Cadastrar {
	protected ObjectContainer manager;
	
  public Cadastrar() {

    try {
      manager = Util.conectarBanco();
      System.out.println("Cadastrando...");
      
      Assunto a1 = new Assunto(Util.gerarIdAssunto(), "Esportes");
      manager.store(a1);
      manager.commit();
      
      Assunto a2 = new Assunto(Util.gerarIdAssunto(), "Celebridades");
      manager.store(a2);
      manager.commit();
      
      Assunto a3 = new Assunto(Util.gerarIdAssunto(), "Política");
      manager.store(a3);
      manager.commit();
      
      Assunto a4 = new Assunto(Util.gerarIdAssunto(), "Variedades");
      manager.store(a4);
      manager.commit();
      
      Assunto a5 = new Assunto(Util.gerarIdAssunto(), "Tecnologia");
      manager.store(a5);
      manager.commit();
      
      Assunto a6 = new Assunto(Util.gerarIdAssunto(), "Trabalho");
      manager.store(a6);
      manager.commit();

      Noticia n1 = new Noticia(Util.gerarIdNoticia(),
          "Final emocionante: Botafogo derrota Flamengo nas quartas de finais", "2023-08-24",
          "https://exemplo.com/noticia1");

      addCross(n1, a1);
      manager.store(n1);
      manager.commit();

      Noticia n2 = new Noticia(Util.gerarIdNoticia(),
          "BOMBA! Jojô Toddynho reata namoro com o ex. Veja o seu desabafo!", "2023-08-23",
          "https://exemplo.com/noticia2");

      addCross(n2, a2);
      manager.store(n2);
      manager.commit();

      Noticia n3 = new Noticia(Util.gerarIdNoticia(),
          "Veja quais profissões deixaram de existir graças às IAs.", "2023-08-22",
          "https://exemplo.com/noticia3");

      addCross(n3, a5);
      addCross(n3, a6);
      manager.store(n3);
      manager.commit();

      Noticia n4 = new Noticia(Util.gerarIdNoticia(),
          "Gorveno ameaça baixar o piso da enfermagem.", "2023-07-26",
          "https://exemplo.com/noticia4");

      addCross(n4, a3);
      addCross(n4, a6);
      manager.store(n4);
      manager.commit();

      Noticia n5 = new Noticia(Util.gerarIdNoticia(),
          "Anunciado! GTA 6 não é mais um sonho! Veja a data de lançamento.", "2023-08-20",
          "https://exemplo.com/noticia5");

      addCross(n5, a4);
      manager.store(n5);
      manager.commit();

      Noticia n6 = new Noticia(Util.gerarIdNoticia(),
          "Edu Camargo ensina a fazer uma página Web Top.", "2023-08-19",
          "https://exemplo.com/noticia6");

      addCross(n6, a2);
      addCross(n6, a4);
      addCross(n6, a5);
      manager.store(n6);
      manager.commit();

      Noticia n7 = new Noticia(Util.gerarIdNoticia(),
          "Cachorro chora no velório da dona, confira o vídeo sem se emocionar.", "2023-08-18",
          "https://exemplo.com/noticia7");

      addCross(n7, a5);
      manager.store(n7);
      manager.commit();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    Util.desconectar();
    System.out.println("\nFim do programa");
    
  }

  public void addCross(Noticia n, Assunto a) {
    n.adicionar(a);
    a.adicionar(n);

  }
  public static void main(String[] args){
	  new Cadastrar();
  }
}