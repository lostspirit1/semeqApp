/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Alert;

/**
 *
 * @author SpiriT
 */
public class Alerts {

    //Sem Conexão
    public void semConexão() {
        Alert senhaErrada = new Alert(Alert.AlertType.ERROR);
        senhaErrada.setHeaderText("Sistema fora do AR");
        senhaErrada.show();
    }

    //login
    public void alertSenhaErrada() {
        Alert senhaErrada = new Alert(Alert.AlertType.WARNING);
        senhaErrada.setHeaderText("Senha Inválida");
        senhaErrada.show();
    }

    public void alertLoginError() {
        Alert senhaErrada = new Alert(Alert.AlertType.WARNING);
        senhaErrada.setHeaderText("Não foi possível conectar, banco de dados offiline");
        senhaErrada.show();
    }

    //cadastro
    public void alertLoginExiste() {
        Alert senhaErrada = new Alert(Alert.AlertType.WARNING);
        senhaErrada.setHeaderText("Já existe esse Login cadastrado!");
        senhaErrada.show();
    }
    public void alertUsuarioCadastrado() {
        Alert senhaErrada = new Alert(Alert.AlertType.CONFIRMATION);
        senhaErrada.setHeaderText("Usuario cadastrado com sucesso!");
        senhaErrada.show();
    }

    public void alertUsuarioNCadastrado() {
        Alert senhaErrada = new Alert(Alert.AlertType.ERROR);
        senhaErrada.setHeaderText("Usuario não cadastrado!");
        senhaErrada.show();
    }

    public void alertSenhaDif() {
        Alert senhaErrada = new Alert(Alert.AlertType.WARNING);
        senhaErrada.setHeaderText("Senhas Diferentes!");
        senhaErrada.show();
    }

    public void alertCadastroVazio() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Preencha os campos corretamente");
        al.show();
    }

    //Requisição
    public void selecioneEquipamento() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Selecione um equipamento");
        al.show();
    }
    public void alertReqVazio() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Preencha os campos corretamente");
        al.show();
    }
    public void selecionarRequisicao() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Selecione uma Requisicao");
        al.show();
    }
    public void reqFechada() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Essa requisição está fechada");
        al.show();
    }
    public void reqAberta() {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setHeaderText("Você já tem uma requisição aberta");
        al.show();
    }

    public void alertReqCadastrado() {
        Alert senhaErrada = new Alert(Alert.AlertType.CONFIRMATION);
        senhaErrada.setHeaderText("Requisição cadastrado com sucesso!");
        senhaErrada.show();
    }

    public void alertReqNCadastrado() {
        Alert senhaErrada = new Alert(Alert.AlertType.ERROR);
        senhaErrada.setHeaderText("Requisição não cadastrado!");
    }

    //modificar senha
    public void alertMudarSucesso() {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText("Senha Alterada com Sucesso");
        al.show();
    }

    public void alertSenhaAnteriorFail() {
        Alert senhaErrada = new Alert(Alert.AlertType.ERROR);
        senhaErrada.setHeaderText("A senha antiga está errada!");
        senhaErrada.show();
    }

    public void alertMudarNSucesso() {
        Alert senhaErrada = new Alert(Alert.AlertType.ERROR);
        senhaErrada.setHeaderText("Senha Não Alterada!");
        senhaErrada.show();
    }
}
