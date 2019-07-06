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
public class Matricula {

    private long id_matricula;

    public Matricula(long id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Matricula() {
    }

    public Matricula(long id_matricula, int numero_computador, int numero_matricula) {
        this.id_matricula = id_matricula;
        this.numero_computador = numero_computador;
        this.numero_matricula = numero_matricula;
    }

    public long getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(long id_matricula) {
        this.id_matricula = id_matricula;
    }

    public int getNumero_computador() {
        return numero_computador;
    }

    public void setNumero_computador(int numero_computador) {
        this.numero_computador = numero_computador;
    }

    public int getNumero_matricula() {
        return numero_matricula;
    }

    public void setNumero_matricula(int numero_matricula) {
        this.numero_matricula = numero_matricula;
    }

    public Matricula(int numero_computador, int numero_matricula) {
        this.numero_computador = numero_computador;
        this.numero_matricula = numero_matricula;
    }
    private int numero_computador, numero_matricula;
}
