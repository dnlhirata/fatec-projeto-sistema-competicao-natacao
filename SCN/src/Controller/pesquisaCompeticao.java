/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CompeticaoDAO;
import Model.Equipe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnlhi
 */
public class pesquisaCompeticao {
    
    public static int pesquisaCompeticao(String nome) throws SQLException{
        return CompeticaoDAO.pegaCodigoCompeticaoNome(nome);
    }
    
    public static int pesquisaCompeticao(Equipe e) throws SQLException{
        return CompeticaoDAO.pegaCodigoCompeticaoEquipe(e);
    }
    
    public static ResultSet pesquisaCompeticao(int codCompeticao) throws SQLException{
        return CompeticaoDAO.pesquisarCompeticaoCodigo(codCompeticao);
    }
    
    public static void pesquisaCompeticao(DefaultTableModel model, int codCompeticao){
        CompeticaoDAO.carregaCompeticaoPesquisada(model, codCompeticao);
    }
    
    public static String pesquisaCompeticao(ResultSet rs) throws SQLException{
        return CompeticaoDAO.pegaNomeCompeticao(rs);
    }
    
    public static void pesquisaCompeticao(JFormattedTextField ftf, JTable table, JScrollPane pane){
        CompeticaoDAO.carregaTabelaCompeticaoPesquisada(ftf, table, pane);
    }
    
    public static void carregaJTableCompeticao(DefaultTableModel model){
        CompeticaoDAO.carregaCompeticoes(model);
    }
    
    public static ArrayList<String> carregaCompeticoes() throws SQLException{
        return CompeticaoDAO.carregaComboBoxCompeticoes();
    }
    
    public static int validaLinhaSelecionada(JTable tabela) {
        int linha = tabela.getSelectedRow();
        int codCompeticao = Integer.parseInt((String) tabela.getValueAt(linha, 0));
        return codCompeticao;
    }
    
    public static ResultSet relatorioCompeticoesPorEquipe() throws SQLException{
        return CompeticaoDAO.relatorioCompeticoesPorEquipe();
    }
    
    public static ResultSet relatorioMedalhas(int codCompeticao) throws SQLException{
        return CompeticaoDAO.relatorioMedalhas(codCompeticao);
    }
}
