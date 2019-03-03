package gogreenclient;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class HelloTest {

    private Hello hello = new Hello();
    private final LocalDateTime time = LocalDateTime.now();

    @Test
    public void nameGetterSetter_test() {
        hello.setName("zhao");
        assertEquals("zhao", hello.getName());
    }

    @Test
    public void timeGetterSetter_test() {
        hello.setTime(time);
        assertEquals(time.toString(), hello.getTime().toString());
    }

    @Test
    public void toString_test() {
        hello.setName("zhao");
        hello.setTime(time);
        String expectedRes = "Hello zhao" + "\nIt's " + time.toString() + " now";
        assertEquals(expectedRes, hello.toString());
    }
}
