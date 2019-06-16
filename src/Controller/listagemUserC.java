/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import Views.Principal;
import Views.listagemUser;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class listagemUserC implements Initializable {
    
    @FXML private TableView<Usuario> tb;
    @FXML private TableColumn<Usuario, Long> clmid;
    @FXML private TableColumn<Usuario, String> clmLogin;
    @FXML private JFXButton deletar;
    @FXML private JFXButton btCancelar;
    @FXML private JFXButton atualizar;
    @FXML private JFXButton btBuscar;
    @FXML private JFXTextField txPequisar;

    private Usuario selecionada;
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
            
            

    @FXML
    void listarPessoa(ActionEvent event) {
        
    }
    public void initTable(){
        clmid.setCellValueFactory(new PropertyValueFactory("id"));
        clmLogin.setCellValueFactory(new PropertyValueFactory("login"));
        tb.setItems(atualizarTabela());
    }
    public ObservableList<Usuario> atualizarTabela(){
        UsuarioDAO dao = new UsuarioDAO();
        usuarios = FXCollections.observableArrayList(dao.getList());
        return usuarios;
    }
    public void fecha(){
        listagemUser.getStage().close();
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
        UsuarioDAO dao = new UsuarioDAO();
        dao.delete(selecionada);
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Usuario Deletado com Sucesso");
        a.show();
        tb.setItems(atualizarTabela());
        }else{
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
    }
    
    private ObservableList<Usuario> buscar(){
        ObservableList<Usuario> usuariopesquisa = FXCollections.observableArrayList();
        for(int x = 0; x< usuarios.size(); x++){
            if(usuarios.get(x).getLogin().toLowerCase().contains(txPequisar.getText().toLowerCase())){
                usuariopesquisa.add(usuarios.get(x));
            }
        }
        return usuariopesquisa;
    }
    
    @Override
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
                selecionada = (Usuario) newValue;
            }
        });
        
    }    
    
}
