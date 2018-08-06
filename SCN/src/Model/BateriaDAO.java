package Model;

import Controller.pesquisaBateria;
import static Model.CompetidorDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * //Definições da classe
 *
 * @date 24/09/2017
 * @author Daniel
 */
public class BateriaDAO {

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

    public static int pegaNumBateria(int codModalidade, int codCompeticao) throws SQLException {
        int numBaterias = 0;

        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT BATERIAS FROM MODALIDADE"
                + " WHERE CODIGO = " + codModalidade
                + " AND COMPETICAO_CODIGO = " + codCompeticao;

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            numBaterias = rs.getInt("BATERIAS");
        }

        return numBaterias;
    }

    public static int pegaCodBateria(int numBateria, int codModalidade, int codCompeticao) throws SQLException {
        int codBateria = 0;

        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT CODIGO FROM BATERIA"
                + " WHERE MODALIDADE_CODIGO = " + codModalidade
                + " AND COMPETICAO_CODIGO = " + codCompeticao
                + " AND NUMERO = " + numBateria;

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            codBateria = rs.getInt("CODIGO");
        }

        return codBateria;
    }

    public static ArrayList<Integer> preencheComboBoxBaterias(int numBaterias) {
        ArrayList<Integer> baterias = new ArrayList<>();

        for (int i = 1; i <= numBaterias; i++) {
            baterias.add(i);
        }

        return baterias;
    }

    public static int inserirNovaBateria(Bateria b, int codModalidade, int codCompeticao) throws SQLException, Exception {
        conexao = getConnection();
        stmt = conexao.createStatement();
        Uteis funcoes = new Uteis();

        int codigo = funcoes.pegaUltimoCodigo(stmt, "BATERIA") + 1;

        boolean validado = validaBateria(codCompeticao, codModalidade, b.getNumero());

        if (validado) {
            sql = "INSERT INTO BATERIA VALUES (" + codigo + ","
                    + "'" + b.getData() + "',"
                    + "'" + b.getHoraIni() + "',"
                    + "'" + b.getHoraFim() + "',"
                    + "'" + b.getStatus() + "',"
                    + codModalidade + ","
                    + codCompeticao + ","
                    + b.getNumero() + ")";

            stmt.executeUpdate(sql);
        } else {
            JOptionPane.showMessageDialog(null, "Bateria já existente. Verificar os dados prenchidos");
            return 0;
        }

        return codigo;
    }

    public static ArrayList<String> carregaBaterias(int codCompeticao, int codModalidade) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ArrayList<String> baterias;
        baterias = new ArrayList<>();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT NUMERO FROM SYSTEM.BATERIA WHERE COMPETICAO_CODIGO = " + codCompeticao + "AND MODALIDADE_CODIGO = " + codModalidade);
            while (rs.next()) {
                baterias.add(rs.getString("NUMERO"));
            }
        } finally {
            conexao.close();
            stmt.close();
        }
        return baterias;
    }

    public static boolean validaBateria(int codCompeticao, int codModalidade, String numero) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "SELECT * FROM SYSTEM.BATERIA WHERE COMPETICAO_CODIGO = " + codCompeticao
                + " AND MODALIDADE_CODIGO = " + codModalidade + ""
                + " AND NUMERO = " + numero;

        ResultSet rs = stmt.executeQuery(sql);
        if (!rs.isBeforeFirst()) {
            return true;
        }
        /*while (rs.next()) {
            System.out.println(rs.getInt("Valida"));
            if (rs.getInt(1) == 0) {
                return true;
            }
        }*/
        return false;
    }

    /*public void atualizaClassificao() throws SQLException{
        conexao = getConnection();
        stmt = conexao.createStatement();
        
        sql = "SELECT CAST(TEMPO AS TIME) FROM COMPETIDOR ORDER BY TEMPO";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
    }*/
    public static ResultSet alteraBateria(String numBateria, int codCompeticao, int codModalidade) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        int numBaterias = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);

        if ("Final".equals(numBateria)) {
            numBateria = Integer.toString(numBaterias);
        }

        sql = "SELECT * FROM SYSTEM.BATERIA WHERE COMPETICAO_CODIGO = " + codCompeticao
                + " AND MODALIDADE_CODIGO = " + codModalidade + ""
                + " AND NUMERO = '" + numBateria + "'";

        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public static boolean geraBateriaFinal(int codModalidade, int codCompeticao) throws SQLException, Exception {
        conexao = getConnection();
        stmt = conexao.createStatement();
        Uteis funcoes = new Uteis();
        
        int numBateriaFinal = pesquisaBateria.qtdBaterias(codModalidade, codCompeticao);
        
        int codigo = funcoes.pegaUltimoCodigo(stmt, "BATERIA") + 1;
        
        boolean validado = validaBateria(codCompeticao, codModalidade, Integer.toString(numBateriaFinal));

        if (validado) {
            sql = sql = "INSERT INTO BATERIA VALUES (" + codigo + ", '10-10-1010', '10:10:10', '10:10:10', 'FINALIZADO', " + codModalidade + ", " + codCompeticao + "," + numBateriaFinal + ")";
            stmt.executeUpdate(sql);
        } else {
            JOptionPane.showMessageDialog(null, "Bateria já existente. Verificar os dados prenchidos");
            return false;
        }
        return true;
    }

    
}
