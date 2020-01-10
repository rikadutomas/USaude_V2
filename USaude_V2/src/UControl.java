//import java.util.ArrayList;
import java.util.TreeMap;

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
		Profissional Pro = new Profissional(categoria,nome);		
		switch(String.contains(categoria)){
			case "Medicina": arrMedicina.put(nome, Pro);
			case "Enfermagem": arrEnfermagem.put(nome,Pro);
			case "Auxiliar": arrMedicina.put(nome,Pro);
		}
		///kjahdfkjshd
	}
	
	
	
	
}
