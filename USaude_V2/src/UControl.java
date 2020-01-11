import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.List;

public class UControl implements UInterface{
	
	TreeMap<String,Profissional> arrMedicina = new TreeMap<String,Profissional>();
	TreeMap<String,Profissional> arrEnfermagem = new TreeMap<String,Profissional>();
	TreeMap<String,Profissional> arrAuxiliar = new TreeMap<String,Profissional>();
	TreeMap<String,Utente> arrUtente = new TreeMap<String,Utente>();
	TreeMap<String,Familia> arrFamilia = new TreeMap<String,Familia>();
	
	List<String> arrFaixaEtaria = Arrays.asList(new String[]{"Jovem", "Adulto","Idoso"});
	
	@Override
	public boolean isCategoria(String categoria) {
		if (categoria.equals("Medicina") || categoria.equals("Enfermagem") || categoria.equals("Auxiliar")) {
			return true;
		}
		else {
			return false;
		}		
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
		Set <String>keys = arrUtente.keySet();
		for (String key:keys) {		
//			String nomeFamilia = key;
//			Familia x = arrFamilia.get(key);
			Utente x = arrUtente.get(key);
//			for(Utente utente: x.listaUtentes()) {
//				out.add(utente.toString());
//			}	
			out.add(x.toString());
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
		

	
	
	
}
