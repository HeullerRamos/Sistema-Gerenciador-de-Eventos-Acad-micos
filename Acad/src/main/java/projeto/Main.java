package projeto;

import controllers.ControllerParticipante;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Repositorio repositorio = Repositorio.getInstancia();
        ControllerParticipante controllerParticipante = new ControllerParticipante();

        Participante x = controllerParticipante.cadastrarParticipante
                ("Gu", "g@", "if", "Organizador");


        if (x instanceof Organizador) {
            Organizador organizador = (Organizador) x;
            organizador.cadastrarEvento("x", "y",
                    LocalDate.of(2025, 5, 28),
                    LocalDate.of(2025, 12, 12), "a", 1);

            organizador.cadastrarEvento("dsa", "231",
                    LocalDate.of(2025, 5, 28),
                    LocalDate.of(2025, 5, 29), "a", 1);
        }
        x.visualizarEventos();
    }
}