package projeto;
import java.util.ArrayList;
import java.util.UUID;

public class Repositorio {
    private static final Repositorio instancia = new Repositorio();
    private Repositorio(){

    }
    public static Repositorio getInstancia(){
        return instancia;
    }
    private ArrayList<Participante> participantes = new ArrayList<>();

    private ArrayList<Evento> eventos = new ArrayList<>();



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

    public Evento selecionarEvento(int id){
       Evento temp = eventos.stream().filter(evento -> evento.getId().equals(id)).findFirst().orElse(null);
        return temp;
    }
}
