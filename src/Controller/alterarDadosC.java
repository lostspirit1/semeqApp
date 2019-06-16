/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Conexao.ConnectionFactory;
import DAO.UsuarioDAO;
import Model.Usuario;
import Views.AlterarDados;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class alterarDadosC implements Initializable {
   
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

    public void fecha(){
        AlterarDados.getStage().close();
    }
    public void abreTelaP(){
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(listagemUserC.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @FXML
    void xd(ActionEvent event) {
alterarSenha();
    }
    
    public void alterarSenha() {
        try {
            ConnectionFactory conn = new ConnectionFactory(); 
            String sql = "SELECT id_usuario,login,senha FROM usuario where login = '" + txtLogin.getText() + "' and  senha = '" + txtSenha.getText() + "'";
            conn.executeSQL(sql);

            //Se houver resultado, ou seja, se validar o usuario e senha, faça algo.
            if (conn.resultset.next()) {
                System.out.println("Você pode alterar a senha.");
                Long idU = conn.resultset.getLong("id_usuario");
                Usuario u = new Usuario(idU);
                String novaSenha = JOptionPane.showInputDialog("Digite sua Senha nova por favor \n");
                String novaSenhaConfirma = JOptionPane.showInputDialog("Confirme a nova senha por favor \n");

                if (novaSenha.equals(novaSenhaConfirma)) {
                    String update = "UPDATE usuario SET senha='" + novaSenha + "' WHERE id_usuario = " + idU + "";
                    conn.statement.executeUpdate(update);
                    JOptionPane.showMessageDialog(null, "Senha alterada com sucesso...");
                } else {
                    JOptionPane.showMessageDialog(null, "As duas senhas digitadas não são a mesma, tente novamente por favor");
                }
            } else {
                System.out.println("Acesso negado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + e.getMessage());
        }
    }
    
    public void atualizar(){
        UsuarioDAO dao = new UsuarioDAO();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        String confSenha = txtConfirm.getText();
        
        if(senha.equals(confSenha)){
        Usuario u = new Usuario(login,senha);
        if(dao.update(u)){
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText("Senha Alterada");
        al.show();
        }else{
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText("Senha não Alterada");
        al.show();
        }
        }else{
         Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText("Senhas não são iguais");
        al.show();
        }
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVoltar.setOnMouseClicked ((MouseEvent e)->{
            abreTelaP();
            fecha();
            System.out.println("a");
        });
    }    
    
}
