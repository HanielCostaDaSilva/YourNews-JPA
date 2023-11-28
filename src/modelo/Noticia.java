package modelo;

import java.util.ArrayList;
import java.util.List;

import daojpa.LowerToUpperConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//@Convert(converter=LowerToUpperConverter.class)
	private String titulo;
	
	private String dataPublicacao;
	
	@Convert(converter=LowerToUpperConverter.class)
	private String link;
	
	@ManyToMany(
		cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
		fetch= FetchType.LAZY)

	private List<Assunto> listaAssuntos = new ArrayList<>();

	public Noticia(){}
	
	public Noticia(String titulo, String dataPublicacao, String link) {
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.link = link;
	}

	public Noticia(int id, String titulo, String dataPublicacao, String link) {
		this.id = id;
		this.titulo = titulo;
		this.dataPublicacao = dataPublicacao;
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void adicionar(Assunto assunto) {
		this.listaAssuntos.add(assunto);
	}

	public void remover(Assunto assunto) {
		this.listaAssuntos.remove(assunto);
	}

	public List<Assunto> listar() {
		return this.listaAssuntos;
	}

	public List<Assunto> getListaAssuntos() {
		return this.listaAssuntos;
	}

	public Assunto localizar(String assuntoKey) {
		for (Assunto assunto : listaAssuntos) {
			if (assuntoKey.equals(assunto.getNome())) {
				return assunto;
			}
		}
		return null;
	}

	@Override
	public String toString() {

		return "id: " + id + ", titulo: " + titulo + ", Publicada: " + dataPublicacao + ", link: " + link;
//				+ "\n Assuntos: \n" + this.assuntosNome() + "";
	}

/* 	public String assuntosNome() {
		String assuntosNomes = "";
		int cont = 1;
		if (this.listaAssuntos.size() > 0)
			for (Assunto a : this.listaAssuntos) {
				assuntosNomes += " Assunto " + cont + " "+a.getNome() + "|";
				cont++;
			}
		return assuntosNomes;
	} */
}
