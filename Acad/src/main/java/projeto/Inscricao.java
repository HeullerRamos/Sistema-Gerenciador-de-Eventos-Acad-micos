package projeto;

import java.time.LocalDateTime;
import java.util.Date;

public class Inscricao {
    private LocalDateTime data;
    private boolean ativa;
    private Participante participante;
    private boolean presenca;

    public Inscricao( boolean ativa, Participante participante) {
        this.data = LocalDateTime.now();
        this.ativa = ativa;
        this.participante = participante;
        this.presenca = false;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean getPresenca() {
        return presenca;
    }


    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }
}
