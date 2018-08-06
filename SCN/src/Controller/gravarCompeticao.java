/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Competicao;
import Model.CompeticaoDAO;
import java.sql.SQLException;

/**
 *
 * @author dnlhi
 */
public class gravarCompeticao {
    
    public static void cadastrarNovaCompeticao(Competicao c) throws Exception{
        CompeticaoDAO.inserirNovaCompeticao(c);
    }
    
    public static void alterarCompeticao(Competicao c, int codCompeticao) throws SQLException{
        CompeticaoDAO.alterarCompeticao(c, codCompeticao);
    }
    
}
