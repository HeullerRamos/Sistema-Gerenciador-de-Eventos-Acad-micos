package projeto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Organizador extends Participante{

    Repositorio repositorio = Repositorio.getInstancia();
    private ArrayList<Evento> meusEventosCriado = new ArrayList<>();

    public Organizador(String nome,String email,String instituicao,String tipo){
        super(nome,email,instituicao,tipo);
    }

    public ArrayList<Evento> getMeusEventosCriado() {
        return meusEventosCriado;
    }

    public void cadastrarEvento(String nome, String descricao, LocalDate dataInicio,
                                LocalDate dataFim, String local, int capacidade, Organizador organizador){

        Evento temp = new Evento(nome,descricao,dataInicio,dataFim,local,capacidade,organizador);
        meusEventosCriado.add(temp);
        repositorio.adicicionarEvento(temp);
    }

    public ArrayList<Evento> meusEventosCriados(){
        for(Evento evento : repositorio.getEventos()){
            if(evento.getOrganizador().equals(this))
                meusEventosCriado.add(evento);
        }
        return meusEventosCriado;
    }

    @Override
    public Evento selecionarEvento(int id) {
        Evento temp = meusEventosCriado.stream().filter(evento ->
            evento != null && evento.getId()==id).findFirst().orElse(null);
        return temp;
    }

    //Tem que ir para um controller
    public void visualizarMeusEventos(){
        for (Evento evento : meusEventosCriado) {
            System.out.println(evento);
        };
    }
    public void adicionarPresenca(Evento evento,int idParticipante,boolean presenca){
        evento.setaPresenca(idParticipante,presenca);
    }

    public void inserirDataSubmissao(Evento evento,LocalDate dataInicio,LocalDate dataFim){
        evento.setPeriodoInscricaoIni(dataInicio);
        evento.setPeriodoInscricaoFim(dataFim);
    }
    public Avaliador designarAvaliador(String nome, String email,Evento evento){
        Avaliador avaliador = new Avaliador(nome,email);
        avaliador.setEventosParaAvaliar(this.selecionarEvento(evento.getId()));
        return avaliador;
    }
}
