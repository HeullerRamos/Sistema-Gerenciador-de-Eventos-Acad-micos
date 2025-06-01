package projeto;

import controllers.ControllerParticipante;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Repositorio repositorio = Repositorio.getInstancia();
        ControllerParticipante controllerParticipante = new ControllerParticipante();

        Participante x = controllerParticipante.cadastrarParticipante
                ("Gu", "g@", "if", "Organizador");

        System.out.println(x);
    }
}