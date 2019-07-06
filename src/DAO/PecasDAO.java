/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Pecas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author SpiriT
 */
public class PecasDAO {
    private static Connection con;

    public PecasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public List<Pecas> pegarPecasById(List<Integer> ids) {
        List<Pecas> pecaslist = new ArrayList<>();
    if (ids.isEmpty()) {
        return new ArrayList<>(0);
    } else {
        String sql = ids.stream().map(Object::toString).collect(Collectors.joining(",", "SELECT * FROM pecas WHERE id_pecas IN (", ")"));

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {       
                Pecas pecas = new Pecas();
                pecas.setIdpecas(rs.getInt("id_pecas"));
                pecas.setNome(rs.getString("nome"));
                pecas.setQtd_Pecas(rs.getInt("qtdPecas"));
                pecaslist.add(pecas);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return pecaslist;
    }   
}
    
}
