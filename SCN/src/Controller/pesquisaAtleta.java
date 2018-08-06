/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AtletaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * //Definições da classe
 *
 * @date 23/10/2017
 * @author Daniel
 */
public class pesquisaAtleta {

    public static ResultSet pesquisaAtleta(int codAtleta) throws SQLException {
        return AtletaDAO.pesquisarAtletaCodigo(codAtleta);
    }

    public static ResultSet pesquisaTodosAtleta(int codCompeticao) throws SQLException {
        return AtletaDAO.pesquisarTodosAtletas(codCompeticao);
    }

    public static void pesquisaAtleta(DefaultTableModel model, String cpf) throws SQLException {
        AtletaDAO.carregaAtletaPesquisado(model, cpf);
    }
    
    public static String pesquisaAtleta(String cpf) throws SQLException{
        return AtletaDAO.pegaNomeAtleta(cpf);
    }
    
    public static int pesquisaCodAtleta(String cpf) throws SQLException{
        return AtletaDAO.pegaCodigoAtleta(cpf);
    }

    public static int validaLinhaSelecionada(JTable tabela) {
        int linha = tabela.getSelectedRow();
        int codAtleta = Integer.parseInt((String) tabela.getValueAt(linha, 0));
        return codAtleta;
    }
    
    public static void carregaJTableAtletas(DefaultTableModel model, int codCompeticao) throws SQLException{
        AtletaDAO.carregaAtletas(model, codCompeticao);
    }
    
    public static void carregaJTableAtletaPesquisado(DefaultTableModel model, int codCompeticao) throws SQLException{
        AtletaDAO.carregaAtletas(model, codCompeticao);
    }
    
    public static ArrayList<String> carregaAtletas() throws SQLException{
        return AtletaDAO.carregaAtletas();
    }
    
    
}
