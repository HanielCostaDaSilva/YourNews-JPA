package util;

import model.Assunto;
import model.Noticia;

public class Tools {

    /**
     * Método utilizado para associar uma notícia com um assunto
     * 
     * @param noticia
     * @param assunto
     */
    public static void addCross(Noticia noticia, Assunto assunto) {
        noticia.adicionar(assunto);
        assunto.adicionar(noticia);
    }

    /**
     * Método utilizado para remover a ligação entre uma notícia e um assunto
     * 
     * @param noticia
     * @param assunto
     */
    public static void removeCross(Noticia n, Assunto assunto) {
        n.remover(assunto);
        assunto.remover(n);
    }

}
