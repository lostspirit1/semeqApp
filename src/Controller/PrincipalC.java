/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Sessao;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class PrincipalC implements Initializable {

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private BorderPane alterarDados;
    @FXML
    private BorderPane requisicoes;
    @FXML
    private JFXButton Cadastrar;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton Historico;
    @FXML
    private JFXButton Adicionar;
    @FXML
    private JFXButton AlterarDados;
    @FXML
    private JFXButton Listagem;
    private static Sessao aSessao;
    @FXML
    void secAlterar(ActionEvent event) {
        mostrarAlterarDados();
    }

    @FXML
    void secHistorico(ActionEvent event) {
        mostrarReq();
    }

    @FXML
    void secBoletim(ActionEvent event) {
        mostrarBoletim();
    }

    @FXML
    void secCadastro(ActionEvent event) {
        mostrarCadastro();
    }

    @FXML
    void secUser(ActionEvent event) {
        mostrarUser();
    }

    public void fecha() {
        Principal.getStage().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void mostrarAlterarDados() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/Fxmls/AlterarDados.fxml"));
        try {
            BorderPane alterarDados = loader.load();
            panePrincipal.setCenter(alterarDados);
            alterarDados.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarReq() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/Fxmls/addReq.fxml"));
        try {
            BorderPane requisicoes = loader.load();
            panePrincipal.setCenter(requisicoes);
            requisicoes.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarBoletim() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/Fxmls/boletim.fxml"));
        try {
            AnchorPane boletim = loader.load();
            panePrincipal.setCenter(boletim);
            boletim.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCadastro() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/Fxmls/Cadastrar.fxml"));
        try {
            BorderPane cadastro = loader.load();
            panePrincipal.setCenter(cadastro);
            cadastro.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUser() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principal.class.getResource("/Fxmls/Historico.fxml"));
        try {
            BorderPane listaUser = loader.load();
            panePrincipal.setCenter(listaUser);
            listaUser.toFront();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
