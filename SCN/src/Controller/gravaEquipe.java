/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Equipe;
import Model.EquipeDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class gravaEquipe {

    public static void cadastrarNovaEquipe(Equipe e) throws Exception {
        EquipeDAO.inserirNovaEquipe(e);
    }
    
    public static void alteraEquipe(Equipe e, int codEquipe) throws SQLException{
        EquipeDAO.alteraEquipe(e, codEquipe);
    }
}
