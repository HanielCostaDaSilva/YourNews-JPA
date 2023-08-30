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
      Util.sendToDatabase(a1);

      Assunto a2 = new Assunto(Util.gerarIdAssunto(), "Celebridades");
      Util.sendToDatabase(a2);

      Assunto a3 = new Assunto(Util.gerarIdAssunto(), "Política");
      Util.sendToDatabase(a3);

      Assunto a4 = new Assunto(Util.gerarIdAssunto(), "Variedades");
      Util.sendToDatabase(a4);

      Assunto a5 = new Assunto(Util.gerarIdAssunto(), "Tecnologia");
      Util.sendToDatabase(a5);

      Assunto a6 = new Assunto(Util.gerarIdAssunto(), "Trabalho");
      Util.sendToDatabase(a6);

      Noticia n1 = new Noticia(Util.gerarIdNoticia(),
          "Final emocionante: Botafogo derrota Flamengo nas quartas de finais", "2023-08-24",
          "https://exemplo.com/noticia1");

      Util.addCross(n1, a1);
      Util.sendToDatabase(n1);

      Noticia n2 = new Noticia(Util.gerarIdNoticia(),
          "BOMBA! Jojô Toddynho reata namoro com o ex. Veja o seu desabafo!", "2023-08-23",
          "https://exemplo.com/noticia2");

      Util.addCross(n2, a2);
      Util.sendToDatabase(n2);

      Noticia n3 = new Noticia(Util.gerarIdNoticia(),
          "Veja quais profissões deixaram de existir graças às IAs.", "2023-08-22",
          "https://exemplo.com/noticia3");

      Util.addCross(n3, a5);
      Util.addCross(n3, a6);
      Util.sendToDatabase(n3);

      Noticia n4 = new Noticia(Util.gerarIdNoticia(),
          "Gorveno ameaça baixar o piso da enfermagem.", "2023-07-26",
          "https://exemplo.com/noticia4");

      Util.addCross(n4, a3);
      Util.addCross(n4, a6);
      Util.sendToDatabase(n4);

      Noticia n5 = new Noticia(Util.gerarIdNoticia(),
          "Anunciado! GTA 6 não é mais um sonho! Veja a data de lançamento.", "2023-08-20",
          "https://exemplo.com/noticia5");

      Util.addCross(n5, a4);
      Util.sendToDatabase(n5);

      Noticia n6 = new Noticia(Util.gerarIdNoticia(),
          "Edu Camargo ensina a fazer uma página Web Top.", "2023-08-19",
          "https://exemplo.com/noticia6");

      Util.addCross(n6, a2);
      Util.addCross(n6, a4);
      Util.addCross(n6, a5);
      Util.sendToDatabase(n6);

      Noticia n7 = new Noticia(Util.gerarIdNoticia(),
          "Cachorro chora no velório da dona, confira o vídeo sem se emocionar.", "2023-08-18",
          "https://exemplo.com/noticia7");

      Util.addCross(n7, a5);
      Util.sendToDatabase(n7);

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