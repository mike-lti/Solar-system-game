public class Sistema1DSeguro extends AbstractSistemaSolar{
    private CorpoCeleste[][] m;
    private LinhaALinha d;

    public Sistema1DSeguro(String nome, CorpoCeleste[][] m){
        super(nome);
        this.m = m;
        

    }


    public int quantosElementos(){
        int counter = 0;
        for(CorpoCeleste [] e : this.m){
            for(CorpoCeleste i : e){
                counter ++;
            }
        }
        return counter;
    }

    public CorpoCeleste getElemento(int n){
        return this.d.nEsimoElemento(n);
    }

    public BuracoNegro buracoNegroMaisPerto(CorpoCeleste c){
        //duvidas este metodo (quanto ha implementacao)
        BuracoNegro bn = null;
        for(int i = 0; i<this.m.length; i++){
            for(int v = 0; v<this.m[i].length; v++){
                if (this.m[i][v] instanceof BuracoNegro){
                    BuracoNegro newObj = new BuracoNegro(this.m[i][v].massa(), this.m[i][v].posicao());
                    if(bn == null){
                        
                        bn = newObj;
                    }else{
                        if(bn.distancia(c) > newObj.distancia(c)){
                            bn = newObj;
                        }
                    }
                }
            }
        }
        return bn;
    }

    @Override
    public String toString(){    
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: " + nome + "\n");
        sb.append("Planetas:"+ "\n");
        for(int i = 0; i < this.m.length; i++){
            sb.append(this.m[i] + " ");
        }
        return sb.toString();
    }


}
