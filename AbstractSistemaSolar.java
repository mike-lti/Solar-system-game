import java.util.List;

public abstract class AbstractSistemaSolar implements
SistemaSolar {
    protected String nome;

    public AbstractSistemaSolar(String nome){
        this.nome = nome;
    }
    
    @Override
    public String nome(){
        return this.nome;
    }

    @Override
    public Boolean podeVisitar(List<Integer> aVisistar){
        for(int e : aVisistar){
            if(e < 0 && e > quantosElementos()){
                 return false;
            }
        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: "+ this.nome + "\n");
        return sb.toString();
    }

}
