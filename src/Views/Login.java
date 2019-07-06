/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Model.Pecas;
import Model.Sessao;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author SpiriT
 */
public class Login extends Application {

    private static Stage stage; //uma janela
    private static Sessao aSessao;
    public Login() {
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

    private double yOffset = 0;
    private double xOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Fxmls/Login.fxml")); //carrega fxml+

        Scene scene = new Scene(root); //coloca o fxml em uma cena
        stage.setScene(scene); // coloca a cena em uma janela
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.sizeToScene();
        stage.show(); //abre a janela
        setStage(stage);
        
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
