/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AtletaDAO;

/**
 * //Definições da classe
 *
 * @date 23/10/2017
 * @author Daniel
 */
public class deletaAtleta {

    public static void deletarAtleta(int codAtleta) throws Exception {
        AtletaDAO.excluirAtleta(codAtleta);
    }
}
