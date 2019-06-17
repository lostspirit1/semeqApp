/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Requisicao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SpiriT
 */
public class RequisicaoDAO  {
    
    private static RequisicaoDAO aRequisicaoDAO; 
    public static RequisicaoDAO getInstancia() {
		
		if (aRequisicaoDAO == null) {
			aRequisicaoDAO = new RequisicaoDAO();
		}
		
		return aRequisicaoDAO;
	}
    
    private Connection con;
    public RequisicaoDAO(){
    this.con = new ConnectionFactory().getConnection();
    }
    public boolean addReq(Requisicao r){
        String sql = "INSERT INTO requisicao(nome) VALUES(?)";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, r.getNome());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
    
    public boolean update(Requisicao r){
        String sql = "UPDATE requisicao set nome = ? WHERE id_requisicao=?";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, r.getNome());
        stmt.setLong(2, r.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
        
    public boolean delete(Requisicao r){
        String sql = "DELETE  FROM requisicao WHERE id_requisicao=?";
        try{
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, r.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
        } catch (SQLException ex){
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }
    }
    
    public List<Requisicao> getList(){
       List<Requisicao> requisicoes = new ArrayList<>();
       String sql = "SELECT * FROM requisicao";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Requisicao req = new Requisicao();
                req.setId(rs.getLong("id_requisicao"));
                req.setNome(rs.getString("nome"));
                requisicoes.add(req);
            }
            stmt.close();
            rs.close();
            con.close();  
        } catch (SQLException ex) {
            return null;
        }
        return requisicoes;
    }
    
    
}
