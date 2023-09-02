package Application;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import model.Noticia;
import model.Assunto;
import util.Util;

public class Deletar {
    protected ObjectContainer manager;

    public Deletar() {
        manager = Util.conectarBanco();
        this.apagar();
        Util.desconectar();
    }

    public void apagar() {
        System.out.println("Deletando...");

        /* Apagar o assunto 'Variedades' do banco de dados. */
        /*
         * Query q1 = manager.query();
         * q1.constrain(Assunto.class);
         * q1.descend("nome").constrain("variedades");
         * List<Assunto> resultado = q1.execute();
         * 
         * if (resultado.size() > 0) {
         * Assunto a = resultado.get(0);
         * for (Noticia n : a.getListaNoticia()) {
         * 
         * n.remover(a);
         * System.out.println("Removido a notícia com id: " + n.getId());
         * 
         * manager.store(n);
         * 
         * if (n.getListaAssuntos().isEmpty())
         * manager.delete(n);
         * }
         * 
         * manager.delete(a);
         * manager.commit();
         * 
         * } else
         * System.out.println("inexistente");
         */
        /* Apagar a notícia que possua 'governo' no título do banco de dados. */
        Query q2 = manager.query();
        q2.constrain(Noticia.class);
        q2.descend("titulo").constrain("governo").like();
        List<Noticia> resultado2 = q2.execute();

        if (resultado2.size() > 0) {
            for (Noticia noticia : resultado2) {
                System.out.println("Removendo a notícia com o título: " + noticia.getTitulo());

                for (Assunto assunto : noticia.getListaAssuntos()) {
                    assunto.remover(noticia);
                    /*
                     * if (assunto.getListaNoticia().isEmpty())
                     * manager.delete(assunto);
                     */ // ão acho que seja necessário remover o assunto do db se não possuir notícias.
                }
                manager.delete(noticia);
            }
            manager.commit();
        }

        System.out.println("Fim do programa.");
    }

    public static void main(String[] args) {
        new Deletar();
    }
}