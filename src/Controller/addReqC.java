/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.EquipamentoDAO;
import Model.Alerts;
import Model.Equipamentos;
import Model.Fachada;
import Model.Requisicao;
import Views.Principal;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class addReqC implements Initializable {

    @FXML
    private JFXComboBox<Equipamentos> cbEquipamentos;
    private List<Equipamentos> equipamentos = new ArrayList();
    private ObservableList<Equipamentos> obsEquipamentos;
    @FXML
    private TextArea txtObservacao;
    @FXML
    private TextField txtNomeReq;
    private Fachada aFachada;
    @FXML
    void xd(ActionEvent event) {
        requisicao();
    }

    @FXML
    void criarReq(ActionEvent event) throws SQLException {
    }

 

    public void requisicao() {
        Alerts alerts = new Alerts();
        String nome = txtNomeReq.getText(), observacao = txtObservacao.getText();
        Equipamentos equipamentos = new Equipamentos();
        Requisicao requisicao = new Requisicao(nome,observacao);
        if(cbEquipamentos.getSelectionModel().getSelectedItem() != null ){
             equipamentos.setId_equipamento_do_Usuario(cbEquipamentos.getSelectionModel().getSelectedItem().getId_equipamento_do_Usuario());
             if (txtNomeReq.getText().trim().isEmpty() || txtObservacao.getText().trim().isEmpty()){
            alerts.alertReqVazio();
            }else if(aFachada.getInstancia().incluirRequisicao(requisicao,equipamentos)){
            alerts.alertReqCadastrado();
            }else{
            alerts.alertReqNCadastrado();
            }
        }else{
            alerts.selecioneEquipamento();
        }
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EquipamentoDAO dao = new EquipamentoDAO();
        obsEquipamentos = FXCollections.observableArrayList(dao.read());
        cbEquipamentos.setItems(obsEquipamentos);
    }

}
