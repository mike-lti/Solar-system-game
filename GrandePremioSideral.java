import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
public class GrandePremioSideral {
    private SistemaSolar ss;
    private HashMap<String, Viajante> jogs;
    private int PONTOS_BURACO_NEGRO = Integer.MAX_VALUE;
    private int TAXA_RISCO = 5;
    private int premioBase;

    public GrandePremioSideral(SistemaSolar ss, List<Viajante> jogs, int premioBase){
        this.ss = ss;
        for(Viajante v:jogs){
            this.jogs.put(v.nome(), v);
        }
        
        this.premioBase = premioBase;
    }

    public int premioBase(){
        return this.premioBase;
    }
    public void fazJogada(List<Par<String,Integer>> jogadas){
        //ver classe viajante tenho tudo o que preciso la
        for(int i = 0; i<jogadas.size(); i++){
            String currentName = "";
            Par<String,Integer> jogador = jogadas.get(i);
            for(String name : this.jogs.keySet()){
                if(jogador.primeiro().equals(name)){
                    currentName = name;
                }
            }
            Viajante vActual = this.jogs.get(currentName);
            CorpoCeleste c = this.ss.getElemento(jogador.segundo());
            if(c instanceof CorpoCeleste) {
                if(vActual.podeViajar(c.posicao())){
                    if(vActual.posicaoGlobal().equals(c.posicao())){
                        int pontosNovos = vActual.pontuacao() * -1/5;
                        vActual.registaPontos(pontosNovos);
                        this.jogs.put(currentName, vActual);
                    }else{
                        vActual.mudaPosicaoGlobal(c.posicao());
                        if(c instanceof BuracoNegro){
                            
                            vActual.registaPontos(- this.PONTOS_BURACO_NEGRO);
                            this.jogs.put(currentName, vActual);
                        }else{
                            BuracoNegro buracoProximo = this.ss.buracoNegroMaisPerto(c);
                            double distMin = buracoProximo.distanciaMinimaSeguranca(c);
                            double dist = buracoProximo.distancia(c);
                            if (dist < distMin){
                                int pontosToAdd = vActual.pontuacao() + premioBase() * this.TAXA_RISCO;
                                vActual.registaPontos(pontosToAdd);
                                this.jogs.put(currentName, vActual);
                            }else{
                                int pontosToAdd = vActual.pontuacao() + premioBase();
                                vActual.registaPontos(pontosToAdd);
                                this.jogs.put(currentName, vActual);
                            }
                        }


                    }
                }else{
                    int pontosNovos = vActual.pontuacao() * -1/5;
                    vActual.registaPontos(pontosNovos);
                    this.jogs.put(currentName, vActual);

                }
            }else{
                int pontosNovos = vActual.pontuacao() * -1/2;
                vActual.registaPontos(pontosNovos);
                this.jogs.put(currentName, vActual);
            }
        }
    }

    public List<String> vencedores(){
        List<String> returning = new ArrayList<String>(); 
        int highScore = 0;
        for(String nome : this.jogs.keySet()){
            if(this.jogs.get(nome).pontuacao() > highScore){
                returning.clear();
                returning.add(nome);
            }else if(this.jogs.get(nome).pontuacao() == highScore){
                returning.add(nome);
            }
        }

        return returning;
        
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("============ GRANDE PREMIO ============" + "\n");
        sb.append("Premio base: " + this.premioBase() + "\n");
        sb.append("Sistema Solar: " + "\n");
        sb.append(this.ss);
        sb.append("Viajantes:" + "\n");
        for(Viajante vj : this.jogs.values()){
            sb.append(vj + "\n");
        }
       
        
        
        return sb.toString();
    }


}
