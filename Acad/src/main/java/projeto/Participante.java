package projeto;

import controllers.ControllerEvento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Participante {

    private static int proximoId = 1;
    private int id;
    private String nome;
    private String email;
    private String instituicao;
    private String tipo;
    private ArrayList<Evento> meusEventos = new ArrayList<>();
    public ArrayList<Evento> eventosCriado = new ArrayList<>();
    Repositorio repositorio = Repositorio.getInstancia();
    Participante(){}

    public Participante(String nome, String email, String instituicao,String tipo) {
        this.id = proximoId++;
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

    public ArrayList<Evento> listarEventos(){
        return eventosCriado = repositorio.getEventos();
    }
    public Evento selecionarEvento(int id){
       Evento temp =  eventosCriado.stream().filter(evento -> evento.getId().equals(id)).findFirst().orElse(null);
       return temp;
    }
    public Inscricao participarEvento(Evento evento){
        Inscricao temp = new Inscricao(true,this);
        evento.adicionarInscricao(temp);
        return temp;
    }

    public ArrayList<Evento> listarMeusEventos() {
        ArrayList<Evento> todosEventos = repositorio.getEventos();
        if(todosEventos.isEmpty())
            meusEventos = new ArrayList<>();
        for (Evento evento : todosEventos){
            for(Inscricao inscricao : evento.getInscricoes()){
                if(inscricao.getParticipante().equals(this)){
                    meusEventos.add(evento);
                }
            }
        }

        return meusEventos;
    }
    public ArrayList<Evento> meusEventosConcluidos(){
        ArrayList<Evento> meusEventosFin = new ArrayList<>();

        for(Evento evento : meusEventos){
            for(Inscricao inscricao : evento.getInscricoes()){
                if(inscricao.getParticipante().equals(this) && inscricao.getPresenca()==true){
                    meusEventosFin.add(evento);
                }
            }
        }
        return meusEventosFin;
    }
    public void imprimirEventos(ArrayList<Evento> eventos){
        for(Evento evento :eventos){
            System.out.println(evento);
        }
    }
    public void submeterTrabalho(Evento evento,String nome,boolean ativo){
        evento.adicionarTrabalho(new Trabalho(nome,ativo));
    }


    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
