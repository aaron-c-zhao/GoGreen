package gogreenserver.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    // Define fields
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name", nullable = true)
    private String email;

    @Column(name = "email", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "bdate")
    private LocalDate bdate;

    @Column(name = "nationality")
    private String nationality;

    // define constructors 
    public User() {

    }

    /**Constructor for User
     * @param username Primary Key. 
     * @param password Password.
     * @param email Email.
     * @param firstName First name of user.
     * @param lastName Last name of user.
     * @param bdate Birthdate of user.
     * @param nationality Nationality of user.
     */
    public User(String username, String password, String email, String firstName,
                String lastName, LocalDate bdate, String nationality) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bdate = bdate;
        this.nationality = nationality;
    }

    // define getters and setters

    public LocalDate getBdate() {
        return bdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
