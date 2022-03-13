import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Programa para testar as classes feitas pelos alunos na Fase 3
 * do trabalho de PCO
 * 
 * @author minunes
 * @date Novembro 2021
 */
public class PCOFase3 {

	/**
	 * Comeca por criar uma matriz de corpos celestes a partir de informacao lida
	 * do ficheiro de texto InfoSistema2.txt.
	 * Pergunta ao utilizador qual dos direcionadores disponiveis quer e de seguida
	 * constroi um sistema 2D. Constroi tambem um sistema 1D seguro baseado na mesma
	 * matriz de corpos celestes.
	 * De seguida invoca varios metodos sobre estes dois sistemas solares.
	 * Cria 2 viajantes e um grande premio e faz varias jogadas com valores pedidos
	 * ao utilizador (ate' um dos viajantes escolher 0, para terminar). No fim da
	 * prova sao anunciados os vencedores.
	 * De seguida e' criada outra matriz de corpos celestes a partir do ficheiro 
	 * InfoSistema2.txt, outros dois viajantes e outro grande premio com um sistema 2D. 
	 * Sao feitas varias jogadas nesta nova prova e no fim sao anunciados os vencedores.
	 * Finalmente e' criado um sistema 1D seguro baseado na mesma matriz e um novo grande
	 * premio e sao feitas algumas jogadas.
	 * @param args Nao utilizado
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Scanner leitor = new Scanner(System.in);		

		// Construir uma matriz de corpos celestes a partir de um ficheiro
		CorpoCeleste[][] m = obtemMatriz("InfoSistema2.txt");
		// Perguntar user qual o direcionador que pretende para decidir o percurso
		Direcionador d = umDirecionador(leitor);
		
		// Criar dois sistemas solares, um 2D e outro 1D seguro
		Sistema2D sistema = new Sistema2D("Orion", m, d);				
		Sistema1DSeguro sistemaSeguro = new Sistema1DSeguro("Orion", m);
		
		// Testar varios metodos sobre os dois sistemas solares
		testarAlgunsMetodos(sistema, sistemaSeguro);
		
		/* Ponto de inicio do proximo Grande Premio Sideral.
		 * Embora se va' usar o sistema 2D no Grande Premio, para ter a certeza 
		 * que o planeta inicial nao e' null nem buraco negro, vamos pedir o
		 * primeiro corpo celeste ao sistema 1D seguro, pois foi construido sobre
		 * a mesma matriz de corpos celestes */
		Ponto3D inicioGP = sistemaSeguro.getElemento(1).posicao(); 
		// Criar dois viajantes
		Viajante v1 = new Viajante("Han Solo", inicioGP, 50);
		Viajante v2 = new Viajante("Chewbacca", inicioGP, 50);
		List<Viajante> jogs = new ArrayList<Viajante>();
		jogs.add(v1);
		jogs.add(v2);

		// E agora... Vamos dar inicio ao Grande Premio Sideral!
		GrandePremioSideral gp = new GrandePremioSideral(sistema, jogs, 2000);
		System.out.println(gp.toString());
		
		realizarProva(leitor, sistema.quantosElementos(), jogs, gp);
		System.out.println("===================");

		/*
		 * Usamos agora o ficheiro InfoSistema1.txt para criar a  
		 * matriz de um novo sistema solar e um novo grande premio
		 */
		Scanner leitor1 = new Scanner(System.in);
		CorpoCeleste[][] m1 = obtemMatriz("InfoSistema1.txt");
		Direcionador d1 = umDirecionador(leitor1);		
		SistemaSolar sistema1 = new Sistema2D("Sirius", m1, d1);		

		Ponto3D inicioGP1 = sistema1.getElemento(1).posicao(); 
		Viajante v11 = new Viajante("Luke Skywalker", inicioGP1, 30);
		Viajante v21 = new Viajante("Darth Vather", inicioGP1, 30);
		List<Viajante> jogs1 = new ArrayList<Viajante>();
		jogs1.add(v11);
		jogs1.add(v21);

		GrandePremioSideral gp1 = new GrandePremioSideral(sistema1, jogs1, 500);
		System.out.println(gp1.toString());
		
		realizarProva(leitor1, sistema1.quantosElementos(), jogs1, gp1);
		System.out.println("===================");

		// Criar o sistema 1D seguro baseado na mesma matriz e
		// um novo grande premio realizado sobre este sistema seguro
		leitor1 = new Scanner(System.in);
		SistemaSolar sistemaSeguro1 = new Sistema1DSeguro("Sirius", m1);

		inicioGP1 = sistemaSeguro1.getElemento(1).posicao(); 
		v11 = new Viajante("Luke Skywalker", inicioGP1, 30);
		v21 = new Viajante("Darth Vather", inicioGP1, 30);
		jogs1 = new ArrayList<Viajante>();
		jogs1.add(v11);
		jogs1.add(v21);

		gp1 = new GrandePremioSideral(sistemaSeguro1, jogs1, 300);
		System.out.println(gp1.toString());
		
		realizarProva(leitor1, sistemaSeguro1.quantosElementos(), jogs1, gp1);
		System.out.println();
	}

	/***********************************************************************/
	/************************   OUTROS METODOS   ***************************/
	/***********************************************************************/

	/**
	 * Ler informacao para criar uma matriz de corpos celestes a partir de um 
	 * ficheiro
	 * Formato do ficheiro: - 1 linha com 2 inteiros definindo o numero de linhas(l) 
	 *                        e colunas(c) da matriz; 
	 *                      - restantes lxc linhas:
	 *                        - um double indicando a massa
	 *                        - se o valor da massa for > 0, ainda:
	 *                         	 - um inteiro indicando o tipo de corpo celeste (1 e' 
	 *                             normal; 2 e' buraco negro)
	 *                           - 3 inteiros indicando as coordenadas da posicao 
	 *                             do corpo celeste
	 * @param nomeFich Nome do ficheiro a ser lido
	 * @throws FileNotFoundException se nao existir ficheiro acessivel com o 
	 *         nome nomeFich
	 */
	private static CorpoCeleste[][] obtemMatriz(String nomeFich) throws FileNotFoundException {

		Scanner leitor = new Scanner(new FileReader(nomeFich));

		int lin = leitor.nextInt();
		int col = leitor.nextInt();
		CorpoCeleste[][] result = new CorpoCeleste[lin][col];

		for(int i = 0 ; i < lin ; i++) {
			for(int j = 0 ; j < col ; j++) {
				CorpoCeleste c = null;
				double massa = leitor.nextDouble();
				if(massa > 0) {
					int tipo = leitor.nextInt();
					Ponto3D posicao = new Ponto3D(leitor.nextInt(),
							leitor.nextInt(), leitor.nextInt());
					if(tipo == 1) {
						c = new CorpoCeleste(massa, posicao);										
					} else {
						c = new BuracoNegro(massa, posicao);									
					}
				}
				result[i][j] = c;
			}
		}
				 
		return result;
	}

	/**
	 * Pedir ao utilizador qual o tipo de Direcionador que quer usar
	 * e construir e devolver um objeto desse tipo
	 * @param leitor O canal de leitura
	 * @return Um objeto do tipo Direcionador cujo subtipo e' decidido
	 *         pelo utilizador
	 */
	private static Direcionador umDirecionador(Scanner leitor) {

		System.out.println("Tipos de Direcionador acessiveis:");
		// invoca metodo que retorna lista de Direcionadores existentes
		List<String> direcionadores = tiposDirecionadorExistentes();
		for(String s : direcionadores) {
			System.out.println(s);
		}
		System.out.println("Escreva o nome do que quer escolher.");
		String nome = leitor.nextLine();
		System.out.println("Tipo escolhido: " + nome);
		return obtemDirecionador(nome);
	}

	/**
	 * Lista dos nomes das subclasses de Direcionador existentes, que
	 * estao referidas no ficheiro configuracao.properties
	 * @return
	 */
	private static List<String> tiposDirecionadorExistentes() {

		String classesDirecionador = "";
		// Array que vai conter os nomes dos direcionadores. E' inicializado
		// ja' com um elemento para o caso de nao ser encontrado o fich de 
		// configuracao
		String[] nomes = {"LinhaALinha"};
		Properties prop = new Properties ();	
		try {
			prop.load(new FileInputStream("configuracao.properties") );
			classesDirecionador = prop.getProperty("direcionadores");	
			nomes = classesDirecionador.split(";");
		} catch (IOException e1) {
			System.out.println(classesDirecionador);
		}	
		return Arrays.asList(nomes);
	}

	/**
	 * Constroi um Direcionador a partir do nome da classe
	 * (usando carregamento dinamico de classes)
	 * @param tipoDesejado O tipo do Direcionador a criar
	 * @requires tipoDesejado != null
	 * @return Um Direcionador do tipo indicado por tipoDesejado, caso 
	 *         exista; caso contrario eh retornado um do tipo LinhaALinha
	 */
	private static Direcionador obtemDirecionador(String tipoDesejado) {
		Direcionador result;
		try {
			// Quando as classes estao no default package.
			// Se nao estiverem, tera' que se acrescentar o path.
			// Exemplo: "projeto3." + tipoDesejado
			Constructor construtor = 
					Class.forName(tipoDesejado).getDeclaredConstructor();
			result = (Direcionador) construtor.newInstance();
		} catch (Exception e) {	// caso nao encontre a classe desejada
			System.out.println("Exception " + tipoDesejado);
			result = new LinhaALinha();		
		}
		return result;
	}

	/**
	 * Testar a invocacao de alguns metodos sobre dois sistemas solares
	 * @param sistema Um sistema 2D
	 * @param sistemaSeguro Um sistema 1D seguro
	 * @requires sistema != null && sistemaSeguro != null
	 */
	private static void testarAlgunsMetodos(Sistema2D sistema, Sistema1DSeguro sistemaSeguro) {
		System.out.println("=========  Sistema 2D  ==========");
		System.out.println(sistema.toString());
		System.out.println("======  Sistema 1D seguro   =======");
		System.out.println(sistemaSeguro.toString());
		System.out.println();

		List<Integer> visitas = Arrays.asList(new Integer[]{2,15,24});		
		System.out.println("Sistema pode visitar " + visitas.toString() + "? " +
		                   sistema.podeVisitar(visitas));
		System.out.println("Sistema seguro pode visitar" + visitas.toString() + "? " + 
		                   sistemaSeguro.podeVisitar(visitas));
		System.out.println();
		
		CorpoCeleste corpo = sistema.getElemento(24);
		BuracoNegro bn = sistema.buracoNegroMaisPerto(corpo);
		System.out.println("Corpo celeste: " + corpo.posicao() + 
				           "   Buraco negro mais perto: " + bn.posicao());
		System.out.println("Distancia entre eles: " + corpo.distancia(bn));
		
		
		corpo = sistemaSeguro.getElemento(4);
		bn = sistemaSeguro.buracoNegroMaisPerto(corpo);
		System.out.println("Corpo celeste: " + corpo.posicao() + 
		           "   Buraco negro mais perto: " + bn.posicao());
		System.out.println("Distancia entre eles: " + corpo.distancia(bn));
		System.out.println();
	}

	/**
	 * Realizar um dado grande premio
	 * @param leitor O canal de leitura
	 * @param quantosElementos Numero de elementos que o sistema solar tem
	 * @param jogs Os viajantes que participam da prova
	 * @param gp O grande premio a realizar
	 * @requires leitor != null && jogs != null && gp != null
	 */
	private static void realizarProva(Scanner leitor, int quantosElementos, 
			List<Viajante> jogs, GrandePremioSideral gp) {
		int nJogadas = 0;
		List<Par<String,Integer>> jogadas = 
				jogadasViajantes(jogs, leitor,quantosElementos);	
		boolean doisEmJogo = jogadas.size() >= 2;
		
		while(doisEmJogo) {
			gp.fazJogada(jogadas);
			nJogadas++;
			imprimeJogadores(jogs);
			jogadas = jogadasViajantes(jogs, leitor,quantosElementos);	
			doisEmJogo = jogadas.size() >= 2;
		}

		if(nJogadas > 0) {
			List<String> vencedores = gp.vencedores();
			System.out.println("And the winners are:");
			for(String nome : vencedores) {
				System.out.println(nome);
			}
		} else {
			System.out.println("Prova cancelada");
		}
	}

	private static void imprimeJogadores(List<Viajante> jogs) {
		for(Viajante v : jogs) {
			System.out.print(v.toString());
		}
		System.out.println();
	}

	/************************************************************************
	 *********   METODOS PARA LER INFO DURANTE O GRANDE PREMIO  *************
	 ************************************************************************/

	/**
	 * Pedir ao utilizador as jogadas dos varios participantes do Grande Premio
	 * @param jogs Os participantes
	 * @param leitor O canal de leitura
	 * @param maximo O valor maximo de uma jogada
	 * @return
	 */
	private static List<Par<String, Integer>> jogadasViajantes(
			                  List<Viajante> jogs, Scanner leitor, int maximo) {
		List<Par<String, Integer>> result = new ArrayList<Par<String, Integer>>();
		for(Viajante v : jogs) {
			System.out.print("Qual a posicao para onde o "
					+ v.nome() + " quer ir (0 para terminar)? ");
			System.out.println("Tem que ser um inteiro entre 0 e " + maximo);
			int jogada = lerValorNoIntervalo(0, maximo, 
					"Tem que ser um inteiro entre 0 e " + 
                            maximo + "!", leitor);
			if(jogada > 0) {
				result.add(new Par<String, Integer>(v.nome(), jogada));				
			}
		}
		return result;
	}
	

    /**
     * Primeiro inteiro no canal de leitura que esta' num dado intervalo
     * @param infLim   Limite inferior do intervalo
     * @param supLim  Limite superior do intervalo
     * @param errMess  Mensagem de  erro a apresentar no System.out
     * @param sc  Canal de leitura
     * @return um valor entre infLim e supLim
     * @requires sc != null && infLim <= supLim && errMess != null
     */
    static int lerValorNoIntervalo(int infLim, int supLim, 
                                   String errMess, Scanner sc) {
        int valor = 0;
        boolean erro;
        do {
            valor = lerInteiro ( errMess, sc );
            erro = valor < infLim || valor > supLim;
            if ( erro )
                System.out.println ( errMess );
        } while ( erro );

        return valor;
    }

    /**
     * Primeiro inteiro no canal de leitura
     * @param errMess - mensagem a escrever no System.out caso o valor 
     *                  acessivel no canal de leitura nao seja um inteiro
     * @param sc - canal de leitura
     * @return valor inteiro
     * @requires errMess != null && sc != null
     */
    static int lerInteiro ( String errMess, Scanner sc ) {
        int valor = 0;
        boolean erro = true;
        do {
            if ( sc.hasNextInt () ) {
                valor = sc.nextInt ();  // consome o inteiro
                erro = false;
            } else {
                sc.next ();      // consome o que lah esteja
                System.out.println ( errMess );
            }				
        } while ( erro );

        return valor;
    }

}
