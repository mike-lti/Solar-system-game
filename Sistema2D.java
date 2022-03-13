public class Sistema2D extends AbstractSistemaSolar {
    private CorpoCeleste[][] m;
    private Direcionador d;
    public Sistema2D(String nome, CorpoCeleste[][] m, Direcionador d){
        super(nome);
        this.m = m;
        this.d = d;
    }

    public int quantosElementos(){
        return this.m.length * this.m[0].length;
    }

    public CorpoCeleste getElemento(int n){
        //verificar outra vez (especial atenção sem certeza de correta implementacao)
        this.d.defineUniverso(m);
        return this.d.nEsimoElemento(n);
    }

    public BuracoNegro buracoNegroMaisPerto(CorpoCeleste c){
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
        sb.append("Direcionador:"+ this.d);
        for (CorpoCeleste[] c : this.m){
            sb.append("\n");
            for(int i = 0; i < this.m.length; i++){
                sb.append(c[i] + " ");
            }
        }
        return sb.toString();
    }

}
