/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RequisicaoDAO;
import Model.Requisicao;
import Views.Historico;
import Views.Principal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class HistoricoC implements Initializable {
    @FXML private TableView<Requisicao> tb;
    @FXML private TableColumn<Requisicao, Long> clmid;
    @FXML private TableColumn<Requisicao, String> clmNome;
    @FXML private JFXButton deletar;
    @FXML private JFXButton btCancelar;
    @FXML private JFXButton atualizar;
    @FXML private JFXButton btBuscar;
    @FXML private JFXTextField txPequisar;
    private Requisicao selecionada;
    private ObservableList<Requisicao> requisicoes = FXCollections.observableArrayList();
    
    public void initTable(){
        clmid.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tb.setItems(atualizarTabela());
    }
       public ObservableList<Requisicao> atualizarTabela(){
        RequisicaoDAO dao = new RequisicaoDAO();
        requisicoes = FXCollections.observableArrayList(dao.getList());
        return requisicoes;
    }
    public void fecha(){
        Historico.getStage().close();
    }
    public void abreTelaP(){
        Principal p = new Principal();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(listagemUserC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void deletar(){
        if(selecionada != null){
        RequisicaoDAO dao = new RequisicaoDAO();
        dao.delete(selecionada);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Usuario Deletado com Sucesso");
        a.show();
        tb.setItems(atualizarTabela());
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
    }

    private ObservableList<Requisicao> buscar(){
        ObservableList<Requisicao> requisicaopesquisa = FXCollections.observableArrayList();
        for(int x = 0; x< requisicoes.size(); x++){
            if(requisicoes.get(x).getNome().toLowerCase().contains(txPequisar.getText().toLowerCase())){
                requisicaopesquisa.add(requisicoes.get(x));
            }
        }
        return requisicaopesquisa;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
       initTable(); 
               btCancelar.setOnMouseClicked ((MouseEvent e)->{
            abreTelaP();
            fecha();
        });
               
      atualizar.setOnMouseClicked ((MouseEvent e)->{
           tb.setItems(atualizarTabela());
        });
        
        deletar.setOnMouseClicked ((MouseEvent e)->{
            deletar();
        }); 

        txPequisar.setOnKeyReleased((KeyEvent e)->{
            tb.setItems(buscar());
        });
        
        btBuscar.setOnMouseClicked ((MouseEvent e)->{
            tb.setItems(buscar());
        });        
        
        tb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Requisicao) newValue;
            }
        });        
        
    }    
    
}
