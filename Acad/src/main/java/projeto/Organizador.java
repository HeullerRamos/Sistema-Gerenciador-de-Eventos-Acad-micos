package projeto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organizador extends Participante{

    private ArrayList<Evento> meusEventosCriado = new ArrayList<>();

    Organizador(String nome,String email,String instituicao){
        super(nome,email,instituicao);
    }

    public void cadastrarEvento(String nome, String descricao,LocalDate dataInicio,
                                LocalDate dataFim, String local, int capacidade){

        meusEventosCriado.add(controllerEvento.criarEvento
                (nome, descricao,dataInicio,dataFim,local,capacidade));
    }

    public ArrayList<Evento> meusEventosCriados(){
        return meusEventosCriado;
    }

    //Tem que ir para um controller
    public void visualizarMeusEventos(){
        for (Evento evento : meusEventosCriado) {
            System.out.println(evento);
        };
    }
}
