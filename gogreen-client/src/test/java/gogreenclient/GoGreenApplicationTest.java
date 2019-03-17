package gogreenclient;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class GoGreenApplicationTest extends ApplicationTest {

    @Test
    public void mainTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ApplicationTest.launch(GoGreenApplication.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new GoGreenApplication().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        thread.start();
    }

}