/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConnectionFactory;
import Model.Alerts;
import Model.BCrypt;
import Model.ChefesDepartamentos;
import Model.Departamento;
import Model.Equipamentos;
import Model.Permissao;
import Model.Sessao;
import Model.Usuario;
import Views.Login;
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
public class UsuarioDAO {

    private static UsuarioDAO aUsuarioDAO;
    private static Sessao aSessao;
    Alerts alerts = new Alerts();
    public static UsuarioDAO getInstancia() {

        if (aUsuarioDAO == null) {
            aUsuarioDAO = new UsuarioDAO();
        }

        return aUsuarioDAO;
    }

    private static Connection con;

    public UsuarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param u
     * @param m
     * @return
     */
    public boolean verificarLogin(Usuario u){
        Alerts alerts = new Alerts();
        String sql = "SELECT * FROM usuario where login="+u.getLogin()+" ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
               if (rs.next()) {
               
               }else if(!rs.next()){
                   
               }
        } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
                }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean cadastrar(Usuario u, List<Equipamentos> e, Departamento d) throws SQLException {        
        String geradoSenha = BCrypt.gensalt(10);
        String senhaCrypt = BCrypt.hashpw(u.getSenha(), geradoSenha);
        u.setSenha(senhaCrypt);        
        try {
            con.setAutoCommit(false);
            String verificarLogin = "SELECT * FROM usuario where login='"+u.getLogin()+"' ";
            PreparedStatement stmt = con.prepareStatement(verificarLogin);
            ResultSet resultverificarLogin = stmt.executeQuery();
               if (!resultverificarLogin.next()) {
                PreparedStatement cadastrar = con.prepareStatement("INSERT INTO USUARIO(idpermissao,login, senha, nome,matricula,online,iddepartamento) VALUES(1,?,?,?,?,0,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                cadastrar.setString(1, u.getLogin());
                cadastrar.setString(2, u.getSenha());
                cadastrar.setString(3, u.getNome());
                cadastrar.setString(4, u.getMatricula());
                cadastrar.setInt(5, d.getId_departamento());
                cadastrar.executeUpdate();
                ResultSet rs = cadastrar.getGeneratedKeys();
                rs.next();
            Long idGerado = rs.getLong(1);
            for (Equipamentos equipamentosbd : e) {
                String servicossql = "INSERT INTO equipamento_user (idusuario,idequipamentos,serial_equipamento) VALUES(?,"+equipamentosbd.getId_equipamentos()+","+equipamentosbd.getSerial_equipamento()+")";
                PreparedStatement inserirservicos = con.prepareStatement(servicossql);
                inserirservicos.setLong(1, idGerado);
                inserirservicos.executeUpdate();
            }
            alerts.alertUsuarioCadastrado();
               }else{
                   alerts.alertLoginExiste();
               }

            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
/*
    public boolean cadastrarADM(Usuario u, Matricula m) throws SQLException {
        String geradoSenha = BCrypt.gensalt();
        String senhaCrypt = BCrypt.hashpw(u.getSenha(), geradoSenha);
        u.setSenha(senhaCrypt);
        PreparedStatement matricula = con.prepareStatement("INSERT INTO MATRICULA(numero_matricula, numero_computador) VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        matricula.setInt(1, m.getNumero_matricula());
        matricula.setInt(2, m.getNumero_computador());
        try {
            con.setAutoCommit(false);
            matricula.executeUpdate();
            ResultSet rs = matricula.getGeneratedKeys();
            rs.next();
            Long idGerado = rs.getLong(1);
            Sessao.getInstancia().getMatricula().setId_matricula(idGerado);
            String cadastrarx = "INSERT INTO USUARIO(idpermissao,idmatricula, login, senha, nome,online) VALUES(" + u.isAdm() + "," + idGerado + ",?,?,?,0)";
            PreparedStatement cadastrar = con.prepareStatement(cadastrarx);
            cadastrar.setString(1, u.getLogin());
            cadastrar.setString(2, u.getSenha());
            cadastrar.setString(3, u.getNome());
            cadastrar.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            return false;
        }
        return true;
    }

    public boolean add(Usuario u) {

        String sql = "INSERT INTO usuario(login,senha,administrador) VALUES(?,?,?)";
        try {
            this.con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getSenha());
            stmt.setInt(3, u.isAdm());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }*/

    public boolean update(Usuario u) {
        String sql = "UPDATE usuario set senha = ? WHERE id_usuario=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getSenha());
            stmt.setLong(2, u.getId());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(Usuario u) {
        String sql = "DELETE  FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getId());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Usuario> getList() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            return null;
        }
        return usuarios;
    }

    public void verificarSenha(Usuario u ){
        Alerts alerts = new Alerts();
        String sql = "SELECT * FROM usuario where id_usuario = " + Sessao.getInstancia().getUsuario().getId() + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
               if (rs.next()) {
               if (BCrypt.checkpw(u.getSenha(), rs.getString("senha"))){
               mudarSenha(u);
               }else{
               alerts.alertSenhaAnteriorFail();
               }
               }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            alerts.alertMudarNSucesso();
        }  
    }
    public Usuario mudarSenha(Usuario u) throws SQLException {
        Alerts alerts = new Alerts();
        String sql = "UPDATE usuario SET senha=? WHERE id_usuario = " + Sessao.getInstancia().getUsuario().getId() + " and login='" + Sessao.getInstancia().getUsuario().getLogin() + "'";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNovaSenha());
            stmt.executeUpdate();
            stmt.close();
            Sessao.getInstancia().getUsuario().setSenha(u.getNovaSenha());
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            alerts.alertMudarNSucesso();
        }
        return u;
    }

    public boolean validarSenha(Usuario u) {
        String sql = "SELECT * from usuario u INNER JOIN equipamento_user equ ON(equ.idusuario = u.id_usuario) INNER JOIN departamentos dp ON(u.iddepartamento = dp.id_departamentos) INNER JOIN chefe_departamento cp on (dp.id_departamentos=cp.iddepartamento) INNER JOIN equipamentos e on (e.id_equipamentos = equ.idequipamentos) INNER JOIN  permissao p ON(p.id_permissao=u.idpermissao) WHERE u.login = ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getLogin());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (BCrypt.checkpw(u.getSenha(), rs.getString("senha"))){
               // Departamento departamento = new Departamento(rs.getInt("u.iddepartamento"), rs.getInt("cp.idusuario"), rs.getString("dp.nome_setor"));
                
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
                Permissao permissaoUsuario = new Permissao();
                permissaoUsuario.setId_permissao(rs.getInt("u.idpermissao"));
                permissaoUsuario.setCategoria_permissao(rs.getString("p.categoria"));
                //fim permissao usuario
                
                //incio departamento usuario
                Departamento departamentoUsuario = new Departamento();
                departamentoUsuario.setId_departamento(rs.getInt("dp.id_departamentos"));
                departamentoUsuario.setNome_setor(rs.getString("dp.nome_setor"));
                //FIM
                
                // usuario
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("u.nome"));
                usuario.setLogin(rs.getString("u.login"));
                usuario.setSenha(rs.getString("u.senha"));
                usuario.setStatus(rs.getBoolean("u.online"));
                usuario.setMatricula(rs.getString("u.matricula"));
                usuario.setId(rs.getLong("u.id_usuario"));
                usuario.setMatricula(rs.getString("u.matricula"));
                usuario.setLogin(rs.getString("u.login"));
                usuario.setpUsuario(permissaoUsuario);
                usuario.setdUsuario(departamentoUsuario);       
                aSessao.getInstancia().setUsuario(usuario);
           
                Equipamentos equipamento = new Equipamentos();
                PreparedStatement pesquisarEquipDados = con.prepareStatement("SELECT e.equipamento_nome,e.id_equipamentos,eu.idusuario,eu.id_equipamento_do_usuario,eu.idequipamentos,eu.serial_equipamento FROM equipamento_user eu INNER JOIN equipamentos e on(e.id_equipamentos=eu.idequipamentos) INNER JOIN usuario u on (u.id_usuario=eu.idusuario) where u.id_usuario= "+Sessao.getInstancia().getUsuario().getId()+"");
                ResultSet resultEquip = pesquisarEquipDados.executeQuery();
                while(resultEquip.next()){
                    
                    Usuario idequipamentos = new Usuario();
                    idequipamentos.setId(resultEquip.getLong("eu.idusuario"));
                    equipamento.setEquipamentoIDUsuario(idequipamentos);
                    
                    if(resultEquip.getLong("eu.idequipamentos")==1){
                    equipamento.setEquipamento_nome(resultEquip.getString("e.equipamento_nome"));
                    equipamento.setId_equipamentoUser_computador(resultEquip.getLong("eu.id_equipamento_do_usuario"));
                    equipamento.setSerial_computador(resultEquip.getString("eu.serial_equipamento"));
                    }
                    if(resultEquip.getLong("eu.idequipamentos")==2){
                    equipamento.setEquipamento_nome(resultEquip.getString("e.equipamento_nome"));
                    equipamento.setId_equipamentoUser_modem(resultEquip.getLong("eu.id_equipamento_do_usuario"));
                    equipamento.setSerial_modem(resultEquip.getString("eu.serial_equipamento"));
                    }
                    if(resultEquip.getLong("eu.idequipamentos")==3){
                    equipamento.setEquipamento_nome(resultEquip.getString("e.equipamento_nome"));
                    equipamento.setId_equipamentoUser_impressora(resultEquip.getLong("eu.id_equipamento_do_usuario"));
                    equipamento.setSerial_impressora(resultEquip.getString("eu.serial_equipamento"));
                    }
                    if(resultEquip.getLong("eu.idequipamentos")==4){
                    equipamento.setEquipamento_nome(resultEquip.getString("e.equipamento_nome"));
                    equipamento.setId_equipamentoUser_monitor(resultEquip.getLong("eu.id_equipamento_do_usuario"));
                    equipamento.setSerial_monitor(resultEquip.getString("eu.serial_equipamento"));
                    }
                }
                
                aSessao.getInstancia().setEquipamentos(equipamento);  
                aSessao.getInstancia().setDepartamento(departamentoUsuario);
                aSessao.getInstancia().setChefes(chefes);
                aSessao.getInstancia().setPermissao(permissaoUsuario);
                setOnline();
                stmt.close();
                rs.close();
                return true;
                }

            } else {
                stmt.close();
                rs.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public void setOnline() {
        String online = "UPDATE usuario SET online=1 WHERE id_usuario = " + Sessao.getInstancia().getUsuario().getId() + " ";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(online);
            stmt.executeUpdate(online);
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /*
    public void coletarMatricula(){
    String matriculax = "SELECT m.numero_matricula,m.numero_computador from usuario u INNER JOIN matricula m ON(u.idmatricula = m.id_matricula) WHERE u.id_usuario=" + Sessao.getInstancia().getUsuario().getId() +" AND u.idmatricula = " + Sessao.getInstancia().getMatricula().getId_matricula() + "";    
    try {
        PreparedStatement stmt = con.prepareStatement(matriculax);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
        Matricula matricula = new Matricula(rs.getInt("m.numero_computador"),rs.getInt("m.numero_matricula"));
        aSessao.getInstancia().setMatricula(matricula);
        }
        } catch (SQLException ex) {
           
        }
    }*/
    
    
    public Usuario mudarSenha2(Usuario u, Equipamentos e) throws SQLException {
        Alerts alerts = new Alerts();
        System.out.println(Sessao.getInstancia().getUsuario().getId());
        System.out.println(Sessao.getInstancia().getUsuario().getLogin());
        String sql = "UPDATE usuario SET senha=? WHERE id_usuario = " + Sessao.getInstancia().getUsuario().getId() + " and login='" + Sessao.getInstancia().getUsuario().getLogin() + "'";
        String mudarComputador = "UPDATE equipamento_user SET serial_equipamento=? WHERE id_equipamento_do_usuario = " + Sessao.getInstancia().getEquipamentos().getId_equipamentoUser_computador() + "";
        String mudarModem = "UPDATE equipamento_user SET serial_equipamento=? WHERE id_equipamento_do_usuario = " + Sessao.getInstancia().getEquipamentos().getId_equipamentoUser_modem()+ "";
        String mudarMonitor = "UPDATE equipamento_user SET serial_equipamento=? WHERE id_equipamento_do_usuario = " + Sessao.getInstancia().getEquipamentos().getId_equipamentoUser_monitor()+ "";
        String mudarImpressora = "UPDATE equipamento_user SET serial_equipamento=? WHERE id_equipamento_do_usuario = " + Sessao.getInstancia().getEquipamentos().getId_equipamentoUser_impressora()+ "";
        try {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNovaSenha());
            PreparedStatement computador = con.prepareStatement(mudarComputador);
            computador.setString(1, e.getSerial_computador());
            PreparedStatement modem = con.prepareStatement(mudarModem);
            modem.setString(1, e.getSerial_modem());   
            PreparedStatement monitor = con.prepareStatement(mudarMonitor);
            monitor.setString(1, e.getSerial_monitor());  
            PreparedStatement impressora = con.prepareStatement(mudarImpressora);
            impressora.setString(1, e.getSerial_impressora());  
            stmt.executeUpdate();
            computador.executeUpdate();
            modem.executeUpdate();   
            monitor.executeUpdate();   
            impressora.executeUpdate(); 
            stmt.close();
            Sessao.getInstancia().getEquipamentos().setSerial_computador(e.getSerial_computador());
            Sessao.getInstancia().getEquipamentos().setSerial_monitor(e.getSerial_monitor());
            Sessao.getInstancia().getEquipamentos().setSerial_modem(e.getSerial_modem());
            Sessao.getInstancia().getEquipamentos().setSerial_impressora(e.getSerial_impressora());
            Sessao.getInstancia().getUsuario().setSenha(u.getNovaSenha());
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            alerts.alertMudarNSucesso();
        }
        return u;
    }
    
    public void fecha() {
        Login.getStage().close();
    }

}
