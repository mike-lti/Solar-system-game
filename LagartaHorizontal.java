public class LagartaHorizontal implements Direcionador {
    private CorpoCeleste[][] matriz;

    @Override
    public void defineUniverso(CorpoCeleste[][] m){
        this.matriz = m;
    }

    @Override
    public CorpoCeleste nEsimoElemento(int n){
        int counter = 0;
        CorpoCeleste c = null;
        for (int i = 0; i < this.matriz.length; i++){
            if (i % 2 == 0){
                for (int v = 0; v < this.matriz[i].length; v++){
                    counter++;
                    if(counter == n + 1){
                        
                        c = this.matriz[i][v];                       
                    }
                }            
            }else{
                for(int v = this.matriz[0].length - 1; v >= 0; v--){
                    counter++;
                    if(counter == n + 1){
                        
                        c = this.matriz[i][v];
                    }
                }
            }
        }
        return c;
    }
}
