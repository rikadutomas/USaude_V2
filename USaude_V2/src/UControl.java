import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Set;

public class UControl implements UInterface{
	
	TreeMap<String,Profissional> arrMedicina = new TreeMap<String,Profissional>();
	TreeMap<String,Profissional> arrEnfermagem = new TreeMap<String,Profissional>();
	TreeMap<String,Profissional> arrAuxiliar = new TreeMap<String,Profissional>();

	
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
		

	
	
	
}
