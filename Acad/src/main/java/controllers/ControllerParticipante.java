package controllers;

import projeto.*;

import java.util.ArrayList;
import java.util.UUID;

public class ControllerParticipante {
    private Participante participante;
    Evento evento;
    private ArrayList<Evento> eventosParticipou = new ArrayList<>();
    Repositorio repositorio = Repositorio.getInstancia();

    public ControllerParticipante(Participante participante) {
        this.participante = participante;
    }

    public  Participante cadastrarParticipante
            (String nome, String email, String instituicao, String tipo){
        if(tipo.equals("Organizador")){
            return new Organizador(nome,email,instituicao,tipo);
        }
        else if(tipo.equals("Avaliador")){
            //Tem que adicionar neh
        }
        tipo = "Participante";
        return new Participante(nome,email,instituicao,tipo);
    }

    public ArrayList<Evento> listarEventos(){
        ArrayList<Evento> eventos = repositorio.getEventos();
        //LocalDate hoje = LocalDate.now();
        return eventos;
    }

    public Evento selecionarEvento(int id){
       evento = participante.selecionarEvento(id);
       return evento;
    }
    public void imprimeEvento(Evento evento){
        System.out.println(evento);
    }

    public void participarEvento(Evento evento){
        participante.participarEvento(evento);
    }
    public void listaEventosParticipou(){
        eventosParticipou = participante.listaEventosParticipou();
    }

    public void impimprimirEventosCertificados(){
        participante.imprimirEventosCertificados();
    }

    public void emitirCertificado(int id){
        participante.emitirCertificado(id);
    }

    public void imprimirCertificados(){
        participante.imprimirCertificados();
    }

}
