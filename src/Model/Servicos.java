/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SpiriT
 */
public class Servicos {

    private Long id_Servicos;
    private String nomes_servico;

    public Servicos(Long id_Servicos, String nomes_servico) {
        this.id_Servicos = id_Servicos;
        this.nomes_servico = nomes_servico;
    }

    public Servicos(Long id_Servicos) {
        this.id_Servicos = id_Servicos;
    }

    public Servicos() {
    }

    public Long getId_Servicos() {
        return id_Servicos;
    }

    public void setId_Servicos(Long id_Servicos) {
        this.id_Servicos = id_Servicos;
    }

    public String getNomes_servico() {
        return nomes_servico;
    }

    public void setNomes_servico(String nomes_servico) {
        this.nomes_servico = nomes_servico;
    }

}
