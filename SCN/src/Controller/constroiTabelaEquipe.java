/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EquipeDAO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnlhi
 */
public class constroiTabelaEquipe {

    public static DefaultTableModel estruturaTabelaEquipe(JTable tabela, JScrollPane pane) {
        return EquipeDAO.montaTabelaEquipes(tabela, pane);
    }

}
