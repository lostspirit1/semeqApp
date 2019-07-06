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
public class Departamento {

    private int id_departamento, idchefe;
    private String nome_setor;

    public Departamento(int id_departamento, int idchefe, String nome_setor) {
        this.id_departamento = id_departamento;
        this.idchefe = idchefe;
        this.nome_setor = nome_setor;
    }

    public Departamento(String nome_setor) {
        this.nome_setor = nome_setor;
    }

    public Departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Departamento(int id_departamento, String nome_setor) {
        this.id_departamento = id_departamento;
        this.nome_setor = nome_setor;
    }

    public Departamento() {
    }

    public int getIdchefe() {
        return idchefe;
    }

    public void setIdchefe(int idchefe) {
        this.idchefe = idchefe;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNome_setor() {
        return nome_setor;
    }

    public void setNome_setor(String nome_setor) {
        this.nome_setor = nome_setor;
    }

    @Override
    public String toString() {
        return getNome_setor();
    }
}
