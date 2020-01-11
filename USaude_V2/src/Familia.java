import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;

public class Familia {
	private String nomeFamilia;
	private Utente utente;
	private TreeMap<String,Utente> membros;
	
	
	public Familia(String nomeFamilia) {
		membros = new TreeMap<String,Utente>();
	}
	
	public void adicionar(Utente utente) {
		membros.put(utente.getNome(), utente);
	}

	public void remover(Utente utente) {
		membros.remove(utente.getNome());
	}

	public boolean isMembro(String nome) {
		return membros.containsKey(nome);
	}
	
	public ArrayList<Utente> listaUtentes() {
		ArrayList<Utente> out = new ArrayList<Utente>();
		Set <String> keys = this.membros.keySet();
		for (String key:keys){
			Utente u = membros.get(key);
			out.add(u);
		}
		return out;
	}

	public String getNomeFamilia() {
		return nomeFamilia;
	}

	public Utente getUtente() {
		return utente;
	}

	public TreeMap<String, Utente> getMembros() {
		if (membros==null) {
			return null;
		}
		else {
			return membros;
		}
	}

}
