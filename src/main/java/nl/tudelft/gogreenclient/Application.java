package nl.tudelft.gogreenclient;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

public class Application {

	public static void main(String[] args) {
		String name = retriveName();
		name = (name.equals(""))? "stranger" : name;
		RestTemplate template = new RestTemplate();
		Hello hello = template.getForObject("http://localhost:8080/api/greeting/" + name, Hello.class);
		System.out.println(hello.toString());
	}
	
	private static String retriveName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Hi! What's your name?");
		String name = input.nextLine().trim();
		input.close();
		return name;
	}
	
}
