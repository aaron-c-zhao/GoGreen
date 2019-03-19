package gogreenserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * MVC controller for miscellaneous end points.
 */
@RestController
@RequestMapping("/api")
public class MiscController {

    /**
     * A simple echo end point.
     *
     * @param name The value to echo back.
     * @return A {@link Greeting} class, converted to JSON, containing the name and date.
     */
    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable(value = "name") String name) {
        return new Greeting(name);
    }

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
}
