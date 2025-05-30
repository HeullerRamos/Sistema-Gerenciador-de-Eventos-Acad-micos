package org.acad;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerEvento {

    //private Evento evento;
    private final Repositorio repositorio = Repositorio.getInstancia();

    public void criarEvento(String nome, String descricao, LocalDate dataInicio,
                              LocalDate dataFim, String local, int capacidade){

        repositorio.adicicionarEvento(
                new Evento(nome,descricao,dataInicio,dataFim,local,capacidade));
    }

    public void listarEventos(){
        ArrayList<Evento> eventos = repositorio.getEventos();

        for (Evento evento: eventos){
            System.out.println(evento);
        }
    }
}
