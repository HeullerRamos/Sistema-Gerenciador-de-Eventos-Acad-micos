package org.acad;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Repositorio repositorio = Repositorio.getInstancia();

        Organizador x = new Organizador("Gu","g@","if");

        x.cadastrarEvento("x","y",
                LocalDate.now(),LocalDate.now(),"a",1);

        x.visualizarEventos();
    }

}