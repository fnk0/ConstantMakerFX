import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by marcus on 7/25/14.
 */
public class MainPanel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layouts/mainPanel.fxml"));

        Parent root = (Parent) loader.load();
        MainController mController = loader.getController();
        mController.initData(stage);

        stage.setTitle("Constant Maker");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
