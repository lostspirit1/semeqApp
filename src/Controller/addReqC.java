/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RequisicaoDAO;
import Model.Requisicao;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class addReqC implements Initializable {
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXButton btReq;
    
    @FXML
    void criarReq(ActionEvent event) {
    criarReq();
    }
    public void criarReq() {
    String Nome = txtNome.getText();
     Requisicao r = new Requisicao(Nome);
      RequisicaoDAO dao = new RequisicaoDAO();
        if(dao.addReq(r)){
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("Usuário cadastrado");
            al.show();
        }else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("Usuário não cadastrado");
            al.show();
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
