/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package database;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import model.Assunto;
import model.Noticia;

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

	/**
	 * Retorna uma lista de notícias associadas a um assunto com base no ID do
	 * assunto.
	 * 
	 * @param id O ID do assunto para o qual deseja recuperar as notícias.
	 * @return Uma lista de notícias associadas ao assunto com o ID especificado.
	 */
	public List<Noticia> getnoticiasPorAssunto(String assunto) {
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("nome").constrain(assunto);
		List<Noticia> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados;

		// forço o erro, fazendo com que tente pegar o primeiro valor da consulta,
		// existindo ou não.
		try {
			Assunto a = ((Assunto) q.execute().get(0));
			return a.getListaNoticia(); // Se existir, retorne sua lista;

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	/**
	 * Retorna uma lista de assuntos que têm uma quantidade desejada de notícias.
	 * 
	 * @param quantidadeDesejada A quantidade desejada de notícias para filtrar os
	 *                           assuntos.
	 * @return Uma lista de assuntos com a quantidade desejada de notícias.
	 */
	public List<Assunto> getAssuntosPorQuantidadeNoticia(int quantidadeDesejada) {
		Query q = manager.query();
		q.constrain(Assunto.class);
		q.constrain(new FiltroQuantidade(quantidadeDesejada));
		List<Assunto> assuntos = q.execute();

		return assuntos;

	}

	/**
	 * Classe utilizada para filtrar assuntos com base na quantidade de notícias
	 * associadas.
	 */
	class FiltroQuantidade implements Evaluation {
		private static final long serialVersionUID = 1L;

		private int quantidade = 0;

		FiltroQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}

		public void evaluate(Candidate candidate) {
			Assunto a = (Assunto) candidate.getObject();
			if (a.getListaNoticia().size() > quantidade) {
				candidate.include(true);
			} else
				candidate.include(false);
		}

	}
}
