import java.util.List;

public interface SistemaSolar{

    /**
     * 
     */
    String nome();

    /**
     * 
     */

    Boolean podeVisitar(List<Integer> aVisitar);

    /**
     * 
     */
    int quantosElementos();


    /**
     * 
     */
    CorpoCeleste getElemento(int n);


    /**
     * 
     */

    BuracoNegro buracoNegroMaisPerto(CorpoCeleste c);

    



}