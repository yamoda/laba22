import controller.MainApplicationController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ItemModel;
import view.MainView;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        var mainView = new MainView(1200, 700);
        var mainModel = new ItemModel();
        var controller = new MainApplicationController(mainView, mainModel);

        primaryStage.setScene(mainView.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
