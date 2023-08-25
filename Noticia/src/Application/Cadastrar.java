package Application;

import model.Assunto;
import model.Noticia;

import service.Register.RegisterHelper;;

class Cadastrar {
  public Cadastrar() {

    try {

      Assunto a1 = new Assunto(1, "esporte");
      Assunto a2 = new Assunto(2, "celebridade");
      Assunto a3 = new Assunto(3, "política");
      Assunto a4 = new Assunto(4, "variedade");
      Assunto a5 = new Assunto(5, "tecnologia");
      Assunto a6 = new Assunto(6, "trabalho");

      Noticia n1 = new Noticia(1,
          "Final emocionante: Bota Fogo Derrota Flamengo nas Quartas de Finais", "2023-08-24",
          "https://exemplo.com/noticia1");

      addCross(n1, a1);

      Noticia n2 = new Noticia(2,
          "BOMBA! Jojô Toddynho reata namoro com o Ex, veja o seu desabafo! ", "2023-08-23",
          "https://exemplo.com/noticia2");

      addCross(n2, a2);

      Noticia n3 = new Noticia(3,
          "Veja quais profissões deixaram de existir graças as IAs.", "2023-08-22",
          "https://exemplo.com/noticia3");

      addCross(n3, a5);
      addCross(n3, a6);

      Noticia n4 = new Noticia(4,
          "Gorveno ameaça baixar o piso enfermagem.", "2023-07-26",
          "https://exemplo.com/noticia4");

      addCross(n4, a3);
      addCross(n4, a6);

      Noticia n5 = new Noticia(5,
          "Anunciado! GTA 6 não é mais um sonho! Veja a data de lançamento.", "2023-08-20",
          "https://exemplo.com/noticia5");

      addCross(n5, a4);

      Noticia n6 = new Noticia(6,
          "Edu Camargo ensina a fazer uma página Web Top. ", "2023-08-19",
          "https://exemplo.com/noticia6");

      addCross(n6, a2);
      addCross(n6, a4);
      addCross(n6, a5);

      Noticia n7 = new Noticia(7,
          "Cachorro chora no velório da dona, confira o vídeo sem se emocionar.", "2023-08-18",
          "https://exemplo.com/noticia7");

      addCross(n7, a5);

      /* Facilita na hora de adicionar todos os elementos no dataBase */
      Assunto[] assuntos = { a1, a2, a3, a4, a5, a6 };
      Noticia[] noticias = { n1, n2, n3, n4, n5, n6, n7 };

      RegisterHelper.sendToDatabase(noticias);
      RegisterHelper.sendToDatabase(assuntos);
      
      
      System.out.println("Assuntos: \n"+ assuntos.toString());
      System.out.println("Noticias: \n"+ noticias.toString());

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /*
   * função para agilizar e economizar código ao realizar ligações entre objetos.
   */
  public void addCross(Noticia n, Assunto a) {
    n.adicionar(a);
    a.adicionar(n);

  }
  public static void main(String[] args){
	  Cadastrar c = new Cadastrar();
  }
}