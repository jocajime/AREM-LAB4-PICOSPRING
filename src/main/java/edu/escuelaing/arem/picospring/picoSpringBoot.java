package edu.escuelaing.arem.picospring;


import edu.escuelaing.arem.header.headerHttp;
import edu.escuelaing.arem.server.HttpServer;
import edu.escuelaing.arem.server.Processor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class picoSpringBoot implements Processor {
    private static picoSpringBoot _instance = new picoSpringBoot();
    private Map<String, Method> requestProcessors = new HashMap();
    private HttpServer hserver;
    private headerHttp headers = new headerHttp ();

    private picoSpringBoot(){}

    public static picoSpringBoot getInstance() {
        return _instance;
    }

    public void loadComponents(String[] componentsList) throws ClassNotFoundException {
        for (String componentName: componentsList){
            loadComponent(componentName);
        }
    }

    private void loadComponent(String componentName) throws ClassNotFoundException {
        Class componentClass = Class.forName (componentName);
        Method[] componentMethods = componentClass.getDeclaredMethods ();
        for (Method m: componentMethods){
            if(m.isAnnotationPresent (RequestMapping.class)){
                requestProcessors.put(m.getAnnotation(RequestMapping.class).value() ,m);
            }
        }


    }

    @Override
    public String handle(String path, HttpRequest req, HttpResponse resp) {
        String respuesta = "";
        for(String key: requestProcessors.keySet ()){
            if (path.startsWith (key)) {
                try {
                    if(path.contains (".")) {
                        switch (path.substring (path.indexOf ("."))) {
                            case ".png":
                                respuesta = headers.validOkHttpHeaderpng () + requestProcessors.get (key).invoke (null, null).toString ();
                            default:
                                respuesta = headers.validOkHttpHeaderhtml () + requestProcessors.get (key).invoke (null, null).toString ();
                        }
                    }else{
                        respuesta = headers.validOkHttpHeaderhtml () + requestProcessors.get (key).invoke (null, null).toString ();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace ();
                } catch (InvocationTargetException e) {
                    e.printStackTrace ();
                }


            }
        }
        return respuesta;
    }

    public void startServer() throws IOException {
        hserver =  new HttpServer ();
        hserver.registerProcessor ("/springapp",this);
        hserver.startServer (8080);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        picoSpringBoot.getInstance ().loadComponents (args);
        picoSpringBoot.getInstance().startServer ();
    }
}
