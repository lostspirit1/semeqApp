/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Departamento;
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
public class DepartamentoDAO {

    private Connection con;

    public DepartamentoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public List<Departamento> read(){
        List<Departamento> departamentos = new ArrayList();
        String query = "SELECT * FROM departamentos";
        try {
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           Departamento departamento = new Departamento();
           departamento.setId_departamento(rs.getInt("id_departamentos"));
           departamento.setNome_setor(rs.getString("nome_setor"));
           departamentos.add(departamento);
        }
        ps.close();
        rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departamentos;
    }
}