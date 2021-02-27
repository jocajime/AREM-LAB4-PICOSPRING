package edu.escuelaing.arem.header;

public class headerHttp {
    public headerHttp(){}

    public String validErrorHttpHeaderhtml() {
        return  "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                +"\r\n";
    }
    public String validOkHttpHeaderhtml() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                +"\r\n";

    }
    public String validOkHttpHeadercss() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/css\r\n"
                +"\r\n";

    }
    public String validOkHttpHeaderjs() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/javascript\r\n"
                +"\r\n";
    }
    public String validOkHttpHeaderjson() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                +"\r\n";
    }
    public String validOkHttpHeaderjpeg() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/jpeg\r\n"
                +"\r\n";
    }

    public String validOkHttpHeaderpng() {
        return  "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/png\r\n"
                +"\r\n";
    }


}