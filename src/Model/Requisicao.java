package Model;

import java.sql.Timestamp;

/**
 *
 * @author SpiriT
 */
public class Requisicao extends Usuario {

    private Long id_requisicao;
    private String nome_requisicao;
    private String observacao;
    private Usuario reqUsuario;
    private Equipamentos reqEquipamento;
    private Status reqStatus;
    private StatusDetalhes reqStatus_Detalhes;
    private ChefesDepartamentos chefes;

    public ChefesDepartamentos getChefes() {
        return chefes;
    }

    public void setChefes(ChefesDepartamentos chefes) {
        this.chefes = chefes;
    }

    public StatusDetalhes getReqStatus_Detalhes() {
        return reqStatus_Detalhes;
    }

    public void setReqStatus_Detalhes(StatusDetalhes reqStatus_Detalhes) {
        this.reqStatus_Detalhes = reqStatus_Detalhes;
    }

    public Status getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Status reqStatus) {
        this.reqStatus = reqStatus;
    }
    
    public Equipamentos getReqEquipamento() {
        return reqEquipamento;
    }

    public void setReqEquipamento(Equipamentos reqEquipamento) {
        this.reqEquipamento = reqEquipamento;
    }
    private Timestamp data_criada;

    public Timestamp getData_criada() {
        return data_criada;
    }

    public void setData_criada(Timestamp data_criada) {
        this.data_criada = data_criada;
    }
    
    public Requisicao(String nome_requisicao, String observacao) {
        this.nome_requisicao = nome_requisicao;
        this.observacao = observacao;
    }

    public Requisicao(String nome_requisicao) {
        this.nome_requisicao = nome_requisicao;
    }

    public Requisicao() {
    }

    public Requisicao(Long id_requisicao) {
        this.id_requisicao = id_requisicao;
    }

    public void setId(Long id_requisicao) {
        this.id_requisicao = id_requisicao;
    }

    public Long getId() {
        return id_requisicao;
    }
    
    public void setNome(String nome_requisicao) {
        this.nome_requisicao = nome_requisicao;
    }

    public String getNome() {
        return nome_requisicao;
    }    
    
    public String getMotivo() {
        return observacao;
    }

    public void setMotivo(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getReqUsuario() {
        return reqUsuario;
    }

    public void setReqUsuario(Usuario reqUsuario) {
        this.reqUsuario = reqUsuario;
    }

}
