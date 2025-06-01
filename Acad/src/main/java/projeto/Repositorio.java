package projeto;
import java.util.ArrayList;
import java.util.UUID;

public class Repositorio {
    private static final Repositorio instancia = new Repositorio();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Organizador> organizadores = new ArrayList<>();
    private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
    private ArrayList<Certificado> certificados = new ArrayList<>();

    private Repositorio(){

    }
    public static Repositorio getInstancia(){
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

    public Evento selecionarEvento(int id){
       Evento temp = eventos.stream().filter(evento -> evento.getId()==id).findFirst().orElse(null);
        return temp;
    }
    public void adicicionarOrganizador(Organizador organizador) {
        organizadores.add(organizador);
    }
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public void adicionarCertificado(Certificado certificado) {
        certificados.add(certificado);
    }

    public ArrayList<Certificado> getCertificados() {
        return certificados;
    }
}
