/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Views.Menu;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class MenuC implements Initializable {
    

    @FXML
    private JFXButton cadastrar;

    @FXML
    private JFXButton listagemUser;

    @FXML
    private JFXButton historicoRequi;

    @FXML
    private JFXButton addReq;

    @FXML
    private JFXButton setting;

    @FXML
    private JFXButton logout;
    @FXML
    void close(ActionEvent event) {
        Menu.getStage().close();
    }
    public void fecha(){
        Menu.getStage().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
