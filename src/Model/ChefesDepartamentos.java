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
public class ChefesDepartamentos {
    private int id_chefe;
    private Usuario cpIdusuario;
    private Departamento cpIddepartamento;

    public int getId_chefe() {
        return id_chefe;
    }

    public void setId_chefe(int id_chefe) {
        this.id_chefe = id_chefe;
    }

    public Usuario getCpIdusuario() {
        return cpIdusuario;
    }

    public void setCpIdusuario(Usuario cpIdusuario) {
        this.cpIdusuario = cpIdusuario;
    }

    public Departamento getCpIddepartamento() {
        return cpIddepartamento;
    }

    public void setCpIddepartamento(Departamento cpIddepartamento) {
        this.cpIddepartamento = cpIddepartamento;
    }
}
