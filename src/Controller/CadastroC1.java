/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package Controller;

import DAO.UsuarioDAO;
import Model.Alerts;
import Model.Fachada;
import Model.Matricula;
import Model.Usuario;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXPasswordField;
import java.sql.SQLException;


/**
 * FXML Controller class
 *
 * @author SpiriT

public class CadastroC1 implements Initializable {

    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXPasswordField confirmSenha;
    @FXML
    private JFXTextField idUser;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXCheckBox cADM;
    @FXML
    private JFXCheckBox cEsta;
    @FXML
    private JFXCheckBox cNormal;
    @FXML
    private JFXTextField txtMatricula;
    @FXML
    private JFXTextField txtComputador;
    
    private Fachada aFachada;

    @FXML
    void xd(ActionEvent event) throws SQLException {
        testar();
    }

    public void criarUser() {
        String login = txtLogin.getText(), senha = txtSenha.getText(), confirm = confirmSenha.getText(), nome = txtNome.getText();
        Usuario usuario = new Usuario(login, senha);
     //   usuario.setAdm(cADM.isSelected());
        Alerts alertar = new Alerts();
        if (txtLogin.getText().trim().isEmpty() || txtSenha.getText().trim().isEmpty()) {
            alertar.alertCadastroVazio();
        } else if (senha.equals(confirm)) {
            if (aFachada.getInstancia().incluirUsuario(usuario)) {
                alertar.alertUsuarioCadastrado();
            } else {
                alertar.alertUsuarioNCadastrado();
            }
        } else {
                alertar.alertSenhaDif();
        }
    }

    public void testar() throws SQLException{
        int matriculax = Integer.parseInt(txtMatricula.getText()), computador = Integer.parseInt(txtComputador.getText());
        String login = txtLogin.getText(), senha = txtSenha.getText(), confirm = confirmSenha.getText(), nome = txtNome.getText();
        Usuario usuario = new Usuario(login, senha);
        usuario.setNome(nome);
        boolean[] opcoes = new boolean[3];

        opcoes[0] = cADM.isSelected();
        opcoes[1] = cNormal.isSelected();
        opcoes[2] = cEsta.isSelected();

        int qtdOpcoesMarcadas = 0;

        for (boolean opc : opcoes) {
            if (opc) {
                qtdOpcoesMarcadas++;
            }
        }

        if(cADM.isSelected()){
            usuario.setAdm(1);
        }
        if(cNormal.isSelected()){
            usuario.setAdm(3);
        }
        if(cEsta.isSelected()){
            usuario.setAdm(2);
        }
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Matricula matricula = new Matricula(matriculax,computador);
        Alerts alertar = new Alerts();
        if (qtdOpcoesMarcadas > 1) {
           alertar.alertCadastroVazio();
        }else{
        if (txtLogin.getText().trim().isEmpty() || txtSenha.getText().trim().isEmpty()) {
            alertar.alertCadastroVazio();
        }else if (senha.equals(confirm)) {
            if (aFachada.getInstancia().incluirUsuario2(usuario,matricula)) {
                alertar.alertUsuarioCadastrado();
            } else {
                alertar.alertUsuarioNCadastrado();
            }
        } else {
                alertar.alertSenhaDif();
        }
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
*/
