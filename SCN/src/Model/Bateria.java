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
public class Bateria {
    
    private int codigo;
    private String data;
    private String horaIni;
    private String horaFim;
    private String status;
    private int modalidade;
    private int competicao;
    private String numero;

    public Bateria() {
    }

    public Bateria(int codigo, String data, String horaIni, String horaFim, String status, int modalidade, int competicao, String numero) {
        this.codigo = codigo;
        this.data = data;
        this.horaIni = horaIni;
        this.horaFim = horaFim;
        this.status = status;
        this.modalidade = modalidade;
        this.competicao = competicao;
        this.numero = numero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
    }

    public int getCompeticao() {
        return competicao;
    }

    public void setCompeticao(int competicao) {
        this.competicao = competicao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
}
