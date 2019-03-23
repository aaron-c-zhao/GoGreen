package gogreenclient.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("prototype")
public class UserAccountValidator {

    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String USERNAME_NULL = "User name can not be null.";
    private static final String PASSWORD_NULL = "Password can not be null.";
    private static final String USERNAME_USED = "This user name is already used by other user.";
    private static final String PASSWORD_TOO_SHORT = "The minimum length of password is 6";
    private static final String EMAIL_INVALID = "Your email address is invalid";
    private static final String BDAY_PAST = "Birthday must be in the past.";
    private static final String PASSWORD_NOT_MATCH = "Password not match.";

    @Autowired
    private UserModel userModel;


    public void accountValidate(String username, String password, String repeatPassword,
                                LocalDate bdate, String email)
        throws IllegalArgumentException {

        isUsernameNull(username);
        isPasswordNull(password);
        isPasswordTooShort(password);
        isPasswordMatch(password, repeatPassword);
        isUsernameExists(username);
        isEmailValid(email);
        isBdateInPast(bdate);

    }

    public void loginValidate(String userName, String password)
        throws IllegalArgumentException {
        isUsernameNull(userName);
        isPasswordNull(password);

    }

    private void isUsernameNull(String username) throws IllegalArgumentException {
        if (username.equals("")) {
            throw new IllegalArgumentException(USERNAME_NULL);
        }
    }

    private void isUsernameExists(String username) throws IllegalArgumentException {
        if (userModel.findUser(username)) {
            throw new IllegalArgumentException(USERNAME_USED);
        }
    }

    private void isPasswordNull(String password) throws IllegalArgumentException {
        if (password.equals("")) {
            throw new IllegalArgumentException(PASSWORD_NULL);
        }
    }

    private void isPasswordTooShort(String password) throws IllegalArgumentException {
        if (password.trim().length() < MINIMUM_PASSWORD_LENGTH) {
            throw new IllegalArgumentException(PASSWORD_TOO_SHORT);
        }
    }

    private void isEmailValid(String email) throws IllegalArgumentException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!email.equals("") && !matcher.find()) {
            throw new IllegalArgumentException(EMAIL_INVALID);
        }
    }

    private void isBdateInPast(LocalDate bdate) throws IllegalArgumentException {
        if (bdate != null && bdate.compareTo(LocalDate.now()) > 0) {
            throw new IllegalArgumentException(BDAY_PAST);
        }
    }

    private void isPasswordMatch(String password, String repeatPassword) {
        if(!password.equals(repeatPassword))
            throw new IllegalArgumentException(PASSWORD_NOT_MATCH);
    }


}
