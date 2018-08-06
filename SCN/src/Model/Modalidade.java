package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 //Definições da classe
 * @date 17/09/2017
 * @author Daniel
 */
public class Modalidade {
    
    private int codigo;
    private String nome;
    private int distancia;
    private int baterias;

    public Modalidade(int codigo, String nome, int distancia, int baterias) {
        this.codigo = codigo;
        this.nome = nome;
        this.distancia = distancia;
        this.baterias = baterias;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Modalidade() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getBaterias() {
        return baterias;
    }

    public void setBaterias(int baterias) {
        this.baterias = baterias;
    }
    
    
}
