package org.acad;
import java.util.ArrayList;

public class Repositorio {

    private ArrayList<Participante> participantes = new ArrayList<>();

    private ArrayList<Evento> eventos = new ArrayList<>();

    private static Repositorio instancia;

    public  static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }

    public void adicicionarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public void adicicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }
}
