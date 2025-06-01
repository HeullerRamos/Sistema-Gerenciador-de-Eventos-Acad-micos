package controllers;

import projeto.Evento;
import projeto.Organizador;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerOrganizador {
    private Organizador organizador;
    private Evento evento;

    public ControllerOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public void cadastrarEvento (String nome, String descricao, LocalDate dataInicio,
                                 LocalDate dataFim, String local, int capacidade, Organizador organizador) {
        organizador.cadastrarEvento(nome,descricao,dataInicio,dataFim,local,capacidade,organizador);
    }

    public ArrayList<Evento> meusEventosCriados(){
        return organizador.getMeusEventosCriado();
    }

    public void visualizarMeusEventos(){
        organizador.visualizarMeusEventos();
    }

    public Evento selecionarEvento(int id){
       return evento = organizador.selecionarEvento(id);
    }
    public void adicionarPresenca(Evento evento, int idParticipante,boolean presenca){
        organizador.adicionarPresenca(evento,idParticipante,presenca);
    }
    public void definiPeriodoSubmicao(LocalDate dataInicio,LocalDate dataFim){
        evento.setPeriodoInscricaoIni(dataInicio);
        evento.setPeriodoInscricaoFim(dataFim);
    }
    public void designarAvaliador(String nome, String email){
        organizador.designarAvaliador(nome,email,evento);

    }

}
