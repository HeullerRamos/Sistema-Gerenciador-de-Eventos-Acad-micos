package controllers;

import projeto.Evento;
import projeto.Organizador;

import java.time.LocalDate;

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
    public Evento selecionarEvento(int id){
       return evento = organizador.selecionarEvento(id);
    }
    public void adicionarPresenca(int idAluno,boolean presenca){
        evento.setaPresenca(idAluno,presenca);
    }
    public void definiPeriodoSubmicao(LocalDate dataInicio,LocalDate dataFim){
        evento.setPeriodoInscricaoIni(dataInicio);
        evento.setPeriodoInscricaoFim(dataFim);
    }

}
