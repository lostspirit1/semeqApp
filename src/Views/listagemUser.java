/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Usuario;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author SpiriT
 */
public class listagemUser extends Application {
    private static Stage stage; //uma janela
    
    @Override
    public void start(Stage stage) throws Exception {
        Usuario usuario = Usuario.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("listagemUser.fxml")); //carrega fxml
        
        Scene scene = new Scene(root); //coloca o fxml em uma cena
        stage.setScene(scene); // coloca a cena em uma janela
        stage.show(); //abre a janela
        setStage(stage);
    }

    public static Stage getStage(){
        return stage;
    }
    
    public static void setStage(Stage stage){
        listagemUser.stage = stage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

