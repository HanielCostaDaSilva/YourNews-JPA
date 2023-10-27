package util;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import model.Assunto;
import model.Noticia;

public class Util {

    private static ObjectContainer manager;

    public static ObjectContainer conectarBanco() {
        if (manager != null)
            return manager;

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0);

        // habilitar cascata na alteração, remoção e leitura
        config.common().objectClass(Noticia.class).cascadeOnDelete(false);
        config.common().objectClass(Noticia.class).cascadeOnUpdate(true);
        config.common().objectClass(Noticia.class).cascadeOnActivate(true);
        config.common().objectClass(Assunto.class).cascadeOnDelete(false);
        config.common().objectClass(Assunto.class).cascadeOnUpdate(true);
        config.common().objectClass(Assunto.class).cascadeOnActivate(true);

        // conexao local
        manager = Db4oEmbedded.openFile(config, "./banco.db4o");
        return manager;
    }

    public static void desconectar() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }

    public static int gerarIdAssunto() {
        if (manager.query(Assunto.class).size() == 0) {
            return 1;
        }

        Query q = manager.query();
        q.constrain(Assunto.class);
        q.descend("id").orderDescending();
        List<Assunto> resultados = q.execute();

        if (resultados.size() > 0) {
            Assunto assunto = resultados.get(0);
            return assunto.getId() + 1;
        } else
            return 1;
    }

    public static int gerarIdNoticia() {
        if (manager.query(Noticia.class).size() == 0) {
            return 1;
        }

        Query q = manager.query();
        q.constrain(Noticia.class);
        q.descend("id").orderDescending();
        List<Noticia> resultados = q.execute();

        if (resultados.size() > 0) {
            Noticia noticia = resultados.get(0);
            return noticia.getId() + 1;
        } else
            return 1;
    }

}