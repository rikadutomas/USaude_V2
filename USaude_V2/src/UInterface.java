import java.util.ArrayList;
import java.util.List;

public interface UInterface {
	boolean isCategoria(String categoria);
	boolean isServico(String servico);
	boolean isCategoriaValida(String categoria, String servico);
	boolean isSequenciaCorrecta(List<String> currentSequence);
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
	String marcacao(List<String> command);
	void cancelarCuidados(String s);
	ArrayList<String> listarCuidados(String s);
	ArrayList<String> listarCuidadosPorFamilia(String s);
	ArrayList<String> listarCuidadosAProfissional(String s);
	ArrayList<String> listarMarcacoesPorServico(String s);
	boolean isCarregado();
	boolean isGravado();
}
