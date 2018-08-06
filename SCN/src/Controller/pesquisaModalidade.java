/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModalidadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dnlhi
 */
public class pesquisaModalidade {
    
    public static int pesquisaModalidade(String nomeModalidade) throws Exception {
        return ModalidadeDAO.pegaCodigoModalidade(nomeModalidade);
    }
    
    public static ArrayList<String> carregaComboBoxModalidade(int codModalidade) throws SQLException{
        return ModalidadeDAO.carregaModalidades(codModalidade);
    }
}
