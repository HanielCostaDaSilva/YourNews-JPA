/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package DAO_JPA;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Assunto;

public class DAOAssunto  extends DAO<Assunto>{
	
	public Assunto read (Object chave){
		try{
			String id = (String) chave;
			TypedQuery<Assunto> q = manager.createQuery("select a from Assunto a where a.id = :n ", Assunto.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Assunto> readAll(){
		TypedQuery<Assunto> q = manager.createQuery("select a from Assunto a LEFT JOIN FETCH a.nome order by a.nome", Assunto.class);
		return  q.getResultList();
	}

	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public List<Assunto> readAll (String digitos){		
		TypedQuery<Assunto> q = manager.createQuery
				("select a from Assunto a LEFT JOIN FETCH a.nome where a.nome like :x order by a.nome", Assunto.class);
		q.setParameter("x", "%"+digitos+"%");
		return q.getResultList();
	}
}
