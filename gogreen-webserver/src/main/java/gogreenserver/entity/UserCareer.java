package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_career")
public class UserCareer {

    // define fields
    @Id
    @Column(name = "user_name")
    private String username;

    @Column(name = "co2_saved")
    private int co2saved;

    // define constructors
    public UserCareer() {

    }

    /**
     * Constructor for UserCareer.
     *
     * @param username Primary key. Foreign key and refers to User.userName
     * @param co2saved total career co2 saved by a particular user.
     */
    public UserCareer(String username, int co2saved) {
        super();
        this.username = username;
        this.co2saved = co2saved;
    }

    // define getters and setter
    public String getUsername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public int getco2saved() {
        return co2saved;
    }

    public void setco2saved(int co2saved) {
        this.co2saved = co2saved;
    }

    // define toString
    @Override
    public String toString() {
        return "UserCareer [username=" + username + ", co2saved=" + co2saved + "]";
    }


}
