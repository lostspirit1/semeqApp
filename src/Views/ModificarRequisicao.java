/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.modificarC;
import Controller.modificarRequisicaoC;
import Model.Requisicao;
import Model.Sessao;
import Model.Usuario;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SpiriT
 */
public class ModificarRequisicao extends Application {

    private static Stage stage; //uma janela

    public ModificarRequisicao(Requisicao r1) {
        modificarRequisicaoC.setRequisicao2(r1);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Usuario login = Usuario.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("modificarReq.fxml")); //carrega fxml

        Scene scene = new Scene(root); //coloca o fxml em uma cena
        stage.setScene(scene); // coloca a cena em uma janela
        stage.show(); //abre a janela
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ModificarRequisicao.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
