package projeto;

import java.util.ArrayList;
import java.util.Objects;

public class Avaliador extends Participante {

    private ArrayList<Evento> eventosParaAvaliar;

    public Avaliador (String nome, String email){
        super(nome,email,"","Avaliador");
    }

    public void setEventosParaAvaliar(Evento evento) {
        eventosParaAvaliar.add(evento);
    }

    public ArrayList<Evento> avaliarEvento(){
        return eventosParaAvaliar;
    }

    public void imprimirEventosAvaliar(){
        for(Evento evento : eventosParaAvaliar){
            System.out.println(evento);
        }
    }

    public ArrayList<Trabalho> listarTrabalhos(Evento evento){
        return evento.getTrabalhos();
    }

    public void imprimirTrabalhosAvaliar(){
        if(eventosParaAvaliar.isEmpty()){
            System.out.println("Você não possui trabalhos pendentes de avaliação no momento.");
        }else{
            for(Evento evento : eventosParaAvaliar){
                System.out.println(evento);
            }
        }
    }

    public Trabalho selecionarTrabalho(ArrayList<Trabalho> trabalho,Evento evento){
        Trabalho temp =  trabalho.stream()
                .filter(t -> Objects.equals(t.getId(), evento.getId()))
                .findFirst()
                .orElse(null);
        return temp;
    }

    public Avaliacao avaliarTrabalho(Trabalho trabalho,float nota, String comentario){
        Avaliacao avaliacao = new Avaliacao(nota,comentario);
        if(nota >= 6) trabalho.setAprovado(true);
        repositorio.adicionarAvaliacao(avaliacao);
        trabalho.setAvaliacao(avaliacao);
        return avaliacao;
    }
}