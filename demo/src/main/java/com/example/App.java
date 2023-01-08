package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class App extends Application {

    private static  Scene scen;
    private static  Stage stg;

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        stg.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        gameLauncher gl= new gameLauncher(null);
        gameLaucherController glc =new gameLaucherController(gl);
        gl.setControler(glc);
        scen = new Scene(gl, 600, 450);
        stage.setScene(scen);

        // Tests.run("tetris");
        // Tests.run("temps");
        // Tests.run("nbWords");

        stage.show();
    }

    public static void changeScene (Scene s){
        scen=s;
        stg.setScene(scen);
    }
    static void setRoot(String fxml) throws IOException {
        scen.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}