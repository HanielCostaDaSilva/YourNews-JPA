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
      Util.sendToDatabase(a6);

      Noticia n1 = new Noticia(Util.gerarIdNoticia(),
          "Final emocionante: Botafogo derrota Flamengo nas quartas de finais", "2023-08-24",
          "https://exemplo.com/noticia1");


      n1.adicionar(a1);
      a1.adicionar(n1);

      manager.store(n1);
      manager.commit();


      Noticia n2 = new Noticia(Util.gerarIdNoticia(),
          "BOMBA! Jojô Toddynho reata namoro com o ex. Veja o seu desabafo!", "2023-08-23",
          "https://exemplo.com/noticia2");


      n2.adicionar(a2);
      a2.adicionar(n2);

      manager.store(n2);
      manager.commit();


      Noticia n3 = new Noticia(Util.gerarIdNoticia(),
          "Veja quais profissões deixaram de existir graças às IAs.", "2023-08-22",
          "https://exemplo.com/noticia3");


      n3.adicionar(a5);
      a5.adicionar(n3);

      n3.adicionar(a6);
      a6.adicionar(n3);

      manager.store(n3);
      manager.commit();


      Noticia n4 = new Noticia(Util.gerarIdNoticia(),
          "Gorveno ameaça baixar o piso da enfermagem.", "2023-07-20",
          "https://exemplo.com/noticia4");


      n4.adicionar(a3);
      a3.adicionar(n4);

      n4.adicionar(a6);
      a6.adicionar(n4);

      manager.store(n4);
      manager.commit();


      Noticia n5 = new Noticia(Util.gerarIdNoticia(),
          "Anunciado! GTA 6 não é mais um sonho! Veja a data de lançamento.", "2023-08-20",
          "https://exemplo.com/noticia5");


      n5.adicionar(a4);
      a4.adicionar(n5);

      manager.store(n5);
      manager.commit();


      Noticia n6 = new Noticia(Util.gerarIdNoticia(),
          "Edu Camargo ensina a fazer uma página Web Top.", "2023-08-20",
          "https://exemplo.com/noticia6");


      n6.adicionar(a2);
      a2.adicionar(n6);

      n6.adicionar(a4);
      a4.adicionar(n6);

      n6.adicionar(a5);
      a5.adicionar(n6);

      manager.store(n6);
      manager.commit();


      Noticia n7 = new Noticia(Util.gerarIdNoticia(),
          "Cachorro chora no velório da dona, confira o vídeo sem se emocionar.", "2023-08-18",
          "https://exemplo.com/noticia7");


      n7.adicionar(a4);
      a4.adicionar(n7);

      manager.store(n7);
      manager.commit();


    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    Util.desconectar();
    System.out.println("\nFim do programa");

  }

  public static void main(String[] args) {
    new Cadastrar();
  }
}