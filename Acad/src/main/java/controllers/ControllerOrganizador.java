package controllers;

import projeto.Evento;
import projeto.Organizador;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerOrganizador {
    private Organizador organizador;
    private Evento evento;

    public ControllerOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public void cadastrarEvento (String nome, String descricao, LocalDate dataInicio,
                                 LocalDate dataFim, String local, int capacidade, Organizador organizador) {
        organizador.cadastrarEvento(nome,descricao,dataInicio,dataFim,local,capacidade,organizador);
    }

    public ArrayList<Evento> meusEventosCriados(){
        return organizador.getMeusEventosCriado();
    }

    public void visualizarMeusEventos(){
        organizador.visualizarMeusEventos();
    }

    public Evento selecionarEvento(int id){
       return evento = organizador.selecionarEvento(id);
    }
    public void adicionarPresenca(Evento evento, int idParticipante,boolean presenca){
        organizador.adicionarPresenca(evento,idParticipante,presenca);
    }
    public void definiPeriodoSubmicao(LocalDate dataInicio,LocalDate dataFim){
        evento.setPeriodoInscricaoIni(dataInicio);
        evento.setPeriodoInscricaoFim(dataFim);
    }
    public void designarAvaliador(String nome, String email){
        organizador.designarAvaliador(nome,email,evento);

    }

    public boolean alterarInformacaoEvento(String campoParaAlterar, Object novoValor) {
        if (this.evento == null) {
            System.err.println("Erro: Nenhum evento selecionado para edição.");
            return false;
        }

        boolean sucesso = true;
        try {
            switch (campoParaAlterar.toLowerCase()) {
                case "nome":
                    this.evento.setNome((String) novoValor);
                    break;
                case "descricao":
                    this.evento.setDescricao((String) novoValor);
                    break;
                case "datainicio":
                    this.evento.setDataInicio((LocalDate) novoValor);
                    break;
                case "datafim":
                    this.evento.setDataFim((LocalDate) novoValor);
                    break;
                case "local":
                    this.evento.setLocal((String) novoValor);
                    break;
                case "capacidade":
                    this.evento.setCapacidade((Integer) novoValor);
                    break;
                default:
                    System.err.println("Erro: Campo '" + campoParaAlterar + "' desconhecido para edição.");
                    sucesso = false;
                    break;
            }
            if (sucesso) {
                // Em um sistema real, você poderia persistir as alterações aqui,
                // por exemplo, atualizando o evento no Repositorio se ele não for
                // uma referência direta ao objeto já no repositório.
                // Se Repositorio.getEventos() retorna cópias, você precisaria de um
                // Repositorio.atualizarEvento(this.eventoSelecionadoParaEdicao);
                System.out.println("Informação '" + campoParaAlterar + "' do evento atualizada com sucesso.");
            }
        } catch (ClassCastException e) {
            System.err.println("Erro: Tipo de dado incorreto fornecido para o campo '" + campoParaAlterar + "'.");
            sucesso = false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao tentar alterar a informação: " + e.getMessage());
            sucesso = false;
        }
        return sucesso;
    }
    public Evento getEvento(){
        return evento;
    }

}
