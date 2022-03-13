public class CorpoCeleste {
    protected double massa;
    protected Ponto3D pos;

    public CorpoCeleste (double massa, Ponto3D pos){
        this.massa = massa;
        this.pos = pos;

    }

    public double massa(){
        return this.massa;
    }

    public Ponto3D posicao(){
        return this.pos;
    }

    public double distancia(CorpoCeleste c){

        return this.posicao().distancia(c.posicao());
    }

    public boolean equals(Object other){

        Boolean returningB = false;
        
        
        if(other instanceof CorpoCeleste){
            CorpoCeleste cc ;
            cc = (CorpoCeleste) other;

            if(this.massa() == cc.massa() && this.posicao() == cc.posicao()){
                returningB = true;
            }
        }

        
        return returningB;
    }


}
