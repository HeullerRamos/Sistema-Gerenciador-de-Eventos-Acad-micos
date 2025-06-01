package controllers;

import projeto.Evento;
import projeto.Organizador;
import projeto.Repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class ControllerEvento {
    Evento evento;
    //private Evento evento;
    Repositorio repositorio = Repositorio.getInstancia();

    public Evento criarEvento(String nome, String descricao, LocalDate dataInicio,
                              LocalDate dataFim, String local, int capacidade, Organizador organizador){
        Evento evento = new Evento(nome,descricao,dataInicio,dataFim,local,capacidade,organizador);
        repositorio.adicicionarEvento(evento);
        return evento;
    }

    public void imprimeEventos(){
        evento.imprimeEventos();
    }
    public   void imprimeInscritos(){
        evento.visualizarInscritos();
    }

}
