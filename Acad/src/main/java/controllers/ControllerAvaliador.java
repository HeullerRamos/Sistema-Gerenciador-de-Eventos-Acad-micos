package controllers;

import projeto.*;

import java.util.ArrayList;

public class ControllerAvaliador {
    Organizador organizador;
    Evento eventoSelecionado;
    Evento evento;
    private Avaliador avaliador;
    private ArrayList<Evento> eventos = new ArrayList<>();
    Repositorio repositorio = Repositorio.getInstancia();

    public ControllerAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }

    public void avaliarEvento(){
        eventos = avaliador.avaliarEvento();
    }

    public void imprimirEventosAvaliar(){
        avaliador.imprimirEventosAvaliar();
    }

    public void listarTrabalhos(Evento evento){
        avaliador.listarTrabalhos(evento);
    }

    public void imprimirTrabalhosAvaliar(){
        avaliador.imprimirTrabalhosAvaliar();
    }

    public void selecionarTrabalho(ArrayList<Trabalho> trabalho){
        avaliador.selecionarTrabalho(trabalho,evento);
    }
    public void designarAvaliador(String nome, String email){
        organizador.designarAvaliador(nome,email,evento);
    }

    public void avaliarTrabalho(Trabalho trabalho,float nota, String comentario){
        avaliador.avaliarTrabalho(trabalho,nota,comentario);
    }

    public Avaliador criarAvaliador(String nome, String email){
        return new  Avaliador(nome,email);
    }

    public void selecionarEvento(int id){
        eventoSelecionado = avaliador.selecionarEvento(id);
    }

}
