/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package database;

import java.util.List;

import com.db4o.query.Query;

import model.Assunto;

public class DAOAssunto extends DAO<Assunto> {

	public Assunto read(Object key) {
		int id = (int) key;
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("id").constrain(id);
		List<Assunto> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}

	// metodo da classe DAO sobrescrito DAOAssunto para
	// criar "id" sequencial
	public void create(Assunto obj) {
		int novoid = super.gerarId(); // gerar novo id da classe
		obj.setId(novoid); // atualizar id do objeto antes de grava-lo no banco
		manager.store(obj);
	}

	// --------------------------------------------
	// consultas de Assunto
	// --------------------------------------------

	public List<Assunto> Assuntosmodel(String model) {
		Query q;
		q = manager.query();
		q.constrain(Assunto.class);
		q.descend("carro").descend("model").constrain(model);
		return q.execute();
	}

	/*
	 * public List<Assunto> AssuntosFinalizados() { Query q = manager.query();
	 * q.constrain(Assunto.class); q.descend("finalizado").constrain(true); return
	 * q.execute(); }
	 */
}
