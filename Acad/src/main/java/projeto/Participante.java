package projeto;

import controllers.ControllerEvento;

import java.util.ArrayList;
import java.util.UUID;

public class Participante {

    private UUID id;
    private String nome;
    private String email;
    private String instituicao;
    private String tipo;

    protected ControllerEvento controllerEvento = new ControllerEvento();
    public ArrayList<Evento> eventosCriado = new ArrayList<>();
    Participante(){}

    public Participante(String nome, String email, String instituicao,String tipo) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.instituicao = instituicao;
        this.tipo = tipo;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public ArrayList<Evento> visualizarEventos(){

        eventosCriado = controllerEvento.listarEventos();
        controllerEvento.imprimeEventos();
        return eventosCriado;
    }
    public Evento selecionarEvento(UUID id){
       Evento temp =  eventosCriado.stream().filter(evento -> evento.getId().equals(id)).findFirst().orElse(null);
       return temp;
    }
    public Inscricao participarEvento(UUID id){
        Inscricao temp = new Inscricao(true,this);
        return temp;
    }


}
