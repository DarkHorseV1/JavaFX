package inc.brocorp;

import inc.brocorp.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("inc.brocorp.bundle.Locale", new Locale("en")));
        Parent fxmlMain = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("phoneBook");
        primaryStage.setMinHeight(420);
        primaryStage.setMinWidth(340);
        primaryStage.setScene(new Scene(fxmlMain, 320, 380));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
