
 
package Controller;

import DAO.PecasDAO;
import DAO.RequisicaoDAO;
import Model.Alerts;
import Model.Pecas;
import Model.Requisicao;
import Model.Servicos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;



 
public class HistoricoC implements Initializable {

    @FXML
    private TableColumn<Requisicao, Long> clmIdReq;

    @FXML
    private TableColumn<Requisicao, String> clmEquipamento;

    @FXML
    private TableColumn<Requisicao, String> clmSerial;

    @FXML
    private TableColumn<Requisicao, String> clmRequisitante;

    @FXML
    private TableColumn<Requisicao, String> clmObservacao;

    @FXML
    private TableColumn<Requisicao, String> clmStatus;

    @FXML
    private TableColumn<Requisicao, Timestamp> clmData;
    
    @FXML
    private TableView<Requisicao> tb;
    /*
    @FXML
    private TableColumn<Requisicao, Long> clmid;
    @FXML
    private TableColumn<Requisicao, String> clmNome;

    @FXML
    private TableColumn<Requisicao, String> clmIdNomeUser;
    @FXML
    private TableColumn<Requisicao, Timestamp> clmDate;
    @FXML
    private TableColumn<Requisicao, String> clmMotivo;
    @FXML
    private TableColumn<Requisicao, Boolean> clmStatus; */
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
    @FXML
    private CheckBox checkGravador;
    @FXML
    private CheckBox checkBateria;
    @FXML
    private CheckBox checkMonitor;
    @FXML
    private CheckBox checkTeclado;
    @FXML
    private CheckBox checkMouse;
    @FXML
    private CheckBox checkVGA;
    @FXML
    private CheckBox checkPlacaMae;
    @FXML
    private CheckBox checkHD;
    @FXML
    private CheckBox checkFonte;
    @FXML
    private CheckBox checkRam;
    @FXML
    private CheckBox checkCabos;
    @FXML
    private CheckBox checkComponente;
    @FXML
    private CheckBox checkPeriferico;
    @FXML
    private CheckBox checkLimpezaF;
    @FXML
    private CheckBox checkLimpezaS;
    @FXML
    private CheckBox checkRoteador;
    @FXML
    private CheckBox checkBackup;
    @FXML
    private CheckBox checkFormatar;
    @FXML
    private CheckBox checkImpressoras;
    @FXML
    private TextArea txtMotivo;
    private Requisicao selecionada;
    private CheckBox[] checkBoxes;
    @FXML
    void gerar(ActionEvent event) throws SQLException {
        xd();
        atualizarTabela();
    }

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
    }

    private ObservableList<Requisicao> requisicao = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        tb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Requisicao) newValue;
            }
        });

      checkBoxes = new CheckBox[] {
      checkMonitor,
      checkTeclado,
      checkMouse,
      checkVGA,
      checkPlacaMae,
      checkHD,
      checkFonte,
      checkRam,
      checkCabos,
      checkGravador,
      checkBateria
        };  

    }

    public void initTable() {

        clmIdReq.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, Long>, ObservableValue<Long>>() {
            public ObservableValue<Long> call(CellDataFeatures<Requisicao, Long> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getId());
            }
        });
        clmEquipamento.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Requisicao, String> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getReqEquipamento().getEquipamento_nome());
            }
        });
        clmSerial.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Requisicao, String> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getReqEquipamento().getSerial_equipamento());
            }
        });
        clmRequisitante.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Requisicao, String> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getReqUsuario().getNome());
            }
        });
        clmObservacao.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Requisicao, String> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getMotivo());
            }
        });
        clmStatus.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Requisicao, String> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getReqStatus().getCategoria());
            }
        });
        clmData.setCellValueFactory(new Callback<CellDataFeatures<Requisicao, Timestamp>, ObservableValue<Timestamp>>() {
            public ObservableValue<Timestamp> call(CellDataFeatures<Requisicao, Timestamp> requisicao) {
                return new SimpleObjectProperty(requisicao.getValue().getData_criada());
            }
        });
        tb.setItems(atualizarTabela());

    }
    //metodo novo
    public void xd(){
        List<Integer> pecasList = new ArrayList<>();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isSelected()) {
                pecasList.add(i+1);
            }
        }
    }
    
    
    //metodo antigo
/*
    public void xd() throws SQLException {
// SERVIÇOS LIST

        List<Servicos> servicosList = new ArrayList<>();
        {
            if (checkComponente.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(1L);
                servicosList.add(s);
            }
            if (checkPeriferico.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(2L);
                servicosList.add(s);
            }
            if (checkLimpezaF.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(3L);
                servicosList.add(s);
            }
            if (checkLimpezaS.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(4L);
                servicosList.add(s);
            }
            if (checkRoteador.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(5L);
                servicosList.add(s);
            }
            if (checkBackup.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(6L);
                servicosList.add(s);
            }
            if (checkFormatar.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(7L);
                servicosList.add(s);
            }
            if (checkImpressoras.isSelected()) {
                Servicos s = new Servicos();
                s.setId_Servicos(8L);
                servicosList.add(s);
            }
        }

        List<Pecas> pecasList = new ArrayList<>();
        {
            if (checkMonitor.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(1);
                pecasList.add(p);
            }
            if (checkTeclado.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(2);
                pecasList.add(p);
            }
            if (checkMouse.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(3);
                pecasList.add(p);
            }
            if (checkVGA.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(4);
                pecasList.add(p);
            }
            if (checkPlacaMae.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(5);
                pecasList.add(p);
            }
            if (checkHD.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(6);
                pecasList.add(p);
            }
            if (checkFonte.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(7);
                pecasList.add(p);
            }
            if (checkRam.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(8);
                pecasList.add(p);
            }
            if (checkCabos.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(9);
                pecasList.add(p);
            }
            if (checkGravador.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(10);
                pecasList.add(p);
            }
            if (checkBateria.isSelected()) {
                Pecas p = new Pecas();
                p.setIdpecas(11);
                pecasList.add(p);
            }
        }
// Peças LIST
        Alerts alerts = new Alerts();
        String motivo = txtMotivo.getText();
        RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
        PecasDAO pecasDAO = new PecasDAO();
        
        List<Pecas> pecasList2 = new ArrayList<>();{
        for (Pecas pecasVerificacao : pecasList) {
                    pecasVerificacao.getIdpecas();
                for (Pecas pecasBanco : pecasDAO.pegarPecas()) {
                if(pecasBanco.getIdpecas() == pecasVerificacao.getIdpecas()){
                    pecasVerificacao.setQtd_Pecas(pecasBanco.getQtd_Pecas());
                    pecasVerificacao.getIdpecas();
                    pecasList2.add(pecasVerificacao);
                }
        }
        } 
        }
        

        if(checkMonitor.isSelected() || checkTeclado.isSelected()|| checkMouse.isSelected()|| checkVGA.isSelected()|| checkPlacaMae.isSelected()|| checkHD.isSelected()|| checkFonte.isSelected()|| checkRam.isSelected()|| checkCabos.isSelected() || checkGravador.isSelected() || checkBateria.isSelected()){
            for (Pecas checarQtd : pecasList2){
                checarQtd.getIdpecas();
                checarQtd.getQtd_Pecas();
                if(checarQtd.getIdpecas() < pecasList2.size() && checarQtd.getQtd_Pecas() > 0){
                alerts.alertReqCadastrado();
                }else{
                 alerts.alertSenhaDif();;   
                }
            }
        }
       else{
        if(selecionada!= null){
            requisicaoDAO.verificarStatus(selecionada);
        
            if(selecionada.getReqStatus().getIdstatus() == 1){
                if(requisicaoDAO.inserir(servicosList, selecionada, motivo, pecasList)){
                atualizarTabela();
                alerts.alertReqCadastrado();
                } 
                else{
                alerts.alertReqNCadastrado();
                }
            }
            else{
                alerts.reqFechada();
            }
        
        }else{
            alerts.selecionarRequisicao();
        }
       } 
    }*/

    public ObservableList<Requisicao> atualizarTabela() {
        RequisicaoDAO dao = new RequisicaoDAO();
        requisicao = FXCollections.observableArrayList(dao.getList());
        return requisicao;
    }

    
}
