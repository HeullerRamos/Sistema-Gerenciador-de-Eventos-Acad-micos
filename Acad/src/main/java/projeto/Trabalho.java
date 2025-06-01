package projeto;

public class Trabalho {
    private String titulo;
    private boolean arquivo;
    private boolean ativo;

    public Trabalho(String titulo, boolean arquivo) {
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
}
