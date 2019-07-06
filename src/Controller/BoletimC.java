/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BoletimDAO;
import Model.Boletim;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class BoletimC implements Initializable {

    @FXML
    private TableView<Boletim> tb;
    @FXML
    private TableColumn<Boletim, Long> clmIdBoletim;
    @FXML
    private TableColumn<Boletim, Long> clmIdReq;
    @FXML
    private TableColumn<Boletim, Date> clmDataSaida;
    @FXML
    private TableColumn<Boletim, Long> clmIdServicos;

    @FXML
    private TableColumn<Boletim, String> clmTipoServicos;
    @FXML
    private TableColumn<Boletim, String> clmNomeUsuario;
    //@FXML
    // private TableColumn<Boletim, String> clmObservacao;
    @FXML
    private JFXButton deletar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton atualizar;
    @FXML
    private JFXButton btModificar;
    @FXML
    private JFXButton btBuscar;
    @FXML
    private JFXTextField txPequisar;

    private Boletim selecionada;
    /*
    @FXML
    private void deleteButton() throws SQLException {
        if (selecionada != null) {
            RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
            requisicaoDAO.delete(selecionada);
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("Usuario Deletado com Sucesso");
            a.show();
            tb.setItems(atualizarTabela());
        } else {
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
    }*/

    private ObservableList<Boletim> boletim = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        tb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Boletim) newValue;
            }
        });
    }

    public void initTable() {
        clmIdBoletim.setCellValueFactory(new PropertyValueFactory("id_boletim"));

        clmIdReq.setCellValueFactory(new Callback<CellDataFeatures<Boletim, Long>, ObservableValue<Long>>() {
            public ObservableValue<Long> call(CellDataFeatures<Boletim, Long> boletim) {
                return new SimpleObjectProperty(boletim.getValue().getbRequisicao().getId());
            }
        });
        clmDataSaida.setCellValueFactory(new PropertyValueFactory("data_saida"));
        clmIdServicos.setCellValueFactory(new Callback<CellDataFeatures<Boletim, Long>, ObservableValue<Long>>() {
            public ObservableValue<Long> call(CellDataFeatures<Boletim, Long> boletim) {
                return new SimpleObjectProperty(boletim.getValue().getbServicos().getId_Servicos());
            }
        });
        clmTipoServicos.setCellValueFactory(new Callback<CellDataFeatures<Boletim, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Boletim, String> boletim) {
                return new SimpleObjectProperty(boletim.getValue().getbServicos().getNomes_servico());
            }
        });
        clmNomeUsuario.setCellValueFactory(new Callback<CellDataFeatures<Boletim, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Boletim, String> boletim) {
                return new SimpleObjectProperty(boletim.getValue().getbUsuario().getNome());
            }
        });
        //clmObservacao.setCellValueFactory(new PropertyValueFactory("dataEntrada"));
        tb.setItems(atualizarTabela());
    }

    /*
    public void initTable() {
        clmid.setCellValueFactory(new PropertyValueFactory("id"));
        clmIdUser.setCellValueFactory(new PropertyValueFactory("idu"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmDate.setCellValueFactory(new PropertyValueFactory("dataEntrada"));
        clmMotivo.setCellValueFactory(new PropertyValueFactory("motivo"));
        clmStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tb.setItems(atualizarTabela());
    }*/
    public ObservableList<Boletim> atualizarTabela() {
        BoletimDAO dao = new BoletimDAO();
        boletim = FXCollections.observableArrayList(dao.getList());
        return boletim;
    }
}
