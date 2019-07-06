/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.modificarC.u2;
import DAO.RequisicaoDAO;
import DAO.UsuarioDAO;
import Model.Fachada;
import Model.Requisicao;
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
public class modificarRequisicaoC implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnVoltar;
    @FXML
    private Label lbID;
    public static Requisicao r2;

    public static Requisicao getRequisicao2() {
        return r2;
    }

    public static void setRequisicao2(Requisicao r2) {
        modificarRequisicaoC.r2 = r2;
    }
    private Fachada aFachada;

    @FXML
    void xd(ActionEvent event) {

    }

    /*  public void atualizarRequisicao() {
        String nome = txtNome.getText();
        Requisicao requisicao = new Requisicao(r2.getId(), nome);
        RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
        if (requisicaoDAO.update(requisicao)) {
            System.out.println("sucess");
        } else {
            System.out.println("fail");
        }
   }*/
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
        lbID.setText(r2.getId().toString());
    }

}
