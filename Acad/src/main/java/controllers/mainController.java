package controllers;

import projeto.Repositorio;
import projeto.Avaliador;
import projeto.Evento;
import projeto.Organizador;
import projeto.Participante;
import projeto.Repositorio; // Suas classes de domínio

import java.time.LocalDate;
public class mainController {
    private Participante participanteAtual; // O participante "logado" ou ativo
    private Repositorio repositorio;

    // Controllers especializados
    private ControllerAvaliador controllerAvaliador;
    private ControllerOrganizador controllerOrganizador;
    private ControllerParticipante controllerParticipante;
    private ControllerEvento controllerEvento; // Para operações gerais de evento

    /**
     * Construtor do MainController.
     * Recebe o participante que está ativo no sistema e inicializa os controllers
     * com base no seu tipo.
     * @param participante O participante ativo (pode ser Organizador, Avaliador, ou Participante base).
     */
    public MainController(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("O participante não pode ser nulo.");
        }
        this.participanteAtual = participante;
        this.repositorio = Repositorio.getInstancia(); // Obtém instância do repositório
        this.controllerEvento = new ControllerEvento(); // Ajuste o construtor se necessário

        inicializarControllersComBaseNoParticipante();
    }

    /**
     * Inicializa os controllers especializados com base no tipo real do participanteAtual.
     */
    private void inicializarControllersComBaseNoParticipante() {
        // Garante que os controllers estão limpos antes de tentar inicializar
        this.controllerOrganizador = null;
        this.controllerAvaliador = null;
        this.controllerParticipante = null;

        // Todos os tipos de Participante (incluindo Organizador e Avaliador, se herdarem de Participante)
        // terão um ControllerParticipante associado.
        // Supõe que ControllerParticipante(Participante p) existe.
        this.controllerParticipante = new ControllerParticipante(this.participanteAtual);

        // Se o participanteAtual for um Organizador, inicializa o ControllerOrganizador.
        // Supõe que Organizador estende Participante e ControllerOrganizador(Organizador o) existe.
        if (this.participanteAtual instanceof Organizador) {
            this.controllerOrganizador = new ControllerOrganizador((Organizador) this.participanteAtual);
        }

        // Se o participanteAtual for um Avaliador, inicializa o ControllerAvaliador.
        // Supõe que Avaliador estende Participante e ControllerAvaliador(Avaliador a) existe.
        if (this.participanteAtual instanceof Avaliador) {
            this.controllerAvaliador = new ControllerAvaliador((Avaliador) this.participanteAtual);
        }
        System.out.println("Controllers inicializados para: " + this.participanteAtual.getNome() + " (" + this.participanteAtual.getClass().getSimpleName() + ")");
    }


    // --- Delegação para Controllers Especializados ---

    /**
     * Ação para criar um novo evento.
     * Esta ação só é permitida se o participanteAtual for um Organizador e o ControllerOrganizador estiver ativo.
     * @param nome Nome do evento.
     * @param descricao Descrição do evento.
     * @param dataInicio Data de início do evento.
     * @param dataFim Data de fim do evento.
     * @param local Local do evento.
     * @param capacidade Capacidade máxima do evento.
     * @return O objeto Evento criado, ou null se a ação não for permitida ou falhar.
     */
    public Evento acaoCriarEvento(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, String local, int capacidade) {
        if (this.participanteAtual instanceof Organizador && this.controllerOrganizador != null) {
            // O ControllerEvento é usado para a criação, passando o Organizador atual.
            return this.controllerEvento.criarEvento(nome, descricao, dataInicio, dataFim, local, capacidade, (Organizador) this.participanteAtual);
        } else {
            System.err.println("Ação não permitida: O utilizador atual não é um Organizador ou o controller não está inicializado.");
            return null;
        }
    }

    /**
     * Ação para listar os eventos nos quais o participanteAtual está inscrito.
     * Requer que o ControllerParticipante esteja ativo (o que será sempre o caso se um participante for fornecido).
     */
    public void acaoListarMeusEventosInscritos() {
        if (this.controllerParticipante != null) {
            // O ControllerParticipante usa o participanteAtual com o qual foi inicializado.
            this.controllerParticipante.listaEventosParticipou();
            // Em uma UI real, você obteria a lista de eventos de this.controllerParticipante.getEventosParticipou()
            // e a exibiria.
        } else {
            // Este caso não deveria ocorrer se o construtor funcionar corretamente.
            System.err.println("Erro: ControllerParticipante não inicializado.");
        }
    }

    // --- Métodos para a UI obter os controllers ativos e o participante ---

    /**
     * Retorna o participante atualmente ativo no sistema.
     * @return O objeto Participante ativo.
     */
    public Participante getParticipanteAtual() {
        return this.participanteAtual;
    }

    /**
     * Retorna o ControllerAvaliador se o participanteAtual for um Avaliador e o controller estiver inicializado.
     * @return O ControllerAvaliador, ou null caso contrário.
     */
    public ControllerAvaliador getControllerAvaliador() {
        return this.controllerAvaliador; // Será null se participanteAtual não for Avaliador
    }

    /**
     * Retorna o ControllerOrganizador se o participanteAtual for um Organizador e o controller estiver inicializado.
     * @return O ControllerOrganizador, ou null caso contrário.
     */
    public ControllerOrganizador getControllerOrganizador() {
        return this.controllerOrganizador; // Será null se participanteAtual não for Organizador
    }

    /**
     * Retorna o ControllerParticipante para o participanteAtual.
     * @return O ControllerParticipante (deverá estar sempre inicializado se um participante foi fornecido).
     */
    public ControllerParticipante getControllerParticipante() {
        return this.controllerParticipante;
    }

    // --- Outras ações de fluxo principal (Exemplos) ---

    /**
     * Simula a navegação para o painel do Avaliador.
     */
    public void navegarParaPainelAvaliador() {
        if (this.controllerAvaliador != null) { // Implica que participanteAtual é Avaliador
            System.out.println("Navegando para o painel do Avaliador: " + this.participanteAtual.getNome());
            // A UI usaria o controllerAvaliador para popular as telas específicas
            // Ex: this.controllerAvaliador.imprimirEventosAvaliar();
        } else {
            System.err.println("Ação não permitida: O utilizador atual não tem permissões de Avaliador.");
        }
    }

    /**
     * Simula a navegação para o painel do Organizador.
     */
    public void navegarParaPainelOrganizador() {
        if (this.controllerOrganizador != null) { // Implica que participanteAtual é Organizador
            System.out.println("Navegando para o painel do Organizador: " + this.participanteAtual.getNome());
            // Ex: this.controllerOrganizador.listarMeusEventosCriados(); // Método hipotético
        } else {
            System.err.println("Ação não permitida: O utilizador atual não tem permissões de Organizador.");
        }
    }

    /**
     * Simula a navegação para o painel do Participante (funcionalidades genéricas).
     */
    public void navegarParaPainelParticipante() {
        if (this.controllerParticipante != null) {
            System.out.println("Navegando para o painel do Participante: " + this.participanteAtual.getNome());
            // Ex: this.controllerParticipante.listarEventos();
        } else {
            // Este caso não deveria ocorrer.
            System.err.println("Erro: ControllerParticipante não disponível.");
        }
    }
}
