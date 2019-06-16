/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Requisicao;
import Model.Usuario;

/**
 *
 * @author SpiriT
 */
public class NewMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Requisicao r = new Requisicao();
       Usuario u = new Usuario();
       u.getId();
       r.setNome("xfdxd");
       
       RequisicaoDAO dao = new RequisicaoDAO();
       dao.addReq(r);
    }
    
}
