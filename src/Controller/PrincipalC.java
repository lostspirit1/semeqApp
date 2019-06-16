/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Views.AlterarDados;
import Views.Cadastrar;
import Views.Historico;
import Views.Login;
import Views.Principal;
import Views.addReq;
import Views.listagemUser;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class PrincipalC implements Initializable {
    
    @FXML
    private JFXButton Cadastrar;

    @FXML
    private JFXButton Historico;

    @FXML
    private JFXButton Adicionar;

    @FXML
    private JFXButton AlterarDados;

    @FXML
    private JFXButton Listagem;

    @FXML
    void abrirCadastro(ActionEvent event) {
    Cadastrar c = new Cadastrar();
        fecha();
        try {
            c.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    void abrirHistorico(ActionEvent event) {
        Historico h = new Historico();
        fecha();
        try {
            h.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    void abrirAlterarDados(ActionEvent event) {
        AlterarDados ad = new AlterarDados();
        fecha();
        try {
            ad.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    @FXML
    void addReq(ActionEvent event) {
        addReq add = new addReq();
        fecha();
        try {
            add.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    @FXML
    void abrirListagem(ActionEvent event) {
        listagemUser lu = new listagemUser();
        fecha();
        try {
            lu.start(new Stage());
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }      
    public void fecha(){
        Principal.getStage().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
