/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Competidor;
import Model.CompetidorDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class gravaCompetidor {
    
    public static void cadastrarNovoCompetidor(Competidor competidor, int codAtleta, int codBateria) throws SQLException{
        CompetidorDAO.insereNovoCompetidor(competidor, codAtleta, codBateria);
    }
    
    public static void cadastraCompetidoresFinal(int codModalidade, int codCompeticao, boolean bateriaValidada) throws SQLException{
        if(bateriaValidada){
            CompetidorDAO.geraCompetidoresFinal(codModalidade, codCompeticao);
        }
    }
    
    public static void cadastraMedalhas(int codModalidade, int codCompeticao) throws SQLException{
        CompetidorDAO.distribuiMedalhas(codModalidade, codCompeticao);
    }
    
}
