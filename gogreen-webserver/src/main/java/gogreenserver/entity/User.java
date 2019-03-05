package gogreenserver.entity;


import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	// Define fields 
    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="first_name")
    private String email;
    
    @Column(name="email")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    // define constructors 
    public User() {
    	
    }
    
    public User(String username, String password, String email, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// define getters and setters 
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

    // define toString 
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

    
    
    
}
