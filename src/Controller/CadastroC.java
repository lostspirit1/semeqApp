

package Controller;

import DAO.DepartamentoDAO;
import Model.Alerts;
import Model.Departamento;
import Model.Equipamentos;
import Model.Fachada;
import Model.Usuario;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import util.MaskTextField;


public class CadastroC implements Initializable {

   @FXML
    private MaskTextField txtLogin;
    
    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField confirmSenha;

    @FXML
    private MaskTextField txtNome;

    @FXML
    private MaskTextField txtMatricula;

    @FXML
    private JFXComboBox<Departamento> cbDepart;
    private ObservableList<Departamento> obsDepartamentos;

    @FXML
    private MaskTextField txtComputador;

    @FXML
    private MaskTextField txtModem;

    @FXML
    private MaskTextField txtImpressora;

    @FXML
    private MaskTextField txtMonitor;
    
    private MaskTextField xd;
    @FXML
    void txtNumeros(KeyEvent event) {

    }
    
    private Fachada aFachada;

    @FXML
    void xd(ActionEvent event) throws SQLException {
        cadastrar();
    }
    public void cadastrar() throws SQLException{
    String login = txtLogin.getText(), senha=txtSenha.getText(), confirm = confirmSenha.getText(), nome=txtNome.getText();
    String matricula=txtMatricula.getText(),nComputador=txtComputador.getText(),modem = txtModem.getText(),impressora=txtImpressora.getText(),monitor=txtMonitor.getText();
    
    List<Equipamentos> equipamentosList = new ArrayList<>();
        {
            if (!"".equals(txtComputador.getText())) {
                Equipamentos e = new Equipamentos();
                e.setId_equipamentos(1L);
                e.setSerial_equipamento(txtComputador.getText());
                equipamentosList.add(e);
            }
            if (!"".equals(txtModem.getText())) {
                Equipamentos e = new Equipamentos();
                e.setId_equipamentos(2L);
                e.setSerial_equipamento(txtModem.getText());
                equipamentosList.add(e);
            }
            if (!"".equals(txtImpressora.getText())) {
                Equipamentos e = new Equipamentos();
                e.setId_equipamentos(3L);
                e.setSerial_equipamento(txtImpressora.getText());
                equipamentosList.add(e);
            }
            if (!"".equals(txtMonitor.getText())) {
                Equipamentos e = new Equipamentos();
                e.setId_equipamentos(4L);
                e.setSerial_equipamento(txtMonitor.getText());
                equipamentosList.add(e);
            }
    }
    
    
    Departamento departamentoxd = (Departamento) cbDepart.getSelectionModel().getSelectedItem();
    Alerts alertar = new Alerts();
    Usuario usuario = new Usuario(login,senha,nome,matricula);
    if (txtLogin.getText().trim().isEmpty() || txtSenha.getText().trim().isEmpty() || confirmSenha.getText().trim().isEmpty() || txtNome.getText().trim().isEmpty() || txtMatricula.getText().trim().isEmpty()|| txtComputador.getText().trim().isEmpty()){
    alertar.alertCadastroVazio();
    }else if (senha.equals(confirm)){
        if (aFachada.getInstancia().incluirUsuario(usuario,equipamentosList,departamentoxd)) {
            
        } else {
            alertar.alertUsuarioNCadastrado();
        }
    }else{
     alertar.alertSenhaDif();
    }
    }
/*  
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
    }*/

    public void initialize(URL url, ResourceBundle rb) {
        DepartamentoDAO dao = new DepartamentoDAO();
        obsDepartamentos = FXCollections.observableArrayList(dao.read());
        cbDepart.setItems(obsDepartamentos);
    }
 
    public void test(){
        Departamento departamento = (Departamento) cbDepart.getSelectionModel().getSelectedItem();
        JOptionPane.showMessageDialog(null,"ID: "+departamento.getId_departamento()+"nome"+departamento.getNome_setor());
        JOptionPane.showMessageDialog(null,"ID: "+departamento.getId_departamento()+"nome"+departamento.getNome_setor());
        JOptionPane.showMessageDialog(null,"ID: "+departamento.getId_departamento()+"nome"+departamento.getNome_setor());
    }

}
