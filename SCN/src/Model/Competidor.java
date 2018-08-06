package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 //Definições da classe
 * @date 26/09/2017
 * @author Daniel
 */
public class Competidor extends Atleta {
    private int codigoAtleta;
    private int codigoBateria;
    private String tempo;
    private int classificacao;

    public Competidor(int codigoAtleta, int codigoBateria, String tempo, int classificacao, int codigo, String nome, String sexo, String categoria, int posicao, String equipe, String treinador, String dtNascimento, String cpf, String endereco, String cidade, String estado, String status, boolean borboleta, boolean crawl, boolean peito, boolean medley, boolean costas, boolean revezamento) {
        super(codigo, nome, sexo, categoria, posicao, equipe, treinador, dtNascimento, cpf, endereco, cidade, estado, status, borboleta, crawl, peito, medley, costas, revezamento);
        this.codigoAtleta = codigoAtleta;
        this.codigoBateria = codigoBateria;
        this.tempo = tempo;
        this.classificacao = classificacao;
    }

    public Competidor(int codigoAtleta, int codigoBateria, String tempo, int classificacao) {
        this.codigoAtleta = codigoAtleta;
        this.codigoBateria = codigoBateria;
        this.tempo = tempo;
        this.classificacao = classificacao;
    }

    public Competidor(){
        
    }

    public int getCodigoAtleta() {
        return codigoAtleta;
    }

    public void setCodigoAtleta(int codigoAtleta) {
        this.codigoAtleta = codigoAtleta;
    }

    public int getCodigoBateria() {
        return codigoBateria;
    }

    public void setCodigoBateria(int codigoBateria) {
        this.codigoBateria = codigoBateria;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
     
}
