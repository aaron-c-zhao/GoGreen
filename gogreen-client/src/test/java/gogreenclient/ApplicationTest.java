package gogreenclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.MockServerConfigurer;


import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;


public class ApplicationTest {

    private Application app;

    @Before
    public void setUp() throws Exception {
        app = new Application();
    }

    @After
    public void restoreSystemIn() {
        System.setIn(System.in);
    }


    @Test
    public void retriveName_test() throws Exception {
        System.setIn(new ByteArrayInputStream("zhao".getBytes()));
        Method retriveName = app.getClass().getDeclaredMethod("retriveName");
        retriveName.setAccessible(true);
        assertEquals("zhao", retriveName.invoke(app));
    }

    @Test
    public void retriveName_testEmpty() throws Exception {
        System.setIn(new ByteArrayInputStream(" ".getBytes()));
        Method retriveName = app.getClass().getDeclaredMethod("retriveName");
        retriveName.setAccessible(true);
        assertEquals("stranger", retriveName.invoke(app));
    }


}
