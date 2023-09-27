package model;

import java.util.ArrayList;
import java.util.List;

public class Noticia {
	private int id;
	private String titulo;
	private String dataPublicacao;
	private String link;
	private List<Assunto> listaAssuntos = new ArrayList<>();

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

	public String localizar(String assuntoKey) {
		for (Assunto assunto : listaAssuntos) {
			if (assuntoKey.equals(assunto.getNome())) {
				return assunto.getNome();
			}
		}
		return "Assunto não encontrado";
	}

	@Override
	public String toString() {
		String assuntosNomes = "";
		for (Assunto n : this.listaAssuntos) {
			assuntosNomes += n.getNome() + "\n";
		}
		return "id: " + id + ", titulo: " + titulo + ", Publicada: " + dataPublicacao + ", link: " + link
				+ "\n Assuntos: \n" + assuntosNomes + "";
	}
}
