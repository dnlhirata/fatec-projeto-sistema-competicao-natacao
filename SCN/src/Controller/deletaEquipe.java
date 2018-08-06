/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EquipeDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class deletaEquipe {
    
    public static void deletarEquipe(int codEquipe) throws SQLException{
        EquipeDAO.excluirEquipe(codEquipe);
    }
}
