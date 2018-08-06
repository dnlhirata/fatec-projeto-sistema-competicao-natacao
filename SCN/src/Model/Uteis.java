package Model;

import Controller.pesquisaCompeticao;
import View.FrameAdicionaBateria;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * //Definições da classe
 *
 * @date 18/09/2017
 * @author Daniel
 */
public class Uteis {

    BateriaDAO aBD = new BateriaDAO();
    CompeticaoDAO aBDCompeticao = new CompeticaoDAO();
    CompetidorDAO aBDCompetidor = new CompetidorDAO();
    ModalidadeDAO aBDModalidade = new ModalidadeDAO();

    public ArrayList carregaCidades() {
        ArrayList<String> rj = new ArrayList<>();
        ArrayList<String> sp = new ArrayList<>();
        ArrayList<String> pr = new ArrayList<>();
        ArrayList<String> ba = new ArrayList<>();
        ArrayList<String> sc = new ArrayList<>();
        ArrayList<ArrayList<String>> cidades = new ArrayList<>();

        pr.add("Apucarana");
        pr.add("Curitiba");
        pr.add("Toledo");
        pr.add("Araucária");
        pr.add("Colombo");

        sp.add("Atibaia");
        sp.add("Sumaré");
        sp.add("São Paulo");
        sp.add("Barueri");
        sp.add("Embu das Artes");

        rj.add("Rio de Janeiro");
        rj.add("São Gonçalo");
        rj.add("Magé");
        rj.add("Resende");
        rj.add("Itaboraí");

        sc.add("Chapecó");
        sc.add("Balneário Camboriú");
        sc.add("Florianópolis");
        sc.add("Itajaí");
        sc.add("Joinville");

        ba.add("Camaçari");
        ba.add("Salvador");
        ba.add("Simões Filho");
        ba.add("Itabuna");
        ba.add("Juazeiro");

        cidades.add(ba);
        cidades.add(sp);
        cidades.add(rj);
        cidades.add(pr);
        cidades.add(sc);

        return cidades;
    }

    public static int pegaUltimoCodigo(Statement stmt, String tabela) throws Exception {
        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(CODIGO) FROM SYSTEM." + tabela);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
        return 0;
    }

    public ArrayList<String> verificaSelecionados(JPanel painel) {
        ArrayList<String> selecionados = new ArrayList<>();
        for (Component c : painel.getComponents()) {
            if (c instanceof JComboBox) {
                if (((JComboBox) c).isEnabled()) {
                    selecionados.add((String) ((JComboBox) c).getSelectedItem());
                }
            }
        }
        return selecionados;
    }

    public void preencheComboBox(JComboBox cb, JLabel lb, JPanel painel, JFrame frame, int numBateria) {
        cb.removeAllItems();
        try {
            String nomeCompeticao = (String) lb.getText();
            int codCompeticao = pesquisaCompeticao.pesquisaCompeticao(nomeCompeticao);
            int codModalidade = aBDModalidade.pegaCodigoModalidade(frame.getName());
            ArrayList<String> atletas = aBDCompetidor.carregaCompetidoresComboBox(codCompeticao, codModalidade, numBateria);
            ArrayList<String> selecionados = this.verificaSelecionados(painel);
            for (int i = 0; i < atletas.size(); i++) {
                if (!selecionados.contains(atletas.get(i))) {
                    cb.addItem(atletas.get(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrameAdicionaBateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void habilitaComboBox(JCheckBox chk, JComboBox cb, JPanel painel, JLabel lb, JFrame frame, int numBateria) {
        if (chk.isSelected()) {
            cb.setEnabled(true);
            preencheComboBox(cb, lb, painel, frame, numBateria);
        } else {
            cb.setEnabled(false);
            cb.removeAllItems();
        }
    }

    public DefaultTableModel montaTabelaCompetidor(JTable tabela, JScrollPane pane) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "Código", "Nome", "Tempo", "Classificação"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoCreateRowSorter(true);
        tabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabela.setModel(model);
        tabela.setAutoscrolls(false);

        pane.setViewportView(tabela);
        return model;
    }

    public String pegaUltimoCaracter(JFrame frame) {
        String numBateria = "";
        try {
            numBateria = Character.toString(frame.getTitle().charAt(frame.getTitle().length() - 1));
            Integer.parseInt(numBateria);
        } catch (NumberFormatException e) {
            numBateria = "Final";
        }
        
        System.out.println(numBateria);

        return numBateria;
    }

}
