/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.RequisicaoDAO;
import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author SpiriT
 */
public class Fachada {

    private static Fachada aFachada;
    private static Matricula aMatricula;
    private static Usuario aUsuario;
    private static UsuarioDAO aUsuarioDAO;
    private static RequisicaoDAO aRequsicaoDAO;
    private static Alert aAlert;

    public static Fachada getInstancia() {

        if (aFachada == null) {
            aFachada = new Fachada();
        }

        return aFachada;
    }

    /*public boolean incluirUsuario(Usuario usuario) {
        return aUsuarioDAO.getInstancia().add(usuario);
    }*/
    /*
    public boolean incluirUsuario2(Usuario usuario, Matricula matricula) throws SQLException {
        return aUsuarioDAO.getInstancia().cadastrarADM(usuario, matricula);
    }*/

    public boolean incluirUsuario(Usuario usuario, List<Equipamentos> equipamentos,Departamento departamento) throws SQLException {
        return aUsuarioDAO.getInstancia().cadastrar(usuario, equipamentos, departamento);
    }

    public boolean updateUsuario(Usuario usuario) {
        return aUsuarioDAO.getInstancia().update(usuario);
    }

    public boolean deletarUsuario(Usuario usuario) {
        return aUsuarioDAO.getInstancia().delete(usuario);
    }

    public Usuario validarSenhaUsuario(Usuario usuario) throws SQLException {
        return aUsuarioDAO.getInstancia().mudarSenha(usuario);
    }
    public Usuario validarSenhaUsuario2(Usuario usuario, Equipamentos equipamentos) throws SQLException {
        return aUsuarioDAO.getInstancia().mudarSenha2(usuario,equipamentos);
    }
    /*  public void validarAlterarSenha(Usuario usuario) throws SQLException {
        aUsuarioDAO.getInstancia().verificarSenha(usuario);
    }*/
    public Usuario alterarSenha(Usuario usuario) throws SQLException {
        return aUsuarioDAO.getInstancia().mudarSenha(usuario);
    }

    public boolean incluirRequisicao(Requisicao requisicao, Equipamentos equipamentos){
        return aRequsicaoDAO.getInstancia().addReq(requisicao,equipamentos);
    }

    public boolean updateRequisicao(Requisicao requisicao) {
        return aRequsicaoDAO.getInstancia().update(requisicao);
    }

    public boolean deletarRequisicao(Requisicao requisicao) throws SQLException {
        return aRequsicaoDAO.getInstancia().delete(requisicao);
    }

    public void alerts() {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        Alert alertaConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertNone = new Alert(Alert.AlertType.NONE);
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
    }
}
