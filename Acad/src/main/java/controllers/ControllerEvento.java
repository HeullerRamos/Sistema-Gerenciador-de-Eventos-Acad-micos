package controllers;

import projeto.Evento;
import projeto.Repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerEvento {

    //private Evento evento;
    private final Repositorio repositorio = Repositorio.getInstancia();

    public Evento criarEvento(String nome, String descricao, LocalDate dataInicio,
                              LocalDate dataFim, String local, int capacidade){
        Evento temp = new Evento(nome,descricao,dataInicio,dataFim,local,capacidade);
        repositorio.adicicionarEvento(temp);
        return temp;
    }

    public void listarEventos(){
        ArrayList<Evento> eventos = repositorio.getEventos();
        LocalDate hoje = LocalDate.now();

        for (Evento evento: eventos){
            if ((hoje.isEqual(evento.getDataInicio()) || hoje.isAfter(evento.getDataInicio()))
                    && ((hoje.isEqual(evento.getDataFim()))
                    ||  (hoje.isBefore(evento.getDataFim())))){
                System.out.println(evento);
            }
        }
    }
}
