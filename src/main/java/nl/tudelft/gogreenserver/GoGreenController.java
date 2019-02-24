package nl.tudelft.gogreenserver;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GoGreenController {
	
	@GetMapping("/greeting/{name}")
	public Greeting greeting(@PathVariable(value = "name") String name) {
		return new Greeting(name);
	}
}
