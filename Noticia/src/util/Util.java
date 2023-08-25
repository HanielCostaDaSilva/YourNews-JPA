package util;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import model.Noticia;
import model.Assunto;

public class Util {

    private static ObjectContainer manager;

    public static ObjectContainer conectarBanco() {
        if (manager != null)
            return manager;
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0); // mensagens na tela 0(desliga),1,2,3...

        // habilitar cascata na alteração, remoção e leitura
        config.common().objectClass(Noticia.class).cascadeOnDelete(false);
        config.common().objectClass(Noticia.class).cascadeOnUpdate(true);
        config.common().objectClass(Noticia.class).cascadeOnActivate(true);
        config.common().objectClass(Assunto.class).cascadeOnDelete(false);
        config.common().objectClass(Assunto.class).cascadeOnUpdate(true);
        config.common().objectClass(Assunto.class).cascadeOnActivate(true);

        // conexao local
        manager = Db4oEmbedded.openFile(config, "banco.db4o");
        return manager;
    }

    public static void desconectar() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }

}