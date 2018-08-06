package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 //Definições da classe
 * @date 10/09/2017
 * @author Daniel
 */
public class Atleta {
    private int codigo;
    private String nome;
    private String sexo;
    private String categoria;
    private int posicao;
    private String equipe;
    private String treinador;
    private String dtNascimento;
    private String cpf;
    private String endereco;
    private String cidade;
    private String estado;
    private String status;
    private boolean borboleta;
    private boolean crawl;
    private boolean peito;
    private boolean medley;
    private boolean costas;
    private boolean revezamento;

    public Atleta(int codigo, String nome, String sexo, String categoria, int posicao, String equipe, String treinador, String dtNascimento, String cpf, String endereco, String cidade, String estado, String status, boolean borboleta, boolean crawl, boolean peito, boolean medley, boolean costas, boolean revezamento) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.categoria = categoria;
        this.posicao = posicao;
        this.equipe = equipe;
        this.treinador = treinador;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.status = status;
        this.borboleta = borboleta;
        this.crawl = crawl;
        this.peito = peito;
        this.medley = medley;
        this.costas = costas;
        this.revezamento = revezamento;
    }

    public Atleta(){
    }

    public long getCodigo() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getTreinador() {
        return treinador;
    }

    public void setTreinador(String treinador) {
        this.treinador = treinador;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBorboleta() {
        return borboleta;
    }

    public void setBorboleta(boolean borboleta) {
        this.borboleta = borboleta;
    }

    public boolean isCrawl() {
        return crawl;
    }

    public void setCrawl(boolean crawl) {
        this.crawl = crawl;
    }

    public boolean isPeito() {
        return peito;
    }

    public void setPeito(boolean peito) {
        this.peito = peito;
    }

    public boolean isMedley() {
        return medley;
    }

    public void setMedley(boolean medley) {
        this.medley = medley;
    }

    public boolean isCostas() {
        return costas;
    }

    public void setCostas(boolean costas) {
        this.costas = costas;
    }

    public boolean isRevezamento() {
        return revezamento;
    }

    public void setRevezamento(boolean revezamento) {
        this.revezamento = revezamento;
    }
    
}
