package Model;

import Controller.gravaCompetidor;
import Controller.pesquisaBateria;
import Controller.pesquisaCompetidor;
import static Model.BateriaDAO.getConnection;
import static Model.CompeticaoDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * //Definições da classe
 *
 * @date 26/09/2017
 * @author Daniel
 */
public class CompetidorDAO {

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

    public static void insereNovoCompetidor(Competidor c, int codAtleta, int codBateria) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        final Random random = new Random();
        final int millisIn5min = 11100000 - 10800000 + 1000;
        Time tempo = new Time((long) random.nextInt(millisIn5min) + 10800000);
        int classificacao = 0;

        sql = "INSERT INTO COMPETIDOR VALUES (" + codAtleta + ","
                + codBateria + ","
                + "'" + tempo + "',"
                + classificacao + ")";

        stmt.executeUpdate(sql);

        conexao.close();
        stmt.close();
    }

    public static ArrayList<String> carregaCompetidoresComboBox(int codCompeticao, int codModalidade, int numBateria) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        ArrayList<String> atletas = new ArrayList<>();

        sql = "SELECT A.CPF || ' - ' || A.NOME"
                + " FROM ATLETA A"
                + " LEFT JOIN EQUIPE B ON A.EQUIPE_CODIGO = B.CODIGO"
                + " WHERE A.CODIGO NOT IN("
                + " SELECT C.ATLETA_CODIGO"
                + " FROM COMPETIDOR C"
                + " LEFT JOIN BATERIA D ON C.BATERIA_CODIGO = D.CODIGO"
                + " WHERE D.COMPETICAO_CODIGO = " + codCompeticao
                + " AND D.MODALIDADE_CODIGO = " + codModalidade + ")"
                + " AND B.COMPETICAO_CODIGO = " + codCompeticao;

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            atletas.add(rs.getString(1));
        }

        return atletas;
    }

    /*public int pegaCodigoAtleta(String cpf) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT CODIGO FROM SYSTEM.ATLETA WHERE CPF = '" + cpf + "'");
        int codAtleta = 0;

        while (rs.next()) {
            codAtleta = rs.getInt("Codigo");
        }

        conexao.close();
        stmt.close();
        return codAtleta;
    }*/
    
    public static void removeCompetidor(String codAtleta, int codBateria) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        stmt.executeUpdate("DELETE FROM SYSTEM.COMPETIDOR WHERE ATLETA_CODIGO = " + "'" + codAtleta + "'"
                + " AND BATERIA_CODIGO = " + codBateria);
        conexao.close();
        stmt.close();
    }

    public static void reclassificarCompetidores(int codBateria) throws SQLException {
        conexao = getConnection();
        //stmt = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //ResultSet rs = stmt.executeQuery("SELECT * FROM SYSTEM.COMPETIDOR WHERE BATERIA_CODIGO = " + codBateria + " ORDER BY TEMPO");
        sql = "SELECT ATLETA_CODIGO, BATERIA_CODIGO, TEMPO, CLASSIFICACAO FROM SYSTEM.COMPETIDOR WHERE BATERIA_CODIGO = " + codBateria + " ORDER BY TEMPO";
        PreparedStatement pstmt = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery();

        int n = 1;
        try {
            while (rs.next()) {
                rs.updateInt("CLASSIFICACAO", n);
                rs.updateRow();
                n = n + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        }
    }

    public static void carregaCompetidoresFinal(DefaultTableModel model, int codModalidade, int codCompeticao) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT C.CODIGO, C.NOME, A.TEMPO, A.CLASSIFICACAO"
                    + " FROM SYSTEM.COMPETIDOR A"
                    + " LEFT JOIN SYSTEM.BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                    + " LEFT JOIN SYSTEM.ATLETA C ON A.ATLETA_CODIGO = C.CODIGO"
                    + " LEFT JOIN SYSTEM.EQUIPE D ON C.EQUIPE_CODIGO = D.CODIGO"
                    + " WHERE B.MODALIDADE_CODIGO = " + codModalidade
                    + " AND B.COMPETICAO_CODIGO = " + codCompeticao
                    + " AND TEMPO < '00:02:00'"
                    + " AND ROWNUM <= 8"
                    + " ORDER BY TEMPO");

            int i = 1;
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), i});
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        } finally {
            conexao.close();
            stmt.close();
        }
    }

    public static void carregaCompetidores(DefaultTableModel model, String numBateria, int codModalidade, int codCompeticao) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            int numBaterias = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);

            if ("Final".equals(numBateria)) {
                numBateria = Integer.toString(numBaterias);
            }

            ResultSet rs = stmt.executeQuery("SELECT C.CODIGO, C.NOME, A.TEMPO, A.CLASSIFICACAO"
                    + " FROM SYSTEM.COMPETIDOR A"
                    + " LEFT JOIN SYSTEM.BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                    + " LEFT JOIN SYSTEM.ATLETA C ON A.ATLETA_CODIGO = C.CODIGO"
                    + " LEFT JOIN SYSTEM.EQUIPE D ON C.EQUIPE_CODIGO = D.CODIGO"
                    + " WHERE B.NUMERO = '" + numBateria + "'"
                    + " AND B.MODALIDADE_CODIGO = " + codModalidade
                    + " AND B.COMPETICAO_CODIGO = " + codCompeticao);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        } finally {
            conexao.close();
            stmt.close();
        }
    }

    public static ResultSet relatorioCompetidores(String numBateria, int codModalidade, int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        int numBaterias = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);

        if ("Final".equals(numBateria)) {
            numBateria = Integer.toString(numBaterias);
        }

        ResultSet rs = stmt.executeQuery("SELECT A.ATLETA_CODIGO, C.NOME, A.TEMPO, A.CLASSIFICACAO,"
                + " CASE WHEN B.NUMERO = '" + numBaterias + "' THEN 'FINAL' ELSE CAST(B.NUMERO AS VARCHAR2(10)) END AS NUMERO"
                + " FROM SYSTEM.COMPETIDOR A"
                + " LEFT JOIN SYSTEM.BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                + " LEFT JOIN SYSTEM.ATLETA C ON A.ATLETA_CODIGO = C.CODIGO"
                + " LEFT JOIN SYSTEM.EQUIPE D ON C.EQUIPE_CODIGO = D.CODIGO"
                + " WHERE B.NUMERO = '" + numBateria + "'"
                + " AND B.MODALIDADE_CODIGO = " + codModalidade
                + " AND B.COMPETICAO_CODIGO = " + codCompeticao);

        return rs;
    }

    public static void geraCompetidoresFinal(int codModalidade, int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String sqlSelect = "SELECT A.ATLETA_CODIGO"
                + " FROM SYSTEM.COMPETIDOR A"
                + " LEFT JOIN SYSTEM.BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                + " WHERE B.MODALIDADE_CODIGO = " + codModalidade
                + " AND B.COMPETICAO_CODIGO = " + codCompeticao
                + " AND A.TEMPO < '00:02:00'"
                + " AND ROWNUM <= 8"
                + " ORDER BY A.TEMPO";
        PreparedStatement pstmt = conexao.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery();

        int numBateriaFinal = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);
        int codBateria = pesquisaBateria.pesquisaCodBateria(numBateriaFinal, codCompeticao, codModalidade);


        /*ResultSet rs = stmt.executeQuery("SELECT A.ATLETA_CODIGO"
                + " FROM SYSTEM.COMPETIDOR A"
                + " LEFT JOIN SYSTEM.BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                + " WHERE B.MODALIDADE_CODIGO = " + codModalidade
                + " AND B.COMPETICAO_CODIGO = " + codCompeticao
                + " AND A.TEMPO < '00:02:00'"
                + " AND ROWNUM <= 8"
                + " ORDER BY A.TEMPO");*/
        while (rs.next()) {

            final Random random = new Random();
            final int millisIn5min = 11100000 - 10800000 + 1000;
            Time tempo = new Time((long) random.nextInt(millisIn5min) + 10800000);

            sql = "INSERT INTO COMPETIDOR VALUES (" + rs.getInt(1) + "," + codBateria + ",'" + tempo + "', 0)";
            stmt.executeUpdate(sql);
        }

        pesquisaCompetidor.classificaCompetidores(codBateria);
        gravaCompetidor.cadastraMedalhas(codModalidade, codCompeticao);

    }

    public static ResultSet relatorioCompetidoresPorBateria(int codModalidade, int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT ATLETA_CODIGO, NOME, TEMPO, NUMERO"
                + " FROM COMPETIDOR A"
                + " LEFT JOIN ATLETA B ON A.ATLETA_CODIGO = B.CODIGO"
                + " LEFT JOIN BATERIA C ON A.BATERIA_CODIGO = C.CODIGO"
                + " WHERE C.MODALIDADE_CODIGO = " + codModalidade
                + " AND C.COMPETICAO_CODIGO = " + codCompeticao;

        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public static ResultSet relatorioCompetidoresPorModalidade(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT DISTINCT A.ATLETA_CODIGO, B.NOME, D.NOME"
                + " FROM COMPETIDOR A"
                + " LEFT JOIN ATLETA B ON A.ATLETA_CODIGO = B.CODIGO"
                + " LEFT JOIN BATERIA C ON A.BATERIA_CODIGO = C.CODIGO"
                + " LEFT JOIN MODALIDADE D ON C.MODALIDADE_CODIGO = D.CODIGO"
                + " WHERE C.COMPETICAO_CODIGO = " + codCompeticao;

        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public static void distribuiMedalhas(int codModalidade, int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        int numBateriaFinal = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);
        String sqlSelect = "SELECT * FROM "
                + " (SELECT A.ATLETA_CODIGO,"
                + " CASE"
                + " WHEN A.CLASSIFICACAO = 1"
                + " THEN 'OURO'"
                + " WHEN A.CLASSIFICACAO = 2"
                + " THEN 'PRATA'"
                + " WHEN A.CLASSIFICACAO = 3"
                + " THEN 'BRONZE'"
                + " END AS MEDALHAS"
                + " FROM COMPETIDOR A"
                + " LEFT JOIN BATERIA B ON A.BATERIA_CODIGO = B.CODIGO"
                + " LEFT JOIN COMPETICAO C ON B.COMPETICAO_CODIGO = C.CODIGO"
                + " WHERE B.COMPETICAO_CODIGO = " + codCompeticao
                + " AND B.MODALIDADE_CODIGO = " + codModalidade
                + " AND B.NUMERO = " + numBateriaFinal + ") M"
                + " WHERE M.MEDALHAS IS NOT NULL";
        PreparedStatement pstmt = conexao.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            sql = "INSERT INTO MEDALHA VALUES (" + rs.getInt(1) + ",'" + rs.getString(2) + "')";
            stmt.executeUpdate(sql);
        }
    }
}
