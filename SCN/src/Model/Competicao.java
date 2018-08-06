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
public class Competicao {

    private int codigo;
    private String nome;
    private String local;
    private String dataIni;
    private String dataFim;
    private String status;
    private String cidade;
    private String estado;

    public Competicao(int codigo, String nome, String local, String dataIni, String dataFim, String status) {
        this.codigo = codigo;
        this.nome = nome;
        this.local = local;
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.status = status;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Competicao() {
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataIni() {
        return dataIni;
    }

    public void setDataIni(String dataIni) {
        this.dataIni = dataIni;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
