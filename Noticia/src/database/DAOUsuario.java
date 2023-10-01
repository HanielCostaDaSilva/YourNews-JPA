/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package database;

import java.util.List;

import com.db4o.query.Query;

import model.Usuario;

public class DAOUsuario extends DAO<Usuario> {

    public Usuario read(Object chave) {
        String nicknameSearch = (String) chave; // casting para o tipo da chave
        Query q = manager.query();
        q.constrain(Usuario.class);
        q.descend("nickname").constrain(nicknameSearch);
        List<Usuario> resultados = q.execute();
        if (resultados.size() > 0)
            return resultados.get(0);
        else
            return null;
    }

}
