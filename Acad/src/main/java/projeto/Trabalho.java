package projeto;

public class Trabalho {
    private String titulo;
    private boolean arquivo;
    private boolean ativo;
    private static int  proximoId = 1;
    private int id;
    private boolean aprovado; //revisar
    private Avaliacao avaliacao;
    private Participante participante;

    public Trabalho(String titulo, boolean arquivo) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.arquivo = arquivo;
        this.ativo = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isArquivo() {
        return arquivo;
    }

    public void setArquivo(boolean arquivo) {
        this.arquivo = arquivo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getId() {
        return id;
    }

}
