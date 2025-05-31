package projeto;

import controllers.ControllerEvento;
import java.util.UUID;

public class Participante {

    private UUID id;
    private String nome;
    private String email;
    private String instituicao;
    protected ControllerEvento controllerEvento = new ControllerEvento();

    Participante(){}

    Participante(String nome, String email, String instituicao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.instituicao = instituicao;
    }

    //Isso aqui tem que ir para um controller acredito
    public static Participante cadastrarParticipante(String nome, String email, String instituicao){
        return new Participante(nome,email,instituicao);
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

    public void visualizarEventos(){
        controllerEvento.listarEventos();
    }
}
