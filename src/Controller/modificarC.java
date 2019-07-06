/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Fachada;
import Model.Usuario;
import Views.AlterarDados;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class modificarC implements Initializable {

    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXPasswordField txtConfirm;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnVoltar;
    @FXML
    private Label lbID;
    public static Usuario u2;

    public static Usuario getUsuario2() {
        return u2;
    }

    public static void setUsuario2(Usuario u2) {
        modificarC.u2 = u2;
    }
    private Fachada aFachada;

    @FXML
    void xd(ActionEvent event) {
        atualizarUser();
    }

    public void atualizarUser() {
        String login = txtLogin.getText(), senha = txtSenha.getText(), confirmSenha = txtConfirm.getText();
        if (senha.equals(confirmSenha)) {
            Usuario usuario = new Usuario(u2.getId(), login, senha);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.update(usuario)) {
                System.out.println("sucess");
            } else {
                System.out.println("fail");
            }
        } else {
            System.out.println("n iguais");
        }

    }

    public void fecha() {
        AlterarDados.getStage().close();
    }

    public void abreTelaP() {
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(listagemUserC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbID.setText(u2.getId().toString());
    }

}
