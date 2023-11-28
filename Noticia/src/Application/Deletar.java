package Application;

import model.Noticia;
import service.Fachada;


public class Deletar {

    public Deletar() {
        Fachada.inicializar();
        this.apagar();
        Fachada.finalizar();
    }

    public void apagar() {
        try{
            System.out.println("Deletando...");
            
                    //Pode dá problema
                    Noticia resultado2 = Fachada.removerNoticia("governo");

                    if (resultado2!=null) {
                            System.out.println("Removida a notícia com o título: " + resultado2.getTitulo());
                    }
            
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                System.out.println("Fim do programa.");
    }

    public static void main(String[] args) {
        new Deletar();
    }
}