package controllers;

import projeto.Evento;
import projeto.Repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class ControllerEvento {

    //private Evento evento;
    Repositorio repositorio = Repositorio.getInstancia();

    public Evento criarEvento(String nome, String descricao, LocalDate dataInicio,
                              LocalDate dataFim, String local, int capacidade){
        Evento temp = new Evento(nome,descricao,dataInicio,dataFim,local,capacidade);
        repositorio.adicicionarEvento(temp);
        return temp;
    }

    public void imprimeEventos(){
        for(Evento evento : repositorio.getEventos()){
            System.out.println(evento);
        }
    }

}
