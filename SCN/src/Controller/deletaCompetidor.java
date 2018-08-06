/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CompetidorDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class deletaCompetidor {
    
    public static void deletarCompetidor(String codAtleta, int codBateria) throws SQLException{
        CompetidorDAO.removeCompetidor(codAtleta, codBateria);
    }
    
}
