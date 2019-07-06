/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Alerts;
import Model.ChefesDepartamentos;
import Model.Departamento;
import Model.Equipamentos;
import Model.Pecas;
import Model.Permissao;
import Model.Requisicao;
import Model.Servicos;
import Model.Sessao;
import Model.Status;
import Model.StatusDetalhes;
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
public class RequisicaoDAO {

    private Connection con;
    Alerts alerts = new Alerts();
    public RequisicaoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    private static RequisicaoDAO aRequisicaoDAO;
    
    public static RequisicaoDAO getInstancia() {

        if (aRequisicaoDAO == null) {
            aRequisicaoDAO = new RequisicaoDAO();
        }

        return aRequisicaoDAO;
    }

    public boolean addReq(Requisicao r, Equipamentos e) {

        try {
            con.setAutoCommit(false);
            PreparedStatement addRequisicao = con.prepareStatement("INSERT INTO equipamento_requisicao(nome, data_requisicao, observacao, idequipamento_user) VALUES(?, now(), ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            addRequisicao.setString(1, r.getNome());
            addRequisicao.setString(2, r.getMotivo());
            addRequisicao.setLong(3, e.getId_equipamento_do_Usuario());
            addRequisicao.executeUpdate();
            ResultSet rs = addRequisicao.getGeneratedKeys();
            rs.next();
            Long idGerado = rs.getLong(1);
            PreparedStatement addStatus = con.prepareStatement("INSERT INTO detalhe_status(idequipamento_requisicao,idusuario,idstatus,data,observacao) VALUES(?,0,1, now(),?)");
            addStatus.setLong(1, idGerado);
            addStatus.setString(2, r.getMotivo());
            addStatus.executeUpdate();
            con.commit();
        } catch (SQLException exx) {
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, exx);
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        return true;
    }

    public boolean update(Requisicao r) {
        String sql = "UPDATE requisicao set nome = ? WHERE id_requisicao=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, r.getNome());
            stmt.setLong(2, r.getId());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(Requisicao r) throws SQLException {
        String sql = "DELETE  FROM computador_requisicao WHERE id=?";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, r.getId());
            stmt.executeUpdate();
            stmt.close();
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public List<Requisicao> getList() {
        List<Requisicao> requisicoes = new ArrayList<>();
        String sql = "SELECT * FROM equipamento_requisicao equipreq INNER JOIN equipamento_user equipuser ON (equipreq.idequipamento_user = equipuser.id_equipamento_do_usuario)  INNER JOIN usuario user ON (user.id_usuario=equipuser.idusuario) INNER JOIN equipamentos equip ON (equip.id_equipamentos = equipuser.idequipamentos) INNER JOIN detalhe_status dStatus ON (dStatus.idequipamento_requisicao= equipreq.id_requisicao) INNER JOIN status_requisicao statusreq on (statusreq.id_status= dStatus.idstatus) INNER JOIN permissao p ON(user.idpermissao= p.id_permissao) INNER JOIN departamentos dp ON(user.iddepartamento = dp.id_departamentos) INNER JOIN chefe_departamento cp ON(dp.id_departamentos = cp.iddepartamento) where statusreq.categoria='Ativo' ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {       
                
                /*
                //CHEFES DE DEPARTAMENTOS            
                // usuario chefe depart
                Usuario usuarioChefe = new Usuario();
                usuarioChefe.setId(rs.getLong("cp.idusuario")); 
                // usuario chefe depart
                // departamento chefe
                Departamento departamentoChefe = new Departamento();
                departamentoChefe.setId_departamento(rs.getInt("cp.iddepartamento"));
                // departamento chefe
                // INICIO DA TABELA CHEFE
                ChefesDepartamentos chefes = new ChefesDepartamentos();
                chefes.setId_chefe(rs.getInt("cp.id_chefe"));
                chefes.setCpIdusuario(usuarioChefe);
                chefes.setCpIddepartamento(departamentoChefe);
                // FIM TABELA CHEFE
                // FIM */   // PARA ESTUDOS
                
                // INICIO TABELA USUARIO 
                
                //Permissao Usuario
                Permissao permissaoUsuario = new Permissao();
                permissaoUsuario.setId_permissao(rs.getInt("user.idpermissao"));
                permissaoUsuario.setCategoria_permissao(rs.getString("p.categoria"));
                //fim permissao usuario
                //incio departamento usuario
                Departamento departamentoUsuario = new Departamento();
                departamentoUsuario.setId_departamento(rs.getInt("dp.id_departamentos"));
                departamentoUsuario.setNome_setor(rs.getString("dp.nome_setor"));
                //FIM
                // usuario
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("user.nome"));
                usuario.setId(rs.getLong("user.id_usuario"));
                usuario.setMatricula(rs.getString("user.matricula"));
                usuario.setLogin(rs.getString("user.login"));
                usuario.setpUsuario(permissaoUsuario);
                usuario.setdUsuario(departamentoUsuario);
                // FIM TABELA USUARIO               
                //equipamento // equipamento user tabelas
                Equipamentos equipamento = new Equipamentos();
                equipamento.setEquipamento_nome(rs.getString("equip.equipamento_nome"));
                equipamento.setSerial_equipamento(rs.getString("equipuser.serial_equipamento"));
                equipamento.setId_equipamento_do_Usuario(rs.getLong("equipreq.idequipamento_user"));
                //status tabela
                Status status = new Status();
                status.setCategoria(rs.getString("statusreq.categoria"));
                status.setIdstatus(rs.getInt("statusreq.id_status"));
                //status detalhes tabela
                Usuario usuarioStatus = new Usuario();//id do usuaro na tabela detalhes status
                usuarioStatus.setId(rs.getLong("dStatus.idusuario"));
                StatusDetalhes statusDetalhes = new StatusDetalhes();
                statusDetalhes.setId_statusdetalhes(rs.getLong("dStatus.id_statusdetalhes"));
                statusDetalhes.setData_status(rs.getTimestamp("dStatus.data"));
                statusDetalhes.setObservacao_status(rs.getString("dStatus.observacao"));
                statusDetalhes.setIdUsuario(usuarioStatus);
                statusDetalhes.setIdStatus(status);
                // Id da requisicao na tabela detalhes status
                Requisicao requisicaoStatus = new Requisicao();
                requisicaoStatus.setId(rs.getLong("dStatus.idequipamento_requisicao"));
                //requisicao tabela
                Requisicao req = new Requisicao();
                req.setId(rs.getLong("equipreq.id_requisicao"));
                req.setNome(rs.getString("equipreq.nome"));
                req.setData_criada(rs.getTimestamp("equipreq.data_requisicao"));
                req.setMotivo(rs.getString("equipreq.observacao"));
                req.setReqEquipamento(equipamento);
                req.setReqStatus(status);
                req.setReqUsuario(usuario);
                req.setReqStatus_Detalhes(statusDetalhes);
                requisicoes.add(req);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return requisicoes;
    }
    // pegando status da requisicao selecionada.
    public Requisicao verificarStatus(Requisicao r){
        try {
        String verStatus = "SELECT * FROM detalhe_status where id_statusdetalhes="+r.getReqStatus_Detalhes().getId_statusdetalhes()+" ";
        PreparedStatement stmt = con.prepareStatement(verStatus);
        ResultSet rsStatus = stmt.executeQuery();
        rsStatus.next();
        r.getReqStatus().setIdstatus(rsStatus.getInt("idstatus"));
        return r;
        }catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    //inserindo
    public boolean inserir(List<Servicos> s, Requisicao r, String motivo, List<Pecas> p) throws SQLException {
        try {
            con.setAutoCommit(false);
            PreparedStatement inserirServicos = con.prepareStatement("INSERT INTO boletim (idequipamento_requisicao,motivo) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            inserirServicos.setLong(1, r.getId());
            inserirServicos.setString(2, motivo);
            inserirServicos.executeUpdate();
            ResultSet rs = inserirServicos.getGeneratedKeys();
            rs.next();
            Long idGerado = rs.getLong(1);
            for (Servicos servicosBd : s) {
                String servicossql = "INSERT INTO relatorio_servicos (idservicos,idboletim) VALUES(" + servicosBd.getId_Servicos() + "," + idGerado + ")";
                PreparedStatement inserirservicos = con.prepareStatement(servicossql);
                inserirservicos.executeUpdate();
            }
            for (Pecas pecasBD : p) {
                String pecassql = "INSERT INTO pecas_relatorio (idequipamento_requisicao,idpecas) VALUES(?,?)";
                PreparedStatement inserirpecas = con.prepareStatement(pecassql);
                inserirpecas.setLong(1, r.getId());
                inserirpecas.setInt(2, pecasBD.getIdpecas());
                inserirpecas.executeUpdate();
            }
            String status = "UPDATE detalhe_status set idequipamento_requisicao = ? , idusuario=?, idstatus=2, data=now(), observacao=?  where id_statusdetalhes="+r.getReqStatus_Detalhes().getId_statusdetalhes()+"";
            PreparedStatement statussmt = con.prepareStatement(status );
            statussmt.setLong(1, r.getId());
            statussmt.setLong(2, Sessao.getInstancia().getUsuario().getId()); 
            statussmt.setString(3, motivo);
            statussmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    

        public List<Requisicao> pegarRequisicoesUser() {
        List<Requisicao> requisicoes = new ArrayList<>();
        String sql = "SELECT * FROM equipamento_requisicao equipreq INNER JOIN equipamento_user equipuser ON (equipreq.idequipamento_user = equipuser.id_equipamento_do_usuario)  INNER JOIN usuario user ON (user.id_usuario=equipuser.idusuario) INNER JOIN equipamentos equip ON (equip.id_equipamentos = equipuser.idequipamentos) INNER JOIN detalhe_status dStatus ON (dStatus.idequipamento_requisicao= equipreq.id_requisicao) INNER JOIN status_requisicao statusreq on (statusreq.id_status= dStatus.idstatus) INNER JOIN permissao p ON(user.idpermissao= p.id_permissao) INNER JOIN departamentos dp ON(user.iddepartamento = dp.id_departamentos) INNER JOIN chefe_departamento cp ON(dp.id_departamentos = cp.iddepartamento) where statusreq.categoria='Ativo' and user.id_usuario = "+ Sessao.getInstancia().getUsuario().getId() + " ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {       
                

                //CHEFES DE DEPARTAMENTOS            
                // usuario chefe depart
                Usuario usuarioChefe = new Usuario();
                usuarioChefe.setId(rs.getLong("cp.idusuario")); 
                // usuario chefe depart
                // departamento chefe
                Departamento departamentoChefe = new Departamento();
                departamentoChefe.setId_departamento(rs.getInt("cp.iddepartamento"));
                // departamento chefe
                // INICIO DA TABELA CHEFE
                ChefesDepartamentos chefes = new ChefesDepartamentos();
                chefes.setId_chefe(rs.getInt("cp.id_chefe"));
                chefes.setCpIdusuario(usuarioChefe);
                chefes.setCpIddepartamento(departamentoChefe);
                // FIM TABELA CHEFE
                // FIM    // PARA ESTUDOS
                
                // INICIO TABELA USUARIO 
                
                //Permissao Usuario
                Permissao permissaoUsuario = new Permissao();
                permissaoUsuario.setId_permissao(rs.getInt("user.idpermissao"));
                permissaoUsuario.setCategoria_permissao(rs.getString("p.categoria"));
                //fim permissao usuario
                //incio departamento usuario
                Departamento departamentoUsuario = new Departamento();
                departamentoUsuario.setId_departamento(rs.getInt("dp.id_departamentos"));
                departamentoUsuario.setNome_setor(rs.getString("dp.nome_setor"));
                //FIM
                // usuario
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("user.nome"));
                usuario.setId(rs.getLong("user.id_usuario"));
                usuario.setMatricula(rs.getString("user.matricula"));
                usuario.setLogin(rs.getString("user.login"));
                usuario.setpUsuario(permissaoUsuario);
                usuario.setdUsuario(departamentoUsuario);
                // FIM TABELA USUARIO               
                //equipamento // equipamento user tabelas
                Equipamentos equipamento = new Equipamentos();
                equipamento.setEquipamento_nome(rs.getString("equip.equipamento_nome"));
                equipamento.setSerial_equipamento(rs.getString("equipuser.serial_equipamento"));
                equipamento.setId_equipamento_do_Usuario(rs.getLong("equipreq.idequipamento_user"));
                //status tabela
                Status status = new Status();
                status.setCategoria(rs.getString("statusreq.categoria"));
                status.setIdstatus(rs.getInt("statusreq.id_status"));
                //status detalhes tabela
                Usuario usuarioStatus = new Usuario();//id do usuaro na tabela detalhes status
                usuarioStatus.setId(rs.getLong("dStatus.idusuario"));
                StatusDetalhes statusDetalhes = new StatusDetalhes();
                statusDetalhes.setId_statusdetalhes(rs.getLong("dStatus.id_statusdetalhes"));
                statusDetalhes.setData_status(rs.getTimestamp("dStatus.data"));
                statusDetalhes.setObservacao_status(rs.getString("dStatus.observacao"));
                statusDetalhes.setIdUsuario(usuarioStatus);
                statusDetalhes.setIdStatus(status);
                // Id da requisicao na tabela detalhes status
                Requisicao requisicaoStatus = new Requisicao();
                requisicaoStatus.setId(rs.getLong("dStatus.idequipamento_requisicao"));
                //requisicao tabela
                Requisicao req = new Requisicao();
                req.setId(rs.getLong("equipreq.id_requisicao"));
                req.setNome(rs.getString("equipreq.nome"));
                req.setData_criada(rs.getTimestamp("equipreq.data_requisicao"));
                req.setMotivo(rs.getString("equipreq.observacao"));
                req.setReqEquipamento(equipamento);
                req.setReqStatus(status);
                req.setReqUsuario(usuario);
                req.setReqStatus_Detalhes(statusDetalhes);
                req.setChefes(chefes);
                requisicoes.add(req);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequisicaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return requisicoes;
    }
    
    
}
