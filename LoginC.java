/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Alerts;
import Model.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Views.Login;
import Views.Principal;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author SpiriT
 */
public class LoginC implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField jLogin;
    @FXML
    private PasswordField jSenha;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @FXML
    private void validar(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Views/Menu.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private void xd() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Principal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(LoginC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valida2() {
        String Login = jLogin.getText(), Senha = jSenha.getText();
        Usuario u = new Usuario(Login, Senha);
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.validarSenha(u) == true) {
            xd();
        } else {
            Alerts a = new Alerts();
            a.alertSenhaErrada();
        }
    }

    public void validaLogin() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.getList();

        for (int x = 0; x < usuarios.size(); x++) {
            if (jLogin.getText().equals(usuarios.get(x).getLogin()) && jSenha.getText().equals(usuarios.get(x).getSenha())) {
                Principal pr = new Principal();
                x = usuarios.size();

                try {
                    pr.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (x == usuarios.size() - 1) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText("Login Invalido");
                    al.show();
                }
            }
        }
    }

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
