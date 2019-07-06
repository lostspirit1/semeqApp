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
public class Status {
    private int idstatus;
    private String categoria;

    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

 
}
