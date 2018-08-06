/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Modalidade;
import Model.ModalidadeDAO;

/**
 *
 * @author dnlhi
 */
public class gravaModalidade {
    
    public static void cadastrarNovaModalidade(Modalidade modalidade, int codCompeticao) throws Exception {
        ModalidadeDAO.inserirNovaModalidade(modalidade, codCompeticao);
    }
}
