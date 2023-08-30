package model;

import java.util.ArrayList;
import java.util.List;


public class Assunto {

    private int id;
    private String nome;
    private List<Noticia> listaNoticia = new ArrayList<>();
    
    public Assunto(int id, String nome ){
        this.id = id;
        this.nome=nome;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String newNome){
        this.nome = newNome;
    }
    public void adicionar(Noticia noticia ){
        this.listaNoticia.add(noticia);
    }

    public void remover(Noticia noticia){
        this.listaNoticia.remove(noticia);
    }

    public List<Noticia> listar(){
        return this.listaNoticia;
    }

    @Override
    public String toString(){
    	String titulosNoticias="";
    	for(Noticia n : this.listaNoticia) {	
    		titulosNoticias += n.getTitulo() +"\n" ;
    	}
    	
        return "id: "+ id +" nome: "+ nome + "\n titulos: \n" + titulosNoticias;
    }
}
