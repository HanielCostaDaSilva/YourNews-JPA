package modelo;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import jakarta.persistence.OneToMany;

@Entity 
public class Pessoa {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//converter os nomes para maiusculas no banco
	@Convert(converter=LowerToUpperConverter.class)
	private String nome;
	
	private String dtnascimento ;   

	@OneToMany(mappedBy="pessoa", 
		cascade={CascadeType.PERSIST, CascadeType.MERGE}, 	//nao inclui CascadeType.REMOVE
		orphanRemoval=true,			//default é false
		fetch=FetchType.LAZY) 		//default é LAZY
	private List<Telefone> telefones = new ArrayList<>();


	public Pessoa (){}
	
	public Pessoa(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getNascimento() {
		return LocalDate.parse(dtnascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	public String getDtNascimento() {
		return this.dtnascimento;
	}
	public void setDtNascimento(String data) {
		this.dtnascimento = data;
	}
	public void setNascimento(LocalDate nascimento) {
		this.dtnascimento = nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}


	public void adicionar(Telefone t){
		telefones.add(t);
		t.setPessoa(this);
	}
	public void remover(Telefone t){
		telefones.remove(t);
		t.setPessoa(null);
	}
	
	
	public String toString() {
		String texto = this.getClass().getSimpleName() +": ";
		texto = "id=" + id + ", nome=" + nome 
		+ ", dtnascimento=" + getDtNascimento();

		texto += ",   telefones: ";
		for(Telefone t : telefones)
			texto+= t.getNumero() + ",";
		
		return texto;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}
}
