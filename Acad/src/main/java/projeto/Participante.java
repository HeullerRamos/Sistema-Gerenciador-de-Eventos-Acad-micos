package projeto;

import controllers.ControllerEvento;
import java.util.UUID;

public class Participante {

    private UUID id;
    private String nome;
    private String email;
    private String instituicao;
    private String tipo;

    protected ControllerEvento controllerEvento = new ControllerEvento();

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

    public void visualizarEventos(){
        controllerEvento.listarEventos();
    }
}
