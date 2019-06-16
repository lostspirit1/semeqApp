/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Usuario;
import Views.AlterarDados;
import Views.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

/**
 *
 * @author SpiriT
 */
public class UsuarioDAO  {
    private Connection con;
    
    public UsuarioDAO(){
    this.con = new ConnectionFactory().getConnection();
    }
    public boolean add(Usuario u){
        String sql = "INSERT INTO usuario(login,senha) VALUES(?,?)";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getLogin());
        stmt.setString(2, u.getSenha());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
    
    public boolean update(Usuario u){
        String sql = "UPDATE usuario set senha = ? WHERE id_usuario=?";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getSenha());
        stmt.setLong(2, u.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
        
    public boolean delete(Usuario u){
        String sql = "DELETE  FROM usuario WHERE id_usuario=?";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, u.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
    

    
    
    public List<Usuario> getList(){
       List<Usuario> usuarios = new ArrayList<>();
       String sql = "SELECT * FROM usuario";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getLong("id_usuario"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
            stmt.close();
            rs.close();
            con.close();  
        } catch (SQLException ex) {
            return null;
        }
        return usuarios;
    }
 
    public Usuario validarSenha(Usuario u){
    String sql = "SELECT id_usuario,nome FROM usuario where login = ? and senha = ?";
    try {
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getLogin());
        stmt.setString(2, u.getSenha());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            Usuario usuarioLogado = new Usuario(rs.getLong("id_usuario"));
            usuarioLogado.setNome(rs.getString("nome")); // vai exibir mesmo o login?
                    AlterarDados ad = new AlterarDados();
        fecha();
        try {
            ad.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            return usuarioLogado;
        }
        stmt.close();
        rs.close();
        con.close();  
    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
    return null;
    }
    
        public void fecha(){
        Login.getStage().close();
    }
    
}
