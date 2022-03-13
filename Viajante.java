/**
 * As instancias desta classe representam viajantes que podem
 * participar no grande premio sideral
 * 
 * @author minunes
 */
public class Viajante {
	private static final double GASTA_ANO_LUZ = 1;
	private String nome;
	private int pontuacao;
	private Ponto3D posicaoGlobal;
	private double combustivel;
	
	/**
	 * Inicializa o novo objeto
	 * @param nome O nome do viajante
	 * @param inicial A posicao inicial do viajante
	 * @param combustivel A quantidade de combustivel inicial
	 */
	public Viajante(String nome, Ponto3D inicial, double combustivel) {
		this.nome = nome;
		this.pontuacao = 0;
		this.posicaoGlobal = inicial;
		this.combustivel = combustivel;
	}

	/**
	 * O nome deste viajante
	 */
	public String nome() {
		return this.nome;
	}

	/**
	 * A pontuacao atual deste viajante
	 */
	public int pontuacao() {
		return this.pontuacao;
	}
	                    
	/**
	 * A posicao atual deste viajante
	 */
	public Ponto3D posicaoGlobal() {
		return this.posicaoGlobal;
	}

	/**
	 * Este viajante tem combustivel para viajar para outra posicao?
	 * @param destino A nova posicao
	 * @requires destino != null
	 * @return true se este viajante tem combustivel para viajar para destino
	 */
	public boolean podeViajar(Ponto3D destino) {
		return quantoGasta(destino) <= this.combustivel;
	}
	                    
	/**
	 * Muda a posicao atual deste viajante
	 * @param novaP A nova posicao 
	 * @requires podeViajar(novaP)
	 */
	public void mudaPosicaoGlobal(Ponto3D novaP) {
		this.combustivel -= quantoGasta(novaP);
		this.posicaoGlobal = novaP;
	}

    /**
     * Quanto combustivel gasta este viajante para viajar para
     * outra posicao?
     * @param novaP O destino
     * @return O combustivel que este viajante para viajar para novaP
     * @requires novaP != null
     */
	private double quantoGasta(Ponto3D novaP) {
		double distancia = this.posicaoGlobal.distancia(novaP);
		return GASTA_ANO_LUZ * distancia;
	}
	
	/**
	 * Adiciona um dado numero de pontos 'a pontuacao deste viajante
	 * @param pontos Os pontos a adicionar (podem ser negativos)
	 */
	public void registaPontos(int pontos) {
		this.pontuacao += pontos;
	}
	
	/**
	 * Representacao textual deste viajante
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: " + this.nome + "  Pontuacao: " + this.pontuacao +
				  "  Posicao: " + this.posicaoGlobal + "  Combustivel: " + 
				  this.combustivel + "\n");
		return sb.toString();
	}

}
