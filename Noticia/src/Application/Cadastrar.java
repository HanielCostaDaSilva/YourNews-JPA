package Application;


import model.Assunto;
import model.Noticia;
import service.Fachada;


class Cadastrar {

  public Cadastrar() {

    try {
      System.out.println("cadastrando...");

      Assunto a1 = new Assunto( "esportes");
      Assunto a2 = new Assunto( "celebridades");
      
      Assunto a3 = new Assunto( "política");
      
      Assunto a4 = new Assunto( "variedades");
      
      Assunto a5 = new Assunto( "tecnologia");
      
      Assunto a6 = new Assunto( "trabalho");
      Fachada.adicionarAssunto(a1);
      Fachada.adicionarAssunto(a2);
      Fachada.adicionarAssunto(a3);
      Fachada.adicionarAssunto(a4);
      Fachada.adicionarAssunto(a5);
      Fachada.adicionarAssunto(a6);

      Noticia n1 = new Noticia(
          "final emocionante: Botafogo derrota Flamengo nas quartas de finais", "2023-08-24",
          "https://exemplo.com/noticia1");

      n1.adicionar(a1);
      a1.adicionar(n1);

      Noticia n2 = new Noticia(
        "bomba! Jojô Toddynho reata namoro com o ex. Veja o seu desabafo!", "2023-08-23",
        "https://exemplo.com/noticia2");
        
        n2.adicionar(a2);
        a2.adicionar(n2);
        
        
        Noticia n3 = new Noticia(
          "veja quais profissões deixaram de existir graças às IAs.", "2023-08-22",
          "https://exemplo.com/noticia3");
          
          n3.adicionar(a5);
          a5.adicionar(n3);
          
          n3.adicionar(a6);
          a6.adicionar(n3);
          
          
          Noticia n4 = new Noticia(
            "governo ameaça baixar o piso da enfermagem.", "2023-07-20",
            "https://exemplo.com/noticia4");

      n4.adicionar(a3);
      a3.adicionar(n4);

      n4.adicionar(a6);
      a6.adicionar(n4);
      
      
      Noticia n5 = new Noticia(
        "anunciado! GTA 6 não é mais um sonho! Veja a data de lançamento.", "2023-08-20",
        "https://exemplo.com/noticia5");
        
        n5.adicionar(a4);
        a4.adicionar(n5);
        
        
        Noticia n6 = new Noticia(
          "edu camargo ensina a fazer uma página Web Top.", "2023-08-20",
          "https://exemplo.com/noticia6");
          
          n6.adicionar(a2);
          a2.adicionar(n6);
          
      n6.adicionar(a4);
      a4.adicionar(n6);

      n6.adicionar(a5);
      a5.adicionar(n6);


      Noticia n7 = new Noticia(
          "cachorro chora no velório da dona, confira o vídeo sem se emocionar.", "2023-08-18",
          "https://exemplo.com/noticia7");

      n7.adicionar(a4);
      a4.adicionar(n7);
      
      Noticia n8 = new Noticia(
          "graças ao governo, 17% dos paraíbanos estão desempregados", "2023-07-20",
          "https://exemplo.com/noticia4");

      n8.adicionar(a3);
      a3.adicionar(n8);

      n8.adicionar(a6);
      a6.adicionar(n8);

      Fachada.adicionarNoticia(n1);
      Fachada.adicionarNoticia(n2);
      Fachada.adicionarNoticia(n3);
      Fachada.adicionarNoticia(n4);
      Fachada.adicionarNoticia(n5);
      Fachada.adicionarNoticia(n6);
      Fachada.adicionarNoticia(n7);
      Fachada.adicionarNoticia(n8);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    Fachada.finalizar();
    System.out.println("\nFim do programa");
    
  }
  
  public static void main(String[] args) {
    new Cadastrar();
  }
}