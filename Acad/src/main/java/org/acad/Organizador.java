package org.acad;

import java.time.LocalDate;

public class Organizador extends Participante{

    Organizador(String nome,String email,String instituicao){
        super(nome,email,instituicao);
    }

    public void cadastrarEvento(String nome, String descricao,LocalDate dataInicio,
                                LocalDate dataFim, String local, int capacidade){

        controllerEvento.criarEvento(nome, descricao,dataInicio,dataInicio,local,capacidade);
    }
}
