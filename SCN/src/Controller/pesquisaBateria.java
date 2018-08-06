/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BateriaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dnlhi
 */
public class pesquisaBateria {
    
    public static ArrayList<String> carregaComboBoxBateria(int codCompeticao, int codModalidade) throws Exception {
        return BateriaDAO.carregaBaterias(codCompeticao,codModalidade);
    }

    public static int qtdBaterias(int codModalidade, int codCompeticao) throws SQLException{
        return BateriaDAO.pegaNumBateria(codModalidade, codCompeticao);
    }
    
    public static ResultSet pesquisaBateria(String numBateria, int codCompeticao, int codModalidade) throws SQLException{
        return BateriaDAO.alteraBateria(numBateria, codCompeticao, codModalidade);
    }
    
    public static int pesquisaCodBateria(int numBateria, int codCompeticao, int codModalidade) throws SQLException{
        return BateriaDAO.pegaCodBateria(numBateria, codModalidade, codCompeticao);
    }
}
