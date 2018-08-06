package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * //Definições da classe
 *
 * @date 17/09/2017
 * @author Daniel
 */
public class Equipe {

    private int codigo;
    private String nome;
    private String status;
    private String nacionalidade;
    private String competicao;

    public Equipe() {
    }

    public Equipe(int codigo, String nome, String status, String nacionalidade, String competicao) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
        this.nacionalidade = nacionalidade;
        this.competicao = competicao;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCompeticao() {
        return competicao;
    }

    public void setCompeticao(String competicao) {
        this.competicao = competicao;
    }

}
