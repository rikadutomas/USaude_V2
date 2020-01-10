
public class Profissional {
	private String nome;
	private String categoria;
	
	public Profissional(String categoria, String nome) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public static String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

}
