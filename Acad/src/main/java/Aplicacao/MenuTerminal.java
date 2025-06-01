package Aplicacao;

import controllers.ControllerAvaliador;
import controllers.ControllerOrganizador;
import controllers.ControllerParticipante;
import controllers.MainController;
import projeto.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class MenuTerminal {
    // --- Métodos para Exibir Menus ---
    public static void exibirMenuPrincipal(MainController mainController) {
        System.out.println("\n--- MENU PRINCIPAL ---");
        Participante pAtual = mainController.getParticipanteAtual();

        if (pAtual instanceof Organizador) {
            System.out.println("-- Menu Organizador --");
            System.out.println("1. Cadastrar Novo Evento (UC4)");
            System.out.println("2. Gerenciar Meus Eventos (UC5)");
            System.out.println("3. Designar Avaliadores para Evento (UC6)");
        }

        if (pAtual instanceof Avaliador) {
            System.out.println("-- Menu Avaliador --");
            System.out.println("5. Avaliar Trabalhos (UC7)");
        }

        if (mainController.getControllerParticipante() != null) {
            System.out.println("-- Menu Participante (Ações Comuns) --");
            System.out.println("6. Visualizar e Inscrever-se em Eventos (UC2)");
            System.out.println("7. Submeter Trabalho para Evento (UC3)");
            System.out.println("8. Emitir Certificados (UC8)");
        }
    }

    // --- Método para Processar Escolhas do Menu ---
    public static void processarEscolhaMenuPrincipal(int escolha, MainController mainController, Scanner scanner) {
        Participante pAtual = mainController.getParticipanteAtual();

        if (pAtual instanceof Organizador) {
            switch (escolha) {
                case 1: acaoCadastrarNovoEvento(mainController, scanner); return;
                case 2: acaoGerenciarMeusEventos(mainController, scanner); return;
                case 3: acaoDesignarAvaliadores(mainController, scanner); return;
                case 4: acaoCadastrarNovoParticipante(mainController, scanner); return;
            }
        }

        if (pAtual instanceof Avaliador) {
            if (escolha == 5) {
                acaoAvaliarTrabalhos(mainController, scanner);
                return;
            }
        }

        if (mainController.getControllerParticipante() != null) {
            switch (escolha) {
                case 6: acaoVisualizarEInscreverEventos(mainController, scanner); return;
                case 7: acaoSubmeterTrabalho(mainController, scanner); return;
                case 8: acaoEmitirCertificados(mainController, scanner); return;
            }
        }
        System.out.println("Opção " + escolha + " não reconhecida para o seu perfil ou inválida.");
    }
    // --- Implementações das Ações (delegando ao MainController ou simulando) ---

    // UC1: Cadastrar Novo Participante (realizado por Organizador)
    private static void acaoCadastrarNovoParticipante(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Participante (UC1) ---");
        String email = lerString("E-mail institucional do novo participante: ", scanner);
        String nomeCompleto = lerString("Nome completo: ", scanner);
        String senha = lerString("Senha: ", scanner);
        String confirmacaoSenha = lerString("Confirmação de senha: ", scanner);
        String instituicao = lerString("Instituição: ", scanner);
        String tipo = lerString("Tipo do novo participante (Participante, Organizador, Avaliador): ", scanner);

        if (!senha.equals(confirmacaoSenha)) {
            System.out.println("As senhas não coincidem. Tente novamente.");
            return;
        }

        // Delegação ideal para MainController ou um serviço de cadastro
        // boolean sucesso = mainController.acaoDelegadaCadastrarParticipante(nomeCompleto, email, senha, instituicao, tipo);
        // if(sucesso) { System.out.println("Participante cadastrado com sucesso!");}
        // else {System.out.println("Falha ao cadastrar.");}

        // Simulação direta com o repositório (para fins de exemplo, idealmente via controller)
        if (Repositorio.getInstancia().buscarParticipantePorEmail(email) != null) {
            System.out.println("Este e-mail já foi registrado. Use outro e-mail.");
            return;
        }
        Participante novo;
        if ("Organizador".equalsIgnoreCase(tipo)) {
            novo = new Organizador(nomeCompleto, email, instituicao, tipo);
        } else {
            novo = new Participante(nomeCompleto, email, instituicao, "Participante");
        }
        Repositorio.getInstancia().adicionarParticipante(novo);
        System.out.println("Participante '" + nomeCompleto + "' cadastrado com sucesso!");
    }

    // UC2: Participante visualiza lista de eventos e se inscreve
    private static void acaoVisualizarEInscreverEventos(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Eventos Disponíveis (UC2) ---");
        ControllerParticipante cp = mainController.getControllerParticipante();
        if (cp == null) {
            System.out.println("Erro: Acesso às funcionalidades de participante não disponível.");
            return;
        }

        // ArrayList<Evento> eventos = cp.getEventosDisponiveisParaInscricao(); // Método ideal no controller
        ArrayList<Evento> eventos = Repositorio.getInstancia().getEventos(); // Exemplo direto

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento disponível no momento.");
            return;
        }
        System.out.println("Eventos disponíveis para inscrição:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getNome() + " (Capacidade: " + eventos.get(i).getCapacidade() + ")");
        }
        System.out.print("Selecione o número do evento para ver detalhes (ou 0 para voltar): ");
        int escolhaIndice = lerInteiro("", 0, eventos.size(), scanner);
        if (escolhaIndice == 0) return;

        Evento eventoSelecionado = eventos.get(escolhaIndice - 1);
        System.out.println("\n--- Detalhes do Evento ---");
        System.out.println("Nome: " + eventoSelecionado.getNome());
        System.out.println("Descrição: " + eventoSelecionado.getDescricao());
        System.out.println("Data: " + eventoSelecionado.getDataInicio() + " a " + eventoSelecionado.getDataFim());
        System.out.println("Local: " + eventoSelecionado.getLocal());
        System.out.println("Capacidade: " + eventoSelecionado.getCapacidade());

        System.out.print("Deseja se inscrever neste evento? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            // boolean sucesso = cp.inscreverEmEvento(eventoSelecionado.getId()); // Método ideal
            // if(sucesso) System.out.println("Inscrição realizada com sucesso!");
            // else System.out.println("Falha na inscrição. Evento lotado ou já inscrito.");

            // Simulação
            if (eventoSelecionado.getInscritos() != null && eventoSelecionado.getInscritos().size() >= eventoSelecionado.getCapacidade()) {
                System.out.println("O evento está lotado.");
            } else {
                System.out.println("Inscrição realizada com sucesso (simulado)!");
            }
        }
    }

    // UC3: Participante submete um trabalho
    private static void acaoSubmeterTrabalho(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Submeter Trabalho (UC3) ---");
        ControllerParticipante cp = mainController.getControllerParticipante();
        if (cp == null) { System.out.println("Erro: Acesso às funcionalidades de participante não disponível."); return; }

        System.out.println("Simulação: Listando seus eventos inscritos que aceitam submissão...");
        System.out.print("Insira o ID do evento no qual deseja submeter o trabalho: ");
        int idEvento = lerInteiro("", 1, Integer.MAX_VALUE, scanner);

        String titulo = lerString("Título do Trabalho: ", scanner);
        String arquivo = lerString("Caminho do Arquivo (simulado): ", scanner);

        if (titulo.isEmpty()) { System.out.println("Campo do Título vazio, preencha o campo."); return; }
        if (arquivo.isEmpty()) { System.out.println("Arquivo não enviado."); return; }

        // boolean sucesso = cp.submeterTrabalho(idEvento, titulo, arquivo); // Método ideal
        // if(sucesso) System.out.println("Trabalho submetido com sucesso!");
        // else System.out.println("Falha ao submeter trabalho.");
        System.out.println("Trabalho '" + titulo + "' submetido com sucesso (simulado)!");
    }

    // UC4: Organizador cadastra novo evento
    private static void acaoCadastrarNovoEvento(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Cadastrar Novo Evento (UC4) ---");
        if (!(mainController.getParticipanteAtual() instanceof Organizador)) {
            System.out.println("Apenas Organizadores podem cadastrar eventos.");
            return;
        }
        String nome = lerString("Nome do evento: ", scanner);
        String descricao = lerString("Descrição: ", scanner);
        LocalDate dataInicio = lerData("Data de Início (AAAA-MM-DD): ", scanner);
        LocalDate dataFim = lerData("Data de Término (AAAA-MM-DD): ", dataInicio, scanner);
        String local = lerString("Local ou Link: ", scanner);
        int capacidade = lerInteiro("Capacidade: ", 1, Integer.MAX_VALUE, scanner);

        if (nome.isEmpty() || descricao.isEmpty() || local.isEmpty()) {
            System.out.println("Campos de texto (nome, descrição, local) são obrigatórios.");
            return;
        }
        // Ação delegada ao MainController que já verifica o tipo e usa o ControllerEvento
        Evento novoEvento = mainController.acaoCriarEvento(nome, descricao, dataInicio, dataFim, local, capacidade);
        if (novoEvento != null) {
            System.out.println("Evento '" + novoEvento.getNome() + "' cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar o evento.");
        }
    }

    // UC5: Organizador gerencia eventos
    private static void acaoGerenciarMeusEventos(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Gerenciar Meus Eventos (UC5) ---");
        ControllerOrganizador co = mainController.getControllerOrganizador();
        if (co == null) { System.out.println("Erro: Acesso às funcionalidades de organizador não disponível."); return; }

        System.out.println("Simulação: Listando eventos criados por você...");
        System.out.print("Insira o ID do evento que deseja gerenciar: ");
        int idEvento = lerInteiro("", 1, Integer.MAX_VALUE, scanner);

        System.out.println("Gerenciando Evento ID: " + idEvento);
        System.out.println("Opções de Gerenciamento:");
        System.out.println("1. Editar Informações do Evento");
        System.out.println("2. Visualizar Inscritos (e confirmar presença)");
        System.out.println("3. Definir Período de Submissão de Trabalhos");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int escolha = lerInteiro("", 0, 3, scanner);

        switch (escolha) {
            case 1:
                System.out.println("'Editar Informações do Evento' (simulado).");
                // Chamar co.iniciarEdicaoEvento(idEvento);
                break;
            case 2:
                System.out.println("'Visualizar Inscritos' (simulado).");
                // Chamar co.visualizarInscritosEvento(idEvento);
                break;
            case 3:
                System.out.println("--- Definir Período de Submissão ---");
                LocalDate dataInicioSub = lerData("Data de Início para Submissão (AAAA-MM-DD): ", scanner);
                LocalDate dataFimSub = lerData("Data de Término para Submissão (AAAA-MM-DD): ", dataInicioSub, scanner);
                // co.definirPeriodoSubmissao(idEvento, dataInicioSub, dataFimSub);
                System.out.println("Período de submissão salvo com sucesso (simulado)!");
                break;
            case 0: return;
            default: System.out.println("Opção inválida.");
        }
    }

    // UC6: Organizador designa avaliadores
    private static void acaoDesignarAvaliadores(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Designar Avaliadores para Evento (UC6) ---");
        ControllerOrganizador co = mainController.getControllerOrganizador();
        if (co == null) { System.out.println("Erro: Acesso às funcionalidades de organizador não disponível."); return; }

        System.out.println("Simulação: Listando seus eventos...");
        System.out.print("Insira o ID do evento para designar avaliadores: ");
        int idEvento = lerInteiro("", 1, Integer.MAX_VALUE, scanner);

        String nomeAvaliador = lerString("Nome do Avaliador (para referência): ", scanner);
        String emailAvaliador = lerString("Email do Avaliador (deve ter conta no sistema): ", scanner);

        // boolean sucesso = co.designarAvaliador(idEvento, emailAvaliador, nomeAvaliador);
        // if(sucesso) System.out.println("Avaliador designado!"); else System.out.println("Falha.");
        System.out.println("Avaliador '" + nomeAvaliador + "' designado com sucesso para o evento ID " + idEvento + " (simulado)!");
    }

    // UC7: Avaliador registra avaliação
    private static void acaoAvaliarTrabalhos(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Avaliar Trabalhos Designados (UC7) ---");
        ControllerAvaliador ca = mainController.getControllerAvaliador();
        if (ca == null) { System.out.println("Erro: Acesso às funcionalidades de avaliador não disponível."); return; }

        System.out.println("Simulação: Listando trabalhos pendentes de avaliação para você...");
        System.out.print("Insira o ID do trabalho que deseja avaliar: ");
        int idTrabalho = lerInteiro("", 1, Integer.MAX_VALUE, scanner);

        System.out.println("Avaliando Trabalho ID: " + idTrabalho);
        System.out.print("Nota da avaliação (ex: 0.0 a 10.0): ");
        float nota = -1.0f;
        boolean notaValida = false;
        while(!notaValida) {
            try {
                nota = Float.parseFloat(scanner.nextLine());
                if (nota >= 0.0f && nota <= 10.0f) { notaValida = true; }
                else { System.out.println("Nota inválida. Deve ser entre 0.0 e 10.0."); }
            } catch (NumberFormatException e) { System.out.println("Entrada inválida para nota."); }
        }
        String parecer = lerString("Parecer da avaliação: ", scanner);
        if (parecer.isEmpty()) { System.out.println("O campo 'Parecer' é obrigatório."); return; }

        // boolean sucesso = ca.registrarAvaliacao(idTrabalho, nota, parecer);
        // if(sucesso) System.out.println("Avaliação registrada!"); else System.out.println("Falha.");
        System.out.println("Avaliação para o trabalho ID " + idTrabalho + " registrada com sucesso (simulado)!");
    }

    // UC8: Emissão de Certificados
    private static void acaoEmitirCertificados(MainController mainController, Scanner scanner) {
        System.out.println("\n--- Emissão de Certificados (UC8) ---");
        ControllerParticipante cp = mainController.getControllerParticipante();
        if (cp == null) { System.out.println("Erro: Acesso às funcionalidades de participante não disponível."); return; }

        System.out.println("Simulação: Listando seus eventos/trabalhos elegíveis...");
        System.out.print("Insira o ID da Referência para emitir o certificado: ");
        int idReferencia = lerInteiro("", 1, Integer.MAX_VALUE, scanner);

        // String linkCertificado = cp.emitirCertificado(idReferencia);
        // if(linkCertificado != null) System.out.println("Certificado: " + linkCertificado);
        // else System.out.println("Não foi possível emitir.");
        System.out.println("Certificado para referência ID " + idReferencia + " emitido/disponibilizado com sucesso (simulação).");
    }


    // --- Métodos Utilitários para Leitura de Entrada ---
    public static String lerString(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int lerInteiro(String prompt, int min, int max, Scanner scanner) {
        int valor;
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                valor = Integer.parseInt(linha);
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Valor deve estar entre " + min + " e " + max + ". Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número. Tente novamente.");
            }
        }
    }

    public static LocalDate lerData(String prompt, Scanner scanner) {
        LocalDate data;
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                data = LocalDate.parse(linha); // Espera formato AAAA-MM-DD
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use AAAA-MM-DD. Tente novamente.");
            }
        }
    }

    public static LocalDate lerData(String prompt, LocalDate dataMinima, Scanner scanner) {
        LocalDate data;
        while (true) {
            data = lerData(prompt, scanner);
            if (dataMinima == null || !data.isBefore(dataMinima)) {
                return data;
            } else {
                System.out.println("A data não pode ser anterior a " + dataMinima + ". Tente novamente.");
            }
        }
    }
}
