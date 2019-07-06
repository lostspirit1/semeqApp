/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Alerts;
import Model.BCrypt;
import Model.Equipamentos;
import Model.Fachada;
import Model.Sessao;
import Model.Usuario;
import Views.AlterarDados;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import util.MaskTextField;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class alterarDadosC implements Initializable {


    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private MaskTextField txtComputador;

    @FXML
    private MaskTextField txtModem;

    @FXML
    private MaskTextField txtImpressora;

    @FXML
    private MaskTextField txtMonitor;

    @FXML
    private CheckBox cModem;

    @FXML
    private CheckBox cComputador;

    @FXML
    private CheckBox cImpressora;

    @FXML
    private CheckBox cMonitor;

    @FXML
    private Label labelLogin;

    @FXML
    private Label labelNome;
    @FXML
    private JFXButton btnVoltar;
    private Fachada aFachada;
    private static Sessao aSessao;

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

    @FXML
    void xd(ActionEvent event) throws SQLException {
       alterarSenha2();
    }
    @FXML
    public void CheckBox(ActionEvent event){
        if (cModem.isSelected()) {
            txtModem.setVisible(true);
        }else{
           txtModem.setVisible(false);  
        }  
        if (cComputador.isSelected()) {
            txtComputador.setVisible(true);
        }else{
           txtComputador.setVisible(false);  
        }
        if (cImpressora.isSelected()) {
            txtImpressora.setVisible(true);
        }else{
           txtImpressora.setVisible(false);  
        }
        if (cMonitor.isSelected()) {
            txtMonitor.setVisible(true);
        }else{
           txtMonitor.setVisible(false);  
        }
    }

    public void alterarSenha2() throws SQLException {
        Alerts alerts = new Alerts();
        String senha = txtSenha.getText(),confirmSenha=txtConfirmarSenha.getText(),computador = txtComputador.getText(),modem = txtModem.getText(), impressora=txtImpressora.getText(), monitor=txtMonitor.getText();
        String geradoNovaSenha = BCrypt.gensalt();
        String senhaCrypt = BCrypt.hashpw(confirmSenha, geradoNovaSenha);
        String senhaAntiga = Sessao.getInstancia().getUsuario().getSenha();
        if (txtSenha.getText().trim().isEmpty() || txtConfirmarSenha.getText().trim().isEmpty()){
            alerts.alertCadastroVazio();
        }else if (BCrypt.checkpw(senha, senhaAntiga)){
        Usuario usuario = new Usuario();
        Equipamentos equipamentos = new Equipamentos();
        if (txtSenha.getText().trim().isEmpty()) {
         usuario.setSenha(aSessao.getInstancia().getUsuario().getSenha());   
        }
        else{
         usuario.setSenha(senha);
        }
        if (txtConfirmarSenha.getText().trim().isEmpty()) {
         usuario.setNovaSenha(aSessao.getInstancia().getUsuario().getSenha());   
        }
        else{
         usuario.setNovaSenha(senhaCrypt);
        }
        if (txtComputador.getText().trim().isEmpty()) {
        equipamentos.setSerial_computador(aSessao.getInstancia().getEquipamentos().getSerial_computador());   
        }else{
        equipamentos.setSerial_computador(computador);
        }
        if (txtModem.getText().trim().isEmpty()) {
        equipamentos.setSerial_modem(aSessao.getInstancia().getEquipamentos().getSerial_modem());   
        }else{
        equipamentos.setSerial_modem(modem);
        }
        if (txtImpressora.getText().trim().isEmpty()) {
        equipamentos.setSerial_impressora(aSessao.getInstancia().getEquipamentos().getSerial_impressora());   
        }else{
        equipamentos.setSerial_impressora(impressora);
        }      
        if (txtMonitor.getText().trim().isEmpty()) {
        equipamentos.setSerial_monitor(aSessao.getInstancia().getEquipamentos().getSerial_monitor());   
        }else{
        equipamentos.setSerial_monitor(monitor);
        } 
        aFachada.getInstancia().validarSenhaUsuario2(usuario,equipamentos);
            alerts.alertMudarSucesso();    
        }else{
             alerts.alertSenhaAnteriorFail();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNome.setText(aSessao.getInstancia().getUsuario().getNome());
        labelLogin.setText(aSessao.getInstancia().getUsuario().getLogin());
    }

}
