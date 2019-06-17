/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.RequisicaoDAO;
import DAO.UsuarioDAO;

/**
 *
 * @author SpiriT
 */
public class Fachada {
    private static Fachada aFachada; 
    
    private static Usuario aUsuario;
    private static UsuarioDAO aUsuarioDAO;
    private static RequisicaoDAO aRequsicaoDAO; 
    
    public static Fachada getInstancia() {
		
		if (aFachada == null) {
			aFachada = new Fachada();
		}
		
		return aFachada;
	}

    public boolean incluirUsuario(Usuario usuario){
           return aUsuarioDAO.getInstancia().add(usuario);
    }
    public boolean updateUsuario(Usuario usuario){
           return aUsuarioDAO.getInstancia().update(usuario);
    }
    public boolean deletarUsuario(Usuario usuario){
           return aUsuarioDAO.getInstancia().delete(usuario);
    }
    public Usuario validarSenhaUsuario(Usuario usuario){
           return aUsuarioDAO.getInstancia().validarSenha(usuario);
    }
    public boolean incluirRequisicao(Requisicao requisicao){
           return aRequsicaoDAO.getInstancia().addReq(requisicao);
    }
    public boolean updateRequisicao(Requisicao requisicao){
           return aRequsicaoDAO.getInstancia().update(requisicao);
    }
    public boolean deletarRequisicao(Requisicao requisicao){
           return aRequsicaoDAO.getInstancia().delete(requisicao);
    }
}
