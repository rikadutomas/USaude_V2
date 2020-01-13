import java.io.Serializable;

public class Profissional implements Serializable {


	private static final long serialVersionUID = 1L;
	private String nome;
	private String categoria;
	
	public Profissional(String categoria, String nome) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return categoria + " " + nome.replace("_", " ");
	}
}
