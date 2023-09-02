package util;

import com.db4o.ObjectContainer;

import model.Assunto;
import model.Noticia;

public class RemoveHelpper {
    private static ObjectContainer manager = Util.conectarBanco();

    /* Delete functions */
    public static void deleteFromDatabase(Noticia n) {
        manager.delete(n);
        manager.commit();
    }

    public static void deleteFromDatabase(Assunto a) {
        manager.delete(a);
        manager.commit();
    }

}
