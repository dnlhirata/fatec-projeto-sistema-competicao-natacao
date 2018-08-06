package Model;

import Controller.constroiTabelaCompeticao;
import Controller.pesquisaCompeticao;
import Model.Competicao;
import static Model.EquipeDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
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
 * @date 23/09/2017
 * @author Daniel
 */
public class CompeticaoDAO {

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
            conexao = getConnection();
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(CODIGO) FROM SYSTEM.COMPETICAO");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
        return 0;
    }

    public static int pegaCodigoCompeticaoNome(String nomeCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        int codCompeticao = 0;
        ResultSet rs = stmt.executeQuery("SELECT CODIGO FROM SYSTEM.COMPETICAO WHERE NOME = '" + nomeCompeticao + "'");
        while (rs.next()) {
            codCompeticao = rs.getInt(1);
        }
        return codCompeticao;
    }

    public static int pegaCodigoCompeticaoEquipe(Equipe eq) throws SQLException {
        int codCompeticao = 0;
        ResultSet rs = stmt.executeQuery("SELECT CODIGO FROM SYSTEM.COMPETICAO WHERE NOME = '" + eq.getCompeticao() + "'");
        try {
            while (rs.next()) {
                codCompeticao = rs.getInt("CODIGO");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }

        return codCompeticao;
    }

    public static String pegaNomeCompeticao(ResultSet rs) throws SQLException {
        String nomeCompeticao = "";
        while (rs.next()) {
            nomeCompeticao = rs.getString("Nome");
        }
        return nomeCompeticao;
    }

    public static void inserirNovaCompeticao(Competicao c) throws SQLException, Exception {
        conexao = getConnection();
        int qntCompeticoes = pegaUltimoCodigo() + 1;
        stmt = conexao.createStatement();

        //INSERT INTO nome_tabela VALUES ();
        sql = "INSERT INTO SYSTEM.COMPETICAO VALUES (" + qntCompeticoes + ","
                + "UPPER('" + c.getNome() + "'),"
                + "'" + c.getLocal() + "',"
                + "'" + c.getDataIni() + "',"
                + "'" + c.getDataFim() + "',"
                + "'" + c.getStatus() + "',"
                + "'" + c.getCidade() + "',"
                + "'" + c.getEstado() + "')";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados da competicao inseridos com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados da competicao");
        }
    }

    public static ResultSet pesquisarCompeticaoCodigo(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT A.*"
                + " FROM SYSTEM.COMPETICAO A"
                + " WHERE A.CODIGO = " + codCompeticao);

        return rs;
    }

    public static void carregaCompeticoes(DefaultTableModel model) {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.* FROM SYSTEM.COMPETICAO A");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }

            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
    }

    public static ArrayList<String> carregaComboBoxCompeticoes() throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        ArrayList<String> competicoes;
        competicoes = new ArrayList<>();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM SYSTEM.COMPETICAO");
            while (rs.next()) {
                competicoes.add(rs.getString("Nome"));
            }
        } finally {
            conexao.close();
            stmt.close();
        }
        return competicoes;
    }

    public static void alterarCompeticao(Competicao competicao, int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "UPDATE SYSTEM.COMPETICAO"
                + " SET NOME = UPPER('" + competicao.getNome() + "'),"
                + "DATA_INI = '" + competicao.getDataIni() + "',"
                + "DATA_FIM = '" + competicao.getDataFim() + "',"
                + "LOCAL = '" + competicao.getLocal() + "',"
                + "CIDADE = '" + competicao.getCidade() + "',"
                + "ESTADO = '" + competicao.getEstado() + "',"
                + "STATUS = '" + competicao.getStatus() + "'"
                + " WHERE CODIGO = '" + codCompeticao + "'";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados da competição alterados com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados da competição");
        }
    }

    public static void excluirCompeticao(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        stmt.executeUpdate("DELETE FROM SYSTEM.COMPETICAO WHERE CODIGO = " + "'" + codCompeticao + "'");
    }

    public static void carregaCompeticaoPesquisada(DefaultTableModel model, int codCompeticao) {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.CODIGO, A.NOME, A.LOCAL, A.DATA_INI, A.DATA_FIM"
                    + " FROM SYSTEM.COMPETICAO A"
                    + " WHERE A.CODIGO = '" + codCompeticao + "'");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }

            stmt.close();
            conexao.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
    }

    public static DefaultTableModel montaTabelaCompeticoes(JTable tabela, JScrollPane pane) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "Código", "Nome", "Local", "Data Inicio", "Data Fim"
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

    public static void carregaTabelaCompeticaoPesquisada(JFormattedTextField ftf, JTable table, JScrollPane pane) {
        int codigo = Integer.parseInt(ftf.getText());
        DefaultTableModel model = constroiTabelaCompeticao.estruturaTabelaAtleta(table, pane);
        pesquisaCompeticao.pesquisaCompeticao(model, codigo);
    }

    public static ResultSet relatorioCompeticoesPorEquipe() throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT A.NOME, B.NOME, B.NACIONALIDADE"
                + " FROM COMPETICAO A"
                + " LEFT JOIN EQUIPE B ON A.CODIGO = B.COMPETICAO_CODIGO";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
    }
    
    public static ResultSet relatorioMedalhas(int codCompeticao) throws SQLException{
        conexao = getConnection();
        stmt = conexao.createStatement();
        
        sql = "SELECT A.ATLETA_CODIGO, B.NOME, A.MEDALHA, COUNT(*) AS QNT_MEDALHAS"
                + " FROM MEDALHA A"
                + " LEFT JOIN ATLETA B ON A.ATLETA_CODIGO = B.CODIGO"
                + " GROUP BY ATLETA_CODIGO, NOME, MEDALHA";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
    }

}
