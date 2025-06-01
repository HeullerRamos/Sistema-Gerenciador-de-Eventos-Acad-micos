package controllers;

import projeto.Organizador;

import java.time.LocalDate;

public class ControllerOrganizador {
    private Organizador organizador;
    public void cadastrarEvento (String nome, String descricao, LocalDate dataInicio,
                                 LocalDate dataFim, String local, int capacidade){
        organizador.cadastrarEvento(nome,descricao,dataInicio,dataFim,local,capacidade);
    }

}
