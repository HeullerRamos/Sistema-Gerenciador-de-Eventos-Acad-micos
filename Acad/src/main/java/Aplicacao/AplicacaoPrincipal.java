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
        Repositorio repo = Repositorio.getInstancia(); // Obter instância do repositório

        boolean rodandoAplicacao = true;
        while (rodandoAplicacao) {
            System.out.println("\n=========== Acad-Micos ===========");
            System.out.println("1. Cadastrar Novo Participante (UC1)");
            System.out.println("2. Entrar no Sistema");
            System.out.println("0. Sair da Aplicação");
            System.out.print("Escolha uma opção: ");

            int escolhaInicial = MenuTerminal.lerInteiro("", 0, 2, scanner);

            switch (escolhaInicial) {
                case 1:
                    // Chama o método de cadastro da MenuTerminal, que interage com o repositório
                    MenuTerminal.realizarNovoCadastroDeParticipante(scanner, repo);
                    break;
                case 2:
                    // Chama o método de login da MenuTerminal
                    Participante participanteLogado = MenuTerminal.efetuarLoginPorEmail(scanner, repo);
                    if (participanteLogado != null) {
                        // Se o login for bem-sucedido, instancia o MainController com esse participante
                        mainController = new MainController(participanteLogado);
                        System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + participanteLogado.getNome() + ".");
                        loopSessaoUsuarioLogado(); // Inicia o loop para o usuário logado
                    } else {
                        // A mensagem de falha de login já é dada por efetuarLoginPorEmail
                        // System.out.println("Login falhou. Email ou senha incorretos ou usuário não cadastrado.");
                    }
                    break;
                case 0:
                    rodandoAplicacao = false;
                    System.out.println("Aplicação encerrada. Até logo!");
                    break;
                default:
                    System.out.println("Opção inicial inválida.");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Loop principal para quando um usuário está logado.
     * Exibe o menu principal do usuário e processa suas escolhas.
     */
    private static void loopSessaoUsuarioLogado() {
        boolean sessaoAtiva = true;
        while (sessaoAtiva) {
            MenuTerminal.exibirMenuPrincipal(mainController); // Menu específico do papel
            System.out.print("\nEscolha uma opção do seu menu (ou 0 para Logout/Voltar ao menu inicial): ");
            // Ajuste o limite máximo de opções conforme o menu mais extenso (ex: 0 a 8)
            int escolha = MenuTerminal.lerInteiro("", 0, 8, scanner);

            if (escolha == 0) {
                sessaoAtiva = false;
                mainController = null; // Limpa a referência ao MainController da sessão atual
                System.out.println("Logout realizado. Voltando ao menu inicial...");
            } else {
                MenuTerminal.processarEscolhaMenuPrincipal(escolha, mainController, scanner);
            }
        }
    }
}
