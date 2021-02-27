package edu.escuelaing.arem.picospring.demo;

import edu.escuelaing.arem.picospring.RequestMapping;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author joelc
 */
public class HelloController {

    @RequestMapping("/inicio")
    public static String index() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n" + basePage ();
    }

    @RequestMapping("/eci.png")
    public static String eci() {
        return mostrarRecurso ("eci.png");
    }
    @RequestMapping("/prueba.html")
    public static String prueba() {
        return mostrarRecurso ("prueba.html");
    }

    private static String mostrarRecurso(String recurso)  {
        try {
            if (!(recurso.endsWith (".jpeg") || recurso.endsWith (".jpg") || recurso.endsWith (".png"))) {
                StringBuilder resultado = new StringBuilder ();
                String line;
                BufferedReader bufferedReader = new BufferedReader (new FileReader ("src/resources/" + recurso));
                while ((line = bufferedReader.readLine ()) != null) {
                    resultado.append (line);
                }
                bufferedReader.close ();
                return resultado.toString ();
            } else{
                return "<IMG SRC=\"src/resources/" + recurso +"\" />";
            }
        }catch (Exception e){
            System.out.println (e.getMessage ());
            return basePage ()+"error";
        }
    }

    private static String basePage(){
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Buscador de recursos</title>\n"
                + "</head>\n"
                + "<body>\n"
                +"<center>"
                + "<h1>para encontrar algun recurso, simplemente añada al url /springapp/\"nombre del recurso\".\"extension\"</h1>\n"
                + "<h2> tenemos disponibles eci.png,prueba.html<h2>\n"
                +"</center>"
                + "</body>\n"
                + "</html>\n";
    }


}
