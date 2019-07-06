package Views;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Menu extends Application {

    private static Stage stage; //uma janela
    private double x, y;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml")); //carrega fxml

        Scene scene = new Scene(root); //coloca o fxml em uma cena
        stage.setScene(scene); // coloca a cena em uma janela
        //abre a janela
        setStage(stage);
        //set stage borderless
        stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Menu.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
