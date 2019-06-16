/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class CadastroC implements Initializable {

    @FXML
    private JFXPasswordField senha;

    @FXML
    private JFXPasswordField confirmSenha;
    @FXML
    private JFXTextField idUser;
    @FXML
    private JFXTextField login;
    @FXML
    void xd(ActionEvent event) {
    criarUser();
    }
    
    public void criarUser() {
    String Login = login.getText() , Senha = senha.getText() , Confirm = confirmSenha.getText();
     Usuario u = new Usuario(Login,Senha);
      UsuarioDAO dao = new UsuarioDAO();
    if(Senha.equals(Confirm)){

        if(dao.add(u)){
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("Usuário cadastrado");
            al.show();
        }else{
            Alert al = new Alert(AlertType.ERROR);
            al.setHeaderText("Usuário não cadastrado");
            al.show();
        }
    }else{
        Alert al = new Alert(AlertType.ERROR);
        al.setHeaderText("Senhas diferentes");
        al.show();
    }
    idUser.setText("a"+u.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
