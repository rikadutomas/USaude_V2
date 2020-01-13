import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cuidado implements Serializable {

	private static final long serialVersionUID = 1L;
	Utente utente;
	List<Servico> listaDeServicos;

	public Cuidado(Utente u, List<Servico> ls) {
		utente = u;
		listaDeServicos = ls;
	}

	public String getUtente() {
		return utente.getNome();
	}

	public List<Servico> getServicos() {
		return listaDeServicos;
	}

	public List<String> getServicosPorOrdem() {
		List<String> out = new ArrayList<>();
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("Consulta")) {
				for (Profissional p: s.getProfissionais()) {
					out.add(s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("PequenaCirurgia")) {
				for (Profissional p: s.getProfissionais()) {
					out.add(s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("Enfermagem")){
				for (Profissional p: s.getProfissionais()) {
					out.add(s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		return out;
	}

	public List<String> getServicosPorOrdemComNome() {
		List<String> out = new ArrayList<>();
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("Consulta")) {
				for (Profissional p: s.getProfissionais()) {
					out.add(utente.getNome() + " " + s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("PequenaCirurgia")) {
				for (Profissional p: s.getProfissionais()) {
					out.add(utente.getNome() + " " + s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		for (Servico s: listaDeServicos) {
			if (s.getTipo().equals("Enfermagem")){
				for (Profissional p: s.getProfissionais()) {
					out.add(utente.getNome() + " " + s.getTipo() + " " +p.getCategoria() + " " + p.getNome());
				}
			}
		}
		return out;
	}
}
