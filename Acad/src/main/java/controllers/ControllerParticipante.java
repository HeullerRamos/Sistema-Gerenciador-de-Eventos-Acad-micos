package controllers;

import projeto.Organizador;
import projeto.Participante;

public class ControllerParticipante {

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

}
