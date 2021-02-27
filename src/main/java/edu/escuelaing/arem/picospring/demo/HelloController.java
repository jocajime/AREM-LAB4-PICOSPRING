package edu.escuelaing.arem.picospring.demo;

import edu.escuelaing.arem.picospring.RequestMapping;

/**
 *
 * @author joelc
 */
public class HelloController {

    @RequestMapping("/")
    public static String index() {
        return "Greetings from Spring Boot!";
    }

}
