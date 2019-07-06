/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Sessao;
import Model.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SpiriT
 */
public class Principal extends Application {

    private static Stage stage; //uma janela

    public Principal() {
        blockMultiInstance();
    }

    private void blockMultiInstance() {
        try {
            ServerSocket serverSocket = new ServerSocket(9581);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Software já está aberto!", "Atenção", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Fxmls/Principal.fxml")); //carrega fxml

        Scene scene = new Scene(root); //coloca o fxml em uma cena
        stage.setScene(scene); // coloca a cena em uma janela
        stage.show(); //abre a janela
        setStage(stage);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
