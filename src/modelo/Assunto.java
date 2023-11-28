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
public class Assunto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    //@Convert(converter=LowerToUpperConverter.class)
    private String nome;

    @ManyToMany(
        mappedBy="listaAssuntos",	
        cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch= FetchType.LAZY) 							
        private List<Noticia> listaNoticia = new ArrayList<>();

    public Assunto(){}
    public Assunto(String nome) {
        this.nome = nome;
    }

    public Assunto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String newNome) {
        this.nome = newNome;
    }

    public List<Noticia> getListaNoticia() {
        return this.listaNoticia;
    }

    public void adicionar(Noticia noticia) {
        this.listaNoticia.add(noticia);
    }

    public void remover(Noticia noticia) {
        this.listaNoticia.remove(noticia);
    }

    public List<Noticia> listar() {
        return this.listaNoticia;
    }

    @Override
    public String toString() {
        return "id: " + id + " nome: " + nome;
    }
}
