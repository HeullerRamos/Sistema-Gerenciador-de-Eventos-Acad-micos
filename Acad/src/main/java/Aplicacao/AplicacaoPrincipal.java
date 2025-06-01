package Aplicacao;
import controllers.MainController;
import projeto.*; // Inclui Participante, Repositorio, Organizador, Avaliador, Evento, etc.
// Não precisamos mais de java.time, java.util.ArrayList aqui se MenuTerminal os usa.
import java.util.Scanner;
public class AplicacaoPrincipal {
    private static MainController mainController;
    private static Scanner scanner = new Scanner(System.in); // Scanner para o loop principal
    // participanteLogadoGlobal é gerenciado internamente pelo MainController,
    // mas podemos precisar dele para a saudação inicial.
    private static Participante participanteLogado;

    public static void main(String[] args) {
        inicializarParticipantesDeExemplo();

        System.out.println("--- Simulação de Seleção de Usuário 'Logado' ---");
        System.out.println("Escolha o perfil para esta sessão:");
        System.out.println("1. Organizador (org.chefe@example.com)");
        System.out.println("2. Avaliador (aval.expert@example.com)");
        System.out.println("3. Participante Comum (part.comum@example.com)");
        System.out.print("Opção: ");
        // Usando o método lerInteiro da MenuTerminal (assumindo que o scanner é passado ou ela tem o seu)
        // Para manter simples, AplicacaoPrincipal ainda pode ter seus leitores ou MenuTerminal os expõe.
        // Por agora, vamos assumir que MenuTerminal.lerInteiro é acessível e usa o scanner passado.
        int perfilEscolhido = MenuTerminal.lerInteiro("", 1, 3, scanner);


        Repositorio repo = Repositorio.getInstancia();
        switch (perfilEscolhido) {
            case 1:
                participanteLogado = repo.buscarParticipantePorEmail("org.chefe@example.com");
                break;
            case 2:
                participanteLogado = repo.buscarParticipantePorEmail("aval.expert@example.com");
                break;
            case 3:
                participanteLogado = repo.buscarParticipantePorEmail("part.comum@example.com");
                break;
            default:
                System.out.println("Opção inválida. Selecionando Participante Comum por padrão.");
                participanteLogado = repo.buscarParticipantePorEmail("part.comum@example.com");
                break;
        }

        if (participanteLogado == null) {
            System.err.println("Participante ativo não pôde ser definido (não encontrado no repositório). Encerrando.");
            scanner.close();
            return;
        }

        // Inicializar MainController com o participante ativo.
        mainController = new MainController(participanteLogado);

        System.out.println("\nBem-vindo ao Acad-Micos, " + participanteLogado.getNome() + "!");

        boolean executando = true;
        while (executando) {
            MenuTerminal.exibirMenuPrincipal(mainController); // Chama o método da classe auxiliar
            System.out.print("\nEscolha uma opção (ou 0 para Sair da Sessão): ");

            // A numeração de opções agora é gerenciada internamente por MenuTerminal.
            // O limite aqui deve ser o máximo de opções em qualquer menu + opção de sair.
            int escolha = MenuTerminal.lerInteiro("", 0, 8, scanner); // Exemplo, ajuste o máximo

            if (escolha == 0) {
                executando = false;
                System.out.println("Sessão encerrada.");
            } else {
                MenuTerminal.processarEscolhaMenuPrincipal(escolha, mainController, scanner); // Chama o método da classe auxiliar
            }
        }
        scanner.close();
    }

    // Este método pode permanecer aqui ou ser movido para uma classe de inicialização/configuração de dados.
    private static void inicializarParticipantesDeExemplo() {
        Repositorio repo = Repositorio.getInstancia();
        if (repo.buscarParticipantePorEmail("org.chefe@example.com") == null) {
            repo.adicionarParticipante(new Organizador("Chefe Organização", "org.chefe@example.com", "Grande Eventos SA", "Organizador"));
        }
        if (repo.buscarParticipantePorEmail("aval.expert@example.com") == null) {
            repo.adicionarParticipante(new Avaliador("Dr. Expert", "aval.expert@example.com"));
        }
        if (repo.buscarParticipantePorEmail("part.comum@example.com") == null) {
            repo.adicionarParticipante(new Participante("Zé Comum", "part.comum@example.com", "comunidade", "Participante"));
        }
    }
}
