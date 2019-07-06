/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Boletim;
import Model.Requisicao;
import Model.Servicos;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SpiriT
 */
public class BoletimDAO {

    private Connection con;

    public BoletimDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public List<Boletim> getList() {
        List<Boletim> boletim = new ArrayList<>();
        String sql = "SELECT b.*,sr.id_servicos,sr.nomes,u.nome from boletim b INNER JOIN computador_requisicao cr ON (cr.id = b.idcomputador_requisicao) INNER JOIN usuario u ON (u.id_usuario = cr.idusuario) INNER JOIN relatorio_servicos rs ON (rs.idboletim = b.id_boletim) INNER JOIN servicos sr ON (sr.id_servicos = rs.idservicos)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario bUsuario = new Usuario();
                bUsuario.setNome(rs.getString("u.nome"));
                Servicos bServicos = new Servicos(rs.getLong("sr.id_servicos"), rs.getString("sr.nomes"));
                Requisicao bRequisicao = new Requisicao(rs.getLong("b.idcomputador_requisicao"));
                Boletim bole = new Boletim();
                bole.setId_boletim(rs.getLong("b.id_boletim"));
                bole.setbRequisicao(bRequisicao);
                bole.setData_saida(rs.getDate("b.data_saida"));
                bole.setbServicos(bServicos);
                bole.setbUsuario(bUsuario);
                boletim.add(bole);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            return null;
        }
        return boletim;
    }
}
