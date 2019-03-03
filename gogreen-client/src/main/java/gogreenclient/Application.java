package gogreenclient;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

/**
 * Client side of #GoGreen project. The application is able to send HTTP requests throw HTTPS
 * protocol in JSON format or send query with RequestParam to a RESTful API, which will response
 * with a JSON format object.
 */
public class Application {

    public static void main(String[] args) {
        String name = retriveName();
        sayHello(name);
    }

    private static void sayHello(String name) {
        RestTemplate template = new RestTemplate();
        Hello hello = template.getForObject("http://localhost:8080/api/greeting/" + name, Hello.class);
        System.out.println(hello.toString());
    }

    private static String retriveName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hi! What's your name?");
        String name = input.nextLine().trim();
        name = (name.equals("")) ? "stranger" : name;
        input.close();
        return name;
    }

}
