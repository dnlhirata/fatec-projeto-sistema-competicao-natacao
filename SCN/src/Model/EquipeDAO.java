package Model;

import Controller.constroiTabelaEquipe;
import Controller.pesquisaCompeticao;
import Controller.pesquisaEquipe;
import static Model.AtletaDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * //Definições da classe
 *
 * @date 23/09/2017
 * @author Daniel
 */
public class EquipeDAO {

    private static Connection conexao;
    private static Statement stmt;
    private static String sql;

    public static Connection getConnection() throws SQLException {
        Connection conn;
        String url, user, password;

        url = "jdbc:oracle:thin:@localhost:1521:XE";
        user = "system";
        password = "admin";

        conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    public static int pegaUltimoCodigo() throws Exception {
        try {
            // Statements allow to issue SQL queries to the database
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(CODIGO) FROM SYSTEM.EQUIPE");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
        return 0;
    }

    public static String pegaCodigoEquipeNome(String nome) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        String codEquipe = "";

        ResultSet rs = stmt.executeQuery("SELECT CODIGO FROM SYSTEM.EQUIPE WHERE NOME = '" + nome + "'");
        while (rs.next()) {
            codEquipe = rs.getString(1);
        }
        return codEquipe;
    }

    public static int pegaCodigoEquipeAtleta(Atleta a) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        
        int codEquipe = 0;
        ResultSet rs = stmt.executeQuery("SELECT CODIGO FROM SYSTEM.EQUIPE WHERE NOME = '" + a.getEquipe() + "'");
        try {
            while (rs.next()) {
                codEquipe = rs.getInt("CODIGO");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }

        return codEquipe;
    }

    public static ResultSet pesquisarTodasEquipes(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM SYSTEM.EQUIPE WHERE COMPETICAO_CODIGO = " + codCompeticao);
        return rs;
    }

    public static void inserirNovaEquipe(Equipe e) throws SQLException, Exception {
        conexao = getConnection();
        stmt = conexao.createStatement();
        int qntEquipes = pegaUltimoCodigo() + 1;
        int codCompeticao = pesquisaCompeticao.pesquisaCompeticao(e);

        //INSERT INTO nome_tabela VALUES ();
        sql = "INSERT INTO SYSTEM.EQUIPE VALUES (" + qntEquipes + ","
                + "UPPER('" + e.getNome() + "'),"
                + "'" + e.getStatus() + "',"
                + "" + codCompeticao + ","
                + "'" + e.getNacionalidade() + "')";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados da equipe inseridos com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados da equipe");
        }
    }

    public static ResultSet pesquisarEquipeCodigo(int codEquipe) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT A.*, B.NOME AS NOMECOMPETICAO"
                + " FROM SYSTEM.EQUIPE A"
                + " LEFT JOIN SYSTEM.COMPETICAO B ON A.COMPETICAO_CODIGO = B.CODIGO"
                + " WHERE A.CODIGO = " + codEquipe);

        return rs;
    }

    public static void excluirEquipe(int codEquipe) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        retirarEquipes(codEquipe);

        stmt.executeUpdate("DELETE FROM SYSTEM.EQUIPE WHERE CODIGO = " + "'" + codEquipe + "'");
        conexao.close();
        stmt.close();
    }

    public static void carregaEquipes(DefaultTableModel model, int codCompeticao) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.* FROM SYSTEM.EQUIPE A WHERE COMPETICAO_CODIGO = " + codCompeticao);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("Codigo"), rs.getString("Nome"), rs.getString("Nacionalidade")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        } finally {
            conexao.close();
            stmt.close();
        }
    }

    public static void alteraEquipe(Equipe equipe, int codEquipe) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        int codCompeticao = pesquisaCompeticao.pesquisaCompeticao(equipe);

        sql = "UPDATE SYSTEM.EQUIPE"
                + " SET NOME = UPPER('" + equipe.getNome() + "'),"
                + "NACIONALIDADE = '" + equipe.getNacionalidade() + "',"
                + "STATUS = '" + equipe.getStatus() + "',"
                + "COMPETICAO_CODIGO = '" + codCompeticao + "'"
                + " WHERE CODIGO = '" + codEquipe + "'";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados da competição alterados com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados da competição");
        }
    }

    public static void retirarEquipes(int codEquipe) throws SQLException {
        sql = "UPDATE ATLETA SET EQUIPE_CODIGO = 0 WHERE EQUIPE_CODIGO = " + codEquipe;
        stmt.executeUpdate(sql);
    }

    public static ArrayList carregaEquipesCb(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ArrayList<String> equipes;
        equipes = new ArrayList<>();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM SYSTEM.EQUIPE WHERE COMPETICAO_CODIGO = " + codCompeticao);
            while (rs.next()) {
                equipes.add(rs.getString("Nome"));
            }
        } finally {
            conexao.close();
            stmt.close();
        }
        return equipes;
    }

    public static void carregaEquipePesquisada(DefaultTableModel model, String nome) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.* FROM SYSTEM.EQUIPE A WHERE A.NOME = UPPER('" + nome + "')");

            while (rs.next()) {
                ResultSet rsCompeticao = pesquisaCompeticao.pesquisaCompeticao(rs.getInt("Competicao_codigo"));
                String nomeCompeticao = pesquisaCompeticao.pesquisaCompeticao(rsCompeticao);
                model.addRow(new Object[]{rs.getString("Codigo"), rs.getString("Nome"), nomeCompeticao, rs.getString("Nacionalidade")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        } finally {
            conexao.close();
            stmt.close();
        }
    }

    public static DefaultTableModel montaTabelaEquipes(JTable tabela, JScrollPane pane) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "Código", "Nome", "Nacionalidade"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
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

    public static void carregaTabelaEquipePesquisada(JFormattedTextField ftf, JTable table, JScrollPane pane) throws SQLException {
        String nome = ftf.getText();
        DefaultTableModel model = constroiTabelaEquipe.estruturaTabelaEquipe(table, pane);
        pesquisaEquipe.pesquisaEquipe(model, nome);
    }

}
