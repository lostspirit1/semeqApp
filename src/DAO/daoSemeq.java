/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SpiriT
 */
public class daoSemeq {

    ConnectionFactory conn = new ConnectionFactory();
    private ResultSet rs;
    private Statement stm;

    public daoSemeq() {

    }

    public void alterarSenhaPorId(int id, String novaSenha) {
        try {
            conn.getConnection();
            String sql = "SELECT id,login,senha FROM usuario where id = " + id;
            conn.executeSQL(sql);
            if (conn.resultset.next()) {
                System.out.println("Você pode alterar a senha.");
                String update = "UPDATE usuario SET senha='" + novaSenha + "' WHERE id = " + id + "";
                conn.statement.executeUpdate(update);
            } else {
                System.out.println("Acesso negado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void alterarSenhaPorString(String usuario, String senha, String novaSenha) {
        try {
            conn.getConnection();
            String sql = "SELECT id,login,senha FROM usuario where login = '" + usuario + "' and  senha = '" + senha + "'";
            conn.executeSQL(sql);
            if (conn.resultset.next()) {
                int id = conn.resultset.getInt("id");
                String update = "UPDATE usuario SET senha='" + novaSenha + "' WHERE id = " + id + "";
                conn.statement.executeUpdate(update);
                System.out.println("Senha alterada com sucesso.");
            } else {
                System.out.println("Acesso negado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
        public void adicionarUSER(String usuario, String senha, String confirmarSenha) {
        try {
            conn.getConnection();
            String sql = "SELECT id,login,senha FROM usuario where login = '" + usuario + "'";
            conn.executeSQL(sql);
            if (conn.resultset.next()) {
                System.out.println("Úsuario já está Cadastrado.");
            } else {
                String insert = "INSERT INTO usuario(nome,senha) VALUES('" + usuario + "','" + senha +"')";
                conn.statement.executeQuery(insert);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
        
}