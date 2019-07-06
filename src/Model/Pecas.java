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
public class Pecas {

    private int idpecas;
    private String nome;
    private int qtd_Pecas;

    public int getQtd_Pecas() {
        return qtd_Pecas;
    }

    public void setQtd_Pecas(int qtd_Pecas) {
        this.qtd_Pecas = qtd_Pecas;
    }
    
    public Pecas(int idpecas, String nome) {
        this.idpecas = idpecas;
        this.nome = nome;
    }

    public Pecas() {
    }

    public Pecas(int idpecas) {
        this.idpecas = idpecas;
    }

    public int getIdpecas() {
        return idpecas;
    }

    public void setIdpecas(int idpecas) {
        this.idpecas = idpecas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
