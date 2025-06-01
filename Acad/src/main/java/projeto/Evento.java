package projeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Evento {
    private static int  proximoId = 1;
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String local;
    private int capacidade;
    private Organizador organizador;
    private LocalDate periodoInscricaoIni;
    private LocalDate periodoInscricaoFim;
    Repositorio repositorio = Repositorio.getInstancia();
    private ArrayList<Inscricao> inscricoes = new ArrayList<>();
    private ArrayList<Trabalho> trabalhos = new ArrayList<>();

    public Evento(String nome, String descricao,
           LocalDate dataInicio, LocalDate dataFim, String local, int capacidade,Organizador organizador){

        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.capacidade = capacidade;
        this.organizador = organizador;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getId() {
        return id;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Evento.proximoId = proximoId;
    }

    public LocalDate getPeriodoInscricaoIni() {
        return periodoInscricaoIni;
    }

    public void setPeriodoInscricaoIni(LocalDate periodoInscricaoIni) {
        this.periodoInscricaoIni = periodoInscricaoIni;
    }

    public LocalDate getPeriodoInscricaoFim() {
        return periodoInscricaoFim;
    }

    public void setPeriodoInscricaoFim(LocalDate periodoInscricaoFim) {
        this.periodoInscricaoFim = periodoInscricaoFim;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public ArrayList<Trabalho> getTrabalhos() {
        return trabalhos;
    }



    public ArrayList<Inscricao> getInscricoes() {
        return inscricoes;
    }
    public void adicionarTrabalho(Trabalho trabalho){
        trabalhos.add(trabalho);

    }
    public void adicionarInscricao(Inscricao inscricao){
        inscricoes.add(inscricao);
    }
    public void visualizarInscritos(){
        for (Inscricao inscricao: inscricoes){
    System.out.println("id(" + inscricao.getParticipante().getId()+")" + " nome: " + inscricao.getParticipante().getNome());
        }
    }
    public void setaPresenca(int idAluno,boolean presenca){
        for(Inscricao inscricao: inscricoes){
            if(inscricao.getParticipante().getId() == idAluno){
                inscricao.setPresenca(presenca);
            }
        }
    }
    public void imprimeEventos(){
        for(Evento evento : repositorio.getEventos()){
            System.out.println(evento);
        }
    }
    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", local='" + local + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }
}
