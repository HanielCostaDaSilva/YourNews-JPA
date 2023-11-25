/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package DAO_JPA;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Noticia;

public class DAONoticia extends DAO<Noticia>{


	public Noticia read (Object chave){
		try{
			String titulo = (String) chave;
			titulo = titulo.toUpperCase();
			TypedQuery<Noticia> q = manager.createQuery("select n from Noticia n where n.titulo=:t",Noticia.class);
			q.setParameter("t", titulo);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Noticia> readAll(){
		TypedQuery<Noticia> q = manager.createQuery("select n from Noticia n LEFT JOIN FETCH n.listaAssuntos order by n.titulo", Noticia.class);
		return  q.getResultList();
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	public  List<Noticia> readAll(String caracteres) {
		caracteres = caracteres.toUpperCase();

		TypedQuery<Noticia> q = manager.createQuery
				("select n from Noticia n LEFT JOIN FETCH n.listaAssuntos where n.titulo like :x  order by n.titulo",Noticia.class);
		q.setParameter("x", "%"+caracteres+"%");
		return q.getResultList();
	}


	public  List<Noticia>  readByNAssuntos(int n) {
		TypedQuery<Noticia> q = manager.createQuery
				("select n from Noticia n JOIN FETCH n.listaAssuntos where SIZE(n.listaAssuntos) = :x",Noticia.class);
		q.setParameter("x", n);
		return q.getResultList(); 
	}

	public List<Noticia> readByMes(String mes) {
		TypedQuery<Noticia> q = manager.createQuery
				("select n from Noticia n JOIN FETCH n.listaAssuntos where substring(n.dataPublicacao,5,6) = :m",Noticia.class);
		q.setParameter("m", mes);
		return q.getResultList(); 

	}
		
	public  boolean  temAssuntos(String nome) {
		try{
			nome = nome.toUpperCase();

			Query q = manager.createQuery
					("select count(a) from Noticia N join n.listaAssuntos a where n.titulo = :x and a.id like :y");
			q.setParameter("x", nome);
			q.setParameter("y", "1%");
			long cont = (Long) q.getSingleResult();
			return cont>0;	
		}catch(NoResultException e){
			return false;
		}
	}

	/*
	 * public boolean temTelefoneFixo(String nome) { try{ nome = nome.toUpperCase();
	 * 
	 * Query q = manager.createQuery
	 * ("select count(t) from Pessoa p join p.telefones t where p.nome = :x and t.numero like :y"
	 * ); q.setParameter("x", nome); q.setParameter("y", "3%"); //inicia com 3
	 * 
	 * long cont = (Long) q.getSingleResult(); return cont>0;
	 * }catch(NoResultException e){ return false; } }
	 */


}

