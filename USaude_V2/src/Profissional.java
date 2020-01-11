
public class Profissional {
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
