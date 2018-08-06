/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CompetidorDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnlhi
 */
public class pesquisaCompetidor {

    public static ArrayList<String> carregaComboBoxCompetidores(int codCompeticao, int codModalidade, int numBateria) throws SQLException {
        return CompetidorDAO.carregaCompetidoresComboBox(codCompeticao, codModalidade, numBateria);
    }

    public static void classificaCompetidores(int codBateria) throws SQLException {
        CompetidorDAO.reclassificarCompetidores(codBateria);
    }

    public static void pesquisaCompetidor(DefaultTableModel model, String numBateria, int codModalidade, int codCompeticao) throws SQLException {
        int numBaterias = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);
        if ("Final".equals(numBateria)) {
            numBateria = Integer.toString(numBaterias);
        }
        CompetidorDAO.carregaCompetidores(model, numBateria, codModalidade, codCompeticao);
    }

    public static ResultSet relatorioCompetidor(String numBateria, int codModalidade, int codCompeticao) throws SQLException {
        return CompetidorDAO.relatorioCompetidores(numBateria, codModalidade, codCompeticao);
    }
    
    public static ResultSet relatorioCompetidoresBateria(int codModalidade, int codCompeticao) throws SQLException{
        return CompetidorDAO.relatorioCompetidoresPorBateria(codModalidade, codCompeticao);
    }
    
    public static ResultSet relatorioCompetidoresModalidade(int codCompeticao) throws SQLException{
        return CompetidorDAO.relatorioCompetidoresPorModalidade(codCompeticao);
    }

}
