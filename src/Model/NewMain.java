/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;

/**
 *
 * @author SpiriT
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        JavaMail.exportarBD("localhost", "helpsemeq", "root", "", "C:\\");
    }

}
