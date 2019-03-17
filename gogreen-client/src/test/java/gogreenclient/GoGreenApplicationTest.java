package gogreenclient;

import javafx.application.Application;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class GoGreenApplicationTest {

    private volatile boolean test = false;

    @Test
    public void mainTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Application.launch(GoGreenApplication.class);
                    test = true;
                } catch (Throwable t) {
                    if(t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)){
                        test = true;
                        return;
                    }
                    Logger.getLogger(GoGreenApplicationTest.class.getName()).log(Level.SEVERE, null, t);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            thread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(this.test);
    }

}