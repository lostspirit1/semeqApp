/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author SpiriT
 */
public class Boletim {

    private Long id_boletim;
    private Date data_saida;
    private String observacao;
    private Usuario bUsuario;
    private Servicos bServicos;
    private Requisicao bRequisicao;

    public Boletim(Long id_boletim, Date data_saida, String observacao) {
        this.id_boletim = id_boletim;
        this.data_saida = data_saida;
        this.observacao = observacao;
    }

    public Boletim(Long id_boletim) {
        this.id_boletim = id_boletim;
    }

    public Boletim() {
    }

    public Long getId_boletim() {
        return id_boletim;
    }

    public void setId_boletim(Long id_boletim) {
        this.id_boletim = id_boletim;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // get sets agregração
    public Usuario getbUsuario() {
        return bUsuario;
    }

    public void setbUsuario(Usuario bUsuario) {
        this.bUsuario = bUsuario;
    }

    public Servicos getbServicos() {
        return bServicos;
    }

    public void setbServicos(Servicos bServicos) {
        this.bServicos = bServicos;
    }

    public Requisicao getbRequisicao() {
        return bRequisicao;
    }

    public void setbRequisicao(Requisicao bRequisicao) {
        this.bRequisicao = bRequisicao;
    }

}
