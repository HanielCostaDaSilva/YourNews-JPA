package modelo;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity 
public class Telefone {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String numero;	
	
	@ManyToOne(	
			cascade={CascadeType.PERSIST, CascadeType.MERGE}, 	//nao inclui CascadeType.REMOVE
			fetch= FetchType.LAZY) 							//default é EAGER
	private Pessoa pessoa;		
	
	
	public Telefone (){}
	public Telefone(String numero) {
		this.numero = numero;
	}
	public Telefone(String numero, Pessoa p) {
		this.numero = numero;
		this.pessoa = p;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//	--------------------RELACIONAMENTO--------------------------------
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	@Override
	public String toString() {
		return "id=" + id + ", numero=" + numero +
			 (pessoa != null ? ", pessoa=" + pessoa.getNome() : "sem nome") ;
	}

	public boolean ehCelular() {
		return numero.startsWith("9");
	}
	
	public boolean ehFixo() {
		return numero.startsWith("3");
	}
	
	
}
