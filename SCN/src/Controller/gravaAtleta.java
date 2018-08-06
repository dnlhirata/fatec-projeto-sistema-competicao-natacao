/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Atleta;
import Model.AtletaDAO;
import java.sql.SQLException;

/**
 * //Definições da classe
 *
 * @date 23/10/2017
 * @author Daniel
 */
public class gravaAtleta {

    public static void cadastrarNovoAtleta(Atleta atleta) throws Exception {
        AtletaDAO.inserirNovoAtleta(atleta);
    }
    
    public static void alteraAtleta(Atleta atleta, String cpf) throws Exception {
        AtletaDAO.alterarAtleta(atleta, cpf);
    }
    
    public static void cadastrarAtletaEquipe(String cpf, int codEquipe) throws SQLException{
        AtletaDAO.incluirAtletaEquipe(cpf, codEquipe);
    }
}
