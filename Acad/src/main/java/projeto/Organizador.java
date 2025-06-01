package projeto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organizador extends Participante{

    Repositorio repositorio = Repositorio.getInstancia();
    private ArrayList<Evento> meusEventosCriado = new ArrayList<>();

    public Organizador(String nome,String email,String instituicao,String tipo){
        super(nome,email,instituicao,tipo);
    }

    public void cadastrarEvento(String nome, String descricao,LocalDate dataInicio,
                                LocalDate dataFim, String local, int capacidade){

        Evento temp = new Evento(nome,descricao,dataInicio,dataFim,local,capacidade);
        meusEventosCriado.add(temp);
        repositorio.adicicionarEvento(temp);
    }

    public ArrayList<Evento> meusEventosCriados(){
        for(Evento evento : repositorio.getEventos()){

        }
        return meusEventosCriado;
    }

    //Tem que ir para um controller
    public void visualizarMeusEventos(){
        for (Evento evento : meusEventosCriado) {
            System.out.println(evento);
        };
    }

}
