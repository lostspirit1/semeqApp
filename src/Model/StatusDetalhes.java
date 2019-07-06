/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author SpiriT
 */
public class StatusDetalhes {
    private long id_statusdetalhes;
    private Timestamp data_status;
    private String observacao_status;
    private Status idStatus;

    public Status getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Status idStatus) {
        this.idStatus = idStatus;
    }
    private Usuario idUsuario;
    private Requisicao idEquipamento_Requisicao;

    public long getId_statusdetalhes() {
        return id_statusdetalhes;
    }

    public void setId_statusdetalhes(long id_statusdetalhes) {
        this.id_statusdetalhes = id_statusdetalhes;
    }

    public Timestamp getData_status() {
        return data_status;
    }

    public void setData_status(Timestamp data_status) {
        this.data_status = data_status;
    }

    public String getObservacao_status() {
        return observacao_status;
    }

    public void setObservacao_status(String observacao_status) {
        this.observacao_status = observacao_status;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Requisicao getIdEquipamento_Requisicao() {
        return idEquipamento_Requisicao;
    }

    public void setIdEquipamento_Requisicao(Requisicao idEquipamento_Requisicao) {
        this.idEquipamento_Requisicao = idEquipamento_Requisicao;
    }
    
}
