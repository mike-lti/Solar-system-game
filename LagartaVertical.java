public class LagartaVertical implements Direcionador {
    private CorpoCeleste[][] matriz;

    @Override
    public void defineUniverso(CorpoCeleste[][] m){
        this.matriz = m;
    }
    @Override
    public CorpoCeleste nEsimoElemento(int n){
        int counter = 0;
        CorpoCeleste c = null;
        for (int i = 0; i < this.matriz[0].length; i++){
            if (i % 2 == 0){
                for (CorpoCeleste[] cc: this.matriz){
                    counter++;
                    if(counter == n + 1){
                        
                        c = cc[i];                       
                    }
                }            
            }else{
                for(int v = this.matriz.length - 1; v >= 0; v--){
                    counter++;
                    if(counter == n + 1){
                        
                        c = this.matriz[v][i];
                    }
                }
            }
        }
        return c;
    }
}
