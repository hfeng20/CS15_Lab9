import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ____ _ _ _ _ _
 * | _ \ ___ _ __ ___ | |_ ___ __| (_) |_ | |
 * | | | |/ _ \ | '_ \ / _ \| __| / _ \/ _` | | __| | |
 * | |_| | (_) | | | | | (_) | |_ | __/ (_| | | |_ |_|
 * |____/ \___/ |_| |_|\___/ \__| \___|\__,_|_|\__| (_)
 * This is support code for the lab. You may look at it if you are interested,
 * but you will not have to change anything to complete your lab.
 */

public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Lab 9");
    primaryStage.show();
    primaryStage.setScene(new Scene(new TabOrganizer().toNode()));
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}