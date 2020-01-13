import java.io.*;
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
	private List<Cuidado> arrMarcacoes = new ArrayList<>();
	
	private List<String> arrFaixaEtaria = Arrays.asList(new String[]{"Jovem", "Adulto","Idoso"});
	private List<String> arrCategoria = Arrays.asList(new String[]{"Medicina", "Enfermagem","Auxiliar"});
	private List<String> arrServico = Arrays.asList(new String[]{"Consulta", "PequenaCirurgia","Enfermagem"});
	
	@Override
	public boolean isCategoria(String categoria) {
		return arrCategoria.contains(categoria);
	}

	@Override
	public boolean isServico(String servico) {
		return arrServico.contains(servico);
	}

	@Override
	public boolean isCategoriaValida(String categoria, String servico) {
		switch (categoria){
			case "Medicina":
				return (servico.equals("Consulta") || servico.equals("PequenaCirurgia"));
			case "Enfermagem":
			case "Auxiliar":
				return (servico.equals("Enfermagem") || servico.equals("PequenaCirurgia"));
		}
		return false;
	}

	@Override
	public boolean isSequenciaCorrecta(List<String> currentSequence) {
		int posPC = 1000;
		boolean sequenciaValida = true;
		if (currentSequence.contains("PequenaCirurgia")) {
			for (int i=0; i < currentSequence.size(); i++){
				if (currentSequence.get(i).equals("PequenaCirurgia")){
					posPC = i;
					if (posPC != 1){
						sequenciaValida = false;
						break;
					}
					if (!currentSequence.get(i-1).equals("Consulta")){
						sequenciaValida = false;
						break;
					};
				}
				if (currentSequence.get(i).equals("Consulta")){
					if (i > posPC && (i+1 != currentSequence.size())) {
						sequenciaValida = false;
						break;
					}
				}
			}
			return sequenciaValida;
		} else {
			return true;
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
			return out;
		}
		TreeMap<String,Utente> membros = arrFamilia.get(nomeFamilia).getMembros();
		for (String etaria:arrFaixaEtaria) {
			for (String nome:membros.keySet()) {
				Utente utente = arrUtente.get(nome);
				if(utente.getEtaria().equals(etaria)) {
					out.add(utente.getEtaria() + " " + utente.getNome().replace("_", " "));
				}
			}	
		}
		return out;
	}
//
	@Override
	public ArrayList<String> listarFamilias() {		
		ArrayList<String> out = new ArrayList<String>();		
		//if(arrFamilia.isEmpty()) {out.add("");}
		Set<String>keys = arrFamilia.keySet();
		for(String key:keys) {
			out.add(key);
		}
		return out;
	}


	@Override
	public String marcacao(List<String> command) {
		if (!isUtente(command.get(1))) return "Utente inexistente.";
		List<Servico> listaDeServicos = new ArrayList<>();
		List<String> ordemServicos = new ArrayList<>();
		String servicoASerAdicionado = "";

		//comecar so na posicao 2
		for (String c: command.subList(2, command.size())) {
			if (!c.contains(" ")) {
				if (!isServico(c)) return "Serviço inexistente.";
				listaDeServicos.add(new Servico(c, arrUtente.get(command.get(1))));
				ordemServicos.add(c);
				servicoASerAdicionado = c;
			} else {
				String[] h = c.split(" ");
				if (!isCategoria(h[0])) return "Categoria inexistente.";
				if (!isProfissional(h[0], h[1])) return "Profissional de saúde inexistente.";
				if (!isCategoriaValida(h[0], servicoASerAdicionado)) return "Categoria inválida.";
				switch (h[0]) {
					case "Medicina":
						listaDeServicos.get(listaDeServicos.size() - 1).adicionarProfissional(h[1], arrMedicina.get(h[1]));
						break;
					case "Enfermagem":
						listaDeServicos.get(listaDeServicos.size() - 1).adicionarProfissional(h[1], arrEnfermagem.get(h[1]));
						break;
					case "Auxiliar":
						listaDeServicos.get(listaDeServicos.size() - 1).adicionarProfissional(h[1], arrAuxiliar.get(h[1]));
						break;
				}
			}
		}
		if (!isSequenciaCorrecta(ordemServicos)) return "Sequência inválida.";
		arrMarcacoes.add(new Cuidado(arrUtente.get(command.get(1)), listaDeServicos));
		return "Cuidados marcados com sucesso.";
	}

	@Override
	public void cancelarCuidados(String s) {
		arrMarcacoes.removeIf(c -> c.getUtente().equals(s));
	}

	@Override
	public ArrayList<String> listarCuidados(String s) {
		ArrayList <String> out = new ArrayList<>();

		for (Cuidado c: arrMarcacoes) {
			if (c.getUtente().equals(s)) {
				out.addAll(c.getServicosPorOrdem());
			}
		}

		return out;
	}

	@Override
	public ArrayList<String> listarCuidadosPorFamilia(String s){
		ArrayList <String> out = new ArrayList<>();

		for (Familia f: arrFamilia.values()) {
			if (f.getNomeFamilia().equals(s)){
				for (Utente u: f.getMembros().values()){
					if (u.getEtaria().equals("Jovem")) {
						for (Cuidado c: arrMarcacoes) {
							if (c.getUtente().equals(u.getNome())){
								out.addAll(c.getServicosPorOrdemComNome());
							}
						}
					}
				}
				for (Utente u: f.getMembros().values()){
					if (u.getEtaria().equals("Adulto")) {
						for (Cuidado c: arrMarcacoes) {
							if (c.getUtente().equals(u.getNome())){
								out.addAll(c.getServicosPorOrdemComNome());
							}
						}
					}
				}
				for (Utente u: f.getMembros().values()){
					if (u.getEtaria().equals("Idoso")) {
						for (Cuidado c: arrMarcacoes) {
							if (c.getUtente().equals(u.getNome())){
								out.addAll(c.getServicosPorOrdemComNome());
							}
						}
					}
				}
			}
		}
		return out;
	}

	@Override
	public ArrayList<String> listarCuidadosAProfissional(String s) {
		ArrayList <String> out = new ArrayList<>();
		ArrayList <Servico> servicosList = new ArrayList<>();

		for (Cuidado c: arrMarcacoes) {
			for (Servico ser: c.getServicos()){
				if(ser.getProfissionaisAsString().contains(s)){
					servicosList.add(ser);
				}
			}
		}

		for (Servico ser: servicosList) {
			if (ser.getTipo().equals("Consulta")) {
				out.add(ser.getTipo() + " " + ser.getUtente());
			}
		}
		for (Servico ser: servicosList) {
			if (ser.getTipo().equals("PequenaCirurgia")) {
				out.add(ser.getTipo() + " " + ser.getUtente());
			}
		}
		for (Servico ser: servicosList) {
			if (ser.getTipo().equals("Enfermagem")) {
				out.add(ser.getTipo() + " " + ser.getUtente());
			}
		}
		return out;
	}

	@Override
	public ArrayList<String> listarMarcacoesPorServico(String s) {
		ArrayList<String> out = new ArrayList<>();

		for (Cuidado c : arrMarcacoes) {
			for (Servico serv : c.getServicos()) {
				if (serv.getTipo().equals(s)) {
					for (Profissional p : serv.getProfissionais()) {
						out.add(p.getCategoria() + " " + p.getNome() + " " + serv.getUtente());
					}
				}
			}
		}
		if (out.isEmpty()) {
			out.add("Serviço sem marcações.");
		}
		return out;
	}

	@Override
	public boolean isCarregado() {
		try {
			FileInputStream readData = new FileInputStream("usaude.txt");
			ObjectInputStream readStream = new ObjectInputStream(readData);
			arrMedicina  = (TreeMap<String, Profissional>) readStream.readObject();
			arrEnfermagem  = (TreeMap<String, Profissional>) readStream.readObject();
			arrAuxiliar  = (TreeMap<String, Profissional>) readStream.readObject();
			arrUtente  = (TreeMap<String, Utente>) readStream.readObject();
			arrFamilia  = (TreeMap<String, Familia>) readStream.readObject();
			arrMarcacoes  = (List<Cuidado>) readStream.readObject();

			readData.close();
			readStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean isGravado() {
		try {
			FileOutputStream f = new FileOutputStream(new File("usaude.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(arrMedicina);
			o.writeObject(arrEnfermagem);
			o.writeObject(arrAuxiliar);
			o.writeObject(arrUtente);
			o.writeObject(arrFamilia);
			o.writeObject(arrMarcacoes);

			f.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
