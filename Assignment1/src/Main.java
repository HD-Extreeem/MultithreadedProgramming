import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application{


    public Main() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        
        GUIAssignment1 assignment1 = new GUIAssignment1();

        //Gui starting
        assignment1.start();

        //JavaFx Starting used for musicPlayer
        launch(args);
    }
}