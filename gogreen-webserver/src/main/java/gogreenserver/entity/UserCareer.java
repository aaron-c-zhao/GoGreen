package gogreenserver.entity;

import javax.persistence.*;

@Entity 
@Table(name="user_career")
public class UserCareer {
	
	// define fields 
	@Id
	@Column(name="user_name")
	private String username;
	
	@Column(name="co2_saved")
	private int co2saved;

	// define constructors 
	public UserCareer () {
		
	}
	
	public UserCareer(String username, int co2saved) {
		super();
		this.username = username;
		this.co2saved = co2saved;
	}

	// define getters and setter 
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public float getco2saved() {
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
