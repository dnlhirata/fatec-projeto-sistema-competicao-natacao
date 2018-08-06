/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Atleta;
import Model.EquipeDAO;
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
public class pesquisaEquipe {
    
        public static String pesquisaEquipe(String nome) throws SQLException{
            return EquipeDAO.pegaCodigoEquipeNome(nome);
        }
        
        public static int pesquisaEquipe(Atleta atleta) throws SQLException {
            return EquipeDAO.pegaCodigoEquipeAtleta(atleta);
        }
        
        public static ResultSet pesquisaEquipe(int codEquipe) throws SQLException{
            return EquipeDAO.pesquisarEquipeCodigo(codEquipe);
        }
        
        public static void pesquisaEquipe(DefaultTableModel model, String nome) throws SQLException{
            EquipeDAO.carregaEquipePesquisada(model, nome);
        }
        
        public static void pesquisaEquipe(JFormattedTextField ftf, JTable table, JScrollPane pane) throws SQLException{
            EquipeDAO.carregaTabelaEquipePesquisada(ftf, table, pane);
        }
        
        public static ResultSet pesquisaTodasEquipe(int codCompeticao) throws SQLException{
            return EquipeDAO.pesquisarTodasEquipes(codCompeticao);
        }
        
        public static void carregaJTableEquipes(DefaultTableModel model, int codCompeticao) throws SQLException{
            EquipeDAO.carregaEquipes(model, codCompeticao);
        }
        
        public static ArrayList carregaComboBoxEquipes(int codCompeticao) throws SQLException{
            return EquipeDAO.carregaEquipesCb(codCompeticao);
        }
}
