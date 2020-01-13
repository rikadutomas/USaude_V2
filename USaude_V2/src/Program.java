import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		UInterface ui = new UControl();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if(input.isBlank()) {
				scanner.close();
				System.exit(0);
			}
			String command[] = input.split(" ");
			switch(command[0]) {
			
			// Registar Profissional
			case "XX":
				commandXX();
				break;
				
			case "RP":
				commandRP(ui, command);
			    break;
			
			case "LP":
				commandLP(ui);
			    break;
			    
			case "RU":
				commandRU(ui, command);
			    break;    
			
			case "LU":
				commandLU(ui);
			    break;    
			
			case "RF":
				commandRF(ui,command);
			    break;    
			
			case "AF":
				commandAF(ui,command);
			    break;    
			 
			case "DF":
				commandDF(ui,command);
			    break;    
			
			case "MF":
				commandMF(ui,command);
			    break;    
			case "LF":
				commandLF(ui);
			    break;    		    
			
			case "MC":
				commandMC(ui,command, scanner);
			    break;

			case "CC":
				commandCC(ui,command);
				break;

			case "LCU":
				commandLCU(ui,command);
				break;

			case "LCF":
				commandLCF(ui,command);
				break;

			case "LSP":
				commandLSP(ui,command);
				break;

			case "LMS":
				commandLMS(ui,command);
				break;

			case "G":
				commandG(ui);
				break;

			case "L":
				commandL(ui);
				break;

				default:
				System.out.println("Instrução Inválida.");	
			}
		}
	}

	private static void commandL(UInterface ui) {
		if (ui.isCarregado()){
			System.out.println("Unidade de saúde carregada.");
		} else {
			System.out.println("Ocorreu um erro no carregamento.");
		}
	}

	private static void commandG(UInterface ui) {
		if (ui.isGravado()){
			System.out.println("Unidade de saúde gravada.");
		} else {
			System.out.println("Ocorreu um erro na gravação.");
		}
	}

	// Chamada de Metodos
	private static void commandRP(UInterface ui, String[] command) {
		try {
			String categoria = command[1];
			String nome = command[2];
			if(ui.isCategoria(categoria)) {
				if(ui.isProfissional(categoria,nome)) {			
					System.out.println("Profissinal existente.");
				}
				else {
					ui.registarProfissional(categoria, nome);
					System.out.println("Profissional registado com sucesso.");
				}
			}
			else {
				System.out.println("Categoria inexistente.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução Inválida.");
		}	
	}
	
	private static void commandLP(UInterface ui) {
		ArrayList<String> out = ui.listarProfissionais();
		if(out.isEmpty()) {
			System.out.println("Sem profissionais registados.");
		}
		else {
			for(String outhere:out) {
				System.out.println(outhere);	
			}
		}		
	}

	private static void commandRU(UInterface ui, String[] command) {
		try {
			String nome = command[1];
			String etaria = command[2];
			if (ui.isEtaria(etaria)) {
				if(ui.isUtente(nome)) {
					System.out.println("Utente existente.");
				}
				else {
					ui.registarUtente(nome,etaria);
					System.out.println("Utente registado com sucesso.");
				}
			}
			else {
				System.out.println("Faixa etária inexistente.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução Inválida.");
		}	
	}

	private static void commandLU(UInterface ui) {
		ArrayList<String> out = ui.listarUtentes();
		if(out.isEmpty()) {
			System.out.println("Sem utentes registados.");
		}
		else {
			for(String outhere:out) {
				System.out.println(outhere);	
			}
		}		
	}

	private static void commandRF(UInterface ui, String[] command) {
		try {
			if (ui.isFamilia(command[1])) {
				System.out.println("Familia existente.");
			}
			else {
				ui.registarFamilia(command[1]);
				System.out.println("Familia registada com sucesso.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução Inválida.");
		}	
	}

	private static void commandAF(UInterface ui, String[] command) {
		try {
			String nome = command[1];
			String nomeFamilia = command[2];
			
			if (ui.isFamilia(nomeFamilia)) {
				if(ui.isUtente(nome)) {
					if(ui.utenteInFamilia(nome,nomeFamilia)) {
						System.out.println("Utente pertence a família.");					
					}
					else {
						ui.associarFamilia(nome,nomeFamilia);
						System.out.println("Utente associado a família.");
					}
				}
				else {
					System.out.println("Utente inexistente.");
				}			
			}
			else {
				System.out.println("Família inexistente.");
			}
		
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução Inválida.");
		}	
	}

	private static void commandDF(UInterface ui, String[] command) {
		try {
			String nome = command[1];
			if(ui.isUtente(nome)) {
				if (ui.notFamilia(nome)){
					System.out.println("Utente não pertence a família.");
				}
				else {
					ui.desassociarFamilia(nome);
					System.out.println("Utente desassociado de família.");
				}
			}
			else {
				System.out.println("Utente inexistente.");
			}				
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução Inválida.");
		}	
		
	}
	//// COMMANDO DE SEPARACAO DE TESTES	
	private static void commandXX() {
		System.out.println("---------------------------------------");
	}
	
	private static void commandMF(UInterface ui,String[] command) {
		try {
			if (ui.mostrarFamilia(command[1]).isEmpty()){
				System.out.println("Família inexistente.");
			}
			for (String nomeFamilia: ui.mostrarFamilia(command[1])) {
				System.out.println(nomeFamilia);
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Instrução Inválida.");
		}	
	}
	
	private static void commandLF(UInterface ui) {
		if(ui.listarFamilias().isEmpty()) {
			System.out.println("Sem famílias registadas.");
			return;
		}

		for (String nomeFamilia: ui.listarFamilias()) {
			if (nomeFamilia.equals("")){
				System.out.println("Família inexistente.");
			}
			else {
			System.out.println(nomeFamilia);
			}
		}
	}
//	
	private static void commandMC(UInterface ui,String[] command, Scanner scanner) {
		List<String> newCommands = new ArrayList<String>(Arrays.asList(command));

		//Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if(input.isBlank()) {
				//scanner.close();
				break;
			}
			newCommands.add(input);

		}
		System.out.println(ui.marcacao(newCommands));
	}

	private static void commandCC(UInterface ui, String[] command) {
		if (!ui.isUtente(command[1])) {
			System.out.println("Utente inexistente.");
			return;
		}
		/*if (!ui.temCuidados(command[1])) {
			System.out.println("Utente sem cuidados de saúde marcados.");
			return;
		}
		*/

		//check if tem cuidados
		ui.cancelarCuidados(command[1]);
		System.out.println("Cuidados de saúde desmarcados com sucesso.");
	}

	private static void commandLCU(UInterface ui, String[] command) {
		if (!ui.isUtente(command[1])){
			System.out.println("Utente inexistente.");
			return;
		}
		List<String> out = new ArrayList<>(ui.listarCuidados(command[1]));
		if (out.isEmpty()) {
			System.out.println("Utente sem cuidados de saúde marcados.");
		}
		for (String s: out) {
			System.out.println(s);
		}
	}

	private static void commandLCF(UInterface ui, String[] command) {
		if (!ui.isFamilia(command[1])){
			System.out.println("Família inexistente.");
			return;
		}
		List<String> out = new ArrayList<>(ui.listarCuidadosPorFamilia(command[1]));
		if (out.isEmpty()) {
			System.out.println("Família sem cuidados de saúde marcados.");
		}
		for (String s : out) {
			System.out.println(s);
		}

	}

	private static void commandLSP(UInterface ui, String[] command) {
		if (!ui.isProfissional(command[1], command[2])) {
			System.out.println("Profissional de saúde inexistente.");
			return;
		}
		List<String> out = ui.listarCuidadosAProfissional(command[2]);
		if (out.isEmpty()) {
			System.out.println("Profissional de saúde sem marcações.");
		}
		for (String s: out) {
			System.out.println(s);
		}
	}

	private static void commandLMS(UInterface ui, String[] command) {
		if (!ui.isServico(command[1])){
			System.out.println("Serviço inexistente.");
			return;
		}
		List<String> out = ui.listarMarcacoesPorServico(command[1]);
		if (out.isEmpty()) {
			System.out.println("Serviço sem marcações.");
		}
		for (String s: out) {
			System.out.println(s);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


}
	