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
			case "RP":
				commandRP(ui, command);
			    break;
			
			case "LP":
				commandLP(ui);
			    break;
			default:
				System.out.println("Instrução invalida");	
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
			System.out.println("Instrução invalida");
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
}
	