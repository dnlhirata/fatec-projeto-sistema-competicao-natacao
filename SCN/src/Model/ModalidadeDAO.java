package Model;


import Model.Uteis;
import Model.Modalidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
public class ModalidadeDAO {

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
    
    static public void inserirNovaModalidade(Modalidade m, int codCompeticao) throws SQLException, Exception{
        conexao = getConnection();
        stmt = conexao.createStatement();
        Uteis funcoes = new Uteis();
        
        int codModalidade = funcoes.pegaUltimoCodigo(stmt, "MODALIDADE") + 1;
        
        sql = "INSERT INTO MODALIDADE VALUES (" + codModalidade + ","
                + "UPPER('" + m.getNome() + " - " + m.getDistancia() + "'),"
                + codCompeticao + ","
                + m.getDistancia() + ","
                + m.getBaterias() + ")";
        
        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados da modalidade inseridos com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados da modalidade");
        }
        conexao.close();
        stmt.close();
    }

    public static ArrayList<String> carregaModalidades(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ArrayList<String> modalidades;
        modalidades = new ArrayList<>();
        
        

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT NOME FROM SYSTEM.MODALIDADE WHERE COMPETICAO_CODIGO = " + codCompeticao);
            while (rs.next()) {
                modalidades.add(rs.getString("Nome"));
            }
        } finally {
            conexao.close();
            stmt.close();
        }
        return modalidades;
    }
    
        public static int pegaCodigoModalidade(String nomeModalidade) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        int codModalidade = 0;

        ResultSet rs = stmt.executeQuery("SELECT A.CODIGO"
                + " FROM SYSTEM.MODALIDADE A"
                + " WHERE NOME = '" + nomeModalidade + "'");

        while (rs.next()) {
            codModalidade = rs.getInt("Codigo");
        }

        conexao.close();
        stmt.close();
        return codModalidade;
    }
}
