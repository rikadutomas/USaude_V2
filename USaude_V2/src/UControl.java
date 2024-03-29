import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.List;

public class UControl implements UInterface{
	
	private TreeMap<String,Profissional> arrMedicina = new TreeMap<String,Profissional>();
	private TreeMap<String,Profissional> arrEnfermagem = new TreeMap<String,Profissional>();
	private TreeMap<String,Profissional> arrAuxiliar = new TreeMap<String,Profissional>();
	private TreeMap<String,Utente> arrUtente = new TreeMap<String,Utente>();
	private TreeMap<String,Familia> arrFamilia = new TreeMap<String,Familia>();
	private TreeMap<String,Cuidado> arrMarcacoes = new TreeMap<String,Cuidado>();
	
	
	private List<String> arrFaixaEtaria = Arrays.asList(new String[]{"Jovem", "Adulto","Idoso"});
	private List<String> arrCategoria = Arrays.asList(new String[]{"Medicina", "Enfermage","Auxiliar"});
	private List<String> arrServico = Arrays.asList(new String[]{"Consulta", "PequenaCirurgia","Enfermagem"});
	
	@Override
	public boolean isCategoria(String categoria) {
		return arrCategoria.contains(categoria);
	}

	@Override
	public boolean isProfissional(String categoria, String nome){		
		
		if (categoria.equals("Medicina")) {
			boolean a = arrMedicina.containsKey(nome);
			return a;
		}
		else if (categoria.equals("Enfermagem")) {
			return arrEnfermagem.containsKey(nome);
		}
		else {
			return arrAuxiliar.containsKey(nome);
		}
	}

	@Override
	public void registarProfissional(String categoria, String nome) {
		Profissional pro = new Profissional(categoria,nome);		
		if (categoria.equals("Medicina")){
			arrMedicina.put(nome, pro);
		}
		else if (categoria.equals("Enfermagem")){
			arrEnfermagem.put(nome, pro);
		}
		else {
			arrAuxiliar.put(nome, pro);
		}
	}

	@Override
	public ArrayList<String> listarProfissionais() {	
		ArrayList<String> out = new ArrayList<String>();
		Set<String> keys = arrMedicina.keySet();
		for(String key: keys) {
			Profissional s = arrMedicina.get(key);
			out.add(s.toString());
		}
		Set<String> keys2 = arrEnfermagem.keySet();
		for(String key: keys2) {
			Profissional s = arrEnfermagem.get(key);
			out.add(s.toString());
		}
		Set<String> keys3 = arrAuxiliar.keySet();
		for(String key: keys3) {
			Profissional s = arrAuxiliar.get(key);
			out.add(s.toString());
		}	
		return out;	
	}

	@Override
	public boolean isEtaria(String etaria) {
		return arrFaixaEtaria.contains(etaria);
	}

	@Override
	public boolean isUtente(String nome) {
		return arrUtente.containsKey(nome);
	}

	@Override
	public void registarUtente(String nome, String etaria) {
		Utente utente = new Utente(nome,etaria);
		arrUtente.put(nome, utente);
	}

	@Override
	public ArrayList<String> listarUtentes() {
		ArrayList <String> out = new ArrayList<String>();
		for (String etaria: arrFaixaEtaria) {
			Set <String>keys = arrFamilia.keySet();
			for (String key:keys) {		
				String nomeFamilia = key;
				Familia familia = arrFamilia.get(key);
				for(Utente utente: familia.listaUtentes()) {
					if(utente.getEtaria().equals(etaria)) {
						out.add(utente.toString());
					}
				}	
			}
			Set <String>keys2 = arrUtente.keySet();
			for (String key:keys2) {		
				Utente utente = arrUtente.get(key);
				if (utente.getFamilia().equals("")) {
					if(utente.getEtaria().equals(etaria)) {
						out.add(utente.toString());
					}				
				}
			}
		}
		return out;
	}
	
	@Override
	public boolean isFamilia(String nomeFamilia) {
		return arrFamilia.containsKey(nomeFamilia);
	}

	@Override
	public void registarFamilia(String nomeFamilia) {
		Familia familia = new Familia(nomeFamilia);
		arrFamilia.put(nomeFamilia, familia);		
	}

	@Override
	public boolean utenteInFamilia(String nome, String nomeFamilia) {
		Familia familia = arrFamilia.get(nomeFamilia);
		return familia.isMembro(nome);		
	}

	@Override
	public void associarFamilia(String nome, String nomeFamilia) {
		Familia familia =arrFamilia.get(nomeFamilia);
		Utente utente = arrUtente.get(nome);
		utente.setFamilia(nomeFamilia);
		familia.adicionar(utente);
	}

	
	@Override
	public boolean notFamilia(String nome) {
		Utente utente = arrUtente.get(nome);
		return utente.notFamilia();
	}

	@Override
	public void desassociarFamilia(String nome) {
		Utente utente = arrUtente.get(nome);
		String nomeFamilia = utente.getFamilia();
		Familia familia =arrFamilia.get(nomeFamilia);
		familia.remover(utente);
		arrUtente.remove(nome);
		utente.setFamilia("");
		arrUtente.put(nome, utente);	
	}

	@Override
	public ArrayList<String> mostrarFamilia(String nomeFamilia) {
		Familia familia = arrFamilia.get(nomeFamilia);
		ArrayList <String> out = new ArrayList<String>();
		if (familia==null){
			out.add("");
			return out;
		}
		TreeMap<String,Utente> membros = familia.getMembros();
		for (String etaria:arrFaixaEtaria) {
			for (String nome:membros.keySet()) {
				Utente utente = arrUtente.get(nome);
				if(utente.getEtaria().equals(etaria)) {
					out.add(utente.getEtaria() + " " + utente.getNome().replace("_", " "));
				}
			}	
		}		
		if (out.isEmpty()) {
			out.add("");
		}
		return out;
	}
//
	@Override
	public ArrayList<String> listarFamilias() {		
		ArrayList<String> out = new ArrayList<String>();		
		if(arrFamilia.isEmpty()) {out.add("");}
		Set<String>keys = arrFamilia.keySet();
		for(String key:keys) {
			out.add(key);
		}
		return out;
	}


	@Override
	public int marcacao(ArrayList<String> command) {
		
		
		return 0;
	}
	


	
	
	
	
	
}
