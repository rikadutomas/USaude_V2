import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;
	String tipo;
	Utente u;
	TreeMap<String, Profissional> profissionais;
	
	public Servico(String t, Utente ut) {
		tipo = t;
		u = ut;
		profissionais = new TreeMap<>();
	}

	public String getUtente() {
		return u.getNome();
	}

	public void adicionarProfissional(String nome, Profissional p){
		profissionais.put(nome, p);
	}

	public List<String> getProfissionaisAsString(){
		List<String> out = new ArrayList<>();
		for (Profissional p: profissionais.values()) {
			out.add(p.getNome());
		}
		return out;
	}

	public List<Profissional> getProfissionais() {
		List<Profissional> pOut = new ArrayList<>();
		for (Profissional p: profissionais.values()){
			if (p.getCategoria().equals("Medicina")){
				pOut.add(p);
			}
		}
		for (Profissional p: profissionais.values()){
			if (p.getCategoria().equals("Enfermagem")){
				pOut.add(p);
			}
		}
		for (Profissional p: profissionais.values()){
			if (p.getCategoria().equals("Auxiliar")){
				pOut.add(p);
			}
		}
		return pOut;
	}

	public String getTipo(){
		return tipo;
	}
}
