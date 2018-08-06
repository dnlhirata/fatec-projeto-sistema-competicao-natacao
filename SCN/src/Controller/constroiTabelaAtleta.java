/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AtletaDAO;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnlhi
 */
public class constroiTabelaAtleta {
    
    public static DefaultTableModel estruturaTabelaAtleta(JTable tabela, JScrollPane pane){
        return AtletaDAO.montaTabelaAtletas(tabela, pane);
    }
    
}
