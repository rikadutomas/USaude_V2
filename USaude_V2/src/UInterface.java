import java.util.ArrayList;

public interface UInterface {
	boolean isCategoria(String categoria);
	boolean isProfissional(String categoria, String nome);
	void registarProfissional(String categoria, String profisisonal);
	ArrayList<String> listarProfissionais();
	boolean isEtaria(String etaria);
	boolean isUtente(String nome);
	void registarUtente(String nome, String etaria);
	ArrayList<String> listarUtentes();
	boolean isFamilia(String nomeFamilia);
	void registarFamilia(String string);
	boolean utenteInFamilia(String nome, String nomeFamilia);
	void associarFamilia(String nome, String nomeFamilia);
	boolean notFamilia(String nome);
	void desassociarFamilia(String nome);
	ArrayList<String> mostrarFamilia(String nomeFamilia);
	ArrayList<String> listarFamilias();
}
