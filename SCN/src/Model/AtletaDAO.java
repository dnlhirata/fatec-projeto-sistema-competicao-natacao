package Model;

import Controller.pesquisaEquipe;
import static Model.EquipeDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 *
 * @author Daniel
 */
public class AtletaDAO {

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

    public static void inserirNovoAtleta(Atleta a) throws SQLException, Exception {
        conexao = getConnection();
        stmt = conexao.createStatement();

        long codAtleta = Uteis.pegaUltimoCodigo(stmt, "ATLETA") + 1;

        String categoria;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        String nascimento = a.getDtNascimento();
        Date dtNascimento = formatter.parse(nascimento);
        Date dtHoje = new Date();
        long idade = dtHoje.getTime() - dtNascimento.getTime();
        idade = Math.round(idade / (1000 * 60 * 60 * 24 * 7 * 4.3 * 12));

        if (idade <= 10) {
            categoria = "A";
        } else if (idade > 10 && idade <= 18) {
            categoria = "B";
        } else {
            categoria = "C";
        }

        int codEquipe = pesquisaEquipe.pesquisaEquipe(a);

        //INSERT INTO nome_tabela VALUES ();
        sql = "INSERT INTO SYSTEM.ATLETA VALUES (" + codAtleta + ","
                + "UPPER('" + a.getNome() + "'),"
                + "'" + a.getSexo() + "',"
                + "'" + categoria + "',"
                + "'" + a.getDtNascimento() + "',"
                + "'" + a.getCpf() + "',"
                + "'" + a.getEndereco() + "',"
                + "'" + a.getCidade() + "',"
                + "'" + a.getEstado() + "',"
                + "'" + a.getStatus() + "',"
                + codEquipe + ")";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados do atleta inseridos com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados do atleta");
        }
        conexao.close();
        stmt.close();
    }

    public static void carregaAtletas(DefaultTableModel model, int codCompeticao) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.CODIGO, A.NOME, A.CATEGORIA, E.NOME"
                    + " FROM SYSTEM.ATLETA A"
                    + " LEFT JOIN SYSTEM.EQUIPE E ON A.EQUIPE_CODIGO = E.CODIGO"
                    + " WHERE E.COMPETICAO_CODIGO = " + codCompeticao);

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

    public static ResultSet pesquisarAtletaCodigo(int codAtleta) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT A.*, B.NOME AS NOMEEQUIPE"
                + " FROM SYSTEM.ATLETA A"
                + " LEFT JOIN SYSTEM.EQUIPE B ON A.EQUIPE_CODIGO = B.CODIGO"
                + " WHERE A.CODIGO = " + codAtleta);
        return rs;
    }

    public static ResultSet pesquisarTodosAtletas(int codCompeticao) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM SYSTEM.ATLETA A "
                + "LEFT JOIN EQUIPE B ON A.EQUIPE_CODIGO = B.CODIGO "
                + "WHERE B.COMPETICAO_CODIGO = " + codCompeticao);
        return rs;
    }

    public static void carregaAtletaPesquisado(DefaultTableModel model, String cpf) throws SQLException {
        try {
            conexao = getConnection();
            stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.CODIGO, A.NOME, A.CATEGORIA, E.NOME"
                    + " FROM SYSTEM.ATLETA A"
                    + " LEFT JOIN SYSTEM.EQUIPE E ON A.EQUIPE_CODIGO = E.CODIGO"
                    + " WHERE A.CPF = '" + cpf + "'");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage());
        } finally {
            conexao.close();
            stmt.close();
        }
    }

    public static void alterarAtleta(Atleta atleta, String cpf) throws SQLException, ParseException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        String categoria;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        String nascimento = atleta.getDtNascimento();
        Date dtNascimento = formatter.parse(nascimento);
        Date dtHoje = new Date();
        long idade = dtHoje.getTime() - dtNascimento.getTime();
        idade = Math.round(idade / (1000 * 60 * 60 * 24 * 7 * 4.3 * 12));

        if (idade <= 10) {
            categoria = "A";
        } else if (idade > 10 && idade <= 18) {
            categoria = "B";
        } else {
            categoria = "C";
        }

        int codEquipe = pesquisaEquipe.pesquisaEquipe(atleta);

        sql = "UPDATE ATLETA"
                + " SET NOME = UPPER('" + atleta.getNome() + "'),"
                + "SEXO = '" + atleta.getSexo() + "',"
                + "CATEGORIA = '" + categoria + "',"
                + "DTNASCIMENTO = '" + atleta.getDtNascimento() + "',"
                + "ENDERECO = '" + atleta.getEndereco() + "',"
                + "CIDADE = '" + atleta.getCidade() + "',"
                + "ESTADO = '" + atleta.getEstado() + "',"
                + "STATUS = '" + atleta.getStatus() + "',"
                + "EQUIPE_CODIGO = " + codEquipe
                + " WHERE CPF = '" + cpf + "'";

        if (stmt.executeUpdate(sql) > 0) {
            JOptionPane.showMessageDialog(null, "Dados do atleta alterados com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados do atleta");
        }

        conexao.close();
        stmt.close();
    }

    public static void excluirAtleta(int codAtleta) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        stmt.executeUpdate("DELETE FROM SYSTEM.COMPETIDOR WHERE ATLETA_CODIGO = " + "'" + codAtleta + "'");
        stmt.executeUpdate("DELETE FROM SYSTEM.ATLETA WHERE CODIGO = " + "'" + codAtleta + "'");
        conexao.close();
        stmt.close();

    }

    public static DefaultTableModel montaTabelaAtletas(JTable tabela, JScrollPane pane) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "Código", "Nome", "Categoria", "Equipe"
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

    public static String pegaNomeAtleta(String cpf) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOME FROM SYSTEM.ATLETA WHERE CPF = '" + cpf + "'");
        String nome = "";

        while (rs.next()) {
            nome = rs.getString("Nome");
        }

        conexao.close();
        stmt.close();
        return nome;
    }

    public static ArrayList<String> carregaAtletas() throws SQLException {
        //ArrayList<List<String>> listaAtletas = new ArrayList<>();
        ArrayList<String> atletas = new ArrayList<>();

        conexao = getConnection();
        stmt = conexao.createStatement();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT CPF FROM SYSTEM.ATLETA WHERE EQUIPE_CODIGO = 0");
            while (rs.next()) {
                atletas.add(rs.getString("CPF"));

                //listaAtletas.add(asList(atletas.get(0), atletas.get(1)));
                //atletas.clear();
            }
        } finally {
            conexao.close();
            stmt.close();
        }
        return atletas;
    }

    public static void incluirAtletaEquipe(String cpf, int codEquipe) throws SQLException {
        conexao = getConnection();
        stmt = conexao.createStatement();

        sql = "UPDATE ATLETA SET EQUIPE_CODIGO = '" + codEquipe + "' WHERE CPF = '" + cpf + "'";

        stmt.executeUpdate(sql);

        conexao.close();
        stmt.close();
    }
    
    public static int pegaCodigoAtleta(String cpf) throws SQLException {
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
    }

}
