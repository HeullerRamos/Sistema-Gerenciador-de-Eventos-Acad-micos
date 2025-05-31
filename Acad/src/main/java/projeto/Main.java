package projeto;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Repositorio repositorio = Repositorio.getInstancia();

        Organizador x = new Organizador("Gu","g@","if");

        x.cadastrarEvento("x","y",
                LocalDate.of(2025, 5, 28),
                LocalDate.of(2025, 12, 12),"a",1);

        x.cadastrarEvento("dsa","231",
                LocalDate.of(2025, 5, 28),
                LocalDate.of(2025, 5, 29),"a",1);

        x.visualizarEventos();

    }

}