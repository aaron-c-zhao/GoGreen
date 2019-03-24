package gogreenclient.datamodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserAccountValidatorTest {

    private UserAccountValidator validator = new UserAccountValidator();

    @Test
    public void usernameNull() {
        boolean exception = false;
        try {
            validator.accountValidate(null,"abc", "abc", LocalDate.now(), "abc");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordNull() {
        boolean exception = false;
        try {
            validator.accountValidate("abc",null, "abc", LocalDate.now(), "abc");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordShort() {
        boolean exception = false;
        try {
            validator.accountValidate("abc","abc", "abc", LocalDate.now(), "abc");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void passwordNotMatch() {
        boolean exception = false;
        try {
            validator.accountValidate("abc","abcd", "abc", LocalDate.now(), "abc");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void emailNotValid() {
        boolean exception = false;
        try {
            validator.accountValidate("abc","abcabcc", "abcabcc", LocalDate.now(), "abc gf ");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void bdayFuture() {
        boolean exception = false;
        try {
            validator.accountValidate("abc","abcabcc", "abcabcc", LocalDate.of(2200, 12, 12), "abc");
        }
        catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }
}