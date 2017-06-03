package Handlers;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import java.util.Scanner;
import java.nio.file.*;


/**
 * Created by Greg on 5/27/2017.
 */

public class filehandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String path = "resources/web";

        String file;

        String requestbody;

        requestbody = exchange.getRequestURI().toString();

        if(requestbody.equals("/")){
            requestbody = "/index.html";
        }

        // Get the response body output stream.
        OutputStream os = exchange.getResponseBody();

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

        Path myPath = FileSystems.getDefault().getPath(path + requestbody);
        Files.copy(myPath, exchange.getResponseBody());




        // Write the JSON string to the output stream.
        //writeString(file, os);

        // output stream, indicating that the response is complete.
        exchange.getResponseBody().close();

    }


    private void writeString(File fil, OutputStream os) {
        try {
            OutputStreamWriter sw = new OutputStreamWriter(os);
            Scanner sc = new Scanner(fil);
            char[] array = sc.useDelimiter("").next().toCharArray();
            sw.write(array);
            sw.flush();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

}
