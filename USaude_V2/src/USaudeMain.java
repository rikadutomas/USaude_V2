import java.util.ArrayList;
import java.util.Scanner;

public class USaudeMain {
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
				commandMC(ui,command);
			    break;    
			    
			    
			default:
				System.out.println("Instrução inválida.");	
			}
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
					System.out.println("Profissinal registado com sucesso.");
				}
			}
			else {
				System.out.println("Categoria inexistente.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução inválida.");
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
			System.out.println("Instrução inválida");
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
				System.out.println("Família existente.");
			}
			else {
				ui.registarFamilia(command[1]);
				System.out.println("Família registada com sucesso.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Instrução inválida.");
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
			System.out.println("Instrução inválida.");
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
			System.out.println("Instrução inválida.");
		}	
		
	}
	//// COMMANDO DE SEPARACAO DE TESTES	
	private static void commandXX() {
		System.out.println("---------------------------------------");
	}
	
	private static void commandMF(UInterface ui,String[] command) {
		try {
			for (String nomeFamilia: ui.mostrarFamilia(command[1])) {
				if (nomeFamilia.equals("")){
					System.out.println("Família inexistente.");
				}
				System.out.println(nomeFamilia);
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Instrução inválida.");
		}	
	}
	
	private static void commandLF(UInterface ui) {
		for (String nomeFamilia: ui.listarFamilias()) {
			if (nomeFamilia.equals("")){
				System.out.println("Família inexistente.");
			}
			else {
			System.out.println(nomeFamilia);
			}
		}
	}
	
	private static void commandMC(UInterface ui,String[] command) {
		int flag = 0;
		String nome = command[1];
		flag = ui.iniciarMarcacao(nome);
		//strSender();
	}
	
	private static void strSender(UInterface ui) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String input = scanner.nextLine();
			if(input.isBlank()) {
				scanner.close();
				break;
			}
			String command[] = input.split(" ");
			ui.marcacao(command);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
	