/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CompeticaoDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class deletaCompeticao {
    
    public static void deletaCompeticao(int codCompeticao) throws SQLException{
        CompeticaoDAO.excluirCompeticao(codCompeticao);
    }
    
}
