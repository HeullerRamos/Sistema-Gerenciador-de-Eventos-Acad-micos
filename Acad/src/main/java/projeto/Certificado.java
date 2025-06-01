package projeto;

import java.time.LocalDate;

public class Certificado {
    private String tipo;
    private LocalDate data;
    private int codigoValidacao;
    private static int  proximoCodigo = 1;

    public Certificado(String tipo, LocalDate data) {
        this.tipo = tipo;
        this.data = data;
        this.codigoValidacao = ++proximoCodigo;
    }

    @Override
    public String toString() {
        return "Certificado{" +
                "tipo='" + tipo + '\'' +
                ", data=" + data +
                ", codigoValidacao=" + codigoValidacao +
                '}';
    }

}
