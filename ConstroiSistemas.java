import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/**
 * Esta classe contem um main que permite criar um ficheiro de texto
 * contendo informacao gerada de forma aleatoria para ser usada para
 * criar uma matriz de corpos celestes.
 * 
 * Os ficheiros InfoSistema1.txt e InfoSistema2.txt disponibilizados
 * aos alunos foram criados atraves da execução deste metodo main.
 * 
 * @author minunes
 */
public class ConstroiSistemas {

	/**
	 * Constroi um ficheiro com info para um sistema solar:
	 * Formato do ficheiro: - 1 linha com 2 inteiros definindo o numero de linhas(l) 
	 *                        e colunas(c) da matriz; 
	 *                      - restantes lxc linhas:
	 *                        - um double indicando a massa
	 *                        - se o valor da massa for > 0, ainda:
	 *                         	 - um inteiro indicando o tipo de corpo celeste (1 e' 
	 *                             normal; 2 e' buraco negro)
	 *                           - 3 inteiros indicando as coordenadas da posicao 
	 *                             do corpo celeste
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Random g = new Random();
		Scanner leitor = new Scanner(System.in);
		System.out.println("Qual o numero do ficheiro?");
		int n = leitor.nextInt();
		PrintWriter f = new PrintWriter("InfoSistema" + n + ".txt");
		System.out.println("Quantas linhas?");
		int lin = leitor.nextInt();
		System.out.println("Quantas colunas?");
		int col = leitor.nextInt();
		
		f.println(lin + " " + col);
		for(int i = 0 ; i < lin ; i++) {
			for(int j = 0 ; j < col ; j++) {
				// massa negativa?
				boolean negativa = g.nextBoolean();
				if(negativa) {
					f.println(-1);				
				} else {
					double massa;
					// tipo de corpo celeste
					int tipo = g.nextInt(4);
					// massa
					if(tipo == 0) { // Buraco Negro
						tipo = 2;
						massa = g.nextInt(8) + 1;
					} else {
						tipo = 1;
						massa = g.nextInt(4) + 1;					
					}
					// coordenada z da posicao
					int z = g.nextInt(30);
					f.println(massa + " " + tipo + " " + i + " " + j + " " + z);
				}
			}
		}
		f.close();
	}
}
