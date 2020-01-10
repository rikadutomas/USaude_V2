import java.util.ArrayList;

public interface UInterface {
	boolean isCategoria(String categoria);
	boolean isProfissional(String categoria, String nome);
	void registarProfissional(String categoria, String profisisonal);
	ArrayList<String> listarProfissionais();
	
}
