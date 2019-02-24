package nl.tudelft.gogreenserver;

import java.time.LocalDateTime;

public class Greeting {

	private final String name;
	private final LocalDateTime time;
	
	public Greeting(String name) {
		this.name = name;
		this.time = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getTime() {
		return time;
	}
	
	
	
	
	
}
