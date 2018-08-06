/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bateria;
import Model.BateriaDAO;

/**
 *
 * @author dnlhi
 */
public class gravaBateria {
    
    public static int cadastrarNovaBateria(Bateria bateria, int codModalidade, int codCompeticao) throws Exception {
        return BateriaDAO.inserirNovaBateria(bateria, codModalidade, codCompeticao);
    }
    
    public static boolean cadastraBateriaFinal(int codModalidade, int codCompeticao) throws Exception {
        return BateriaDAO.geraBateriaFinal(codModalidade, codCompeticao);
    }
    
}
