package Handlers;

import java.io.*;
import java.net.*;

import Services.LoginRequest;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

/**
 * Created by Greg on 5/27/2017.
 */

public class clearhandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;

        try {
            // Determine the HTTP request type (GET, POST, etc.).
            // Only allow POST requests for this operation.
            // This operation requires a POST request, because the
            // client is "posting" information to the server for processing.
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Extract the JSON string from the HTTP request body


               Services.ClearService clear = new Services.ClearService();

               success = clear.cleardb();

                if(success){
                    String out;

                    messageresponse mess = new messageresponse();
                    mess.setMessage("Clear succeeded");

                    Gson gson = new Gson();

                    out = gson.toJson(mess);

                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    // Get the response body output stream.
                    OutputStream respBody = exchange.getResponseBody();
                    // Write the JSON string to the output stream.
                    writeString(out, respBody);

                    // output stream, indicating that the response is complete.
                    exchange.getResponseBody().close();
                    success = true;
                }
            }
            if (!success) {
                String out;

                messageresponse mess = new messageresponse();
                mess.setMessage("Clear Failed");

                Gson gson = new Gson();

                out = gson.toJson(mess);

                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);


                // Get the response body output stream.
                OutputStream respBody = exchange.getResponseBody();
                // Write the JSON string to the output stream.
                writeString(out, respBody);


                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();
            // Display/log the stack trace
            e.printStackTrace();
        }

    }


    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
