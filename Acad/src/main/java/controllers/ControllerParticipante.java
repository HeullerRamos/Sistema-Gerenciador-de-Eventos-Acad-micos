package controllers;

import projeto.*;

import java.util.ArrayList;
import java.util.UUID;

public class ControllerParticipante {
    private Participante participante;
    Repositorio repositorio = Repositorio.getInstancia();
    public static Participante cadastrarParticipante
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

    public Evento selecionarEvento(UUID id){
        Evento temp = repositorio.selecionarEvento(id);
        return temp;
    }

    public Inscricao participarEvento(UUID idEvento){
        participante.participarEvento(idEvento);
    }


}
