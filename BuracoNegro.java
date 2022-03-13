public class BuracoNegro extends CorpoCeleste {
    
    public BuracoNegro(Double massa, Ponto3D pos){
        super(massa, pos);
    }

    public double distanciaMinimaSeguranca(CorpoCeleste c){
        return Math.sqrt(this.massa() * c.massa());
    }




}
