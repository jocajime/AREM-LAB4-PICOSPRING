package edu.escuelaing.arem.server;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
    private int port;

    Map<String, Processor> routesToProcessors = new HashMap ();

    public void startServer(int httpPort) throws IOException {
        port = httpPort;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket (getPort());
        } catch (IOException e) {
            System.err.println ("Could not listen on port: "+getPort ());
            System.exit (1);
        }
        Boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println ("Listo para recibir en puerto " + getPort ());
                clientSocket = serverSocket.accept ();
            } catch (IOException e) {
                System.err.println ("Accept failed.");
                System.exit (1);
            }

            PrintWriter out = new PrintWriter (clientSocket.getOutputStream (), true);
            BufferedReader in = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream ()));
            String inputLine, outputLine;
            boolean isfirstLine = true;
            String path = "";

            while ((inputLine = in.readLine ()) != null) {
                System.out.println ("Recibí: " + inputLine);
                if(isfirstLine){
                    path = inputLine.split (" ")[1];
                    isfirstLine=false;
                }
                if (!in.ready ()) {
                    break;
                }
            }

            String resp = null;
            for(String key: routesToProcessors.keySet ()){
                if(path.startsWith (key)){
                    String newPath = path.substring (key.length ());
                    resp = routesToProcessors.get(key).handle(newPath, null, null);
                }

            }

            if(resp==null){
                outputLine = validOkHttpResponse ();
            }else {
                outputLine = resp;
            }

            out.println (outputLine);
            out.close ();
            in.close ();
            clientSocket.close ();
        }
        serverSocket.close();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void registerProcessor(String path, Processor processor) {
        routesToProcessors.put (path, processor);

    }
    public String validOkHttpResponse(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Buscador de recursos</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>para encontrar algun recurso, simplemente añada al url /Apps/\"nombre del recurso\".\"extension\"</h1>\n"
                + "<h1> tenemos disponibles eci.png,prueba.html<h1>\n"
                + "</body>\n"
                + "</html>\n";
    }
}