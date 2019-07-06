/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Equipamentos;
import Model.Sessao;
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
public class EquipamentoDAO {
    private static EquipamentoDAO EquipamentoDAO;
    private static Connection con;
    private static Sessao aSessao;
    
    public static EquipamentoDAO getInstancia() {

        if (EquipamentoDAO == null) {
            EquipamentoDAO = new EquipamentoDAO();
        }

        return EquipamentoDAO;
    }
    
    public EquipamentoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
  
    
    public List<Equipamentos> read(){
        List<Equipamentos> equipamentos = new ArrayList();
        String query = "SELECT e.equipamento_nome,e.id_equipamentos,eu.id_equipamento_do_usuario,eu.idequipamentos FROM equipamento_user eu INNER JOIN equipamentos e on(e.id_equipamentos=eu.idequipamentos) INNER JOIN usuario u on (u.id_usuario=eu.idusuario) where u.id_usuario= "+Sessao.getInstancia().getUsuario().getId()+"";
        try {
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           Equipamentos equipamento = new Equipamentos();
           equipamento.setEquipamento_nome(rs.getString("e.equipamento_nome"));
           equipamento.setId_equipamentos(rs.getLong("eu.idequipamentos"));
           equipamento.setId_equipamento_do_Usuario(rs.getLong("eu.id_equipamento_do_usuario"));
           equipamentos.add(equipamento);
        }
        ps.close();
        rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipamentos;
    }
    
}
